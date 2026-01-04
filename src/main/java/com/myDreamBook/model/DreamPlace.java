package com.myDreamBook.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class DreamPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String country;

    @Column(columnDefinition = "LONGTEXT")
    private String gmapsLink;

    @Lob
    @Column(columnDefinition = "LONGBLOB")   // Store image as BLOB
    private byte[] imageData;

    private String category;
    private boolean visited = false;
    private boolean favorite = false;

    private LocalDateTime createdAt;

    // ----------- Constructor -----------
    public DreamPlace() {
        this.createdAt = LocalDateTime.now();
    }

    // ----------- Getters & Setters -----------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGmapsLink() {
        return gmapsLink;
    }

    public void setGmapsLink(String gmapsLink) {
        this.gmapsLink = gmapsLink;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
