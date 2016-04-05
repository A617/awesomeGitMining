package main.business.utility;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.vo.LanguageStatisticsVO;
import main.vo.RepositoryVO;

/**
 * @author tj
 * @date 2016年3月1日
 */
public class SortHelper {
	public static List<RepositoryVO> sortReposByStar(List<RepositoryVO> vos) {
		Collections.sort(vos, new Comparator<RepositoryVO>() {
			public int compare(RepositoryVO arg0, RepositoryVO arg1) {
				return arg1.getSubscribers_count() - arg0.getSubscribers_count();
			}
		});
		return vos;
	}

	public static List<RepositoryVO> sortReposByFork(List<RepositoryVO> vos) {
		Collections.sort(vos, new Comparator<RepositoryVO>() {
			public int compare(RepositoryVO arg0, RepositoryVO arg1) {
				return arg1.getForks_count() - arg0.getForks_count();
			}
		});
		return vos;
	}

	public static List<RepositoryVO> sortReposByContribute(List<RepositoryVO> vos) {
		Collections.sort(vos, new Comparator<RepositoryVO>() {
			public int compare(RepositoryVO arg0, RepositoryVO arg1) {
				return arg1.getContributors_login().size() - arg0.getContributors_login().size();
			}
		});
		return vos;
	}

	public static LanguageStatisticsVO sortLanguageStatistics(LanguageStatisticsVO vo) {
		int[] nums = vo.getLanguageNum();
		String[] types = vo.getLanguageType();
		for (int i = nums.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (nums[j] < nums[j + 1]) {
					int temp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = temp;
					String tempType = types[j];
					types[j] = types[j + 1];
					types[j + 1] = tempType;
				}
			}
		}
		vo.setLanguageNum(nums);
		vo.setLanguageType(types);
		return vo;
	}
}