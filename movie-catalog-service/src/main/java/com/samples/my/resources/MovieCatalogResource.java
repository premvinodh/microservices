package com.samples.my.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.samples.my.models.CatalogItem;
import com.samples.my.models.Movie;
import com.samples.my.models.Rating;
import com.samples.my.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

//	@Autowired
//	private DiscoveryClient discoveryClient;

//	@Autowired
//	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating ratings = getUserRating(userId);

		List<CatalogItem> catalogItems = new ArrayList<CatalogItem>();
		// For each movie, call movie info service and get details
		for (Rating rating : ratings.getUserRating()) {
			Movie movie = getMovieInfo(rating.getMovieId());
			catalogItems.add(new CatalogItem(movie.getName(), "Test Desc", rating.getRating()));
		}
		return catalogItems;
	}

	private UserRating getUserRating(String userId) {
		// Uses direct url -- version 1
//		UserRating ratings = restTemplate.getForObject("http://localhost:3001/ratingsdata/users"+userId, UserRating.class);

		// Uses discoveryClient -- version 2
//		List<ServiceInstance> ratingsSvcInstances = discoveryClient.getInstances("RATINGS-DATA-SVC");
////	if (ratingsSvcInstances == null || ratingsSvcInstances.size() == 0) {
//			return null;
//		}
//		URI ratigsUri = ratingsSvcInstances.get(0).getUri();

		// Uses loadBalancerClient -- version 3
//		ServiceInstance ratingsSvcInstances = loadBalancerClient.choose("RATINGS-DATA-SVC");
//		URI ratigsUri = ratingsSvcInstances.getUri();
//		String ratingsDataSvcUrl = ratigsUri + "/ratingsdata/users/" + userId;
//		System.out.println("ratingsDataSvcUrl -->" + ratingsDataSvcUrl);
//		UserRating ratings = restTemplate.getForObject(ratingsDataSvcUrl, UserRating.class);
//		return ratings;

		// Uses @LoadBalanced restTemplate -- version 4
		ResponseEntity<UserRating> restExchange = restTemplate.exchange(
				"http://RATINGS-DATA-SVC/ratingsdata/users/{userId}", HttpMethod.GET, null, UserRating.class, userId);
		UserRating ratings = restExchange.getBody();
		return ratings;

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
