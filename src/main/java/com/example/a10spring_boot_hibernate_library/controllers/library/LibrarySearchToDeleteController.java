package com.example.a10spring_boot_hibernate_library.controllers.library;

import com.example.a10spring_boot_hibernate_library.entities.Library;
import com.example.a10spring_boot_hibernate_library.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LibrarySearchToDeleteController {
    private LibraryService libraryService;

    @Autowired
    public LibrarySearchToDeleteController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/searchtodelete")
    public String searchBooksByISBN() {

        return "book";
    }

    @GetMapping("/book/delete")
    public String deleteBookByIsbn(@RequestParam("isbn") String isbn, Model model) {
        Optional<Library> book = libraryService.getBookByEanIsbn13(Long.parseLong(isbn));
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "book";
        } else {
            model.addAttribute("errorMessage", "Book not found");
            return "error-page";
        }
    }

    @PostMapping("/book/delete/{eanIsbn13}")
    public String deleteBookByEanIsbn13(@PathVariable long eanIsbn13) {
        libraryService.deleteBookByEanIsbn13(eanIsbn13);
        return "redirect:/search-to-delete.html";
    }
}
