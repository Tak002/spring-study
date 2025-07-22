package hello.hello_spring.repository;

import hello.hello_spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    public List<Book> getAllBooks();
    public Optional<Book> getBookById(long id);
    public List<Book> getBooksByTitle(String name);
    public Optional<Book> insertBook(Book book);
    public Optional<Book> getBookByTitleAndAuthor(String name, String author);
}
