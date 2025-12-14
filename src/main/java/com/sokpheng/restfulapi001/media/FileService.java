package com.sokpheng.restfulapi001.media;


import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileResponseTemplate uploadSingleFile(MultipartFile file);
    List<FileResponseTemplate> uploadMultipleFiles(List<MultipartFile> files);
    Boolean deleteFileByName(String fileName);
}
