package hello.hello_spring.repository;

import hello.hello_spring.domain.Book;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class JpaBookRepository implements BookRepository {
    EntityManager em;

    public JpaBookRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public List<Book> getAllBooks() {
        return em.createQuery("select b from Book b",Book.class).getResultList();
    }

    @Override
    public Optional<Book> getBookById(long id) {
        Book book = em.find(Book.class, id);
        return  Optional.ofNullable(book);
    }

    @Override
    public List<Book> getBooksByTitle(String name) {
        return em.createQuery("select b from Book b where b.title like :name",Book.class)
                .setParameter("name","%"+name+"%")
                .getResultList();
   }

    @Override
    @Transactional
    public Optional<Book> insertBook(Book book) {
        em.persist(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> getBookByTitleAndAuthor(String name, String author) {
        return em.createQuery("select b from Book b where b.title like :name and  b.author=:author",Book.class)
                .setParameter("name",name)
                .setParameter("author",author)
                .getResultList().stream().findAny();
        }
}
