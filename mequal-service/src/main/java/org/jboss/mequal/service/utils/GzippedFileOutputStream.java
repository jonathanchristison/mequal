package org.jboss.mequal.service.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.GZIPOutputStream;

public class GzippedFileOutputStream extends OutputStream {

    private GZIPOutputStream gzipOutputStream;
    private FileOutputStream fileOutputStream;
    private File file;
    private MessageDigest sha256Digest; // New: For calculating SHA-256

    public GzippedFileOutputStream(URI uri) throws IOException, NoSuchAlgorithmException {
        this(Paths.get(uri).toFile());
    }

    public GzippedFileOutputStream(String filePath) throws IOException, NoSuchAlgorithmException {
        this(new File(filePath));
    }

    public GzippedFileOutputStream(File file) throws IOException, NoSuchAlgorithmException {
        this.file = file;
        this.fileOutputStream = new FileOutputStream(file);
        this.gzipOutputStream = new GZIPOutputStream(fileOutputStream);
        this.sha256Digest = MessageDigest.getInstance("SHA-256"); // Initialize SHA-256
    }

    @Override
    public void write(int b) throws IOException {
        sha256Digest.update((byte) b); // Update digest with uncompressed byte
        gzipOutputStream.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        sha256Digest.update(b); // Update digest with uncompressed bytes
        gzipOutputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        sha256Digest.update(b, off, len); // Update digest with uncompressed bytes
        gzipOutputStream.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        gzipOutputStream.flush();
    }

    @Override
    public void close() throws IOException {
        gzipOutputStream.close(); // This also closes the underlying fileOutputStream
        // The digest calculation is complete once all data is written and stream is closed.
    }

    /**
     * Retrieves the SHA-256 hash of the uncompressed content.
     * This method should be called AFTER all content has been written and the stream is closed.
     *
     * @return The SHA-256 hash as a byte array.
     * @throws IllegalStateException If called before the stream is fully written and closed (though technically it can be called anytime,
     * the hash will only be final after close).
     */
    public byte[] getUncompressedContentSha256() {
        return sha256Digest.digest();
    }

    /**
     * Helper method to convert a byte array hash to a hexadecimal string.
     * @param hashBytes The byte array hash.
     * @return The hash as a hexadecimal string.
     */
    public static String bytesToHex(byte[] hashBytes) {
        StringBuilder hexString = new StringBuilder(2 * hashBytes.length);
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}