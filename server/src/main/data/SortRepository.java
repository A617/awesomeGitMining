package main.data;

/**
 * @author tj
 * @date 2016年3月7日
 */
public class SortRepository {
	/*private IRepoDao repoDao;
	private String starPath = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo_starSort.txt";
	private String contriPath = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo_contriSort.txt";
	private String forkPath = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo_forkSort.txt";
	private List<RepositoryVO> list;

	public static void main(String[] args) {
		SortRepository sr = new SortRepository();
		sr.sortRepos();
	}

	public void sortRepos() {
		repoDao = DataFactory.getRepoDataInstance();
		List<String> names = repoDao.getAllRepo();
		list = new ArrayList<RepositoryVO>();
		if (names != null) {
			for (String name : names) {
				Repository po = null;
				try {
					po = repoDao.getRepository(name);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (po != null) {
					list.add((RepositoryVO) Converter.convert("RepositoryVO", po));
				}
			}
			list = SortHelper.sortReposByContribute(list);
			write2local(contriPath);
			list = SortHelper.sortReposByStar(list);
			write2local(starPath);
			list = SortHelper.sortReposByFork(list);
			write2local(forkPath);
		}

	}

	public void write2local(String path) {
		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter writer = null;
		try {
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);
			for (RepositoryVO vo : list) {
				writer.write(vo.getFull_name() + "\n");
				writer.flush();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}*/
}
