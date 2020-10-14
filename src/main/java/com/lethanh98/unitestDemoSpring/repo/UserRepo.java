package com.lethanh98.unitestDemoSpring.repo;

import com.lethanh98.unitestDemoSpring.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

}
