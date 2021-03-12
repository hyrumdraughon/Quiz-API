package com.cooksys.quizapi.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.quizapi.entities.Quiz;
import com.cooksys.quizapi.model.QuizRequestDto;
import com.cooksys.quizapi.model.QuizResponseDto;

@Mapper(componentModel="spring")
public interface QuizMapper {
	
	Quiz requestDtoToEntity(QuizRequestDto quizRequestDto);
	
	QuizResponseDto entityToResponseDto(Quiz quiz);
	
	List<QuizResponseDto> entitiesToResponseDtos(List<Quiz> quizzes);

}
