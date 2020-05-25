package pojo;

import java.util.List;

public class Courses {
private List<CourseInfo> webAutomation;
private List<CourseInfo> api;
private List<CourseInfo> mobile;
public List<CourseInfo> getWebAutomation() {
	return webAutomation;
}
public void setWebAutomation(List<CourseInfo> webAutomation) {
	this.webAutomation = webAutomation;
}
public List<CourseInfo> getApi() {
	return api;
}
public void setApi(List<CourseInfo> api) {
	this.api = api;
}
public List<CourseInfo> getMobile() {
	return mobile;
}
public void setMobile(List<CourseInfo> mobile) {
	this.mobile = mobile;
}
}
