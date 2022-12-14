package com.example.autogarage.controller.upload_download;

import com.example.autogarage.dto.FileUploadResponse;
import com.example.autogarage.service.DatabaseService;
import com.example.autogarage.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipOutputStream;

@CrossOrigin
@RestController
public class UploadDownloadWithFileSystemController {
    private final FileStorageService fileStorageService;
    private final DatabaseService databaseService;

    public UploadDownloadWithFileSystemController(FileStorageService fileStorageService, DatabaseService databaseService) {
        this.fileStorageService = fileStorageService;
        this.databaseService = databaseService;
    }

//    post for single upload
    @PostMapping("single/upload")
    public FileUploadResponse singleFileUpload(@RequestParam("file") MultipartFile file){

        String fileName = fileStorageService.storeFile(file);

        // next line makes url. example "http://localhost:8080/download/naam.jpg"
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName).toUriString();

        String contentType = file.getContentType();

        return new FileUploadResponse(fileName, contentType, url );
    }

//    get for single download
    @GetMapping("/{fileName}")
    ResponseEntity<Resource> downLoadSingleFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileStorageService.downLoadFile(fileName);

        String mimeType;

        try{
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }

    //    get all names in directory
    @GetMapping("/allNames")
    List<String> downLoadMultipleFile() {

        return fileStorageService.downLoad();

}

//    post for multiple uploads
    @PostMapping("/multiple/upload")
    List<FileUploadResponse> multipleUpload(@RequestParam("files") MultipartFile[] files) {

        if(files.length > 7) {
            throw new RuntimeException("to many files");
        }
        List<FileUploadResponse> uploadResponseList = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {
            String fileName = fileStorageService.storeFile(file);

            // next line makes url. example "http://localhost:8080/download/naam.jpg"
            String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(fileName).toUriString();

            String contentType = file.getContentType();

            FileUploadResponse response = new FileUploadResponse(fileName, contentType, url );
            uploadResponseList.add(response);

        });

        return uploadResponseList;

    }

    @GetMapping("zipDownload")
    public void zipDownload(@RequestParam("fileName") String[] files, HttpServletResponse response) throws IOException {

        try(ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())){
            Arrays.stream(files).forEach(file -> {
                try {
                    databaseService.createZipEntry(file, zos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            zos.finish();
        }

        response.setStatus(200);
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=zipfile");
    }

}