package com.fxtahe.fxblog.controller;

import com.fxtahe.fxblog.config.annotation.ResponseWrapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@ResponseWrapper
@RestController
@RequestMapping(value="/file")
public class FileUploadController {


    @RequestMapping("/uploadImg")
    public ResponseEntity uploadImage(@RequestParam("file")MultipartFile multipartFile){

        if(multipartFile.isEmpty()){
            //TODO throw business Exception
        }String url = "https://img.laoooo.cn:88/api/upload";
        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);

        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource("C:/Users/lenovo/Desktop/TIM图片20200508184447.jpg");
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("image", fileSystemResource);

        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> mapResponseEntity = restTemplate.postForEntity(url, files, Map.class);
        return mapResponseEntity;
    }
}
