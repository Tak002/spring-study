package hello.hello_spring.service;

import hello.hello_spring.domain.Book;
import hello.hello_spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book registerBook(Book book) {
        bookRepository.getBookByTitleAndAuthor(book.getTitle(), book.getAuthor())
                .ifPresent(b -> {
                    throw new IllegalStateException("이미 존재하는 책입니다");
                });
        
        return bookRepository.insertBook(book).get();
    }

    public List<Book> findAllBooks(){
        return bookRepository.getAllBooks();
    }

    public List<Book> findBooksByTitle(String title){
        return bookRepository.getBooksByTitle(title);
    }
}
