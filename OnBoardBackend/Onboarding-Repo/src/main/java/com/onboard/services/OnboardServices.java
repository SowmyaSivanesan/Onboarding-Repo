package com.onboard.services;

import org.springframework.stereotype.Service;

import com.onboard.model.OnboardModel;
import com.onboard.model.OnboardResponse;

@Service
public interface OnboardServices {
	
	public OnboardResponse createUser(OnboardModel values);
	
	public OnboardResponse updateUser(String email,String position);
	
	public OnboardResponse deleteUser(String email);
	
	public OnboardResponse getUser(String email);
	
	public OnboardResponse getAllUser(OnboardModel values);

}
