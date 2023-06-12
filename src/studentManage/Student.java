package studentManage;

public class Student {

	private String name;
	private String gender;
	private String dob;
	private int batch;
	
	public Student(String name, String gender, String dob, int batch) {
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.batch = batch;
	}
	
	public String getName() {
		return name;
	}
	
	public String getGender() {
		return gender;
	}
	
	public String getDob() {
		return dob;
	}
	
	public int getBatch() {
		return batch;
	}
	
}

