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
 * Clase para descargar archivos PDF desde URLs especificadas.
 */
public class PDFDownloader {

    private final String fileURL;
    private String fileName;

    /**
     * Constructor para PDFDownloader.
     *
     * @param fileURL  URL del archivo PDF a descargar.
     * @param fileName Nombre del archivo para guardar el PDF descargado.
     */
    public PDFDownloader(String fileURL, String fileName) {
        this.fileURL = fileURL;
        this.fileName = fileName;
    }

    /**
     * Descarga el archivo PDF desde la URL especificada y lo guarda con el nombre de archivo dado.
     *
     * @throws IOException Si ocurre un error durante la descarga o al guardar el archivo.
     */
    public void downloadPDF() throws IOException {
        OkHttpClient client = new OkHttpClient();
        fileName = this.fileName.contains(".pdf") ? this.fileName : this.fileName + ".pdf";
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