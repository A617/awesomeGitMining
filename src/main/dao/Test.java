package main.dao;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.dao.entity.Language;

public class Test {

	public static void main(String[] args) {

		long startTime = System.nanoTime();
		
/*
		Process proc;
		try {
			String cmdstring = "chmod a+x test.sh";
			 proc = Runtime.getRuntime().exec(cmdstring);
			 proc.waitFor(); //阻塞，直到上述命令执行完
			 String cmdstring = "G:/awesomeGitMining/src/main/dao/test.bat"; //这里也可以是ksh等
			 proc = Runtime.getRuntime().exec(cmdstring);
			 
		//	proc = Runtime.getRuntime().exec("bash test.sh");

			BufferedImage bi = ImageIO.read(proc.getInputStream());
			System.out.println(bi);
			File f = new File("G:/image.png");
			ImageIO.write(bi, "png", f);

			
			BufferedReader bfr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			String line = "";
			while((line = bfr.readLine()) != null) {
				// display each output line form python script
				System.out.println(line);
			}
			
			proc.waitFor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		*/
		
		
		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) + " ns");
	}

}
