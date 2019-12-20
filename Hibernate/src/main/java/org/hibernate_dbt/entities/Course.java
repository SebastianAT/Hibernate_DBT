package org.hibernate_dbt.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "COURSE")
public class Course {
	private int courseID;
	private String courseName;
	private Set<Student> students = new HashSet<Student>(0);
	private Lecturer lecturers;
	
	//emtpy contructor because of peristent 
	public Course() {
	}
	
	public Course(String courseName, Lecturer lecturers) {
		this.courseName = courseName;
		this.lecturers = lecturers;
	}

	@Id
	@GenericGenerator(name="kaugen" , strategy="increment")
	@GeneratedValue(generator="kaugen")
	@Column(name = "COURSEID")
	public int getCourseID() {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
		return this.courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	@Column(name = "COURSENAME", length = 30, nullable = false)
	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@ManyToOne(cascade = CascadeType.ALL)	
	public Lecturer getLecturer() {
		return this.lecturers;
	}
	
	public void setLecturer(Lecturer lecturers) {
		this.lecturers = lecturers;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
	//@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	//@JoinTable(name = "STUDENT_COURSE", joinColumns = { @JoinColumn(name = "COURSEID") }, inverseJoinColumns = { @JoinColumn(name = "STUDENTID") })
	public Set<Student> getStudents(){
		return this.students;
	}
	
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
