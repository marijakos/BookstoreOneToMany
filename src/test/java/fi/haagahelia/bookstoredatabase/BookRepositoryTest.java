package fi.haagahelia.bookstoredatabase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.bookstoredatabase.domain.Book;
import fi.haagahelia.bookstoredatabase.domain.BookRepository;
import fi.haagahelia.bookstoredatabase.domain.Category;



@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
    private BookRepository repository;

    @Test
    public void findByLastnameShouldReturnStudent() {
        List<Book> books = repository.findByAuthor("J.R.R. Tolkien");
        
        assertThat(books).hasSize(2);
        assertThat(books.get(0).getTitle()).isEqualTo("The Loard of the Rings: The Fellowship of the Ring");
    }
    
    @Test
    public void createNewBook() {
    	Book book = new Book(" J. K. Rowling", "Harry Potter and the Philosopher's Stone", "1239523-51", 1997, new Category("Fantasy"));
    	repository.save(book);
    	assertThat(book.getId()).isNotNull();
    }    
    
    @Test
    public void deleteBook_withValidId() {
    	List<Book> book = repository.deleteById(7);
    	
    	assertThat(book).isNull();
    	
    }
}
