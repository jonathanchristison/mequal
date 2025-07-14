package org.jboss.mequal.service.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

public class GzippedFileInputStream extends InputStream {

    private GZIPInputStream gzipInputStream;
    private FileInputStream fileInputStream;
    private File file;

    public GzippedFileInputStream(URI uri) throws IOException {
        this(Paths.get(uri).toFile());
    }

    public GzippedFileInputStream(String filePath) throws IOException {
        this(new File(filePath));
    }

    public GzippedFileInputStream(File file) throws IOException {
        this.file = file;
        this.fileInputStream = new FileInputStream(file);
        this.gzipInputStream = new GZIPInputStream(fileInputStream);
    }

    @Override
    public int read() throws IOException {
        return gzipInputStream.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return gzipInputStream.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return gzipInputStream.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return gzipInputStream.skip(n);
    }

    @Override
    public int available() throws IOException {
        return gzipInputStream.available();
    }

    @Override
    public void close() throws IOException {
        gzipInputStream.close(); 
    }
}