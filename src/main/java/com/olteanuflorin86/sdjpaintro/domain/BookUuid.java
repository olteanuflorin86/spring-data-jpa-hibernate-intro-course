package com.olteanuflorin86.sdjpaintro.domain;

import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BookUuid {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", updatable = false, nullable = false)
	private UUID id;
	
	private String title;
	private String isbn;
	private String publisher;
	
	public BookUuid() {
	}
	
	public BookUuid(String title, String isbn, String publisher) {
//		super();
		this.title = title;
		this.isbn = isbn;
		this.publisher = publisher;
	}
	
	public BookUuid(UUID id, String title, String isbn, String publisher) {
//		super();
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		this.publisher = publisher;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookUuid other = (BookUuid) obj;
		return Objects.equals(id, other.id);
	}

}
