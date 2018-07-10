package dao;

import model.Paper;

import java.util.List;

public interface PaperDao {

	public Paper getPaperById(long id);

	public List<Paper> queryPapers(String name);

}