package com.cortestudios;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Class for downloading PDF files from specified URLs.
 */
public class PDFDownloader {

    private final String fileURL;
    private String fileName;

    /**
     * Constructor for PDFDownloader.
     *
     * @param fileURL  URL of the PDF file to download.
     * @param fileName Name of the file to save the downloaded PDF as.
     */
    public PDFDownloader(String fileURL, String fileName) {
        this.fileURL = fileURL;
        this.fileName = fileName;
    }

    /**
     * Downloads the PDF file from the specified URL and saves it with the given file name.
     *
     * @throws IOException If an error occurs during the download or while saving the file.
     */
    public void downloadPDF() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String lastFourChars = this.fileName.substring(this.fileName.length() - 4);
        fileName = lastFourChars.equals(".pdf") ? this.fileName : this.fileName + ".pdf";
        Request request = new Request.Builder()
                .url(this.fileURL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                try (InputStream inputStream = responseBody.byteStream()) {
                    Path path = Path.of(this.fileName);
                    Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
}