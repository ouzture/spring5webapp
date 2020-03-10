package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author martin = new Author("Martin", "Fowler");
        Author kent = new Author("Kent", "Beck");
        Book refactoring2 = new Book("Refactoring 2nd Edition", "0134757599");
        Book tdd = new Book("Test Driven Development", "2341");

        Publisher addison = new Publisher("Addison-Wesley", "75 Arlington Street", "Boston", "MA", "02116");


        publisherRepository.save(addison);


        martin.getBooks().add(refactoring2);

        kent.getBooks().add(refactoring2);

        kent.getBooks().add(tdd);


        authorRepository.save(martin);
        authorRepository.save(kent);

        refactoring2.getAuthors().add(martin);
        refactoring2.getAuthors().add(kent);
        refactoring2.setPublisher(addison);

        tdd.getAuthors().add(kent);
        tdd.setPublisher(addison);


        bookRepository.save(refactoring2);
        bookRepository.save(tdd);


        addison.getBooks().add(refactoring2);

        addison.getBooks().add(tdd);
        publisherRepository.save(addison);


        System.out.println("Started with bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Publisher count:" + publisherRepository.count());
        System.out.println("Publisher number of books:" + addison.getBooks().size());

    }
}
