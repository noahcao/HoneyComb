package dao.impl;

import dao.PaperDao;
import model.Paper;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class PaperDaoImpl extends HibernateDaoSupport implements PaperDao {

    public Paper getPaperById(long id) {
        @SuppressWarnings("unchecked")
        List<Paper> papers = (List<Paper>) getHibernateTemplate().find(
                "from Paper as p where p.id=?", id);
        Paper paper = papers.size() > 0 ? papers.get(0) : null;
        return paper;
    }

    @Override
    public List<Paper> queryPapers(String name) {
        /* need to implement tf-idf */
        @SuppressWarnings("unchecked")
        List<Paper> papers = (List<Paper>) getHibernateTemplate().find(
                "from Paper as p where p.title like ?",
                "%" + name + "%");
        return papers;
    }
}
