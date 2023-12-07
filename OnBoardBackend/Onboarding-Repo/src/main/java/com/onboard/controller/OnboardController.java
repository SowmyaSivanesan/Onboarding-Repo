package com.onboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onboard.dao.OnboardDao;
import com.onboard.model.OnboardModel;
import com.onboard.model.OnboardResponse;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class OnboardController {
	
	@Autowired
	OnboardDao dao;
	
	@PostMapping("/create")
	public ResponseEntity<OnboardResponse> createUser(@RequestBody OnboardModel values){
		return  ResponseEntity.ok(dao.createUser(values));
	}
	
	@PutMapping("/update")
	public ResponseEntity<OnboardResponse> updateUser (@RequestParam String email,@RequestParam String position) {
		return ResponseEntity.ok(dao.updateUser(email,position));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<OnboardResponse> deleteUser (@RequestParam String email){
		return ResponseEntity.ok(dao.deleteUser(email));
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<OnboardResponse> getUser (@RequestParam String email){
		return ResponseEntity.ok(dao.getUser(email));
	}
	
	@GetMapping("/getAllUser")
	public ResponseEntity<OnboardResponse> getAllUser (@RequestBody OnboardModel values){
		return ResponseEntity.ok(dao.getAllUser(values));
	}

}