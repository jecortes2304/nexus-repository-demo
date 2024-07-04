package com.cortestudios;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class PDFDownloader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

        System.out.print("Ingrese la URL del PDF: ");
        String fileURL = scanner.nextLine();

        System.out.print("Ingrese el nombre con el que desea guardar el archivo (sin extensión): ");
        String fileName = scanner.nextLine();

        try {
            downloadPDF(fileURL, fileName + ".pdf");
            System.out.println("El archivo se ha descargado correctamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al descargar el archivo: " + e.getMessage());
        }

        scanner.close();
    }

    public static void downloadPDF(String fileURL, String fileName) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(fileURL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                try (InputStream inputStream = responseBody.byteStream()) {
                    Path path = Path.of(fileName);
                    Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
}

