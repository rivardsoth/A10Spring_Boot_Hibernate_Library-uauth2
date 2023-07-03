package com.example.a10spring_boot_hibernate_library.controllers.library;

import com.example.a10spring_boot_hibernate_library.entities.Library;
import com.example.a10spring_boot_hibernate_library.services.LibraryService;
import com.example.a10spring_boot_hibernate_library.entities.Client;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LibraryCombinedFilteredSearchController {
    private final LibraryService libraryService;

    @Autowired
    public LibraryCombinedFilteredSearchController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Autowired
    private HttpServletRequest request;
    @GetMapping("/combinedsearch")
    public String searchBooks(
            @RequestParam("searchTerm") String searchTerm,
            @RequestParam(value = "price", required = false) String price,
            @RequestParam(value = "length", required = false) String length,
            @RequestParam(value = "publisher", required = false) String publisher,
            Model model) {
        List<Library> books;

        // Perform the combined search
        books = libraryService.searchBooksByFilters(searchTerm, price, length, publisher);

        HttpSession session = request.getSession();
        Client authenticatedClient = (Client) session.getAttribute("authenticatedClient");

        model.addAttribute("books", books);
        model.addAttribute("authenticatedClient", authenticatedClient);

        String firstName = (authenticatedClient != null) ? authenticatedClient.getFirstName() : "empty";
        System.out.println("Authenticated Client First Name: " + firstName);
        System.out.println("Authenticated Client: " + authenticatedClient);


        return "results";
    }
}
