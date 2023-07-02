package com.example.a10spring_boot_hibernate_library.controllers.library;

import com.example.a10spring_boot_hibernate_library.entities.Library;
import com.example.a10spring_boot_hibernate_library.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class LibraryUnfilteredSearchAJAXController {
    private LibraryService libraryService;

    @Autowired
    public void LibraryUnfilteredSearchAJAXController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/get-result-count")
    @ResponseBody
    public Map<String, Integer> getResultCount(@RequestParam("searchTerm") String searchTerm) {
        int resultCount = libraryService.getResultCount(searchTerm);
        Map<String, Integer> response = new HashMap<>();
        response.put("count", resultCount);
        return response;
    }

}

