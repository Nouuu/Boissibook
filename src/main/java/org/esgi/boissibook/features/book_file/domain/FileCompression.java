package org.esgi.boissibook.features.book_file.domain;

import org.esgi.boissibook.features.book_file.kernel.exception.ZipCompressionException;

public interface FileCompression {

    byte[] compress(String name, byte[] content) throws ZipCompressionException;

    byte[] decompress(String name, byte[] content) throws ZipCompressionException;
}
