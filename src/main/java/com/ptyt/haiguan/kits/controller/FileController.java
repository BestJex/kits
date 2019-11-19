package com.ptyt.haiguan.kits.controller;

import com.ptyt.haiguan.kits.service.FileService;
import com.ptyt.haiguan.kits.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author: yq
 * @date: 2019/11/14 17:05
 * @description:
 */
@RestController
@RequestMapping("/ptyt/customs/kits")
public class FileController {

    @Autowired
    private FileService fileService;

    @Value("${spring.fileLocation}")
    private String fileLocation;

    @GetMapping("/getRootFileList")
    public List getFileList() {
        return fileService.getFileListByPath(new File(fileLocation), "");
    }

    @GetMapping("/getFileListByDir/{dir}")
    public List getFileListByDir(@PathVariable("dir") String dir) {
        return fileService.getFileListByPath(new File(fileLocation + File.separator + dir), "/" + dir);
    }

    @GetMapping("/getDirectoryList")
    public List getDirectoryList() {
        return fileService.getAllFileListByPath(new File(fileLocation), "");
    }

    @GetMapping("/download")
    public void download(@RequestParam String filePath , HttpServletResponse response) {
        fileService.download(fileLocation + "/" + filePath , response);
    }

    @PostMapping("/upload")
    public Map upload(@RequestParam("file") MultipartFile file) {
        return fileService.upload(file, fileLocation);
    }

    @GetMapping("/index")
    public String index() {
        return "upload";
    }

    @PostMapping("/createFolder/{folderName}")
    public R createFolder(@PathVariable("folderName") String folderName) {
        return fileService.createFolder(fileLocation + "/" + folderName);
    }

    @PostMapping("/uploadWithPath")
    public R uploadWithPath(@RequestParam("file") MultipartFile file, @RequestParam("filePath") String filePath) {
        return fileService.uploadWithPath(file, fileLocation + "/" + filePath);
    }
}
