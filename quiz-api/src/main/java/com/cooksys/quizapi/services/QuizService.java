package com.cooksys.quizapi.services;

import java.util.List;

import com.cooksys.quizapi.model.QuizRequestDto;
import com.cooksys.quizapi.model.QuizResponseDto;

public interface QuizService {

	List<QuizResponseDto> getAllQuizzes();

	QuizResponseDto createQuiz(QuizRequestDto quizRequestDto);

	QuizResponseDto deleteQuiz(Long id);

	QuizResponseDto renameQuiz(Long id, String newName);

}
