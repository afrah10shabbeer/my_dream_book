package com.myDreamBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.myDreamBook.model.DreamPlace;

@Repository
public interface MyDreamBookRepository extends JpaRepository<DreamPlace, Long> {

    List<DreamPlace> findByCountry(String country);

    List<DreamPlace> findByCategory(String category);

    List<DreamPlace> findByCountryAndCategory(String country, String category);

    List<DreamPlace> findByFavoriteTrue();

    @Query("SELECT DISTINCT d.country FROM DreamPlace d ORDER BY d.country")
    List<String> findAllCountries();

    @Query("SELECT DISTINCT d.category FROM DreamPlace d ORDER BY d.category")
    List<String> findAllCategories();
}

