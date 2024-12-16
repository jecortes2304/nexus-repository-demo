package com.cortestudios;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PDFDownloaderTest {

    @TempDir
    Path tempDir;

    @Test
    void testDownloadPDF() throws IOException {
        String fileURL = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
        String fileName = "test.pdf";

        Path filePath = tempDir.resolve(fileName);

        PDFDownloader downloader = PDFDownloaderBuilder.aPDFDownloader()
                .fileURL(fileURL)
                .fileName(filePath.toString())
                .build();

        downloader.downloadPDF();

        assertTrue(Files.exists(filePath), "El archivo PDF debería existir después de la descarga");
        assertTrue(Files.size(filePath) > 0, "El archivo PDF descargado no debería estar vacío");
    }

    @Test
    void testDownloadPDFPassingFileUrlAndName() throws IOException {
        String fileURL = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf";
        String fileName = "test.pdf";

        Path filePath = tempDir.resolve(fileName);

        PDFDownloader downloader = new PDFDownloader();

        downloader.downloadPDF(fileURL, filePath.toString());

        assertTrue(Files.exists(filePath), "El archivo PDF debería existir después de la descarga");
        assertTrue(Files.size(filePath) > 0, "El archivo PDF descargado no debería estar vacío");
    }

    @Test
    void testDownloadPDFInvalidURL() {
        String fileURL = "https://invalid-url.com/nonexistent.pdf";
        String fileName = "nonexistent.pdf";

        PDFDownloader downloader = PDFDownloaderBuilder.aPDFDownloader()
                .fileURL(fileURL)
                .fileName(fileName)
                .build();

        assertThrows(IOException.class, downloader::downloadPDF, "Debería lanzar una IOException para una URL inválida");
    }
}