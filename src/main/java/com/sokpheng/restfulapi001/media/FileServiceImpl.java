package com.sokpheng.restfulapi001.media;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    @Value("${file.upload-location}")
    private String fileUploadLocation;
    @Value("${file.preview-path}")
    private String previewPath;
    @Value("${file.download-path}")
    private String downloadPath;
    private String uploadLogic(MultipartFile multipartFile){
        // get position of the dot symbol in order to get the location of extension
        int dotSymbolPosition = multipartFile
                .getOriginalFilename().indexOf(".");
        String extension = multipartFile.getOriginalFilename()
                .substring(dotSymbolPosition+1);
        if(extension.toLowerCase().contains("jpg") ||
                extension.toLowerCase().contains("jpeg") ||
                extension.toLowerCase().contains("png")){
            String newFileName = UUID.randomUUID() + "." + extension;
            // define the location to upload
            Path fileUploadTo = Paths.get(fileUploadLocation, newFileName);
            try{
                // upload file to specific location
                Files.copy(multipartFile.getInputStream(), fileUploadTo);
                return newFileName;
            }catch (Exception exception){
                throw new RuntimeException(exception.getMessage());
            }
        }else {
            throw new RuntimeException("File is not supported");
        }
    }
    @Override
    public FileResponseTemplate uploadSingleFile(MultipartFile file){
        String fileName = uploadLogic(file);
        return FileResponseTemplate.builder()
                .fileName(fileName)
                .size(file.getSize())
                .type(file.getContentType())
                .previewUrl(previewPath+fileName)
                .downloadUrl(downloadPath+"api/v1/files"+fileName)
                .build();
    }

    @Override
    public List<FileResponseTemplate> uploadMultipleFiles(List<MultipartFile> files) {
        return List.of();
    }

    @Override
    public Boolean deleteFileByName(String fileName) {
        return null;
    }
}
