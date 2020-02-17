package fi.haagahelia.bookstoredatabase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.bookstoredatabase.domain.Book;
import fi.haagahelia.bookstoredatabase.domain.BookRepository;
import fi.haagahelia.bookstoredatabase.domain.Category;
import fi.haagahelia.bookstoredatabase.domain.CategoryRepository;


@SpringBootApplication
public class BookstoredatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoredatabaseApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository) {
	return (args) -> {
		
	crepository.save(new Category("Fantasy"));
	crepository.save(new Category("Fairy Tale"));
	crepository.save(new Category("Crime and Detective"));
	crepository.save(new Category("Classic"));
	
	Book b1 = new Book("Ernest Hemingway", "A Farewell to Arms", "1232323-21", 1929, crepository.findByName("Classic").get(0));
	Book b2 = new Book("George Orwell", "Animal Farm", "2212343-5", 1945, crepository.findByName("Classic").get(0));
	Book b3 = new Book("J.R.R. Tolkien", "The Loard of the Rings: The Fellowship of the Ring", "1245623-11", 1954, crepository.findByName("Fantasy").get(0));
	Book b4 = new Book("J.R.R. Tolkien", "The Loard of the Rings: The Two Towers", "1234523-31", 1954, crepository.findByName("Fantasy").get(0));
	
	

	
	repository.save(b1);
	repository.save(b2);
	repository.save(b3);
	repository.save(b4);
	
	
	};
	}

}
