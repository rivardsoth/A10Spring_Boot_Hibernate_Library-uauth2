package com.example.a10spring_boot_hibernate_library.controllers.library;

import com.example.a10spring_boot_hibernate_library.entities.Library;
import com.example.a10spring_boot_hibernate_library.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LibraryLengthFilteredSearchController {
    private LibraryService libraryService;

    @Autowired
    public LibraryLengthFilteredSearchController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/lengthfilteredsearch")
    public String searchBooks(@RequestParam("searchTerm") String searchTerm,
                              @RequestParam(value = "length", required = false) String length,
                              Model model) {
        List<Library> books;

        if (length != null) {
            switch (length) {
                case "lessThan250":
                    books = libraryService.searchBooksByLengthAndTerm(250, searchTerm);
                    break;
                case "lessThan500":
                    books = libraryService.searchBooksByLengthAndTerm(500, searchTerm);
                    break;
                case "moreThan500":
                    books = libraryService.searchBooksByLengthAndTermMore(500, searchTerm);
                    break;
                default:
                    books = libraryService.searchBooksByTitleAndDescription(searchTerm);
                    break;
            }
        } else {
            books = libraryService.searchBooksByTitleAndDescription(searchTerm);
        }

        model.addAttribute("books", books);
        return "results";
    }
}
