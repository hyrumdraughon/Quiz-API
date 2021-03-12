package com.cooksys.quizapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.quizapi.model.QuestionRequestDto;
import com.cooksys.quizapi.model.QuestionResponseDto;
import com.cooksys.quizapi.model.QuizRequestDto;
import com.cooksys.quizapi.model.QuizResponseDto;
import com.cooksys.quizapi.services.QuestionService;
import com.cooksys.quizapi.services.QuizService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("quiz")
@AllArgsConstructor
public class QuizController {

	private QuizService quizService;
	private QuestionService questionService;
	
	@GetMapping
	public List<QuizResponseDto> getAllQuizzes() {
		return quizService.getAllQuizzes();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public QuizResponseDto createQuiz(@RequestBody QuizRequestDto quizRequestDto) {
		return quizService.createQuiz(quizRequestDto);
	}
	
	@RequestMapping("/{id}")
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public QuizResponseDto deleteQuiz(@PathVariable Long id) {
		return quizService.deleteQuiz(id);
	}
	
	@RequestMapping("/{id}/rename/{newName}")
	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	public QuizResponseDto renameQuiz(@PathVariable Long id, @PathVariable String newName) {
		return quizService.renameQuiz(id, newName);
	}
	
	@RequestMapping("/{id}/random")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public QuestionResponseDto getRandomQuestion(@PathVariable Long id) {
		return questionService.getRandomQuestion(id);
	}
	
	@RequestMapping("/{id}/add")
	@PatchMapping
	@ResponseStatus(HttpStatus.CREATED)
	public QuizResponseDto addQuestion(@PathVariable Long id, @RequestBody QuestionRequestDto questionRequestDto) {
		return questionService.addQuestion(id, questionRequestDto);
	}
	
	@RequestMapping("/{id}/delete/{questionID}")
	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public QuestionResponseDto deleteQuestion(@PathVariable Long id, @PathVariable Long questionID) {
		return questionService.deleteQuestion(id, questionID);
	}
	
}
