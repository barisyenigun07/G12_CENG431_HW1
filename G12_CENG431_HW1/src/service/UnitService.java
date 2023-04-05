package service;

import model.Unit;
import model.Quiz;
import java.util.*;

public class UnitService {
	private QuizService quizService;
	
	public UnitService(QuizService quizService) {
		this.quizService = quizService;
	}
	
	public List<Unit> createUnits(){
		List<Unit> units = new ArrayList<>();
		Random random = new Random();
		int unitNum = random.nextInt(60, 101);
		for (int i = 0; i < unitNum; i++) {
			Unit unit = new Unit();
			int unitNumber = i + 1;
			List<Quiz> quizzes = quizService.createQuizzes();
			unit.setUnitNumber(unitNumber);
			unit.setQuizzes(quizzes);
			units.add(unit);
		}
		return units;
	}
}
