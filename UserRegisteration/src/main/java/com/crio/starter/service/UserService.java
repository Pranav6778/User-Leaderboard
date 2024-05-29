package com.crio.starter.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.starter.data.Badges;
import com.crio.starter.data.Users;
import com.crio.starter.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	public List<Users> getAllUsers() {
		
		return userRepository.findAll();
	}
	
	public Users postUser(int id , String userName) {
		Users user = new Users();
		user.setId(id);
		user.setUserName(userName);
		user.setScore(0);
		user.setBadges(Collections.emptyList());
		return userRepository.save(user);
	}

	public Users deleteUser(int id){
		Optional<Users> user = userRepository.findById(id);
		if(user.isPresent()){
			userRepository.deleteById(id);
			return user.get();
		}else{
			return null;
		}			
	}

	public Users updatUsers(int id , int score){
		Optional<Users> user = userRepository.findById(id);
		if(user.isPresent()){
			user.get().setScore(score);
			addBadge(score, user.get().getBadges());
			return userRepository.save(user.get());
		}else{
			return null;
		}
	}
	public List<Users> getLeaderBoard(){
		return userRepository.findByOrderByScoreDesc();
	}
	private void addBadge(int score, List<Badges> badges){
		if(1 <= score && score < 30){
			badges.add(Badges.Code_Ninja);
		}else if(30 <= score && score < 60){
			badges.add(Badges.Code_Champ);
		}else{
			badges.add(Badges.Code_Master);
		}
	}

}
