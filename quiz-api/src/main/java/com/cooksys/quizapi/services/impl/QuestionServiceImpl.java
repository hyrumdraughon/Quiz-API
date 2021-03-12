package com.cooksys.quizapi.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.quizapi.entities.Question;
import com.cooksys.quizapi.exceptions.BadRequestException;
import com.cooksys.quizapi.exceptions.NotFoundException;
import com.cooksys.quizapi.mappers.QuestionMapper;
import com.cooksys.quizapi.mappers.QuizMapper;
import com.cooksys.quizapi.model.QuestionRequestDto;
import com.cooksys.quizapi.model.QuestionResponseDto;
import com.cooksys.quizapi.model.QuizResponseDto;
import com.cooksys.quizapi.repositories.QuestionRepository;
import com.cooksys.quizapi.repositories.QuizRepository;
import com.cooksys.quizapi.services.QuestionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

	private QuizRepository quizRepository;
	private QuizMapper quizMapper;
	private QuestionRepository questionRepository;
	private QuestionMapper questionMapper;

	@Override
	public QuestionResponseDto getRandomQuestion(Long id) {
		if (!quizRepository.existsById(id)) {
			throw new NotFoundException("no quiz found with id: " + id + "; cannot get random question from quiz");
		} else if (quizRepository.getOne(id).getQuestions() == null || quizRepository.getOne(id).getQuestions().isEmpty()) {
			throw new NotFoundException("the quiz with id: " + id + " exists, but it has no questions in it");
		}
		
		List<Question> questionList = quizRepository.getOne(id).getQuestions();
		return questionMapper.entityToResponseDto(questionList.get((int) (Math.random() * questionList.size())));
	}

	@Override
	public QuizResponseDto addQuestion(Long id, QuestionRequestDto questionRequestDto) {
		if (!quizRepository.existsById(id)) {
			throw new NotFoundException("no quiz found with id: " + id + "; cannot add question");
		} else if (questionRequestDto.getText() == null) {
			throw new BadRequestException("must provide text for question to add question");
		}
		
		Question questionToSave = questionMapper.requestDtoToEntity(questionRequestDto);
		questionToSave.setQuiz(quizRepository.getOne(id));
		questionRepository.saveAndFlush(questionToSave);
		return quizMapper.entityToResponseDto(quizRepository.getOne(id));
	}

	@Override
	public QuestionResponseDto deleteQuestion(Long id, Long questionID) {
		if (!quizRepository.existsById(id)) {
			throw new NotFoundException("no quiz found with id: " + id + "; cannot delete question");
		} else if (!questionRepository.existsById(questionID)) {
			throw new NotFoundException("no question found with id: " + questionID + "; cannot delete question");
		} else if (!quizRepository.getOne(id).getQuestions().contains(questionRepository.getOne(questionID))) {
			throw new BadRequestException("question with id: " + questionID + " is not in quiz with id: " + id + "; cannot delete question");
		}
		
		QuestionResponseDto questionResponseDto = questionMapper.entityToResponseDto(questionRepository.getOne(questionID));
		questionRepository.deleteById(questionID);
		questionRepository.flush();
		return questionResponseDto;
	}

}
