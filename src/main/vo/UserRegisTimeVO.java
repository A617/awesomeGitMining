package main.vo;

import main.dao.entity.Statistics;

public class UserRegisTimeVO {
	private int[] nums;
	private String[] years;

	// bad coding
	public UserRegisTimeVO() {
		years = Statistics.getInstance().getYear();
	}

	public int[] getNums() {
		return nums;
	}

	public void setNums(int[] nums) {
		this.nums = nums;
	}

	public String[] getYears() {
		return years;
	}

	public void setYears(String[] years) {
		this.years = years;
	}

}
