package com.crio.starter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.crio.starter.data.Users;

public interface UserRepository extends MongoRepository<Users, Integer> {

    List<Users> findByOrderByScoreDesc();

}
