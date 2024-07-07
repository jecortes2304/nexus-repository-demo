package com.cortestudios;

/**
 * Builder for creating instances of PDFDownloader.
 */
public final class PDFDownloaderBuilder {
    private String fileURL;
    private String fileName;

    private PDFDownloaderBuilder() {
    }

    /**
     * Creates a new instance of PDFDownloaderBuilder.
     *
     * @return A new instance of PDFDownloaderBuilder.
     */
    public static PDFDownloaderBuilder aPDFDownloader() {
        return new PDFDownloaderBuilder();
    }

    /**
     * Sets the URL of the file to download.
     *
     * @param fileURL URL of the PDF file.
     * @return The current instance of PDFDownloaderBuilder.
     */
    public PDFDownloaderBuilder fileURL(String fileURL) {
        this.fileURL = fileURL;
        return this;
    }

    /**
     * Sets the name of the file to save the downloaded PDF as.
     *
     * @param fileName Name of the file.
     * @return The current instance of PDFDownloaderBuilder.
     */
    public PDFDownloaderBuilder fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    /**
     * Builds and returns an instance of PDFDownloader.
     *
     * @return A new instance of PDFDownloader.
     */
    public PDFDownloader build() {
        return new PDFDownloader(fileURL, fileName);
    }
}