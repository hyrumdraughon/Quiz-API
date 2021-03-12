package com.cooksys.quizapi.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.quizapi.entities.Quiz;
import com.cooksys.quizapi.exceptions.BadRequestException;
import com.cooksys.quizapi.exceptions.NotFoundException;
import com.cooksys.quizapi.mappers.QuizMapper;
import com.cooksys.quizapi.model.QuizRequestDto;
import com.cooksys.quizapi.model.QuizResponseDto;
import com.cooksys.quizapi.repositories.QuizRepository;
import com.cooksys.quizapi.services.QuizService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {
	
	private QuizRepository quizRepository;
	private QuizMapper quizMapper;

	@Override
	public List<QuizResponseDto> getAllQuizzes() {
		return quizMapper.entitiesToResponseDtos(quizRepository.findAll());
	}

	@Override
	public QuizResponseDto createQuiz(QuizRequestDto quizRequestDto) {
		if (quizRequestDto.getName() == null) {
			throw new BadRequestException("must provide a name for quiz to create quiz");
		}
		
		Quiz quizToSave = quizMapper.requestDtoToEntity(quizRequestDto);
		return quizMapper.entityToResponseDto(quizRepository.saveAndFlush(quizToSave));
	}

	@Override
	public QuizResponseDto deleteQuiz(Long id) {
		if (!quizRepository.existsById(id)) {
			throw new NotFoundException("no quiz found with id: " + id + "; cannot delete quiz");
		}
		
		QuizResponseDto quizResponseDto = quizMapper.entityToResponseDto(quizRepository.getOne(id));
		quizRepository.deleteById(id);
		quizRepository.flush();
		return quizResponseDto;
	}

	@Override
	public QuizResponseDto renameQuiz(Long id, String newName) {
		if (!quizRepository.existsById(id)) {
			throw new NotFoundException("no quiz found with id: " + id + "; cannot rename quiz");
		} else if (newName == null) {
			throw new BadRequestException("must provide a name for quiz to rename quiz");
		}
		
		quizRepository.getOne(id).setName(newName);
		quizRepository.flush();
		return quizMapper.entityToResponseDto(quizRepository.getOne(id));
	}

}
