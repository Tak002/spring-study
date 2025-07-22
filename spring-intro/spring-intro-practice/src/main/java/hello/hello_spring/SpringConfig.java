package hello.hello_spring;


import hello.hello_spring.repository.BookRepository;
import hello.hello_spring.repository.JpaBookRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    EntityManager em;

    SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public BookRepository getBookRepository() {
        return new JpaBookRepository(em);
    }
}
