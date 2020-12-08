package com.blizzard.hearstone.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blizzard.hearstone.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByPassword(String password);
	public User findByName(String username);
}