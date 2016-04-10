package org.Common.vo;

import org.Common.po.Statistics;

public class LanguageStatisticsVO {
	private int[] languageNum;
	private String[] languageType;
	
	public LanguageStatisticsVO() {
		languageType = Statistics.language.clone();
	}

	public int[] getLanguageNum() {
		return languageNum;
	}

	public void setLanguageNum(int[] languageNum) {
		this.languageNum = languageNum;
	}

	public String[] getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String[] languageType) {
		this.languageType = languageType;
	}

}
