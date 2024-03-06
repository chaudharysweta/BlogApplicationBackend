package com.practice.blogappapi.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileSerivce {

    String uploadImage(String path, MultipartFile file) throws IOException;
    InputStream getResource(String path,String fileName) throws FileNotFoundException;
}
