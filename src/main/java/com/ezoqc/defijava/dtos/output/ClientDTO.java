package com.ezoqc.defijava.dtos.output;

import java.util.Date;

public class ClientDTO {
	private Long id;
	private String firstName;
	private String lastName;
	private String socialSecurityNumber;
	private Date birthdate;
	private Float age;
	
	public ClientDTO() {}

	public ClientDTO(Long id, String firstName, String lastName, String socialSecurityNumber, Date birthdate,
			Float age) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
		this.birthdate = birthdate;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Float getAge() {
		return age;
	}

	public void setAge(Float age) {
		this.age = age;
	}
	
	public String getFullName() {
		return this.firstName.concat(" ").concat(lastName);
	}
	
	public static class ClientDTOBuilder {
		private Long id;
		private String firstName;
		private String lastName;
		private String socialSecurityNumber;
		private Date birthdate;
		private Float age;
		
		public ClientDTOBuilder id(Long id) {
			this.id = id;
			return this;
		}
		
		public ClientDTOBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public ClientDTOBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public ClientDTOBuilder socialSecurityNumber(String socialSecurityNumber) {
			this.socialSecurityNumber = socialSecurityNumber;
			return this;
		}
		
		public ClientDTOBuilder birthdate(Date birthdate) {
			this.birthdate = birthdate;
			return this;
		}
		
		public ClientDTOBuilder age(Float age) {
			this.age = (float) Math.floor(age);
			return this;
		}
		
		public ClientDTO build() {
			return new ClientDTO(id,firstName, lastName, socialSecurityNumber, birthdate, age);
		}
	}
}
