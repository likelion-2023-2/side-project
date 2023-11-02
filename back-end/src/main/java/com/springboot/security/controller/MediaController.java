package com.springboot.security.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("media")
public class MediaController {
    private static final Logger logger= LoggerFactory.getLogger(MediaController.class);
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    }) 
    @PostMapping("test")
    public void uploadMedia(
            @RequestParam("file") MultipartFile file
    ) {
        String basePath="./media";

        File directory=new File(basePath);
        if(!directory.exists()) directory.mkdir();
        logger.info("media");
        Path newFilePath = Path.of(basePath, file.getOriginalFilename());
        try{
            logger.info("파일생성됨");
            file.transferTo(newFilePath);
        }catch(IOException e){
            e.printStackTrace();
            logger.info("파일삭제됨");
        }

    }
}
