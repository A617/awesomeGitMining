package main.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tj
 * @date 2016年3月4日
 */
public class SearchHelper {
	/**
	 * 
	 * @param data
	 * @param match
	 * @return
	 */
	public static List<String> fuzzySearch(List<String> data, String match) {
		List<String> result = new ArrayList<String>();
		Pattern pattern = Pattern.compile(match+".*");
		if (data != null) {
			for (String str : data) {
				Matcher matcher = pattern.matcher(str);
				if(matcher.find()){
					result.add(str);
					System.out.println(str);
				}
				
			}
		}
		return result;
	}
}
