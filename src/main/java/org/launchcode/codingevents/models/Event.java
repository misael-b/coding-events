package org.launchcode.codingevents.models;

import jakarta.validation.constraints.*;

import java.util.Objects;

public class Event {
    @Size(min = 3, max=50, message = "Name must be between 3 and 50 characters!")
    @NotBlank
    private String name;
    @Size(max=500, message = "Description too long.")
    private String description;
    @NotBlank
    @Email(message = "Invalid Email. Try again")
    private String contactEmail;

    @NotBlank(message = "Need to include a location")
    private String location;

    @AssertTrue(message = "Must be able to attend")
    private Boolean canAttend;

    @Min(message = "Number of attendees must be one or more.", value = 0)
    private int numberOfAttendees;
    private int id;
    private static int nextID = 1;

    public Event(String name, String description, String contactEmail, String location, Boolean canAttend, int numberOfAttendees) {
        this();
        this.name = name;
        this.description = description;
        this.contactEmail=contactEmail;
        this.location = location;
        this.canAttend = canAttend;
        this.numberOfAttendees = numberOfAttendees;

    }
    public Event(){
        this.id = nextID;
        nextID++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getCanAttend() {
        return canAttend;
    }

    public void setCanAttend(Boolean canAttend) {
        this.canAttend = canAttend;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
