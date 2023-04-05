package model;

import java.util.List;
import java.util.ArrayList;

public class Language {
	
	private LanguageType languageType;
	private List<Unit> units;
	
	public Language(LanguageType languageType) {
		this.units = new ArrayList<>();
		this.languageType = languageType;
	}
	
	public LanguageType getLanguageType() {
		return languageType;
	}
	
	public void setLanguageType(LanguageType languageType) {
		this.languageType = languageType;
	}
	
	public List<Unit> getUnits() {
		return units;
	}
	
	public void setUnits(List<Unit> units) {
		this.units = units;
	}
	
	public void addUnit(Unit unit) {
		this.units.add(unit);
	}
	
	public String toString() {
		String str = languageType.toString() + ",";
		for (Unit unit : units) {
			str += unit.toString() + ",";
		}
		return str;
	}
}
