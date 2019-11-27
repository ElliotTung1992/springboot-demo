package com.ecer.kafka.connect.oracle;

import com.ecer.kafka.connect.oracle.models.AnalysisSourceParam;
import com.ecer.kafka.connect.oracle.models.AnalysisSourceResult;
import com.ecer.kafka.connect.oracle.models.Data;
import com.github.dge1992.javautils.map.MapUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.*;
import java.util.Date;

import static com.ecer.kafka.connect.oracle.OracleConnectorSchema.*;

/**
 * 解析数据任务
 */
public class AnalysisSourceTask {

    String logMinerOptions=OracleConnectorSQL.LOGMINER_START_OPTIONS;
    String logMinerStartScr=OracleConnectorSQL.START_LOGMINER_CMD;
    CallableStatement logMinerStartStmt=null;
    String logMinerSelectSql;
    static PreparedStatement logMinerSelect;
    ResultSet logMinerData;
    private ConnectorSQL sql;
    public OracleSourceConnectorConfig config;
    private OracleSourceConnectorUtils utils;

    public AnalysisSourceTask() {
        this.sql = new ConnectorSQL();
    }

    // 解析数据
    public List<AnalysisSourceResult> analysisSource(AnalysisSourceParam baseAnalysisSource, Connection dbConn) throws Exception {

        //进行非空判断
        if(dbConn == null || StringUtils.isEmpty(baseAnalysisSource.getXid())
                || baseAnalysisSource.getScn() == null){
            throw new NullPointerException("参数不能为空！");
        }
        String xid = baseAnalysisSource.getXid();
        Long scnParam = baseAnalysisSource.getScn();
        Long commitScnParam = scnParam + 1;
        Map<String, String> map = new HashMap<>();
        map.put(OracleSourceConnectorConfig.DB_NAME_ALIAS, "");
        map.put(OracleSourceConnectorConfig.DB_HOST_NAME_CONFIG, "");
        map.put(OracleSourceConnectorConfig.DB_USER_CONFIG, "");
        map.put(OracleSourceConnectorConfig.DB_USER_PASSWORD_CONFIG, "");
        map.put(OracleSourceConnectorConfig.DB_NAME_CONFIG, "");
        map.put(OracleSourceConnectorConfig.DB_PORT_CONFIG, "1521");
        map.put(OracleSourceConnectorConfig.TABLE_WHITELIST, "");
        map.put(OracleSourceConnectorConfig.DB_FETCH_SIZE, "1");
        map.put(OracleSourceConnectorConfig.PARSE_DML_DATA, "true");
        map.put(OracleSourceConnectorConfig.RESET_OFFSET, "true");
        map.put(OracleSourceConnectorConfig.MULTITENANT, "false");
        map.put(OracleSourceConnectorConfig.TOPIC_CONFIG, "");
        config=new OracleSourceConnectorConfig(map);
        utils = new OracleSourceConnectorUtils(dbConn, config, sql);

        //开启会话
        logMinerStartScr=logMinerStartScr+logMinerOptions+") \n; end;";
        logMinerStartStmt=dbConn.prepareCall(logMinerStartScr);
        logMinerStartStmt.setLong(1, scnParam);
        logMinerStartStmt.setLong(2, commitScnParam);
        logMinerStartStmt.execute();

        //根据事务id查询日志视图
        logMinerSelectSql = "SELECT XID,thread#, scn, start_scn, commit_scn,timestamp, operation_code, operation,status, SEG_TYPE_NAME ,info,seg_owner, table_name, username, sql_redo ,row_id, csf, TABLE_SPACE, SESSION_INFO, RS_ID, RBASQN, RBABLK, SEQUENCE#, TX_NAME, SEG_NAME, SEG_TYPE_NAME FROM  v$logmnr_contents  WHERE OPERATION_CODE in (1,2,3) and XID = ?";
        logMinerSelect=dbConn.prepareCall(logMinerSelectSql);
        logMinerSelect.setFetchSize(1);
        logMinerSelect.setString(1, xid);
        logMinerData=logMinerSelect.executeQuery();

        List<AnalysisSourceResult> list = new ArrayList<>();

        //循环获取数据
        while(logMinerData.next()){
            Long scn=logMinerData.getLong(SCN_FIELD);
            boolean contSF = logMinerData.getBoolean(CSF_FIELD);

            String segOwner = logMinerData.getString(SEG_OWNER_FIELD);
            String segName = logMinerData.getString(TABLE_NAME_FIELD);
            String sqlRedo = logMinerData.getString(SQL_REDO_FIELD);
            if (sqlRedo.contains(TEMPORARY_TABLE)) continue;

            while(contSF){
                logMinerData.next();
                sqlRedo +=  logMinerData.getString(SQL_REDO_FIELD);
                contSF = logMinerData.getBoolean(CSF_FIELD);
            }
            Timestamp timeStamp=logMinerData.getTimestamp(TIMESTAMP_FIELD);
            Date date = new Date(timeStamp.getTime());
            String operation = logMinerData.getString(OPERATION_FIELD);
            //基本数据
            Data row = new Data(xid, scn, segOwner, segName, sqlRedo,date,operation);
            Map<String, Object> dataMap = MapUtil.getMapFromBean(row);
            //获取数据变动集合
            Map<String, LinkedHashMap<String, String>> allDataMap = utils.createDataSchema2(segOwner, segName, sqlRedo, operation);
            AnalysisSourceResult result = new AnalysisSourceResult(dataMap, allDataMap);
            list.add(result);
        }
        return list;
    }

    public static void main(String[] args) throws Exception {

    }
}
