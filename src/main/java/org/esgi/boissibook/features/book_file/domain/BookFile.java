package org.esgi.boissibook.features.book_file.domain;

import java.util.Arrays;
import java.util.Objects;

public class BookFile {
    private String id;
    private String name;
    private String type;
    private String bookId;
    private String userId;
    private int downloadCount;
    private byte[] content;

    public BookFile(String id, String name, String type, String bookId, String userId, int downloadCount, byte[] content) {
        this.id = id;
        this.type = type;
        this.bookId = bookId;
        this.userId = userId;
        this.name = name;
        this.downloadCount = downloadCount;
        this.content = content;
    }

    public String id() {
        return id;
    }

    public String bookId() {
        return bookId;
    }

    public String userId() {
        return userId;
    }

    public String name() {
        return name;
    }

    public byte[] content() {
        return content;
    }

    public String type() {
        return type;
    }

    public int downloadCount() {
        return downloadCount;
    }

    public BookFile setId(String id) {
        this.id = id;
        return this;
    }

    public BookFile setBookId(String bookId) {
        this.bookId = bookId;
        return this;
    }

    public BookFile setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public BookFile setName(String name) {
        this.name = name;
        return this;
    }

    public BookFile setContent(byte[] content) {
        this.content = content;
        return this;
    }

    public BookFile setType(String type) {
        this.type = type;
        return this;
    }

    public BookFile setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
        return this;
    }

    @Override
    public String toString() {
        return "BookFile{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", bookId='" + bookId + '\'' +
            ", userId='" + userId + '\'' +
            ", downloadCount=" + downloadCount +
            ", content=" + Arrays.toString(content) +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookFile bookFile = (BookFile) o;
        return Objects.equals(id, bookFile.id) && Objects.equals(name, bookFile.name) && Objects.equals(type, bookFile.type) && Objects.equals(bookId, bookFile.bookId) && Objects.equals(userId, bookFile.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, bookId, userId);
    }
}