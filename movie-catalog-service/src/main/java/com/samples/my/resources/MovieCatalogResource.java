package com.samples.my.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.samples.my.models.CatalogItem;
import com.samples.my.models.Rating;
import com.samples.my.models.UserRating;
import com.samples.my.services.MovieInfo;
import com.samples.my.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

//	@Autowired
//	private DiscoveryClient discoveryClient;

//	@Autowired
//	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MovieInfo movieInfo;

	@Autowired
	private UserRatingInfo userRatingInfo;

//	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
//		return Arrays.asList(new CatalogItem("No movie", "", "0"));
//	}

	@RequestMapping("/{userId}")
//	@HystrixCommand(fallbackMethod = "getFallbackCatalog")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		UserRating ratings = userRatingInfo.getUserRating(userId);

		List<CatalogItem> catalogItems = new ArrayList<CatalogItem>();
		// For each movie, call movie info service and get details
		for (Rating rating : ratings.getUserRating()) {
			CatalogItem catalogItem = movieInfo.getCatalogItem(rating);
			catalogItems.add(catalogItem);
		}
		return catalogItems;
	}
	
//	// The above method using streams and lambda expressions
//	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
//		UserRating ratings = userRatingInfo.getUserRating(userId);
//
//		return ratings.getUserRating().stream()
//				.map(rating -> movieInfo.getCatalogItem(rating))
//				.collect(Collectors.toList());
//	}
}
