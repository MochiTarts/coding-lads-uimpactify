package com.utsc.project_coding_lads.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = User.TABLE_NAME)
//@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseDataEntity {
	
	public static final String TABLE_NAME = "USER";
	
	private String firstName;
	private String lastName;
	private String username;
	private String hashedPassword;
	private SocialInitiative socialInit;
	private Role role;
	private Integer age;
	
	
	@ManyToOne(optional = true, cascade = CascadeType.ALL) //Feel free to remove cascade, I only needed it for testing my signup api controller
	@JoinColumn(name="socialinit_id")
	public SocialInitiative getSocialInitiative() {
		return socialInit;
	}
	public void setSocialInitiative(SocialInitiative socialInit) {
		this.socialInit = socialInit;
	}
	@ManyToOne(optional = true)
	@JoinColumn(name="role_id")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Column(name="first_name", nullable = false, length = 32)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="last_name", nullable = false, length = 32)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="user_pw", nullable = false, length = 64)
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	@Column(name="username", nullable = false, length = 32)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "age")
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	
}
