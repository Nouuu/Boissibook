package org.esgi.boissibook.features.book.infra.repository;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    private String id;
    @Column(unique = true)
    private String apiId;
    private String title;
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    @Lob
    private String description;
    private String isbn13;
    private String language;
    @Lob
    private String imgUrl;
    private int pages;

    public BookEntity() {
    }

    public BookEntity(String id, String apiId, String title, List<String> authors, String publisher, String publishedDate, String description, String isbn13, String language, String imgUrl, int pages) {
        this.id = id;
        this.apiId = apiId;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.isbn13 = isbn13;
        this.language = language;
        this.imgUrl = imgUrl;
        this.pages = pages;
    }

    public BookEntity setId(String id) {
        this.id = id;
        return this;
    }

    public BookEntity setApiId(String apiId) {
        this.apiId = apiId;
        return this;
    }

    public BookEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookEntity setAuthors(List<String> authors) {
        this.authors = authors;
        return this;
    }

    public BookEntity setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public BookEntity setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
        return this;
    }

    public BookEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public BookEntity setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
        return this;
    }

    public BookEntity setLanguage(String language) {
        this.language = language;
        return this;
    }

    public BookEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public BookEntity setPages(int pages) {
        this.pages = pages;
        return this;
    }

    public String id() {
        return id;
    }

    public String apiId() {
        return apiId;
    }

    public String title() {
        return title;
    }

    public List<String> authors() {
        return authors;
    }

    public String publisher() {
        return publisher;
    }

    public String publishedDate() {
        return publishedDate;
    }

    public String description() {
        return description;
    }

    public String isbn13() {
        return isbn13;
    }

    public String language() {
        return language;
    }

    public String imgUrl() {
        return imgUrl;
    }

    public int pages() {
        return pages;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
            "id='" + id + '\'' +
            ", apiId='" + apiId + '\'' +
            ", title='" + title + '\'' +
            ", authors=" + authors +
            ", publisher='" + publisher + '\'' +
            ", publishedDate='" + publishedDate + '\'' +
            ", description='" + description + '\'' +
            ", isbn13='" + isbn13 + '\'' +
            ", language='" + language + '\'' +
            ", imgUrl='" + imgUrl + '\'' +
            ", pages=" + pages +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return pages == that.pages && Objects.equals(id, that.id) && Objects.equals(apiId, that.apiId) && Objects.equals(title, that.title) && Objects.equals(authors, that.authors) && Objects.equals(publisher, that.publisher) && Objects.equals(publishedDate, that.publishedDate) && Objects.equals(description, that.description) && Objects.equals(isbn13, that.isbn13) && Objects.equals(language, that.language) && Objects.equals(imgUrl, that.imgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apiId, title, authors, publisher, publishedDate, description, isbn13, language, imgUrl, pages);
    }
}
