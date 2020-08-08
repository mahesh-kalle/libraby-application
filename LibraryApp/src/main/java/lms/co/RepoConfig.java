package lms.co;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lms.co.repository.BookRepository;
import lms.co.repository.BorrowerRepository;

@Configuration
public class RepoConfig {
    @Bean
    public BookRepository booksRepo(){
        return new BookRepository();
    }
    
    @Bean
    public BorrowerRepository usersRepo(){
        return new BorrowerRepository();
    }
}

