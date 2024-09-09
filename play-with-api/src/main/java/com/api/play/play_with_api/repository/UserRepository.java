package com.api.play.play_with_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.play.play_with_api.Userr;

@Repository
public interface UserRepository extends JpaRepository<Userr,Integer> {

}
