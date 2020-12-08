package com.blizzard.hearstone.model.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class GameCharacter implements IGameCharacter {
	
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column
	private int stamina;
	
	@Column
	private int life;
	
	@Column
	private int mana;
	
	@Column
	private int strength;
	
	@OneToMany( cascade=CascadeType.ALL  , mappedBy = "character" )
	private List<Card> cards;
	
	@Column
	private String name; 
	
	{
		this.setName("no_name");
		this.setLife(0);
		this.setMana(0);
		this.setStamina(0);
		this.setStrength(0);
		this.setCards(new ArrayList<Card>());
	}
	

	public List<Card> getCards()			{ 	return this.cards; 			}
	public String getName() 				{	return name;				}
	public Long getId() 					{	return id; 					}
	public int getStrength() 				{	return strength; 			}
	public int getLife() 					{	return life;				}
	public int getMana() 					{	return mana;				}
	public int getStamina() 				{	return stamina;				}
	
	public void setCards(List<Card> cards)  { 	this.cards		= cards; 			}
	public void setName(String name) 		{	this.name 		= name;				}
	public void setId(Long id) 				{	this.id 		= id;				}
	public void setStamina(int v) 			{	this.stamina 	= (v<0) ? 0 : v;	}
	public void setStrength(int v) 			{ 	this.strength 	= (v<0) ? 0 : v;	}
	public void setLife(int v) 				{	this.life 		= (v<0) ? 0 : v;	}
	public void setMana(int v) 				{	this.mana 		= (v<0) ? 0 : v;	}
	
	
	
	
	@Override
	public String toString() {
		return "GameCharacter [stamina=" + stamina + 
								", life=" + life + 
								", mana=" + mana + 
								", strength=" + strength+ 
								", name=" + name + 
								"]";
	}

	public void atk( IGameCharacter enemy ) {
		int dmg = 2;
		enemy.setLife(enemy.getLife() - dmg );
	}

}
