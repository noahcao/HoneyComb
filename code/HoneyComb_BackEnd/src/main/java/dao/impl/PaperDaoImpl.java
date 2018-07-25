package dao.impl;

import dao.PaperDao;
import javafx.util.Pair;
import model.Author;
import model.Paper;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import tfidf.Tfidf;

import java.util.*;

public class PaperDaoImpl extends HibernateDaoSupport implements PaperDao {

    @Override
    public Paper getPaperById(long id) {
        @SuppressWarnings("unchecked")
        List<Paper> papers = (List<Paper>) getHibernateTemplate().find(
                "from Paper as p where p.id=?", id);
        return papers.size() > 0 ? papers.get(0) : null;
    }

    @Override
    public List<String> queryTitles(String name) {
        @SuppressWarnings("unchecked")
        List<String> titles = (List<String>) getHibernateTemplate().find(
                "select p.title from Paper as p where lower(p.title) like ?",
                "%" + name + "%");
        return titles;
    }

    @Override
    public List<Paper> getPaperByTitle(String title) {
        @SuppressWarnings("unchecked")
        List<Paper> papers = (List<Paper>) getHibernateTemplate().find(
                "select p.title, p.from Paper as p where lower(p.title)=?", title);
        return papers;
    }

    @Override
    public Author getAuthorById(Long id) {
        @SuppressWarnings("unchecked")
        List<Author> authors = (List<Author>) getHibernateTemplate().find(
                "from Author as a where a.id=?", id);
        return authors.size() > 0 ? authors.get(0) : null;
    }
}
