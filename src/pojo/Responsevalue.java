package pojo;

public class Responsevalue {
private String url;
private String services;
private String expertise;
private Courses courses;
private String linkedIn;
private String instructor;
//alt+shift+s to generate getter setter
//this pojo is for nested json
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getServices() {
	return services;
}
public void setServices(String services) {
	this.services = services;
}
public String getExpertise() {
	return expertise;
}
public void setExpertise(String expertise) {
	this.expertise = expertise;
}
public Courses getCourses() {
	return courses;
}
public void setCourses(Courses courses) {
	this.courses = courses;
}
public String getlinkedIn() {
	return linkedIn;
}
public void setlinkedIn(String linkedIn) {
	this.linkedIn = linkedIn;
}
public String getInstructor() {
	return instructor;
}
public void setInstructor(String instructor) {
	this.instructor = instructor;
}
}
