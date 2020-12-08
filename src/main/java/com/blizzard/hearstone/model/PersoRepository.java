package com.blizzard.hearstone.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blizzard.hearstone.model.entity.GameCharacter;

@Repository
public interface PersoRepository extends JpaRepository<GameCharacter, Long>{}