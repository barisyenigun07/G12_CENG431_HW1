package service;

import model.Question;
import model.Quiz;
import java.util.*;

public class QuizService {
	private QuestionService questionService;

	public QuizService(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	public List<Quiz> createQuizzes(){
		List<Quiz> quizzes = new ArrayList<>();
		Random random = new Random();
		int quizNum = random.nextInt(1, 11);
		for (int i = 0; i < quizNum; i++) {
			Quiz quiz = new Quiz();
			quiz.setQuizNumber(i + 1);
			List<Question> questions = questionService.createQuestions();
			quiz.setQuestions(questions);
			quizzes.add(quiz);
		}
		return quizzes;
	}
}
