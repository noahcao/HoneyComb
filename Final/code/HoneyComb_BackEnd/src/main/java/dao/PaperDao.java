package dao;

import model.Author;
import model.Paper;
import model.PaperSmall;

import java.util.List;

public interface PaperDao {

    Paper getPaperById(long id);

    List<PaperSmall> getPaperSmallByTitle(String name);

    List<Paper> getPaperByTitle(String title);

    List<PaperSmall> getPaperSmallLikeTitle(String title);

    Author getAuthorById(Long id);

}