package org.esgi.boissibook.features.book.domain;

import java.util.List;
import java.util.Objects;

public class Book {
    private String id;
    private String apiId;
    private String title;
    private List<String> authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private String isbn13;
    private String language;
    private String imgUrl;
    private int pages;

    public Book(String id, String apiId, String title, List<String> authors, String publisher, String publishedDate, String description, String isbn13, String language, String imgUrl, int pages) {
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

    public Book setId(String id) {
        this.id = id;
        return this;
    }

    public Book setApiId(String apiId) {
        this.apiId = apiId;
        return this;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public Book setAuthors(List<String> authors) {
        this.authors = authors;
        return this;
    }

    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Book setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
        return this;
    }

    public Book setDescription(String description) {
        this.description = description;
        return this;
    }

    public Book setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
        return this;
    }

    public Book setLanguage(String language) {
        this.language = language;
        return this;
    }

    public Book setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public Book setPages(int pages) {
        this.pages = pages;
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
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
        Book book = (Book) o;
        return pages == book.pages && Objects.equals(id, book.id) && Objects.equals(apiId, book.apiId)
            && Objects.equals(title, book.title) && Objects.equals(publisher, book.publisher) && Objects.equals(publishedDate, book.publishedDate)
            && Objects.equals(description, book.description) && Objects.equals(isbn13, book.isbn13)
            && Objects.equals(language, book.language) && Objects.equals(imgUrl, book.imgUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, apiId, title, authors, publisher, publishedDate, description, isbn13, language, imgUrl, pages);
    }
}
