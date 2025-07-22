package hello.hello_spring.controller;

import hello.hello_spring.domain.Book;
import hello.hello_spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {
    BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book/new")
    public String newBook() {
        return "newBook";
    }

    @PostMapping("/book/new")
    public String register(NewBookForm newBookForm, Model model) {
        Book book = new Book();
        book.setTitle(newBookForm.getTitle());
        book.setAuthor(newBookForm.getAuthor());
        bookService.registerBook(book);
        return "redirect:/";
    }
    @GetMapping("/book/all")
    public String allBook(Model model) {
        model.addAttribute("books",bookService.findAllBooks());
        model.addAttribute("pageTitle","모든 책 목록");
        return "bookTable";
    }

    @GetMapping("book/search")
    public String searchBook() {
        return "searchBook";
    }

    @PostMapping("book/search")
    public String showSearchBooks(NewBookForm newBookForm, Model model ) {
        List<Book> books = bookService.findBooksByTitle(newBookForm.getTitle());
        model.addAttribute("books",books);
        model.addAttribute("pageTitle","검색한 책 목록");
        return "bookTable";
    }

}
