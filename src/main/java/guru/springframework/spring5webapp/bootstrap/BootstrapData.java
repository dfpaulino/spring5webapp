package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(final AuthorRepository authorRepository, final BookRepository bookRepository, final PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {

        Publisher publisher1 = new Publisher("Atlas","Address 123","Thurles","0000");

        publisherRepository.save(publisher1);

        Author eric = new Author("Eric","Evans");
        Book xxx = new Book("Domain Driven Design","123321");
        eric.getBooks().add(xxx);
        xxx.getAuthors().add(eric);
        xxx.setPublisher(publisher1);

        authorRepository.save(eric);
        bookRepository.save(xxx);

        Author rod  = new Author("rod","Johnson");
        Book yyy = new Book("J2EE Development without  EJB","123321456");
        rod.getBooks().add(yyy);
        yyy.getAuthors().add(rod);
        yyy.setPublisher(publisher1);

        publisher1.getBooks().add(xxx);
        publisher1.getBooks().add(yyy);


        authorRepository.save(rod);
        bookRepository.save(yyy);


        System.out.println("Started Bootstrap");
        System.out.println("Number of book "+ bookRepository.findAll().iterator().next().getAuthors().iterator().next().getFirstName());;
        System.out.println("Publisher  first book"+ publisherRepository.findAll().iterator().next().getBooks().iterator().next().getTitle());
        System.out.println("Publisher books size"+ publisherRepository.findById(1L).get().getBooks().size());
        System.out.println("Any author " +publisherRepository.findAll().iterator().next()
                .getBooks().iterator().next().getAuthors().iterator().next().getFirstName());

    }
}
