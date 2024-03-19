package org.example;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


@Data
public class Book
{
    private final String title, author;
    private final Date published;
    public Book(String title, String author, LocalDateTime published) {
        this.title = title;
        this.author = author;
        this.published= Date.from(published.toInstant(ZoneOffset.UTC));
    }
}
