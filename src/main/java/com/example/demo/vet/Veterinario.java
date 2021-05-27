package com.example.demo.vet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity(name = "vets")
public class Veterinario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "first_name")
	private String name;
	@Column(name = "last_name")
	private String last;
	
	
	
	
	@Override
	public String toString() {
		return "Veterinario [id=" + id + ", name=" + name + ", last=" + last + "]";
	}

	public Veterinario(long id) {
		this.id = id;
	}

	public Veterinario(long id, String name, String last) {
		this.id = id;
		this.name = name;
		this.last = last;
	}

	public Veterinario() {
	}

	public Veterinario(String name, String last) {
		super();
		this.name = name;
		this.last = last;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	
	
}
