package com.example.football.dto;

import java.util.Objects;

public class PlayerDTO {
    
    private String firstName;
    
    private String secondName;
    
    private Double rating;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerDTO playerDTO = (PlayerDTO) o;
        return Objects.equals(firstName, playerDTO.firstName) && Objects.equals(secondName, playerDTO.secondName) &&
            Objects.equals(rating, playerDTO.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, rating);
    }
}
