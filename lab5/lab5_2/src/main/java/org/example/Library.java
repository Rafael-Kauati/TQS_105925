package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private  List<Book> store = new ArrayList<>();

    public void addBook(final Book book) {
        store.add(book);
        //System.out.println("curr store : "+store);
    }

    public List<Book> findBooks(final Date from, final Date to) {
        Calendar end = Calendar.getInstance();
        end.setTime(to);
        end.roll(Calendar.YEAR, 1);
        System.out.println(store.stream().filter(book -> from.before(book.getPublished()) &&
                end.getTime().after(book.getPublished())).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList()));
        return store.stream().filter(book -> from.before(book.getPublished()) && end.getTime().after(book.getPublished())).sorted(Comparator.comparing(Book::getPublished).reversed()).collect(Collectors.toList());
    }


    public List<Book> findBooksByTitle(String title) {
        return store.stream().filter( book -> {return Objects.equals(title, book.getTitle());}).sorted(Comparator.comparing(Book::getTitle).reversed()).collect(Collectors.toList());
    }
}