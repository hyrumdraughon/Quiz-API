package com.cooksys.quizapi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Answer {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String text;
	
	@Column(nullable=false)
	private boolean isCorrect;
	
	@ManyToOne
	@JoinColumn
	private Question question;

}
