package com.example.springboot_mongodb;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.hibernate.annotations.Type;

import java.util.Date;
import javax.persistence.*;


@Document(collection = "books")
public class Books {
    @Id
    public String idBook;
    @TextIndexed
    public String nameBook;
    @TextIndexed
    public String author;

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date datePublic;
    public String describe;

    public Books(String idBook, String nameBook, String author, Date datePublic, String describe) {

        this.idBook = idBook;
        this.nameBook = nameBook;
        this.author = author;
        this.datePublic = datePublic;
        this.describe = describe;
    }

    public String getIdBook() {
        return idBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDatePublic() {
        return datePublic;
    }

    public void setDatePublic(Date datePublic) {
        this.datePublic = datePublic;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Books{" +
                "idBook='" + idBook + '\'' +
                ", nameBook='" + nameBook + '\'' +
                ", author='" + author + '\'' +
                ", datePublic='" + datePublic + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}


