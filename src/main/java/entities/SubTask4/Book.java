package entities.SubTask4;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Book {
    @Getter
    String title;
    @Getter
    List<Author> authors;
    @Getter
    int numberOfPages;

    public Book(String title, int numberOfPages){
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.authors = new ArrayList<>();
    }

    public void addAuthor(Author author){
        authors.add(author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
