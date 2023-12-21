package com.olteanuflorin86.sdjpaintro.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

//@NamedQuery(name = "author_find_all", query = "FROM Author")
@NamedQueries({
	@NamedQuery(name = "author_find_all", query = "FROM Author"),
	@NamedQuery(name = "find_by_name", query = "FROM Author a WHERE a.firstName = :first_name AND a.lastName = :last_name")
})
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}