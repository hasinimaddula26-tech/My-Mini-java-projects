import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String author;
    private double price;
    private LocalDate publishDate;

    public Book(String title, String author, double price, LocalDate publishDate) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public String toString() {
        return "Book{Title='" + title + "', Author='" + author + "', Price=" + price + ", Publish Date=" + publishDate + "}";
    }
}

class BookStore {
    private List<Book> books;

    public BookStore() {
        books = new ArrayList<>();

        books.add(new Book("Java Programming", "Geek1", 39.99, LocalDate.of(2021, 1, 15)));
        books.add(new Book("Learning Java 8", "Geek2", 49.99, LocalDate.of(2020, 5, 10)));
        books.add(new Book("Advanced Java", "Geek3", 59.99, LocalDate.of(2021, 8, 20)));
        books.add(new Book("Spring Framework", "Geek4", 29.99, LocalDate.of(2019, 11, 30)));
    }

    public List<Book> filterBooksByPrice(double maxPrice) {
        return books.stream()
                .filter(b -> b.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Book>> groupBooksByYear() {
        return books.stream()
                .collect(Collectors.groupingBy(b -> b.getPublishDate().getYear()));
    }

    public Optional<Book> searchBookByTitle(String title) {
        return books.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .findFirst();
    }

    public void displayBooks() {
        books.forEach(System.out::println);
    }
}

public class BookStoreApp {
    public static void main(String[] args) {

        BookStore store = new BookStore();

        System.out.println("All Books:");
        store.displayBooks();

        System.out.println("\nBooks under 40:");
        store.filterBooksByPrice(40).forEach(System.out::println);

        System.out.println("\nGrouped by Year:");
        Map<Integer, List<Book>> grouped = store.groupBooksByYear();
        grouped.forEach((year, list) -> {
            System.out.println(year + " -> " + list);
        });

        System.out.println("\nSearch 'Advanced Java':");
        Optional<Book> result = store.searchBookByTitle("Advanced Java");
        result.ifPresent(System.out::println);
    }
}