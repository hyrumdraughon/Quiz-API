package com.cooksys.quizapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.quizapi.entities.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
