package main.business;

import main.business.dto.Converter;
import main.dao.po.UserPO;
import main.vo.UserVO;

/**
 * @author tj
 * @date 2016年3月1日
 */
public class TestMain {
	public static void main(String args[]) {
		UserPO po = new UserPO("id", "tj", "www", "nanjing", "qq", "sinablog", 
				null, null, null, 2, 2, "2015", "2016");
		UserVO vo = null;
		vo = (UserVO) Converter.convert("UserVO", po);
		System.out.println(vo.getLocation());
	}
}
