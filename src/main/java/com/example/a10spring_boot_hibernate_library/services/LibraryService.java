package com.example.a10spring_boot_hibernate_library.services;

import com.example.a10spring_boot_hibernate_library.entities.Library;
import com.example.a10spring_boot_hibernate_library.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibraryService {

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public List<Library> getAllBooks() {
        return libraryRepository.findAll();
    }

    public Optional<Library> getBookByEanIsbn13(long eanIsbn13) {
        return libraryRepository.findById(eanIsbn13);
    }

    public Library createBook(Library book) {
        return libraryRepository.save(book);
    }

    public void updateBook(Library book) {
        libraryRepository.save(book);
    }


    public List<Library> searchBooksByPublishersAndDescription(String searchTerm, List<String> publishers) {
        return libraryRepository.findByPublisherInAndDescriptionContaining(publishers, searchTerm);
    }

    public List<Library> searchBooksByTitleAndDescription(String searchTerm) {
        return libraryRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }



    public List<Library> searchBooksByLengthAndTerm(int maxLength, String searchTerm) {
        return libraryRepository.findByLengthLessThanAndDescriptionContaining(maxLength, searchTerm);
    }

    public List<Library> searchBooksByLengthAndTermMore(int maxLength, String searchTerm) {
        return libraryRepository.findByLengthGreaterThanAndDescriptionContaining(maxLength, searchTerm);
    }

    public List<Library> searchBooksByLengthBetween(int minLength, int maxLength) {
        return libraryRepository.findByLengthBetween(minLength, maxLength);
    }

    public void deleteBookByEanIsbn13(long eanIsbn13) {
        libraryRepository.deleteById(eanIsbn13);
    }

    public List<Library> searchBooksByPriceAndTerm(BigDecimal maxPrice, String searchTerm) {
        return libraryRepository.findByPriceLessThanAndDescriptionContaining(maxPrice, searchTerm);
    }

    public List<Library> searchBooksByPriceMoreThanAndTerm(BigDecimal minPrice, String searchTerm) {
        return libraryRepository.findByPriceGreaterThanAndDescriptionContaining(minPrice, searchTerm);
    }

    public List<Library> searchBooksByDescription(String searchTerm) {
        return libraryRepository.findByDescriptionContaining(searchTerm);
    }

    public int getResultCount(String searchTerm) {
        return libraryRepository.countByDescriptionContaining(searchTerm);
    }


    public List<Library> searchBooksByFilters(String searchTerm, String price, String length, String publisher) {
        // Validate and convert the price parameter
        BigDecimal priceValue;
        try {
            priceValue = new BigDecimal(price);
        } catch (NumberFormatException e) {
            // Handle the case where the price parameter is not a valid numeric value
            throw new IllegalArgumentException("Invalid price value: " + price);
        }

        // Convert the length parameter to an integer value
        int lengthValue;
        try {
            lengthValue = Integer.parseInt(length);
        } catch (NumberFormatException e) {
            // Handle the case where the length parameter cannot be parsed as an integer
            throw new IllegalArgumentException("Invalid length value: " + length);
        }

        return libraryRepository.findByPriceLessThanAndLengthLessThanAndPublisherInAndDescriptionContaining(
                priceValue, lengthValue, List.of(publisher), searchTerm);
    }


}
