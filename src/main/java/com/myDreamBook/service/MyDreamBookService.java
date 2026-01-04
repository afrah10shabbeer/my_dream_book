package com.myDreamBook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myDreamBook.model.DreamPlace;
import com.myDreamBook.repository.MyDreamBookRepository;

@Service
public class MyDreamBookService {

    @Autowired
    private MyDreamBookRepository dreamRepo;

    // Save DreamPlace (with image blob)
    public void save(DreamPlace dreamPlace) {
        dreamRepo.save(dreamPlace);
    }

    // Fetch DreamPlace by ID (for image retrieval)
    public DreamPlace findById(Long id) {
        return dreamRepo.findById(id).orElse(null);
    }

    public List<DreamPlace> getAllDreamPlaces() {
        return dreamRepo.findAll();
    }

    public boolean markAsFavorite(Long id) {
        DreamPlace place = dreamRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Place not found"));
        
        boolean isFavoriteNow;
        if (place.isFavorite() == false) {
            place.setFavorite(true);
            isFavoriteNow = true;
        }
        else {
            place.setFavorite(false);
            isFavoriteNow = false;
        }
        
        dreamRepo.save(place);
        return isFavoriteNow;
    }

    public List<DreamPlace> getFavorites() {

        List<DreamPlace> dreamPlaces = dreamRepo.findAll();
        List<DreamPlace> favoList = new ArrayList<>();

        for (DreamPlace dreamPlace : dreamPlaces) {
            if (dreamPlace.isFavorite()) {
                favoList.add(dreamPlace);
            }
        }
        System.out.println("my size" + favoList.size());
        return favoList;
    }

    public void deleteById(Long id) {
        dreamRepo.deleteById(id);
    }

    public List<String> findAllCountries() {
        return dreamRepo.findAllCountries();
    }

    public List<String> findAllCategories() {
        return dreamRepo.findAllCategories();
    }

    public List<DreamPlace> filterPlaces(String country, String category) {

        if (country != null && !country.isEmpty() &&
            category != null && !category.isEmpty()) {
            return dreamRepo.findByCountryAndCategory(country, category);
        }

        if (country != null && !country.isEmpty()) {
            return dreamRepo.findByCountry(country);
        }

        if (category != null && !category.isEmpty()) {
            return dreamRepo.findByCategory(category);
        }

        return dreamRepo.findAll(); // no filters
    }


}
