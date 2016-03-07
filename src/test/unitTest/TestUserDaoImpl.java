package test.unitTest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import main.dao.entity.User;
import main.dao.impl.UserDaoImpl;

public class TestUserDaoImpl {
	
	UserDaoImpl user = new UserDaoImpl();
	
	@Test
	public void testGetUser() {
		try {
			User user1 = user.getUser("woodmanzee");
			assertEquals(4,user1.getFollowing());
			assertEquals("woodmanzee@github.com",user1.getEmail());
			
			User user2 = user.getUser("abcdser");
			assertEquals(null, user2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchUser() {
		//TODO
		List<String> list = user.searchUser("tj");
		assertEquals(14,list.size());
	}

}
