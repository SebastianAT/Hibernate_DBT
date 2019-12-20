package org.hibernate_dbt.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name = "STUDENT")
public class Student {
	private int studentID;
	private String name, surname, address;
	private String birthday;
	private Set<Course> courses = new HashSet<Course>(0);
	
	public Student() {
	}
	
	public Student(String name, String surname, String address, String birthday) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.birthday = birthday;
	}
	
	public Student(String name, String surname, String address, String birthday, Set<Course> courses) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.birthday = birthday;
		this.courses = courses;
	}
	
	@Id
	@GenericGenerator(name="kaugen" , strategy="increment")
	@GeneratedValue(generator="kaugen")
	@Column(name = "StudentID")
	public int getStudentID() {
		return studentID;
	}
	
	public void setStudentID(int studentID) {
	    this.studentID = studentID;
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

	@Column(name = "ADDRESS", length = 30, nullable = false)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "BIRTHDAY", nullable = false)
	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String string) {
		this.birthday = string;
	}

	//{CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH}CascadeType.ALL
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "STUDENT_COURSE", joinColumns = { @JoinColumn(name = "STUDENTID") }, inverseJoinColumns = { @JoinColumn(name = "COURSEID") })
	//@ManyToMany(mappedBy = "students") 
	public Set<Course> getCourses(){
		return this.courses;
	}
	
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
}