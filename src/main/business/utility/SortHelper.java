package main.business.utility;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.vo.RepositoryVO;

/**
 * @author tj
 * @date 2016年3月1日
 */
public class SortHelper {
	public static List<RepositoryVO> sortReposByStar(List<RepositoryVO> vos) {
		Collections.sort(vos, new Comparator<RepositoryVO>() {
			public int compare(RepositoryVO arg0, RepositoryVO arg1) {
				return arg0.getSubscribers_count() - arg1.getSubscribers_count();
			}
		});
		return vos;
	}

	public static List<RepositoryVO> sortReposByFork(List<RepositoryVO> vos) {
		Collections.sort(vos, new Comparator<RepositoryVO>() {
			public int compare(RepositoryVO arg0, RepositoryVO arg1) {
				return arg0.getForks_count() - arg1.getForks_count();
			}
		});
		return vos;
	}

	public static List<RepositoryVO> sortReposByContribute(List<RepositoryVO> vos) {
		Collections.sort(vos, new Comparator<RepositoryVO>() {
			public int compare(RepositoryVO arg0, RepositoryVO arg1) {
				return arg0.getContributors_login().size() - arg1.getContributors_login().size();
			}
		});
		return vos;
	}
}