package com.softuni.fitlaunch.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.UUID;

@Service
public class ImageCloudService {
    private Cloudinary cloudinary;

    private final String CLOUD_NAME = "diffdysmk";
    private final String API_KEY = "819549632415934";
    private final String API_SECRET = "MlXIgJA3bqnfjVfD2bCzGKXlm00";

    public ImageCloudService() {
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CLOUD_NAME,
                "api_key", API_KEY,
                "api_secret", API_SECRET,
                "secure", true
        ));
    }

    public String saveImage(MultipartFile multipartFile) throws IOException {
        String imageId = UUID.randomUUID().toString();
//        Map params = ObjectUtils.asMap(
//                "public_id", imageId,
//                "overwrite", true,
//                "resource_type", "image"
//        );

        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", imageId))
                .get("url")
                .toString();
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
