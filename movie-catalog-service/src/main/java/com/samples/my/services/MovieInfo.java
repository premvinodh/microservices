package com.samples.my.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.samples.my.models.CatalogItem;
import com.samples.my.models.Movie;
import com.samples.my.models.Rating;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = getMovieInfo(rating.getMovieId());
		return new CatalogItem(movie.getName(), "Test Desc", rating.getRating());
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found", "", rating.getRating());
	}
	
	private Movie getMovieInfo(String movieId) {
		// Uses direct url -- version 1
//		Movie movie = restTemplate.getForObject("http://localhost:2001/movies/"+movieId, Movie.class);

		// Uses discoveryClient -- version 2
//		List<ServiceInstance> movieSvcInstances = discoveryClient.getInstances("MOVIE-INFO-SVC");
//		if (movieSvcInstances == null || movieSvcInstances.size() == 0) {
//			return null;
//		}
//		URI movieUri = movieSvcInstances.get(0).getUri();

//		// Uses loadBalancedClient -- version 3
//		ServiceInstance movieSvcInstances = loadBalancerClient.choose("MOVIE-INFO-SVC");
//		URI moviesUri = movieSvcInstances.getUri();
//		String movieInfoSvcUrl = moviesUri + "/movies/" + movieId;
//		System.out.println("movieInfoSvcUrl -->" + movieInfoSvcUrl);
//		Movie movie = restTemplate.getForObject(movieInfoSvcUrl, Movie.class);

		// Uses @LoadBalanced restTemplate -- version 4
		ResponseEntity<Movie> restExchange = restTemplate.exchange("http://MOVIE-INFO-SVC/movies/{movieId}",
				HttpMethod.GET, null, Movie.class, movieId);
		Movie movie = restExchange.getBody();
		return movie;
	}
}
