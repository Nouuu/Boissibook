package org.esgi.boissibook.features.bookfile.infra;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.esgi.boissibook.features.bookfile.domain.FileCompression;

public class ZipFileCompression implements FileCompression {
    @Override
    public byte[] compress(String name, byte[] content) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(outputStream);
        ZipEntry entry = new ZipEntry(name);
        entry.setSize(content.length);
        zos.putNextEntry(entry);
        zos.write(content);
        zos.closeEntry();
        zos.close();
        return outputStream.toByteArray();
    }

    @Override
    public byte[] decompress(String name, byte[] content) throws IOException {
        ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(content));
        zipStream.getNextEntry();
        byte[] buffer = zipStream.readAllBytes();
        zipStream.close();
        return buffer;
    }
}
