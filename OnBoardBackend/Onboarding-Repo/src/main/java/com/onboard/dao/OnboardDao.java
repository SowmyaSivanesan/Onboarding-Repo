package com.onboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.onboard.model.OnboardModel;
import com.onboard.model.OnboardResponse;
import com.onboard.services.OnboardServices;

@Component
public class OnboardDao implements OnboardServices {
	
	OnboardResponse rsp = new OnboardResponse();
	
	String url = "jdbc:mysql://127.0.0.1:3306/onboarding_details";
	String username = "root";
	String pswrd = "Sowmiya0209";

	@Override
	public OnboardResponse createUser(OnboardModel values) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, username, pswrd);
					Statement st = conn.createStatement();){
				
				String emailValidation = "^(.+)@gmail.com$";
				Pattern emailpattern = Pattern.compile(emailValidation);
				Matcher emailMatch = emailpattern.matcher(values.getEmail());
				
				if (emailMatch.matches() == true) {
					
					String insertQuery = "INSERT INTO onboarding_details.candidate(first_name,last_name,email,position)VALUES('"+values.getFirstName()+"','"+values.getLastName()+"','"+values.getEmail()+"',"
									+ "'"+values.getPosition()+"');";
					System.out.println(insertQuery);
					st.execute(insertQuery);
					
					rsp.setData("User Created Successfully!");
					rsp.setResponseCode(200);
					rsp.setRespondMsg("success");

				} else {
					rsp.setData("Invalid Email");
					rsp.setResponseCode(500);
					rsp.setRespondMsg("failed");
				}
				
				
			} catch (Exception e) {

				e.printStackTrace();

				rsp.setData("Cannot Create User!");
				rsp.setResponseCode(500);
				rsp.setRespondMsg("error");
			
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();

			rsp.setData("Driver Class Error!");
			rsp.setResponseCode(500);
			rsp.setRespondMsg("error");
		
		}
		
		return rsp;
	}
	@Override
	public OnboardResponse updateUser(String email,String position) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			try(Connection conn = DriverManager.getConnection(url, username, pswrd);
					Statement st = conn.createStatement();) {
				
				String updateQuery = "Update candidate set email ='" + email + "' where position ='" + position + "'";
				
				
				st.execute(updateQuery);
				
				rsp.setData("User Updated Successfully!");
				rsp.setResponseCode(200);
				rsp.setRespondMsg("success");
				
			} catch (Exception e) {
				e.printStackTrace();

				rsp.setData("Cannot Update User!");
				rsp.setResponseCode(500);
				rsp.setRespondMsg("error");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			rsp.setData("Driver Class Error!");
			rsp.setResponseCode(500);
			rsp.setRespondMsg("error");
			
		}
		
		
		return rsp;
	}
	@Override
	public OnboardResponse deleteUser(String email) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			try(Connection conn = DriverManager.getConnection(url, username, pswrd);
					Statement st = conn.createStatement();) {
				
				String deleteQuery = "delete from  userdetails where email ='" + email + "'";
				
				st.execute(deleteQuery);
				
				rsp.setData("User Deleted Successfully!");
				rsp.setResponseCode(200);
				rsp.setRespondMsg("success");
				
			} catch (Exception e) {

				e.printStackTrace();

				rsp.setData("Cannot delete User!");
				rsp.setResponseCode(500);
				rsp.setRespondMsg("error");
			
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();

			rsp.setData("Driver Class Error!");
			rsp.setResponseCode(500);
			rsp.setRespondMsg("error");
		
		}
		
		return rsp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OnboardResponse getUser(String email) {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String selectQuery = "select * from userdetails where email ='" + email + "'";
			
			JSONObject jasonObject = new JSONObject();
			
			try (Connection conn = DriverManager.getConnection(url, username, pswrd);
					Statement stm = conn.createStatement();
					ResultSet rst = stm.executeQuery(selectQuery)){
			
			while (rst.next()) {
				
				jasonObject.put("firstName", rst.getString("first_name"));
				jasonObject.put("lastName", rst.getString("last_name"));
				jasonObject.put("email", rst.getString("email"));
				jasonObject.put("lastName", rst.getString("position"));
				
				
			}
			rsp.setData("User updated(one) Successfully!");
			rsp.setResponseCode(200);
			rsp.setRespondMsg("success");
			rsp.setjData(jasonObject);
			
			
			} catch (Exception e) {

				e.printStackTrace();
				rsp.setData("Cannot GetOne User!");
				rsp.setResponseCode(500);
				rsp.setRespondMsg("error");
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
			rsp.setData("Driver Class Error!");
			rsp.setResponseCode(500);
			rsp.setRespondMsg("error");
		
		}
		
		return rsp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OnboardResponse getAllUser(OnboardModel values) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String selectQuery = "select * from candidates;";
			
			try (Connection conn = DriverManager.getConnection(url, username, pswrd);
					Statement st = conn.createStatement();
					ResultSet rst = st.executeQuery(selectQuery);){
				
				JSONArray jsonArray = new JSONArray();
				while (rst.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("firstName", rst.getString("first_name"));
					jsonObject.put("firstName", rst.getString("last_name"));
					jsonObject.put("email", rst.getString("email"));
					jsonObject.put("position", rst.getString("position"));
					
					jsonArray.add(jsonObject);
					
				}
				
				rsp.setData("Success!");
				rsp.setResponseCode(200);
				rsp.setRespondMsg("success");
				rsp.setjData(jsonArray);
				
			} catch (Exception e) {

				e.printStackTrace();
				rsp.setData("Cannot Fetch User!");
				rsp.setResponseCode(500);
				rsp.setRespondMsg("error");

			}
			
		} catch (Exception e) {

			e.printStackTrace();
			rsp.setData("Driver Class Error!");
			rsp.setResponseCode(500);
			rsp.setRespondMsg("error");	
		}
		
		return rsp;
	}

}
