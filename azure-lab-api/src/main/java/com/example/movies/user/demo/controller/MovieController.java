package com.example.movies.user.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movies.user.demo.domain.Movie;
import com.example.movies.user.repository.MovieRepository;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieRepository movieRepo;
	
	
	@Autowired
	MongoTemplate template;

	@PostMapping 
	Movie newMovie(@RequestBody Movie newMovie) {

		movieRepo.insert(newMovie);
		System.out.println("Created Doc");
		return newMovie;

	}

	@PutMapping 
	Movie updateMovie(@RequestBody Movie updatedMovie) {

		Movie movie=movieRepo.findByTitle(updatedMovie.getTitle());
		if(movie != null){
			movieRepo.deleteById(movie.getTitle());
			movieRepo.save(updatedMovie);//updatedUser.getUsername());
		}
		System.out.println("Updated Doc");
		return updatedMovie;

	}

/*	@GetMapping 
	Movie getMovie(@RequestParam String title) {
		Movie movie=movieRepo.findByTitle(title);

		return movie;
 
	}
	*/
	
	
	@GetMapping("/{id}")
	Movie getMovieById(@PathVariable String id) {
		//Movie movie=movieRepo.findById(id).get();

		return template.findById(new ObjectId(id), Movie.class, "movies");
  
	}
	
	@GetMapping("/genres") 
	List<String> getGenres () {

		AggregationOperation unwind = Aggregation.unwind("genres");
		GroupOperation group = Aggregation.group("genres").count().as("total"); 
 

		 AggregationOperation sort = Aggregation.sort( new Sort(Direction.DESC,"total")); 
		 
		Aggregation aggregation = Aggregation.newAggregation(unwind, group, sort);

		AggregationResults<Document> results =  template.aggregate(aggregation, "movies", Document.class);
		
		List<String> response = new ArrayList<String>();
		results.getMappedResults().subList(0, 10).forEach(doc -> {   response.add(doc.getString("_id")); });
		
		return response;
		
	}

	@PostMapping("/search")
	List<Movie> searchMovies() {
		Query query = new Query();
		 
		return template.find(query, Movie.class, "movie");
		
		
	
	}

	
}
