package org.example;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;


@RequiredArgsConstructor
@Data
public class Book
{
    private final String title, author;
    private final Date published;
}
