package fi.haagahelia.bookstoredatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.haagahelia.bookstoredatabase.domain.Book;
import fi.haagahelia.bookstoredatabase.domain.BookRepository;
import fi.haagahelia.bookstoredatabase.domain.CategoryRepository;


@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository; 
	
	@RequestMapping("/bookList")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "bookList";
	}

	@RequestMapping(value = "/add")
	public String addBook(Model model){
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}     
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book){
	    repository.save(book);
	    return "redirect:bookList";
	}    

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
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
