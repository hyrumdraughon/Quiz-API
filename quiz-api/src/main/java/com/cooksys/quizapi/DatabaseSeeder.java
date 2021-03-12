package com.cooksys.quizapi;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cooksys.quizapi.entities.Answer;
import com.cooksys.quizapi.entities.Question;
import com.cooksys.quizapi.entities.Quiz;
import com.cooksys.quizapi.repositories.AnswerRepository;
import com.cooksys.quizapi.repositories.QuestionRepository;
import com.cooksys.quizapi.repositories.QuizRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

	private QuizRepository quizRepository;
	private QuestionRepository questionRepository;
	private AnswerRepository answerRepository;

	@Override
	public void run(String... args) throws Exception {
		// Quizzes, Questions, and Answers must be built from top to bottom
		// Build a Quiz, then its Questions, and then its Answers
		generateRandomQuizzes(2);
	}

	/**
	 * Generates and populates {@code i} amount of simple math quizzes, useful for
	 * populating and testing the database.
	 * 
	 * @param i the amount of quizzes that you wish to generate.
	 */
	private void generateRandomQuizzes(int i) {
		char[] quizOperand = { 'a', 's', 'm', 'd' };
		for (int x = 0; x < i; x++) {
			char quizType = quizOperand[(int) (Math.random() * 4)];
			switch (quizType) {
			case 'a':
				generateAdditionQuiz(x);
				break;
			case 's':
				generateSubtractionQuiz(x);
				break;
			case 'm':
				generateMultiplicationQuiz(x);
				break;
			case 'd':
				generateDivisionQuiz(x);
				break;
			}
		}
	}

	private void generateAdditionQuiz(int i) {
		Quiz quiz = new Quiz();
		quiz.setName("Quiz " + (i + 1) + ": Addition Quiz ");
		quizRepository.saveAndFlush(quiz);
		for (int x = 0; x < 4; x++) {
			int a = (int) (Math.random() * 100);
			int b = (int) (Math.random() * 100);
			int c = a + b;
			Question question = new Question();
			question.setText("Question " + (x + 1) + ": What does " + a + " + " + b + " equal to?");
			question.setQuiz(quiz);
			questionRepository.saveAndFlush(question);
			int correctAnswer = (int) (Math.random() * 4);
			Random random = new Random();
			for (int y = 0; y < 4; y++) {
				Answer answer = new Answer();
				if (y == correctAnswer) {
					answer.setText(a + " + " + b + " = " + c);
					answer.setCorrect(true);
				} else {
					int wrongAnswer;
					if (random.nextBoolean()) {
						wrongAnswer = (int) (Math.random() * 10) + c;
					} else {
						wrongAnswer = (int) (Math.random() * 10) - c;
					}
					answer.setText(a + " + " + b + " = " + wrongAnswer);
					answer.setCorrect(false);
				}
				answer.setQuestion(question);
				answerRepository.saveAndFlush(answer);
			}
		}
	}

	private void generateSubtractionQuiz(int i) {
		Quiz quiz = new Quiz();
		quiz.setName("Quiz " + (i + 1) + ": Subtraction Quiz ");
		quizRepository.saveAndFlush(quiz);
		for (int x = 0; x < 4; x++) {
			int a = (int) (Math.random() * 100);
			int b = (int) (Math.random() * 100);
			int c = a - b;
			Question question = new Question();
			question.setText("Question " + (x + 1) + ": What does " + a + " - " + b + " equal to?");
			question.setQuiz(quiz);
			questionRepository.saveAndFlush(question);
			int correctAnswer = (int) (Math.random() * 4);
			Random random = new Random();
			for (int y = 0; y < 4; y++) {
				Answer answer = new Answer();
				if (y == correctAnswer) {
					answer.setText(a + " - " + b + " = " + c);
					answer.setCorrect(true);
				} else {
					int wrongAnswer;
					if (random.nextBoolean()) {
						wrongAnswer = (int) (Math.random() * 10) + c;
					} else {
						wrongAnswer = (int) (Math.random() * 10) - c;
					}
					answer.setText(a + " - " + b + " = " + wrongAnswer);
					answer.setCorrect(false);
				}
				answer.setQuestion(question);
				answerRepository.saveAndFlush(answer);
			}
		}
	}

	private void generateMultiplicationQuiz(int i) {
		Quiz quiz = new Quiz();
		quiz.setName("Quiz " + (i + 1) + ": Multiplication Quiz ");
		quizRepository.saveAndFlush(quiz);
		for (int x = 0; x < 4; x++) {
			int a = (int) (Math.random() * 100);
			int b = (int) (Math.random() * 100);
			int c = a * b;
			Question question = new Question();
			question.setText("Question " + (x + 1) + ": What does " + a + " * " + b + " equal to?");
			question.setQuiz(quiz);
			questionRepository.saveAndFlush(question);
			int correctAnswer = (int) (Math.random() * 4);
			Random random = new Random();
			for (int y = 0; y < 4; y++) {
				Answer answer = new Answer();
				if (y == correctAnswer) {
					answer.setText(a + " * " + b + " = " + c);
					answer.setCorrect(true);
				} else {
					int wrongAnswer;
					if (random.nextBoolean()) {
						wrongAnswer = (int) (Math.random() * 10) + c;
					} else {
						wrongAnswer = (int) (Math.random() * 10) - c;
					}
					answer.setText(a + " * " + b + " = " + wrongAnswer);
					answer.setCorrect(false);
				}
				answer.setQuestion(question);
				answerRepository.saveAndFlush(answer);
			}
		}
	}

	private void generateDivisionQuiz(int i) {
		Quiz quiz = new Quiz();
		quiz.setName("Quiz " + (i + 1) + ": Division Quiz ");
		quizRepository.saveAndFlush(quiz);
		for (int x = 0; x < 4; x++) {
			int a = (int) (Math.random() * 100);
			int b = (int) (Math.random() * 99) + 1;
			double c = (double) a / b;
			Question question = new Question();
			question.setText("Question " + (x + 1) + ": What does " + a + " / " + b + " equal to?");
			question.setQuiz(quiz);
			questionRepository.saveAndFlush(question);
			int correctAnswer = (int) (Math.random() * 4);
			Random random = new Random();
			for (int y = 0; y < 4; y++) {
				Answer answer = new Answer();
				if (y == correctAnswer) {
					answer.setText(a + " / " + b + " = " + c);
					answer.setCorrect(true);
				} else {
					double wrongAnswer;
					if (random.nextBoolean()) {
						wrongAnswer = ((Math.random() * 10) + a) / b;
					} else {
						wrongAnswer = a / (((Math.random()) * 10) + b + 1);
					}
					answer.setText(a + " / " + b + " = " + wrongAnswer);
					answer.setCorrect(false);
				}
				answer.setQuestion(question);
				answerRepository.saveAndFlush(answer);
			}
		}
	}

}
