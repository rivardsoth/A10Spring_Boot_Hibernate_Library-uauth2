package com.example.a10spring_boot_hibernate_library.repository;

import com.example.a10spring_boot_hibernate_library.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository <Library, Long> {
    List<Library> findByPublisherInAndDescriptionContaining(List<String> publishers, String searchTerm);
    List<Library> findByTitleContainingOrDescriptionContaining(String title, String description);
    List<Library> findByLengthLessThan(int maxLength);
    List<Library> findByLengthGreaterThan(int minLength);
    List<Library> findByLengthBetween(int minLength, int maxLength);

    List<Library> findByLengthLessThanAndDescriptionContaining(int maxLength, String searchTerm);

    List<Library> findByLengthGreaterThanAndDescriptionContaining(int maxLength, String searchTerm);

    void deleteByEanIsbn13(long eanIsbn13);

    List<Library> findByPriceLessThanAndDescriptionContaining(BigDecimal maxPrice, String description);
    List<Library> findByPriceGreaterThanAndDescriptionContaining(BigDecimal minPrice, String description);
    List<Library> findByDescriptionContaining(String searchTerm);

    int countByDescriptionContaining(String searchTerm);

    List<Library> findByPriceLessThanAndLengthLessThanAndPublisherInAndDescriptionContaining(
            BigDecimal maxPrice, int maxLength, List<String> publishers, String searchTerm);

}

