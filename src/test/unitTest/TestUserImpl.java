package test.unitTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.business.impl.user.UserServiceImpl;
import main.vo.UserVO;

public class TestUserImpl {
	UserServiceImpl user=UserServiceImpl.getInstance();
	List<UserVO> list=new ArrayList<UserVO>();
	@Test
	public void testsearchUser(){
		String id ="Tj";
		try{
			assertEquals(list,user.searchUser(id));
		}catch(Exception e){
			e.getStackTrace();
		}
	}
	@Test
	public void testGetUser(){
		try{
			String id2="tj";
			UserVO u1=new UserVO();
			assertEquals(u1,user.getUser(id2));
		}catch(Exception e){
			e.getStackTrace();
		}
	}
	@Test
	public void testGetContributeRepos(){
		try{
			String id3="tJ";
			List<String>list=new ArrayList<String>();
			assertEquals(list,user.getContributeRepos(id3));
		}catch(Exception e){
			e.getStackTrace();
		}
	}
	@Test
	public void getCreateRepos(){
		try{
			String id4="TJ";
			List<String>list2=new ArrayList<String>();
			assertEquals(list2,user.getCreateRepos(id4));
		}catch(Exception e){
			e.getStackTrace();
		}
	}

}
