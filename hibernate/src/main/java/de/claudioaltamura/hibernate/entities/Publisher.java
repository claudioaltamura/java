package de.claudioaltamura.hibernate.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Publisher {
	
	@GeneratedValue
	@Id
	private Long id;

	private String name = "";
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="PUBLISHER_BOOK",
	        joinColumns = @JoinColumn( name="PUBLISHER_ID"),
	        inverseJoinColumns = @JoinColumn( name="BOOK_ID"))
	private List<Book> books = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
