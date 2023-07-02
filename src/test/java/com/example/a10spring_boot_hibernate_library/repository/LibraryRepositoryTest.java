package com.example.a10spring_boot_hibernate_library.repository;

import com.example.a10spring_boot_hibernate_library.entities.Library;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LibraryRepositoryTest {

    @Autowired
    private LibraryRepository libraryRepository;


    @Test
    public void testFindByLengthBetween() {
        // Perform the search
        List<Library> result = libraryRepository.findByLengthBetween(600, 900);

        // Verify the result
        assertEquals(64, result.size());
        Library library = result.get(0);
        assertEquals(9780070570641L, library.getEanIsbn13());
        assertEquals("Modern Processor Design", library.getTitle());
    }

    @Test
    public void testFindByLengthLessThanAndDescriptionContaining() {
        // Perform the search
        List<Library> result = libraryRepository.findByLengthLessThanAndDescriptionContaining(700, "microarchitectural");

        // Verify the result
        assertEquals(1, result.size());
        Library library = result.get(0);
        assertEquals(9780070570641L, library.getEanIsbn13());
        assertEquals("Modern Processor Design", library.getTitle());
    }

    @Test
    public void testFindByLengthGreaterThanAndDescriptionContaining() {
        // Perform the search
        List<Library> result = libraryRepository.findByLengthGreaterThanAndDescriptionContaining(900, "database");

        // Verify the result
        assertEquals(4, result.size());
        Library library = result.get(0);
        assertEquals(9780072465631L, library.getEanIsbn13());
        assertEquals("Database Management Systems", library.getTitle());
    }

    @Test
    public void testFindByPublisherInAndDescriptionContaining() {
        // Perform the search
        List<Library> result = libraryRepository.findByPublisherInAndDescriptionContaining(
                Arrays.asList("McGrawHill Higher Education", "McGraw-Hill Science/Engineering/Math"), "processor");

        // Verify the result
        assertEquals(1, result.size());
        Library library = result.get(0);
        assertEquals(9780070570641L, library.getEanIsbn13());
        assertEquals("Modern Processor Design", library.getTitle());
    }

    @Test
    public void testFindByTitleContainingOrDescriptionContaining() {
        // Perform the search
        List<Library> result = libraryRepository.findByTitleContainingOrDescriptionContaining("Database", "applications");

        // Verify the result
        assertEquals(49, result.size());
        assertTrue(result.stream().anyMatch(l -> l.getEanIsbn13() == 9780072424348L));
        assertTrue(result.stream().anyMatch(l -> l.getEanIsbn13() == 9780072465631L));
    }

    @Test
    public void testFindByPriceLessThanAndDescriptionContaining() {
        // Perform the search
        BigDecimal maxPrice = new BigDecimal("90.000");
        String description = "processor";
        List<Library> result = libraryRepository.findByPriceLessThanAndDescriptionContaining(maxPrice, description);

        // Verify the result
        assertEquals(5, result.size());
        Library library = result.get(0);
        assertEquals(9780070570641L, library.getEanIsbn13());
        assertEquals("Modern Processor Design", library.getTitle());
    }

    @Test
    public void testFindByPriceGreaterThanAndDescriptionContaining() {
        // Perform the search
        BigDecimal minPrice = new BigDecimal("130.000");
        String description = "database";
        List<Library> result = libraryRepository.findByPriceGreaterThanAndDescriptionContaining(minPrice, description);

        // Verify the result
        assertEquals(3, result.size());
        Library library = result.get(0);
        assertEquals(9780072465631L, library.getEanIsbn13());
        assertEquals("Database Management Systems", library.getTitle());
    }

    @Test
    public void testFindByDescriptionContaining() {
        // Perform the search
        String searchTerm = "mathematics";
        List<Library> result = libraryRepository.findByDescriptionContaining(searchTerm);

        // Verify the result
        assertEquals(25, result.size());
        assertTrue(result.stream().anyMatch(library -> library.getEanIsbn13() == 9780072424348L));
        assertFalse(result.stream().anyMatch(library -> library.getEanIsbn13() == 9780072952971L));
    }

    @Test
    public void testCountByDescriptionContaining() {
        // Perform the count
        String searchTerm = "database";
        int count = libraryRepository.countByDescriptionContaining(searchTerm);

        // Verify the count
        assertEquals(11, count);
    }


}
