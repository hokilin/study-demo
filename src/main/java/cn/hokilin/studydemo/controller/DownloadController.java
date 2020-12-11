package cn.hokilin.studydemo.controller;


import cn.hokilin.studydemo.response.ResponseData;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author linhuankai
 * @date 2020/12/10 10:30
 */
@RestController
@RequestMapping("/download")
public class DownloadController {

    @GetMapping("/pdf")
    public ResponseData downloadPdf(HttpServletResponse response) throws IOException {
        writeAsBytes(response, false);
        return ResponseData.success("success");
    }

    private void writeAsBytes(HttpServletResponse response, boolean isDown) throws IOException {
        String path = "D:\\hokilin\\业务文件\\苏宁大规模标签场景应用实践.pdf";
        byte[] bytes = FileUtils.readFileToByteArray(new File(path));
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            response.reset();
            if (isDown) {
                // 强制下载
                response.setHeader("Content-Disposition",
                        String.format("attachment; filename=\"%s\"", new String("苏宁大规模标签场景应用实践".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1))
                );
            } else {
                // 在浏览器端打开
                response.setHeader("Content-Disposition",
                        String.format("inline; filename=\"%s\"", new String("苏宁大规模标签场景应用实践".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1))
                );
            }
            response.addHeader("Content-Length", "" + bytes.length);
            response.setContentType(MediaType.APPLICATION_PDF_VALUE);
            IOUtils.write(bytes, outputStream);
            outputStream.flush();
        }
    }
}
