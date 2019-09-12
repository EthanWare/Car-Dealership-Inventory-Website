package com.ethan;

import org.springframework.data.repository.CrudRepository;

public interface AuthenticationRepository extends CrudRepository<User, Integer> {
}