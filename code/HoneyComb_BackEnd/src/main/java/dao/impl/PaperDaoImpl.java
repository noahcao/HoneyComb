package dao.impl;

import dao.PaperDao;
import model.Author;
import model.Paper;
import model.PaperSmall;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

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
    public List<PaperSmall> getPaperSmallByTitle(String name) {
        @SuppressWarnings("unchecked")
        List<PaperSmall> paperSmalls = (List<PaperSmall>) getHibernateTemplate().find(
                "from PaperSmall as p where lower(p.title)=?",
                name);
        return paperSmalls;
    }

    @Override
    public List<Paper> getPaperByTitle(String title) {
        @SuppressWarnings("unchecked")
        List<Paper> papers = (List<Paper>) getHibernateTemplate().find(
                "from Paper as p where lower(p.title)=?", title);
        return papers;
    }

    @Override
    public List<PaperSmall> getPaperSmallLikeTitle(String title) {
        @SuppressWarnings("unchecked")
        List<PaperSmall> paperSmalls = (List<PaperSmall>) getHibernateTemplate().find(
                "from PaperSmall as p where lower(p.title) like ?", "%" + title + "%");
        return paperSmalls;
    }

    @Override
    public Author getAuthorById(Long id) {
        @SuppressWarnings("unchecked")
        List<Author> authors = (List<Author>) getHibernateTemplate().find(
                "from Author as a where a.id=?", id);
        return authors.size() > 0 ? authors.get(0) : null;
    }
}
