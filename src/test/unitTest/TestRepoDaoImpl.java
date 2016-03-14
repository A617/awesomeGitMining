package test.unitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import main.dao.entity.Branch;
import main.dao.entity.Collaborator;
import main.dao.entity.Contributor;
import main.dao.entity.Fork;
import main.dao.entity.Owner;
import main.dao.entity.Repository;
import main.dao.impl.RepoDaoImpl;

public class TestRepoDaoImpl {
	
	RepoDaoImpl repo = new RepoDaoImpl();

	@Test
	public void testGetRepository() {
		try {
			Repository repo1 = repo.getRepository("awesome_nested_set");
			assertEquals("collectiveidea",repo1.getOwner_name());
			
			Repository repo2 = repo.getRepository("clickatell");
			assertEquals(62,repo2.getForks_count());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllRepo() {
		List<String> list = repo.getAllRepo();
		Assert.assertNotNull(list);
	}
	
	@Test
	public void testSearchRepository() {
		List<String> list = repo.searchRepository("awesome");
		Assert.assertNotNull(list);
	}
}
