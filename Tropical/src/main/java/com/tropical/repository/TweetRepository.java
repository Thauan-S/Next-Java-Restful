package com.tropical.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tropical.model.Tweet;
@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {
 
}
