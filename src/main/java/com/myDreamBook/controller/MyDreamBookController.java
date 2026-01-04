package com.myDreamBook.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.myDreamBook.model.DreamPlace;
import com.myDreamBook.service.MyDreamBookService;


@Controller
public class MyDreamBookController {

    @Autowired
    MyDreamBookService service;

    @GetMapping("/my_dream_book/home")
    public String home() { return "home"; }

    @GetMapping("/my_dream_book/dream_places")
    public ModelAndView getMyDreamPlaces(
        @RequestParam(required = false) String country,
        @RequestParam(required = false) String category) {
        List<DreamPlace> list = service.filterPlaces(country, category);
        ModelAndView mv = new ModelAndView("myDreamPlaces"); 
        mv.addObject("allCountries", service.findAllCountries());
        mv.addObject("allCategories", service.findAllCategories());
        mv.addObject("dreamPlaces", list);
        return mv;
    }

    @GetMapping("/my_dream_book/add_dream_place")
    public String addDreamPlace() {
        return "addDreamPlace";
    }

    @PostMapping("/save_update_card")
    public String saveOrUpdateCard(
            @RequestParam(required = false) Long id,
            @RequestParam("title") String title,
            @RequestParam("country") String country,
            @RequestParam("gmapsLink") String gmapsLink,
            @RequestParam("image") MultipartFile imageFile,
            @RequestParam("category") String category
    ) throws Exception {

        DreamPlace place;

        if (id != null) {
            // Existing card → fetch and update
            place = service.findById(id);

            place.setTitle(title);
            place.setCountry(country);
            place.setGmapsLink(gmapsLink);

            // Update image only if a new one is uploaded
            if (imageFile != null && !imageFile.isEmpty()) {
                place.setImageData(imageFile.getBytes());
            }

            place.setCategory(category);

        } else {
            // New card
            place = createAndSetCardParams(title, country, gmapsLink, imageFile, category);
        }

        service.save(place); // handles both create & update
        return "redirect:/my_dream_book/dream_places";
    }


    @RequestMapping("/deleteMyPlace/{id}")
    public String deleteMyPlace(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "redirect:/my_dream_book/dream_places";
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        DreamPlace place = service.findById(id);

        if (place == null || place.getImageData() == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(place.getImageData());
    }

    private DreamPlace createAndSetCardParams(
        String title,
        String country,
        String gmapsLink,
        MultipartFile imageFile,
        String category
    ) throws IOException {

        DreamPlace place = new DreamPlace();
        place.setTitle(title);
        place.setCountry(country);
        place.setGmapsLink(gmapsLink);

        if (imageFile != null && !imageFile.isEmpty()) {
            place.setImageData(imageFile.getBytes());
        }
        place.setCategory(category);

        return place;
    }

    @GetMapping("/my_dream_book/dream_places/search")
    @ResponseBody
    public List<DreamPlace> getFilteredDreamPlaces(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String category
    ) {
        return service.filterPlaces(country, category);
    }

    @PostMapping("/my_dream_book/favorite/{id}")
    public String markFavorites(@PathVariable Long id) {
        boolean isFavoriteNow = service.markAsFavorite(id);
        if (isFavoriteNow == true) {
            return "redirect:/my_dream_book/favorites";
        } else {
            return "redirect:/my_dream_book/dream_places";
        }
        
    }
    
    @GetMapping("/my_dream_book/favorites")
    public ModelAndView getMyFavorites() {

        List<DreamPlace> list = service.getFavorites();

        ModelAndView mv = new ModelAndView("favorites");
        mv.addObject("favoritePlaces", list); 

        return mv;
    }

}
