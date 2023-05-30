package com.samples.my.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.samples.my.models.Rating;
import com.samples.my.models.UserRating;

@Service
public class UserRatingInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public UserRating getFallbackUserRating(String userId) {
		UserRating userRating = new UserRating();
//		userRating.setUserId(userId);
		userRating.setUserRating(Arrays.asList(new Rating("0", "0")));
		return userRating;
	}

	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(String userId) {
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

}
