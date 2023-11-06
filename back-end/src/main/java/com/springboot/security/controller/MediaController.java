package com.springboot.security.controller;

import com.springboot.security.data.dto.MediaDescriptorDto;
import com.springboot.security.service.MediaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;

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
    private final MediaService mediaService;
    public MediaController(MediaService mediaService){
        this.mediaService=mediaService;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping("upload")
    public ResponseEntity<MediaDescriptorDto> uploadMedia2(
            @RequestParam("file") MultipartFile file
    ){
        MediaDescriptorDto descriptorDto = this.mediaService.saveFile(file);
        return ResponseEntity
                .status(descriptorDto.getStatus())
                .body(descriptorDto);
    }

    @PostMapping("upload-bulk")
    public ResponseEntity<Collection<MediaDescriptorDto>> uploadMediaBulk(
            @RequestParam("file") MultipartFile[] files
    ){
        return ResponseEntity
                .status(HttpStatus.MULTI_STATUS)
                .body(this.mediaService.saveFileBulk(files));
    }

    @GetMapping("**")
    public ResponseEntity<byte[]> staticLikeEndpoint(
            HttpServletRequest request
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(
                this.mediaService.getFileAsBytes(request.getRequestURI().split("media")[1]),
                headers,
                HttpStatus.OK
        );

    }
}
