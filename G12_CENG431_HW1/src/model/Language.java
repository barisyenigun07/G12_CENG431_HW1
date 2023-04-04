package model;

import java.util.List;

enum LanguageType {
	SPANISH,
	TURKISH,
	GERMAN,
	ITALIAN
}

public class Language {
	private LanguageType languageType;
	private List<Unit> units;
	public Language(LanguageType languageType) {
		
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
	
	public String toString() {
		String str = languageType.toString() + ",";
		for (Unit unit : units) {
			str += unit.toString() + ",";
		}
		return str;
	}
}
