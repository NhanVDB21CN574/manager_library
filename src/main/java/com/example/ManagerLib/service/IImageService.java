package com.example.ManagerLib.service;

import com.example.ManagerLib.entity.Book;
import com.example.ManagerLib.entity.Image;
import com.example.ManagerLib.exceptions.DataNotFoundException;
import com.example.ManagerLib.exceptions.FileUploadException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IImageService {
    Image createImage(Image image);

    String saveImage(MultipartFile file) throws IOException, FileUploadException;
    List<Image> createListImage(List<String> urlImages, Book book) throws FileUploadException, IOException;
}
