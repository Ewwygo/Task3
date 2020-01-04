package entities.SubTask4;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Author {

    @Getter
    String name;
    @Getter
    short age;
    @Getter
    List<Book> books;

    public Author(String name, short age){
        this.name = name;
        this.age = age;
        this.books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", books=" + books +
                '}';
    }
}
