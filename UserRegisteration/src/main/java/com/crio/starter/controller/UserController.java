package com.crio.starter.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.crio.starter.data.Users;
import com.crio.starter.exchange.PostUserRequest;
import com.crio.starter.exchange.PutUserRequest;
import com.crio.starter.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello";
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<Users>>getAllUsersOrderByScoreDesc(){
		if(userService.getLeaderBoard() == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
		}else {
			return ResponseEntity.ok(userService.getLeaderBoard());
		}
	}

	@PostMapping("/users")
	public ResponseEntity<Users> postUser(@RequestBody PostUserRequest postUserRequest) {

		return ResponseEntity.ok(userService.postUser(postUserRequest.getId(), postUserRequest.getUserName()));
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Users> deleteUser(@PathVariable("id") Integer id) {
		Users user = userService.deleteUser(id);
		if(user == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}else{
			return ResponseEntity.ok(user);
		}
	}

	@PutMapping("users/{id}")
	public ResponseEntity<Users> putMethodName(@PathVariable Integer id, @RequestBody PutUserRequest putUserRequest) {
		if(putUserRequest.getScore() < 0 || putUserRequest.getScore() > 100){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		Users users = userService.updatUsers(id, putUserRequest.getScore());
		if(users == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}else{
			return ResponseEntity.ok(users);
		}
	}
	
}
