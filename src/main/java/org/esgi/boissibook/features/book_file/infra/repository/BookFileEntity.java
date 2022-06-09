package org.esgi.boissibook.features.book_file.infra.repository;

import org.esgi.boissibook.features.book_file.domain.BookFile;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Arrays;

@Entity
@Table(name = "book_file")
public class BookFileEntity {
    @Id
    private String id;
    private String name;
    private String type;
    private String bookId;
    private String userId;
    private int downloadCount;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] content;

    public BookFileEntity() {
    }

    private BookFileEntity(String id, String name, String type, String bookId, String userId, int downloadCount, byte[] content) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.bookId = bookId;
        this.userId = userId;
        this.downloadCount = downloadCount;
        this.content = content;
    }

    public static BookFileEntity create(String id, String name, String type, String bookId, String userId, int downloadCount, byte[] content) {
        return new BookFileEntity(id, name, type, bookId, userId, downloadCount, content);
    }

    public String id() {
        return id;
    }


    public String name() {
        return name;
    }

    public String bookId() {
        return bookId;
    }

    public String userId() {
        return userId;
    }

    public String type() {
        return type;
    }

    public int downloadCount() {
        return downloadCount;
    }

    public byte[] content() {
        return content;
    }

    public BookFileEntity id(String id) {
        this.id = id;
        return this;
    }

    public BookFileEntity name(String name) {
        this.name = name;
        return this;
    }

    public BookFileEntity bookId(String bookId) {
        this.bookId = bookId;
        return this;
    }

    public BookFileEntity content(byte[] content) {
        this.content = content;
        return this;
    }

    public BookFileEntity userId(String userId) {
        this.userId = userId;
        return this;
    }

    public BookFileEntity type(String type) {
        this.type = type;
        return this;
    }

    public BookFileEntity downloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
        return this;
    }

    public BookFile toBookFile() {
        return new BookFile(id, userId, type, name, bookId, downloadCount, content);
    }

    @Override
    public String toString() {
        return "BookFileEntity{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", bookId='" + bookId + '\'' +
            ", content=" + Arrays.toString(content) +
            '}';
    }
}
