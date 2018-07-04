package dao;

import model.Paper;

import java.util.List;

public interface PaperDao {

	public Paper getPaperById(int id);

	public List<Paper> queryPapers(String name);

}