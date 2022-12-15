package org.example.models;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private int personId;

    @NotEmpty(message = "Name field can`t be empty")
    @Size(min = 2, max = 150, message = "full name should be more than 2  and less than 100 symbols")
    private String personName;
    @Min(value = 1900, message = "Year should be greater than 1900")
    private int yearOfBirth;

    public Person() {
    }

    public Person(int personId, String personName, int yearOfBirth) {
        this.personName = personName;
        this.yearOfBirth = yearOfBirth;
        this.personId = personId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }


}
