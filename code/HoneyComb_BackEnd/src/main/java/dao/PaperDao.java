package dao;

import model.Author;
import model.Paper;

import java.util.List;

public interface PaperDao {

    Paper getPaperById(long id);

    List<String> queryTitles(String name);

    List<Paper> getPaperByTitle(String title);

    Author getAuthorById(Long id);

}