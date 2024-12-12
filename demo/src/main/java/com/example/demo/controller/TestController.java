package com.example.demo.controller;

import com.cortestudios.PDFDownloader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {

    @GetMapping("/api/download-pdf")
    public String test() {
        PDFDownloader downloader = new PDFDownloader(
                "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf",
                "dummy"
        );

        try {
            downloader.downloadPDF();
            return "Pdf downloaded successfully";
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return "Error downloading pdf";
        }
    }
}
