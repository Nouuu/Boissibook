package org.esgi.boissibook.features.bookfile.domain;

import java.io.IOException;

public interface FileCompression {

    byte[] compress(String name, byte[] content) throws IOException;

    byte[] decompress(String name, byte[] content) throws IOException;
}
