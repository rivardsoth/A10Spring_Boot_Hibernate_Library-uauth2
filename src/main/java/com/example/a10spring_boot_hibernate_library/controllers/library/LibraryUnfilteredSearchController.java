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
public class LibraryUnfilteredSearchController {
    private LibraryService libraryService;

    @Autowired
    public void LibraryUnfilteredSearchController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/search-no-filter")
    public String searchBooksWithoutFilter(@RequestParam("searchTerm") String searchTerm, Model model) {
        List<Library> books = libraryService.searchBooksByDescription(searchTerm);
        model.addAttribute("books", books);
        return "results";
    }

}





