package com.example.a10spring_boot_hibernate_library.controllers.library;

import com.example.a10spring_boot_hibernate_library.entities.Library;
import com.example.a10spring_boot_hibernate_library.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class LibraryPriceFilteredSearchController {
    private LibraryService libraryService;

    @Autowired
    public LibraryPriceFilteredSearchController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/pricefilteredsearch")
    public String searchBooks(@RequestParam("searchTerm") String searchTerm,
                              @RequestParam(value = "price", required = false) String price,
                              Model model) {
        List<Library> books;

        if (price != null) {
            switch (price) {
                case "lessThan40":
                    books = libraryService.searchBooksByPriceAndTerm(new BigDecimal(40), searchTerm);
                    break;
                case "lessThan100":
                    books = libraryService.searchBooksByPriceAndTerm(new BigDecimal(100), searchTerm);
                    break;
                case "moreThan100":
                    books = libraryService.searchBooksByPriceMoreThanAndTerm(new BigDecimal(100), searchTerm);
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

