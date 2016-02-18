package com.yart.auth.controller.auth;

import javax.xml.ws.soap.AddressingFeature.Responses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yart.common.bean.Response;
import com.yart.common.bean.UserWrapper;
import com.yart.common.bean.dto.AuthData;
import com.yart.common.exception.YartServiceException;
import com.yart.user.bean.User;
import com.yart.user.service.UserService;

@RestController
public class AuthController {
	@Autowired
	UserService userService;

	@RequestMapping("/auth")
	public Response<User> autheticate(@RequestBody User user) {
		Response<User> response = null;
		System.out.println(user);
		UserWrapper userWrapper = null;
		try {
			userWrapper = userService.verifyCredentials(user);
			if (userWrapper != null){
				if (userWrapper.getStatusCode() == UserWrapper.STATUS_CODES.VALID_CREDENTIALS) {
					// not handling inactive user accounts
					response = generateResponse(user, Response.RESPONSE_STATUS.SUCCESS, "Authentication success");
				}
			}else{
				response = generateResponse(null,Response.RESPONSE_STATUS.FAIL, "");
			}
		} catch (YartServiceException e) {
		   response = generateResponse(null,Response.RESPONSE_STATUS.FAIL, e.getMessage());
		}

		return response;
	}

	@RequestMapping("/createUser")
	public Response<User> createUser() {
		// test user
		User user = new User();
		user.setUserId("visvv");
		user.setEmail("visv@vv.com");
		user.setPassword("password");
		Response<User> response = new Response<>();
		try {
			userService.createUser(user);
			response.setStatus(Response.RESPONSE_STATUS.STATUS_OK);
			response.setPayload(user);
		} catch (YartServiceException e) {
			response.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	private Response<User> generateResponse(User user,Response.RESPONSE_STATUS status, String message){
		Response<User> response = new Response<>();
		response.setMessage(message);
		response.setStatus(status);
		return response;
	}
}
