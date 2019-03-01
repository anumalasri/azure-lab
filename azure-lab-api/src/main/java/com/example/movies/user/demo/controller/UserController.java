package com.example.movies.user.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movies.user.demo.domain.Movie;
import com.example.movies.user.demo.domain.User;
import com.example.movies.user.repository.MovieRepository;
import com.example.movies.user.repository.UserRepository;


@RestController
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private MovieRepository movieRepo;
	
	
	
	@Autowired
	private MongoTemplate template;
	

	@GetMapping()
	List<User> getUser() {
		return repository.findAll();
	}
	
	@GetMapping("/{username}")
	User getUserByName(@PathVariable("username") String username) {
		return repository.findByUsername(username);
	}
	
	@PostMapping
	User newUser(@RequestBody User newUser) {

		logger.info("User Request {} ", newUser);
		newUser.setId(newUser.getUsername());
		newUser.setMovies(new ArrayList<>());
		
		repository.save(newUser);
		logger.info("Created User ");
		return newUser;

	}

	@PutMapping
	User updateUser(@RequestBody User updatedUser) {

		User username=repository.findByUsername(updatedUser.getUsername());
		if(username != null){
			repository.deleteById(username.getId());
			repository.save(updatedUser);//updatedUser.getUsername());
		}
		System.out.println("Updated Doc");
		return updatedUser;

	}
	
	
	
	@GetMapping("/movies")
	List<Object> getMovies(@RequestParam String username) {
		User user=repository.findByUsername(username);
 		
		return user.getMovies();
		
		
	}

	
	@GetMapping("/{username}/updateMovies")
	public String updateUserMovies(@PathVariable String username) {
		User user=repository.findByUsername(username);
		
		List<String> preferneces = user.getPreferences();
		
		Query query = new Query();
		
		query.addCriteria(Criteria.where("genres").in(preferneces));
		query.with(new Sort(Sort.Direction.DESC, "$imdb.rating"));
		query.fields().include("title").include("year").include("imdb");
		query.with(PageRequest.of(0, 20));
		
		List<Document> movies = template.find(query, Document.class, "movies");
		
		List<Object> movies2 = new ArrayList<Object>();
		
		movies.forEach(mov-> {
			Document doc = new Document();
			doc.put("id", mov.get("_id").toString());
			doc.put("title", mov.getString("title"));
			doc.put("year", mov.getInteger("year"));
			Document imdb = (Document) mov.get("imdb");
			doc.put("rating", imdb.get("rating"));
			movies2.add(doc);
		});
		
		 
		
		//List<Object> movies = movieRepo.finduserPreferredMovies(preferneces, PageRequest.of(1, 20));
		
		user.setMovies(movies2);
		repository.save(user);
		
		return "Successfully Updated User Preferred Movies";
	}
	
}
