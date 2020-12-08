package com.blizzard.hearstone.model.entity;

public interface IGameCharacter {
	
	public String getName();
	public void setName(String name); 
	
	public Long getId(); 
	public void setId(Long id);
	
	public int getLife();
	public void setLife(int life); 

	public int getMana();
	public void setMana(int mana); 

	public int getStamina();
	public void setStamina(int stamina);
	
	public int getStrength();
	public void setStrength(int strength); 
	
	public void atk( IGameCharacter enemy );

}
