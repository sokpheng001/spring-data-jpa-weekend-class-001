package com.sokpheng.restfulapi001.media;

import com.sokpheng.restfulapi001.utils.ResponseTemplate;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileController {
    private final FileService fileService;
    /**
     * Inject the location where the file are stored
     * to search for files by unique name
     */
    @Value("${file.upload-location}")
    private String fileUploadLocation;
    //
    @PostMapping("/upload")
    public ResponseTemplate<Object> uploadSingleFile
            (@RequestParam("file") MultipartFile multipartFile){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Upload single file successfully")
                .data(fileService.uploadSingleFile(multipartFile))
                .build();
    }
    @PostMapping("/upload-multi")
    public ResponseTemplate<Object> uploadMultipleFiles
            (@RequestParam("files") List<MultipartFile> multipartFiles){
        return ResponseTemplate
                .builder()
                .status(HttpStatus.OK.toString())
                .date(Date.from(Instant.now()))
                .message("Upload multiple files successfully")
                .data(fileService.uploadMultipleFiles(multipartFiles))
                .build();
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Object> getDownloadFile
            (@PathVariable(name = "filename") String fileName){
        try{
            // logic to download file
            // find the file in the location
            Path path = Paths.get(fileUploadLocation).resolve(fileName);
            Resource resource = new UrlResource(path.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        }catch (Exception exception){
            throw new RuntimeException("Error Downloading file");
        }
    }
}
