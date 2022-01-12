package com.tweetapp.mytweetapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.mytweetapp.model.Tweet;
import com.tweetapp.mytweetapp.model.User;
import com.tweetapp.mytweetapp.repository.TweetRepository;
import com.tweetapp.mytweetapp.repository.UserRepository;

@RestController
//@RequestMapping("/api/v1.0/tweets")
@RequestMapping("/")
public class TweetAppController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TweetRepository tweetRepository;

	private User user;

	// REgister new User
	@PostMapping("/register")
	public void registerUser(@RequestBody User user) {
		User newUser = new User();
		newUser.setFirst_name(user.getFirst_name());
		newUser.setLast_name(user.getLast_name());
		newUser.setGender(user.getGender());
		newUser.setDob(user.getDob());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setStatus(false);

		userRepository.save(newUser);
	}

	// Login the User
	@GetMapping("/login")
	public User loginUser(@RequestBody User user) {
		// User u = userRepository.findByEmail(user.getEmail());
		User u = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		System.out.println(u);
		return u;
	}

	// Forgot Password
	@GetMapping("/{username}/forgot")
	public User forgotPassword(@PathVariable("username") String userName, @RequestBody User user) {
		// User u = userRepository.findByEmail(user.getEmail());
		User u = userRepository.findByEmail(userName);
		if (u != null) {
			u.setPassword(user.getPassword());
			userRepository.save(u);
		}
		System.out.println(u);
		return u;
	}

	// Get All USers
	@GetMapping("/users/all")
	public List<User> getAllUsers() {
		List<User> allUsers = userRepository.findAll();
		return allUsers;
	}

	// Get user by Username, username may be partial or complete
	@GetMapping("/user/search/{username}")
	public List<User> getUser(@PathVariable("username") String userName) {
		List<User> allUsers = userRepository.findByEmailLike(userName);
		return allUsers;
	}

	// Get All Tweets
	@GetMapping("/all")
	public List<Tweet> getAllTweets() {
		List<Tweet> allTweets = tweetRepository.findAll();
		return allTweets;
	}

	// Get All Tweets of a user ??-implementation correct or not
	@GetMapping("/{username}")
	public List<Tweet> getAllTweetsByUserName(@PathVariable("username") String userName) {
		System.out.println("Inside getAllTweetsByUserName api");
		System.out.println("username "+userName);
		User user = userRepository.findByEmail(userName);
		System.out.println("User info- "+user);
		List<Tweet> tweets = tweetRepository.findAllByUserId(user.getId()); 

//		List<Tweet> userTweets = new ArrayList<Tweet>();
//		while (tweets.isPresent()) {
//			userTweets.add(tweets.get());
//		}
//		System.out.println("Exit getAllTweetsByUserName api");
//		return userTweets;
		return tweets;
	}

	// Post new Tweet
	@PostMapping("/tweets/{username}/add")
	public Tweet postTweet(@PathVariable("username") String userName, @RequestBody Tweet tweet) {
		//String userId="61daee674e8aa617b8a127f4"; //temp soln
		//tweet.setUserId(userId);
		Tweet savedTweet = tweetRepository.save(tweet);
		return savedTweet;
	}

}
