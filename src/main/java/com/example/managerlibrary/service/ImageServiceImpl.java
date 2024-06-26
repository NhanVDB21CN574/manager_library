package com.example.managerlibrary.service;

import com.cloudinary.Cloudinary;
import com.example.managerlibrary.entity.Book;
import com.example.managerlibrary.entity.Image;
import com.example.managerlibrary.exceptions.FileUploadException;
import com.example.managerlibrary.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService{
    private final ImageRepository imageRepository;
    private final Cloudinary cloudinary;

    @Override
    public Image createImage(Image newImage) {
        Image image = imageRepository.save(newImage);
        return image;
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException, FileUploadException {

        //check size of file
        long sizeOfFile = file.getSize();
        if(sizeOfFile==0){
            throw new FileUploadException("file is empty!");
        }
        if(sizeOfFile> 10*1024*1024){ //max : 10MB
            throw new FileUploadException("file must be <10mb");
        }

        //check type of file
        String contentType = file.getContentType();
        if(contentType == null ||!contentType.startsWith("image/")){
            throw new FileUploadException("Must be image");
        }
        String filename = file.getOriginalFilename();
        if(filename==null){
            throw new IOException("Invalid image format");
        }


        String uniqueFilename = UUID.randomUUID().toString()+"_"+filename;
        Path uploadDir = Paths.get("uploads");
        if(!Files.exists(uploadDir)){
            Files.createDirectories(uploadDir);
        }
        Path destination = Paths.get(uploadDir.toString(),uniqueFilename);
        Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
    }


    @Override
    public List<Image> createListImage(List<String> urlImages, Book book) throws FileUploadException {
        List<Image> existImages = imageRepository.getImagesByBookId(book.getId());
        int numberImagesOfBook = existImages.size();
        if(numberImagesOfBook>=5||(numberImagesOfBook+urlImages.size()>5)){
            throw  new FileUploadException("Each book can only upload a maximum of 5 photos");
        }
        List<Image> images = new ArrayList<>();

        urlImages.forEach(urlImage->{
            Image newImage=new Image();
            newImage.setImageUrl(urlImage);
            newImage.setBook(book);
            images.add(newImage);
        });
        List<Image> savedImages = imageRepository.saveAll(images);
        return savedImages;

    }

    @Override
    public List<String> upload(List<MultipartFile>files) throws FileUploadException {

//            files = files==null?new ArrayList<MultipartFile>():files;
//            if(files.size()>5){
//                throw new FileUploadException("Số ảnh tải lên cho mỗi sách tối đa là 5 ảnh.");
//            }
//            List<String> urlImages = new ArrayList<>();
////            Map data = null;
//                files.forEach(file -> {
//                            long sizeOfFile = file.getSize();
//                            if (sizeOfFile > 10 * 1024 * 1024) { //max : 10MB
//                               try {
//                                    throw new FileUploadException("Độ lớn ảnh tải lên nhỏ hơn 10MB");
//                                } catch (FileUploadException e) {
//                                    throw new RuntimeException(e);
//                                }
//                            }
//                            String contentType = file.getContentType();
//                            if (contentType == null || !contentType.startsWith("image/")) {
//                                try {
//                                    throw new FileUploadException("Định dạng File không hỗ trợ");
//                                } catch (FileUploadException e) {
//                                    throw new RuntimeException(e.getMessage());
//                                }
//                            }
//                            String filename = file.getOriginalFilename();
//                            if (filename == null) {
//                                try {
//                                    throw new FileUploadException("Định dạng File không hỗ trợ");
//                                } catch (FileUploadException e) {
//                                    throw new RuntimeException(e.getMessage());
//                                }
//                            }
//                    try {
//                        Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
//                        urlImages.add(data.get("url").toString());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e.getMessage());
//                    }
//
//                });
//
//            return urlImages;
        files = files == null ? new ArrayList<MultipartFile>() : files;
        if (files.size() > 5) {
            throw new FileUploadException("Số ảnh tải lên cho mỗi sách tối đa là 5 ảnh.");
        }
        List<String> urlImages = new ArrayList<>();
        for (MultipartFile file : files) {
            long sizeOfFile = file.getSize();
            if (sizeOfFile > 10 * 1024 * 1024) { //max : 10MB
                throw new FileUploadException("Độ lớn ảnh tải lên nhỏ hơn 10MB");
            }
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new FileUploadException("Định dạng File không hỗ trợ");
            }
            String filename = file.getOriginalFilename();
            if (filename == null) {
                throw new FileUploadException("Định dạng File không hỗ trợ");
            }
            try {
                Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
                urlImages.add(data.get("url").toString());
            } catch (IOException e) {
                throw new FileUploadException(e.getMessage());
            }
        }
        return urlImages;



    }

}
