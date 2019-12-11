package com.github.dge1992.mongo.controller;

import com.github.dge1992.mongo.doamin.User;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 小眼睛带鱼
 * @Description
 * @Date 2019/6/20
 **/
@RequestMapping("/gridFs")
@RestController
@Api(description = "Mongo文件系统Api测试")
public class GridFsController {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    static final Integer STEAM_BYTE_LENGTH = 1024;

    @ApiOperation(value = "上传文件")
    @PostMapping("/uploadFile")
    public Object uploadFile(MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            gridFsTemplate.store(file.getInputStream(),
                    filename.substring(0, filename.lastIndexOf(".")),
                    filename.substring(filename.lastIndexOf(".")));
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    @ApiOperation(value = "下载文件")
    @GetMapping("/downloadFile/{fileId}")
    public void downloadFile(@PathVariable("fileId") String fileId, HttpServletResponse response) throws IOException {
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(new GridFsCriteria("_id").is(fileId)));
        //打开下载流
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        //转换成资源
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
//        //创建临时文件
//        File temp = File.createTempFile(gridFsResource.getFilename(), gridFsResource.getContentType());
//        temp.deleteOnExit();
//        OutputStream outputStream = new FileOutputStream(temp);
//        //gridFsResource写入到临时文件
//        try {
//            outputStream.write(IOUtils.toByteArray(gridFsResource.getInputStream()));
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            outputStream.close();
//        }
        responseBody(gridFsResource, response);
    }

    @ApiOperation(value = "删除文件")
    @DeleteMapping("/deleteFile/{fileId}")
    public Object deleteFile(@PathVariable("fileId")String fileId){
        gridFsTemplate.delete(new Query(new GridFsCriteria("_id").is(fileId)));
        return "删除成功";
    }

    @ApiOperation(value = "获取文件详情")
    @GetMapping("/getFile/{fileId}")
    public Object getFile(@PathVariable("fileId")String fileId){
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(new GridFsCriteria("_id").is(fileId)));
        return gridFSFile.toString();
    }

    @ApiOperation(value = "获取文件列表")
    @GetMapping("/selectFileList")
    public Object selectFileList(){
        GridFSFindIterable gridFSFiles = gridFsTemplate.find(new Query());
        List<String> list = new ArrayList<>();
        MongoCursor<GridFSFile> iterator = gridFSFiles.iterator();
        while (iterator.hasNext()){
            list.add(iterator.next().toString());
        }
        return list;
    }

    /**
     * 设置文件下载response头信息
     *
     * @param gridFsResource
     * @param response
     * @throws UnsupportedEncodingException
     */
    public void responseBody(GridFsResource gridFsResource, HttpServletResponse response) throws IOException {
        if (gridFsResource != null) {
            //Content-Disposition:激活文件下载对话框 attachment:提示保存还是打开 filename:文件名
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(gridFsResource.getFilename() + gridFsResource.getContentType(), "UTF-8"));
            responseFile(response, gridFsResource.getInputStream());
        }
    }

    /**
     * 处理文件下载response
     *
     * @param response
     * @param is
     */
    public void responseFile(HttpServletResponse response, InputStream is) {
        try (OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[STEAM_BYTE_LENGTH];
            while (is.read(buffer) != -1) {
                os.write(buffer);
            }
            os.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
