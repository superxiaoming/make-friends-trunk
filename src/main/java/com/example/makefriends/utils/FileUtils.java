package com.example.makefriends.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @description: 文件上传类
 * @Author: yinshm
 * @Date: 16:34 2020-03-24
 */
public class FileUtils {

    static String rootPath = "/Users/yinshiming/uploadPics";

    // static String rootPath = System.getProperty("user.dir") + "/src/main/resources/static";

    /**
     *
     * @param file 文件
     * @param fileName 源文件名
     * @return
     */
    public static String upload(MultipartFile file, String fileName){

        String newFileName = FileNameUtils.getFileName(fileName);
        String realPath = rootPath + "/" + newFileName;
        File dest = new File(realPath);
        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }
        try {
            //保存文件
            file.transferTo(dest);
            return newFileName;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }

    public static String getPicAddress(HttpServletRequest request, String fileName){
        return "http://192.168.1.11" + ":" + request.getServerPort() +
                "/makefriends/static/" + fileName;
    }

}
