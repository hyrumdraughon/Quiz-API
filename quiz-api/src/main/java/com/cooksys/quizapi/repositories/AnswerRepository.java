package com.cooksys.quizapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.quizapi.entities.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
