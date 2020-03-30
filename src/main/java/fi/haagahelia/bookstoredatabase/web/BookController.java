package fi.haagahelia.bookstoredatabase.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.bookstoredatabase.domain.Book;
import fi.haagahelia.bookstoredatabase.domain.BookRepository;
import fi.haagahelia.bookstoredatabase.domain.CategoryRepository;


@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository; 
	
	//Login page
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/bookList")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "bookList";
	}

	// RESTful service to get all books
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> bookListRest() {
	return (List<Book>) repository.findAll();
	}
	
	@RequestMapping(value = "/add")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}     
	
	// RESTful service to get on book by id
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
		return repository.findById(id);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book){
	    repository.save(book);
	    return "redirect:bookList";
	}    

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteBook(@PathVariable("id") Long Id, Model model) {
		repository.deleteById(Id);
		return "redirect:../bookList";
	} 
	
	@RequestMapping(value= "/edit/{id}", method = RequestMethod.GET)
	public String editForm(@PathVariable("id") Long id, Model model ) {
		model.addAttribute("book", repository.findById(id));
		model.addAttribute("categories", crepository.findAll());
		return  "editBook";
	}
}
