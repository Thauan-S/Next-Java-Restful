package com.tropical.controller;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tropical.data.dto.CreateTweetDto;
import com.tropical.data.dto.FeedDto;
import com.tropical.data.dto.FeedItemDto;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.model.Role;
import com.tropical.model.Tweet;
import com.tropical.repository.TweetRepository;
import com.tropical.repository.UserRepository;

@RestController
public class TweetController {

	private final TweetRepository tweetRepository;
	private final UserRepository userRepository;
	public TweetController(TweetRepository tweetRepository,UserRepository userRepository) {
		this.tweetRepository = tweetRepository;
		this.userRepository = userRepository;
	}
	@GetMapping("/feed")
	public ResponseEntity<FeedDto>feed(@RequestParam(name="page",defaultValue = "0" )int page,@RequestParam(name="size",defaultValue = "10" )int size){
	var tweets=	tweetRepository.findAll(PageRequest.of(page, size,Sort.Direction.DESC,"instant"))
			.map(tweet->  new FeedItemDto(tweet.getTweetId(),tweet.getContent(),tweet.getUser().getUsername()));
		
	return ResponseEntity.ok(new FeedDto(
				tweets.getContent(),page,size, tweets.getTotalPages(),tweets.getTotalElements()));
	}
	@PostMapping("/tweet")
	public ResponseEntity<Void> createTweet(@RequestBody CreateTweetDto dto,JwtAuthenticationToken token){
		var user= userRepository.findById(UUID.fromString( token.getName()));
		var tweet=new Tweet();
		tweet.setUser(user.get());
		tweet.setContent(dto.content());
		tweetRepository.save(tweet);
		return ResponseEntity.ok().build();
		
	}
	@DeleteMapping("/tweet/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long tweetId,JwtAuthenticationToken token){
		var user= userRepository.findById(UUID.fromString( token.getName()));
		var tweet =tweetRepository.findById(tweetId).orElseThrow(()-> new ResourceNotFoundException("tweet não encontrado"));
		var isAdmin=user.get().getRoles()
		.stream()
		.anyMatch(role-> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));
		if(isAdmin || tweet.getUser().getUserId().equals(UUID.fromString(token.getName()))) {
			tweetRepository.delete(tweet);
		}else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		return ResponseEntity.ok().build();
		
	}
}
