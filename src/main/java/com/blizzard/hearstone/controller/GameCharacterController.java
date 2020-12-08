package com.blizzard.hearstone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.blizzard.hearstone.model.CardRepository;
import com.blizzard.hearstone.model.PersoRepository;
import com.blizzard.hearstone.model.entity.Card;
import com.blizzard.hearstone.model.entity.GameCharacter;

@CrossOrigin(origins= {"*"})
@RestController
public class GameCharacterController{
	
	@Autowired
	PersoRepository repo;
	
	@Autowired
	CardRepository repocard;
	
	@GetMapping("character")
	public List<GameCharacter> getAll() throws Exception{
		try {
			return repo.findAll();
		}
		catch( Exception error ) {
			throw new ResponseStatusException( HttpStatus.INTERNAL_SERVER_ERROR, "error" );
		}
	}
	
	@GetMapping("character/{id}")
	public GameCharacter getById(@PathVariable("id") Long id) throws Exception{
		try {
			return repo.findById(id).get();
		}
		catch( Exception error ) {
			throw new ResponseStatusException( HttpStatus.NOT_FOUND, "not found" );
		}
	}
	
	@PostMapping("character")
	public GameCharacter create(@RequestBody GameCharacter perso) {
		
		Card blueEyesWhiteDragon  = new Card(); 
		blueEyesWhiteDragon.setName("Blue Eyes White Dragon");
		blueEyesWhiteDragon.setCharacter(perso);
		perso.getCards().add( blueEyesWhiteDragon );
		
		repo.saveAndFlush(perso);
		repocard.saveAndFlush(blueEyesWhiteDragon);
		
		
		
		return perso;
	}
	
	@PutMapping("character/{id}")
	public GameCharacter update(@PathVariable("id") Long id, @RequestBody GameCharacter dto ) {
		
		GameCharacter perso = repo.findById(id).get();
		
		if( perso != null ) {
			perso.setLife(dto.getLife());
			perso.setMana(dto.getMana());
			perso.setStamina(dto.getStamina());
			perso.setStrength(dto.getStrength());
			perso.setName(dto.getName());
		}
		
		return repo.save(perso);
	}
	
	@DeleteMapping("character/{id}")
	public boolean delete(@PathVariable("id") Long id)  {
		
		try {
			if( repo.findById(id).get() != null ) {
				repo.deleteById(id);
				return true;
			}
			else {
				return false;
			}
		}
		catch( Exception error ) {
			return false;
		}
		
	}
	
	
	// GET /character
	// GET /character/1
	// POST /character
	// PUT /character/1
	// DELETE /character/1
	
}