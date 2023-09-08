package com.packt.cardatebase.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
	//null예외를 방지하기 위해 Optional을 반환 해준다.
	//예외가 발생 하더라도 Optional로 인해 괜찮다
	Optional<User> findByUsername(String username);
	

}
