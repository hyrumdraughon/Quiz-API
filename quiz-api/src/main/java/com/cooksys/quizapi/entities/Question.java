package com.cooksys.quizapi.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Question {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String text;
	
	@ManyToOne
	@JoinColumn
	private Quiz quiz;
	
	@OneToMany(mappedBy="question", orphanRemoval=true)
	private List<Answer> answers;

}
