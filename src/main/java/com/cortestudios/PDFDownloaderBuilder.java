package com.cortestudios;

/**
 * Builder para crear instancias de PDFDownloader.
 */
public final class PDFDownloaderBuilder {
    private String fileURL;
    private String fileName;

    private PDFDownloaderBuilder() {
    }

    /**
     * Crea una nueva instancia de PDFDownloaderBuilder.
     *
     * @return Una nueva instancia de PDFDownloaderBuilder.
     */
    public static PDFDownloaderBuilder aPDFDownloader() {
        return new PDFDownloaderBuilder();
    }

    /**
     * Establece la URL del archivo a descargar.
     *
     * @param fileURL URL del archivo PDF.
     * @return La instancia actual de PDFDownloaderBuilder.
     */
    public PDFDownloaderBuilder fileURL(String fileURL) {
        this.fileURL = fileURL;
        return this;
    }

    /**
     * Establece el nombre del archivo para guardar el PDF descargado.
     *
     * @param fileName Nombre del archivo.
     * @return La instancia actual de PDFDownloaderBuilder.
     */
    public PDFDownloaderBuilder fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * Construye y devuelve una instancia de PDFDownloader.
     *
     * @return Una nueva instancia de PDFDownloader.
     */
    public PDFDownloader build() {
        return new PDFDownloader(fileURL, fileName);
    }
}