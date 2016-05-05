package de.claudioaltamura.hibernate.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Author {

	@GeneratedValue
	@Id
	private Long id;

	private String name = "";

	@ManyToMany(fetch=FetchType.EAGER , cascade=CascadeType.ALL)
	@JoinTable(name = "author_book", joinColumns = { 
			@JoinColumn(name = "AUTHOR_ID", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "BOOK_ID", 
					nullable = false, updatable = false) })
	private List<Book> books;
	
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
