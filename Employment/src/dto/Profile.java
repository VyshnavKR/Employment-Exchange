package dto;

public class Profile {
	
	private int profile_id;
	private int user_id;
	private float experience;
	private float salary;
	private String firstName;
	private String lastName;

	public Profile(String firstName, String lastName, float experience, float salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.experience = experience;
		this.salary = salary;
	}
	public Profile(int profile_id, int user_id, float experience, float salary, String firstName, String lastName) {
		super();
		this.profile_id = profile_id;
		this.user_id = user_id;
		this.experience = experience;
		this.salary = salary;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public Profile(int user_id, float experience, float salary, String firstName, String lastName) {
		super();
		this.user_id = user_id;
		this.experience = experience;
		this.salary = salary;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public int getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public float getExperience() {
		return experience;
	}
	public void setExperience(float experience) {
		this.experience = experience;
	}
	public float getSalary() {
		return salary;
	}
	public void setSalary(float salary) {
		this.salary = salary;
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


	
}
