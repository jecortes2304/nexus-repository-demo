package com.example.demo.controller;

import com.cortestudios.PDFDownloader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {

    private final PDFDownloader downloader = new PDFDownloader();

    @GetMapping("/api/download-pdf")
    public String test() {

        try {
            downloader.downloadPDF("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf", "dummy");
            return "Pdf downloaded successfully";
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return "Error downloading pdf";
        }
    }
}
