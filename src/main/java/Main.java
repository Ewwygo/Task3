import entities.Person;
import entities.SubTask4.Author;
import entities.SubTask4.Book;
import entities.SubTask5.A;
import interfaces.MyFunctionalInterface;
import interfaces.ThreeFunction;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static List<Person> persons = new ArrayList<>();
    static Author[] authors = new Author[3];
    static Book[] books = new Book[5];
    static A[] a = new A[50];

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
    }

    private static void printPersonsByAge(List<Person> persons){
        Comparator<Person> ageComparator = Comparator.comparingInt(Person::getAge);
        Collections.sort(persons,ageComparator);
        System.out.println("Sorted by age");
        for (Person p: persons
        ) {
            System.out.println(p.toString());
        }
        System.out.println();
    }

    private static void printPersonsByName(List<Person> persons){
        Comparator<Person> nameComparator = Comparator.comparing(Person::getName);
        Collections.sort(persons,nameComparator);
        System.out.println("Sorted by name");
        for (Person p: persons
        ) {
            System.out.println(p.toString());
        }
        System.out.println();
    }

    private static void task1(){
        // Task 1
        // -----------------------------------------------------------------------

        persons.add(new Person("John",20));
        persons.add(new Person("Fill",15));
        persons.add(new Person("Jake",22));

        printPersonsByAge(persons);
        printPersonsByName(persons);

    }

    private static void task2(){
        // Task 2
        // ------------------------------------------------------------------------
        System.out.println("------------------------------------------------------\nTask 2");
        Predicate<Person> predicate = (p1) -> p1.getAge() >= 18 ;

        Consumer<Person> consumer = (p1) -> System.out.println(predicate.test(p1));
        consumer.accept(persons.get(0));

        Function<Person, String> function = (p1) -> p1.toString();
        System.out.println(function.apply(persons.get(1)));

        MyFunctionalInterface myInterface = (p1,p2) -> System.out.println(p1.getAge() > p2.getAge() ? p1.getName() + " is older" : p2.getName() + " is older");
        myInterface.run(persons.get(0),persons.get(1));     // lambda method

        MyFunctionalInterface myInnerInterface = new MyFunctionalInterface() {
            @Override
            public void run(Person p1, Person p2) {
                System.out.println(p1.getAge() > p2.getAge() ? p1.getName() + " is older" : p2.getName() + " is older");
            }
        };
        myInnerInterface.run(persons.get(0),persons.get(2));        // inner anon class method
        myInnerInterface.run();     // default method
        myInnerInterface.run(persons.get(0));        // default method
        MyFunctionalInterface.hello();      // static method
        System.out.println();

    }

    private static void task3(){
        //-------------------------------------------------------------------
        //Task 3

        System.out.println("---------------------------------------------------\nTask3");
        ThreeFunction<Person,Integer> averageAge = (p1,p2,p3) -> (p1.getAge() + p2.getAge() + p3.getAge())/3;
        System.out.println("Average age is - " + averageAge.run(persons.get(0),persons.get(1),persons.get(2)));
        ThreeFunction<Person,String> friends = (p1,p2,p3) -> (p1.getName() + ", " + p2.getName() + " and" + p3.getName() + " all hate each other");
        System.out.println(friends.run(persons.get(0),persons.get(1),persons.get(2)));
    }

    private static void task4(){

        initAuthorsAndBooks();

        System.out.println("Books with more than 200 pages");
        Stream.of(books).parallel().filter(book -> book.getNumberOfPages() > 200).forEach(book -> System.out.println(book.toString()));    // books with more than 200 pages
        System.out.println();

        System.out.println("Book with max pages");
        System.out.println(Stream.of(books).parallel().max(Comparator.comparing(book -> book.getNumberOfPages())));     // book with max pages
        System.out.println();

        System.out.println("Book with min pages");
        System.out.println(Stream.of(books).parallel().min(Comparator.comparing(book -> book.getNumberOfPages())));     // book with min pages
        System.out.println();

        System.out.println("Books with only 1 author");
        Stream.of(books).parallel().filter(book -> book.getAuthors().size()==1).forEach(book -> System.out.println(book.toString()));      // books with only 1 author

        System.out.println("\n---------------------------------------\nSorted by pages");
        Stream.of(books).parallel().sorted(Comparator.comparingInt(book -> book.getNumberOfPages())).forEach(book -> System.out.println(book));        //  printing books sorted by pages

        System.out.println("\n---------------------------------------\nGetting and printing titles");
        List<String> titles = Stream.of(books).parallel().map(book -> book.getTitle()).collect(Collectors.toList());
        for (String s: titles
             ) {
            System.out.println(s);
        }

        System.out.println("\nPrinting authors for each book");
        Stream.of(books).parallel().forEach(book -> System.out.println(book.getAuthors()));        // getting and printing authors for each book

        Optional<Book> book = Stream.of(books).filter(book1 -> book1.getAuthors().contains(authors[2])).max(Comparator.comparing(book1 -> book1.getNumberOfPages()));
        if (book.isPresent()){
            System.out.println("Largest book of the author3" + book.get().getTitle());
        }

    }

    private static void task5(){
        for (int i = 0; i < 50 ; i++) {
            a[i] = new A(1,"a");
        }

        // Count int + string.length of all A in a[]
        int count = Stream.of(a).parallel().collect(Collector.of(
                () -> new int[1],
                (result,x) -> result[0] += x.sum(x.getI(),x.getS()),
                (result1,result2) -> {
                    result1[0] += result2[0];
                    return result1;
                },
                total -> total[0],
                Collector.Characteristics.UNORDERED
        ));
        System.out.println(count);
    }
    private static void initAuthorsAndBooks(){
        authors[0] = new Author("Author1", (short) 30);
        authors[1] = new Author("Author2", (short) 22);
        authors[2] = new Author("Author3", (short) 27);
        books[0] = new Book("Book1",100);
        books[1] = new Book("Book2",200);
        books[2] = new Book("Book3",150);
        books[3] = new Book("Book4",310);
        books[4] = new Book("Book5",350);
        authors[0].addBook(books[0]);
        books[0].addAuthor(authors[0]);
        authors[0].addBook(books[1]);
        books[1].addAuthor(authors[0]);
        authors[0].addBook(books[2]);
        books[2].addAuthor(authors[0]);
        authors[1].addBook(books[2]);
        books[2].addAuthor(authors[1]);
        authors[1].addBook(books[3]);
        books[3].addAuthor(authors[1]);
        authors[2].addBook(books[3]);
        books[3].addAuthor(authors[2]);
        authors[2].addBook(books[4]);
        books[4].addAuthor(authors[2]);
    }
}
