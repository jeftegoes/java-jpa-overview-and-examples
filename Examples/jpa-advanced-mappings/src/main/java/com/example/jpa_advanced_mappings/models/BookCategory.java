package com.example.jpa_advanced_mappings.models;

import jakarta.persistence.*;

@Entity
@Table(name = "book_category")
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    public BookCategory() {
    }

    public BookCategory(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "BookCategory{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}