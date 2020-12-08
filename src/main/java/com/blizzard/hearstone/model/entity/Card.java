package com.blizzard.hearstone.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Card {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;;
	
	@Column
	private String name; 
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="character_id", nullable = false)
	public GameCharacter character; 
	
	
	public GameCharacter getCharacter() 		{ return character;				}
	public void setCharacter(GameCharacter c)	{ this.character = c; 			}

	
	public String getName() 					{	return name;				}
	public Long getId() 						{	return id; 					}
	
	public void setName(String name) 			{	this.name 		= name;		}
	public void setId(Long id) 					{	this.id 		= id;		}
	
	


}
