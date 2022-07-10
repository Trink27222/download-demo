package com.trink.controller;


import com.trink.config.DownloadPathConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class DownloadController {

    @Resource
    private DownloadPathConfig downloadPathConfig;

    @GetMapping("get/{name}")
    public void download(@PathVariable(name = "name") String name, HttpServletResponse response) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(downloadPathConfig.getPath() +name);
            byte[] flush = new byte[1024 * 1024];
            String[] split = name.split("\\.");
            response.setHeader("Content-Disposition", "attachment;filename=" + split[split.length - 2] + "." + split[split.length - 1]);
            response.setContentLength(fis.available());
            ServletOutputStream fos = response.getOutputStream();
            while (fis.read(flush) != -1) {
                fos.write(flush);
            }
            fos.flush();
            fos.close();
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
