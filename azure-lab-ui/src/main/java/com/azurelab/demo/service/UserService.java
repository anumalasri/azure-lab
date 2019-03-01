package com.azurelab.demo.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.azurelab.demo.model.User;

@Service
public class UserService {

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${api.gateway.url}")
	String apiURL;

	public User createUser(User user) {
		HttpEntity<User> userEntity = createRequest(user);

		ResponseEntity<User> userResponse;
		try {
			userResponse = restTemplate.exchange(getURL("/user"), HttpMethod.POST, userEntity, User.class);
			return userResponse.getBody();
		} finally {
			userEntity = null;
		}

	}

	
	public User updateUser(User user) {
		HttpEntity<User> userEntity = createRequest(user);

		ResponseEntity<User> userResponse;
		try {
			userResponse = restTemplate.exchange(getURL("/user"), HttpMethod.POST, userEntity, User.class);
			return userResponse.getBody();
		} finally {
			userEntity = null;
		}

	}

	
	public User getUser(String username) {
		ResponseEntity<User> userResponse;
		userResponse = restTemplate.getForEntity(getURL("/user/" + username), User.class);
		return userResponse.getBody();
	}

	public String getURL(String uri) {
		return apiURL + uri;
	}

	public HttpEntity<User> createRequest(User payload) {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		return new HttpEntity<User>(payload);
	}


	public List<Object> getPreferredMovies(String username) {
		ResponseEntity<List> userResponse;
		userResponse = restTemplate.getForEntity(getURL("/user/movies?username="+username), List .class);
		return (List<Object>) userResponse.getBody();
	}

}
