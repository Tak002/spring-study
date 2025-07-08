package hello.hello_spring.repository;

import hello.hello_spring.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void insertBookTest() {
        Book book = new Book();
        book.setTitle("CleanCode");
        book.setAuthor("GreatOne");
        Optional<Book> result = bookRepository.insertBook(book);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(book.getId(), result.get().getId());
        Assertions.assertEquals(book.getTitle(), result.get().getTitle());
        Assertions.assertEquals(book.getAuthor(), result.get().getAuthor());
    }

    @Test
    public void insertDuplicatedBookTest() {
        Book book1 = new Book();
        book1.setTitle("CleanCode");
        book1.setAuthor("GreatOne");
        bookRepository.insertBook(book1);

        Book book2 = new Book();
        book2.setTitle("CleanCode");
        book2.setAuthor("GreatOne");
        Assertions.assertThrows(Exception.class, () -> {bookRepository.insertBook(book2);});
    }

    @Test
    public void getBookByIdTest() {
        Book book = new Book();
        book.setTitle("CleanCode");
        book.setAuthor("GreatOne");
        bookRepository.insertBook(book);
        Optional<Book> result = bookRepository.getBookById(book.getId());
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(book.getId(), result.get().getId());
        Assertions.assertEquals(book.getTitle(), result.get().getTitle());
        Assertions.assertEquals(book.getAuthor(), result.get().getAuthor());
    }
    @Test
    public void getBooksByTitleTest(){
        Book book1 = new Book();
        book1.setTitle("SameNameBook");
        book1.setAuthor("GreatOne");
        bookRepository.insertBook(book1);

        Book book2 = new Book();
        book2.setTitle("SameNameBook");
        book2.setAuthor("GreatTwo");
        bookRepository.insertBook(book2);

        Book book3 = new Book();
        book3.setTitle("SameNameBook");
        book3.setAuthor("GreatThree");
        bookRepository.insertBook(book3);

        List<Book> books = bookRepository.getBooksByTitle("SameNameBook");
        Assertions.assertTrue(books.stream().anyMatch(book
                -> book1.getTitle().equals(book.getTitle()) &&  book1.getAuthor().equals(book.getAuthor())));
        Assertions.assertTrue(books.stream().anyMatch(book
                -> book2.getTitle().equals(book.getTitle()) &&  book2.getAuthor().equals(book.getAuthor())));
        Assertions.assertTrue(books.stream().anyMatch(book
                -> book3.getTitle().equals(book.getTitle()) &&  book3.getAuthor().equals(book.getAuthor())));

    }

    @Test
    public void getAllBooksTest() {
        Book book1 = new Book();
        book1.setTitle("SameNameBook");
        book1.setAuthor("GreatOne");
        bookRepository.insertBook(book1);

        Book book2 = new Book();
        book2.setTitle("SameNameBook");
        book2.setAuthor("GreatTwo");
        bookRepository.insertBook(book2);

        Book book3 = new Book();
        book3.setTitle("SameNameBook");
        book3.setAuthor("GreatThree");
        bookRepository.insertBook(book3);

        List<Book> books = bookRepository.getAllBooks();
        Assertions.assertTrue(books.stream().anyMatch(book
                -> book1.getTitle().equals(book.getTitle()) &&  book1.getAuthor().equals(book.getAuthor())));
        Assertions.assertTrue(books.stream().anyMatch(book
                -> book2.getTitle().equals(book.getTitle()) &&  book2.getAuthor().equals(book.getAuthor())));
        Assertions.assertTrue(books.stream().anyMatch(book
                -> book3.getTitle().equals(book.getTitle()) &&  book3.getAuthor().equals(book.getAuthor())));

    }

    @Test
    public void getAllBooksByNameAndAuthorTest() {
        Book book = new Book();
        book.setTitle("CleanCode");
        book.setAuthor("GreatOne");
        bookRepository.insertBook(book);

        Optional<Book> result = bookRepository.getBookByTitleAndAuthor("CleanCode", "GreatOne");
        Assertions.assertTrue(result.isPresent());
        Assertions.assertSame(book.getTitle(), result.get().getTitle());
        Assertions.assertEquals(book.getAuthor(), result.get().getAuthor());
    }
}
