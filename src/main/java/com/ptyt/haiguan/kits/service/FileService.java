package com.ptyt.haiguan.kits.service;

import cn.hutool.core.date.DateUtil;
import com.ptyt.haiguan.kits.constant.FileConstant;
import com.ptyt.haiguan.kits.util.FileUtils;
import com.ptyt.haiguan.kits.vo.FileVo;
import com.ptyt.haiguan.kits.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.*;

/**
 * @author: yq
 * @date: 2019/11/15 09:10
 * @description:
 */
@Service
@Slf4j
public class FileService {

    /**
     * 遍历整个目录不包含子目录
     * @param file
     * @return
     */
    public List getFileListByPath(File file, String parentDir) {
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        List list = new ArrayList();
        File[] files = file.listFiles();
        if(files != null && files.length > 0){
            for(File f : files){
                FileVo fileVo = toBaseFileVo(f);
                fileVo.setPath(parentDir + "/" + f.getName());
                if(f.isDirectory()){
                    fileVo.setType(FileConstant.FILE_TYPE_DIR);
                } else {
                    fileVo.setType(FileConstant.FILE_TYPE_FILE);
                }
                list.add(fileVo);
            }
        }
        return list;
    }

    /**
     * 遍历整个目录包含子目录
     * @param file
     * @return
     */
    public List getAllFileListByPath(File file, String parentDir) {
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        List list = new ArrayList();
        File[] files = file.listFiles();
        if(files != null && files.length > 0){
            for(File f : files){
                FileVo fileVo = toBaseFileVo(f);
                fileVo.setPath(parentDir + "/" + f.getName());
                if(f.isDirectory()){
                    fileVo.setType(FileConstant.FILE_TYPE_DIR);
                    //递归
                    List fileList = getAllFileListByPath(f, parentDir + "/" + f.getName());
                    if(fileList != null) {
                        List dirList = fileVo.getDirList();
                        dirList.addAll(fileList);
                        fileVo.setDirList(dirList);
                    }
                } else {
                    fileVo.setType(FileConstant.FILE_TYPE_FILE);
                }
                list.add(fileVo);
            }
        }
        return list;
    }

    /**
     * 文件下载，返回二进制流
     * @param filePath
     * @param response
     */
    public void download(String filePath, HttpServletResponse response) {
        File file = new File(filePath);
        if(!file.exists()) {
            log.info("{}文件不存在", filePath);
            return;
        }
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        try{
            ServletOutputStream outputStream = response.getOutputStream();
            try{
                outputStream.write(Files.readAllBytes(file.toPath()));
            } catch (NoSuchFileException e) {
                log.info("{}文件不匹配", filePath);
            }

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传，按照日期区分文件夹
     * @param file
     * @param path
     * @return
     */
    public Map upload( MultipartFile file, String path) {
        Map r = new HashMap();
        boolean result = true;
        String filename = file.getOriginalFilename();
        File filepath = new File(path + "/" + FileUtils.getFileDir(), filename);
        result = FileUtils.saveFile(file, filepath);
        r.put("result", result);
        return r;
    }

    /**
     * 文件上传，指定文件保存路径
     * @return
     */
    public R uploadWithPath(MultipartFile file, String path) {
        String filename = file.getOriginalFilename();
        File filepath = new File(path, filename);
        boolean result = FileUtils.saveFile(file, filepath);
        if(result) {
           return R.ok();
        } else {
            return R.failed();
        }
    }

    /**
     * 创建文件夹
     * @param folderPath
     * @return
     */
    public R createFolder(String folderPath) {
        File file = new File(folderPath);
        if(file.exists()) {
            return R.failed("创建失败，文件夹已存在！");
        }
        file.mkdirs();
        return R.ok();
    }


    public FileVo toBaseFileVo(File file) {
        FileVo fileVo = new FileVo();
        long modifyTime = file.lastModified();
        long length = file.length();
        Date date = DateUtil.date(modifyTime);
        fileVo.setModifyTime(DateUtil.formatDateTime(date));
        fileVo.setLength(length);
        fileVo.setName(file.getName());
        return fileVo;
    }
}
