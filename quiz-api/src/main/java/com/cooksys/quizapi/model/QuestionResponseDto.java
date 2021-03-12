package com.cooksys.quizapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionResponseDto {
	
	private Long id;
	
	private String text;

}
