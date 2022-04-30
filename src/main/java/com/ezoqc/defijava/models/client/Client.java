package com.ezoqc.defijava.models.client;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CLIENT")
public class Client implements Serializable {
    
	private static final long serialVersionUID = 1L;
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private Date birthdate;
    
    public Client() {}

    public Client(Long id, String firstName, String lastName, String socialSecurityNumber, Date birthdate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
		this.birthdate = birthdate;
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

    @JsonIgnore
    public float getAge() {
    	final int DAYS_YEAR = 365;
    	final float MILLISECONDS_DAY = (24 * 60 * 60 * 1000);
    	
    	float diffTime = (new Date().getTime() - this.birthdate.getTime());
    	float diffDates = diffTime / MILLISECONDS_DAY;
    	float age = (diffDates / DAYS_YEAR);
    	return age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
    
    public static class ClientBuilder {
		private Long id;
		private String firstName;
		private String lastName;
		private String socialSecurityNumber;
		private Date birthdate;
		
		public ClientBuilder id(Long id) {
			this.id = id;
			return this;
		}
		
		public ClientBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public ClientBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public ClientBuilder socialSecurityNumber(String socialSecurityNumber) {
			this.socialSecurityNumber = socialSecurityNumber;
			return this;
		}
		
		public ClientBuilder birthdate(Date birthdate) {
			this.birthdate = birthdate;
			return this;
		}
		
		public Client build() {
			return new Client(id,firstName, lastName, socialSecurityNumber, birthdate);
		}
	}
}
