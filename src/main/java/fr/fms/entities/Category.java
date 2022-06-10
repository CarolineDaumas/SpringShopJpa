package fr.fms.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@OneToMany (mappedBy = "category")private Collection<Article>articles;
	
	
	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public Category(String name) {
		super();
		this.name = name;
	}

	

	public Category() {
		super();
	}



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



	@Override
	public String toString() {
		return "" + id + "  " + name + "";
	}
	
	
	

}
