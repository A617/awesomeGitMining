package main.business.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tj
 * @date 2016年3月7日
 */
public class LocalHelper {

	public static List<String> getRepos(String path) {
		path = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/"+path+".txt";
		List<String> list = new ArrayList<String>();
		File file = new File(path);
		FileReader fr = null;
		BufferedReader reader = null;
		try {
			fr = new FileReader(file);
			reader = new BufferedReader(fr);
			String line = reader.readLine();
			while (line != null) {
				list.add(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}
}
