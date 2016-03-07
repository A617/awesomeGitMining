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
	public void testGetBranches() {
		try {
			List<Branch> repo1 = repo.getBranches("awesome_nested_set");
			assertEquals(4,repo1.size());
			
			List<Branch> repo2 = repo.getBranches("clickatell");
			assertEquals(3,repo2.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetContributors() {
		try {
			List<Contributor> repo1 = repo.getContributors("rush");
			assertEquals(5,repo1.size());
			
			List<Contributor> repo2 = repo.getContributors("functional-javascript");
			assertEquals(6,repo2.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetCollaborators() {
		try {
			List<Collaborator> repo1 = repo.getCollaborators("grit");
			assertEquals(9,repo1.size());
			
			List<Collaborator> repo2 = repo.getCollaborators("clickatell");
			assertEquals(1,repo2.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetForks() {
		try {
			List<Fork> repo1 = repo.getForks("grit");
			assertNotNull(repo1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetOwner() {
		try {
			Owner repo1 = repo.getOwner("mojombo");
			assertEquals("https://github.com/mojombo",repo1.getHtml_url());
			
			Owner repo2 = repo.getOwner("awesome_nested_set");
			assertEquals("collectiveidea",repo2.getLogin());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetLanguages() {
		try {
			List<Branch> repo1 = repo.getBranches("awesome_nested_set");
			assertEquals(4,repo1.size());
			
			List<Branch> repo2 = repo.getBranches("clickatell");
			assertEquals(3,repo2.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchRepository() {
		List<String> list = repo.searchRepository("awesome");
		Assert.assertNotNull(list);
	}
}
