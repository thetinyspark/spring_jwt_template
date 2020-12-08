package com.blizzard.hearstone.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column
	private String name; 
	
	@Column
	private String password; 
	
	@Column
	private String role;
	
	public User() {}
	
	 
	
	public String getPassword() 				{	return password;					}
	public void setPassword(String password) 	{	this.password = password;			}

	public String getRole() 					{	return role;						}
	public void setRole(String role) 			{	this.role = role;					}
	
	public String getName() 					{	return name;						}
	public Long getId() 						{	return id; 							}
	
	public void setName(String name) 			{	this.name 		= name;				}
	public void setId(Long id) 					{	this.id 		= id;				}
}
