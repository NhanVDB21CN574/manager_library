package com.example.managerlibrary.service;

import com.example.managerlibrary.entity.Book;
import com.example.managerlibrary.entity.Image;
import com.example.managerlibrary.exceptions.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IImageService {
    Image createImage(Image image);

    String saveImage(MultipartFile file) throws IOException, FileUploadException;
    List<Image> createListImage(List<String> urlImages, Book book) throws FileUploadException, IOException;
    List<String> upload(List<MultipartFile>files) throws IOException, FileUploadException;
}
