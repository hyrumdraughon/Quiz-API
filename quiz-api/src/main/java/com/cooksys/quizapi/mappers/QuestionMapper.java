package com.cooksys.quizapi.mappers;

import org.mapstruct.Mapper;

import com.cooksys.quizapi.entities.Question;
import com.cooksys.quizapi.model.QuestionRequestDto;
import com.cooksys.quizapi.model.QuestionResponseDto;

@Mapper(componentModel="spring")
public interface QuestionMapper {
	
	QuestionResponseDto entityToResponseDto(Question question);
	
	Question requestDtoToEntity(QuestionRequestDto questionRequestDto);

}
