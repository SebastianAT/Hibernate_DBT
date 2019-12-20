package org.hibernate_dbt.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "LECTURER",
   uniqueConstraints = { @UniqueConstraint(columnNames = { "LecturerID" }) })
public class Lecturer {
	private String name, surname, birthday, address;
	private int lecturerID;
	
	public Lecturer() {	
	}
	
	public Lecturer(String name, String surname, String birthday, String address) {
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.address = address;
	}
	
	@Id
	@GenericGenerator(name="kaugen" , strategy="increment")
	@GeneratedValue(generator="kaugen")
	@Column(name = "LecturerID")
	public int getLecturerID() {
		return this.lecturerID;
	}

	public void setLecturerID(int lecturerID) {
		this.lecturerID = lecturerID;
	}

	@Column(name = "NAME", length = 30, nullable = false)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SURNAME", length = 30, nullable = false)
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "BIRTHDAY", nullable = false)
	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "ADDRESS", length = 30, nullable = false)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
