package action;

import com.opensymphony.xwork2.ActionSupport;
import model.Book;
import service.AppService;

import java.util.List;

public class AllBooksAction extends ActionSupport {
    private List<Book> goods;

    public void setGoods(List<Book> goods) {
        this.goods = goods;
    }

    public List<Book> getGoods() {
        return goods;
    }

    private AppService appService;

    public void setAppService(AppService appService) {
        this.appService = appService;
    }

    @Override
    public String execute() throws Exception {
        List<Book> goods = appService.getAllBooks();
        setGoods(goods);
        return SUCCESS;
    }
}
