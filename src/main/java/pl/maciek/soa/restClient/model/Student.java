package pl.maciek.soa.restClient.model;

import java.util.List;

public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private String albumNo;
	private List<String> subjects;

	public Student() {
		super();
	}
	public Student(String firstName, String lastName, String albumNo,
				   List<String> subjects) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.albumNo = albumNo;
		this.subjects = subjects;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getAlbumNo() {
		return albumNo;
	}
	public void setAlbumNo(String albumNo) {
		this.albumNo = albumNo;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public List<String> getSubjects() {
		return subjects;
	}


}
