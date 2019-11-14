package com.github.dge1992.mybatis.spring.transaction;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;
import org.springframework.lang.Nullable;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.DefaultTransactionStatus;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 小眼睛带鱼
 * @date 2019-11-13 17:35
 * @desc 自定义事务拦截器
 */
@Log4j
public class LogTransactionInterceptor extends TransactionInterceptor {

    public LogTransactionInterceptor(PlatformTransactionManager ptm, TransactionAttributeSource tas) {
        super(ptm, tas);
    }

    /**
     * Execute after successful completion of call, but not after an exception was handled.
     * Do nothing if we didn't create a transaction.
     * @param txInfo information about the current transaction
     */
    protected void commitTransactionAfterReturning(@Nullable TransactionInfo txInfo) {
        if (txInfo != null && txInfo.getTransactionStatus() != null) {
            if (logger.isTraceEnabled()) {
                logger.trace("Completing transaction for [" + txInfo.getJoinpointIdentification() + "]");
            }
            Map<String, Object> transactionData = new HashMap<>();
            if(!txInfo.getTransactionAttribute().isReadOnly()) {
                DataSourceTransactionManager transactionManager = (DataSourceTransactionManager)txInfo.getTransactionManager();
                DruidDataSource dataSource = (DruidDataSource)transactionManager.getDataSource();
                String driverClassName = dataSource.getDriverClassName();
                if(StringUtils.isNotBlank(driverClassName)) {
                    if("oracle.jdbc.driver.OracleDriver".equals(driverClassName)){
                        getOracleTransactionId(txInfo, transactionData);
                    }
                }
            }
            transactionData.forEach((k, v) -> System.out.println(k + "   " + v));
            txInfo.getTransactionManager().commit(txInfo.getTransactionStatus());
        }
    }

    private void getOracleTransactionId(TransactionInfo txInfo, Map<String, Object> transactionData) {
        try {
            String sql = "SELECT XID,USED_UREC,START_SCN FROM v$transaction tx JOIN v$session s ON tx.SES_ADDR = s.SADDR AND s.AUDSID=userenv('sessionid') AND s.SID=userenv('sid')";
            DefaultTransactionStatus transactionStatus = (DefaultTransactionStatus) txInfo.getTransactionStatus();
            JdbcTransactionObjectSupport transactionObject = (JdbcTransactionObjectSupport) transactionStatus.getTransaction();
            Connection conn = transactionObject.getConnectionHolder().getConnection();
            Statement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if(null != rs) {
                if(rs.next()) {
                    String tid = rs.getString("xid");
                    long scn = rs.getLong("START_SCN");
                    transactionData.put("xid", tid);
                    transactionData.put("scn", scn);
                }
            }
        }catch(Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
