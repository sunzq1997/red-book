package com.sunzq.controller;

import com.sunzq.GraceJSONResult;
import com.sunzq.config.MinioConfig;
import com.sunzq.utils.MinioUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author sunzq
 */
@Api(tags = "文件上传接口")
@RestController
public class FileUploadController {

    @Autowired
    private MinioConfig minioConfig;

    @PostMapping("upload")
    public GraceJSONResult upload(MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        MinioUtils.uploadFile(minioConfig.getBucketName(), filename, file.getInputStream());
        String url = minioConfig.getEndpoint() + "/" + minioConfig.getBucketName() + "/" + filename;
        return GraceJSONResult.ok(url);
    }
}
