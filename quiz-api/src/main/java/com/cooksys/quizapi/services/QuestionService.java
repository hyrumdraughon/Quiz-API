package com.cooksys.quizapi.services;

import com.cooksys.quizapi.model.QuestionRequestDto;
import com.cooksys.quizapi.model.QuestionResponseDto;
import com.cooksys.quizapi.model.QuizResponseDto;

public interface QuestionService {

	QuestionResponseDto getRandomQuestion(Long id);

	QuizResponseDto addQuestion(Long id, QuestionRequestDto questionRequestDto);

	QuestionResponseDto deleteQuestion(Long id, Long questionID);

}
