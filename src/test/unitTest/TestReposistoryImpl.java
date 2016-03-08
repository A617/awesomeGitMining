package test.unitTest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import main.business.impl.repository.RepositoryServiceImpl;
import main.vo.RepositoryVO;

public class TestReposistoryImpl {
	RepositoryServiceImpl repo=RepositoryServiceImpl.getInstance();
	List<RepositoryVO> repoList;
	int startIndex=0;
	@Test
	public void testSearchRepository(){
		try{
			String id ="TJ";
			RepositoryVO re = null;
			repoList.add(re);
			assertEquals(repoList,repo.searchRepository(id));

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void testShowRepositories(){
		try{
			assertEquals(repoList,repo.showRepositories(startIndex));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Test
	public void showReposByStar(){
		try{
			assertEquals(repoList,repo.showReposByStar(startIndex));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void showReposByFork(){
		try{
			assertEquals(repoList,repo.showReposByFork(startIndex));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void showReposByContribute(){
		try{
			assertEquals(repoList,repo.showReposByContribute(startIndex));
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}
