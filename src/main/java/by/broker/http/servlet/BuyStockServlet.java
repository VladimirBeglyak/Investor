package by.broker.http.servlet;


import by.broker.http.dao.ClientStockDao;
import by.broker.http.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.broker.http.util.UrlPath.BUY;

@WebServlet(BUY)
public class BuyStockServlet extends HttpServlet {

    private final ClientStockDao clientStockDao = ClientStockDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Long value = Long.valueOf(req.getParameter("buy"));
//        System.out.println(value);
        req.getRequestDispatcher(ServletUtil.getFullPath("buy-stock"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        System.out.println(Integer.parseInt(req.getParameter("count")));


//        ClientStock clientStock = clientStockDao.findById(3L).get();
//        Long oldAmount = clientStock.getCount();
//        clientStock.setCount(oldAmount+Long.parseLong(req.getParameter("count")));

//        long id = Long.parseLong(req.getParameter("id"));
//        System.out.println(id);


//        long amount = Long.parseLong(req.getParameter("amount"));
//        System.out.println(amount);

//        clientStockDao.update(clientStock);
        req.getRequestDispatcher(ServletUtil.getFullPath("private-cabinet"))
                .forward(req,resp);
    }
}
