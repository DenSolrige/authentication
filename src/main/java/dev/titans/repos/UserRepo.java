package dev.titans.repos;

import dev.titans.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    @Query("{'username':'?0'}")
    User findByUsername(String username);
}
