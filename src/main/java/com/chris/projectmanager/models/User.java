package com.chris.projectmanager.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="username is required!")
	@Size(min=3, max=30, message="username must be between 3 and 30 characters long")
	private String userName;
	
	@NotEmpty(message="email is required!")
	@Email(message="please enter a valid email!")
	private String email;
	
	@NotEmpty(message="password is required!")
	@Size(min=8, max=100, message="password must be between 8 and 100 characters long")
	private String password;
	
	@Transient
	@NotEmpty(message="confirm password")
	@Size(min=8, max=100, message="confirm password must be between 8 and 100 characters long")
	private String confirm;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_projects",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "project_id")
			)
	private List<Project> projects;
	
	@Column(updatable=false)
	@OneToMany(mappedBy="lead", fetch=FetchType.LAZY)
	private List<Project> projectsLed;
	
	public User() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Project> getProjectsLed() {
		return projectsLed;
	}

	public void setProjectsLed(List<Project> projectsLed) {
		this.projectsLed = projectsLed;
	}

	@PrePersist 
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate 
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	

}

