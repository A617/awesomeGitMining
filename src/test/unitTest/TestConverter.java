package test.unitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import main.business.dto.Converter;
import main.dao.entity.Repository;
import main.vo.RepositoryVO;

/**
 *@author tj
 *@date 2016年3月2日
 */
public class TestConverter {
	@Test
	public void test() {
		
		
		Map<String, Integer> languages = new HashMap<String,Integer>();
		languages.put("java", 100);
		
		List<String> forks = new ArrayList<String>();
		forks.add("fork1");
		forks.add("fork2");
		
		Repository po = new Repository();
		po.setName("awesome");
		po.setClone_url("www");
		po.setLanguages(languages);
		po.setForks_fullname(forks);
		
		RepositoryVO vo = new RepositoryVO();
		vo.setName("awesome");
		vo.setClone_url("www");
		vo.setLanguages(languages);
		vo.setForks_fullname(forks);
		
		RepositoryVO result = (RepositoryVO) Converter.convert("RepositoryVO", po);
		assertEquals(vo.getForks_fullname().get(0),result.getForks_fullname().get(0));
		assertEquals(vo.getLanguages().get(0),result.getLanguages().get(0));
		assertEquals(vo.getName(),result.getName());
	}

}
