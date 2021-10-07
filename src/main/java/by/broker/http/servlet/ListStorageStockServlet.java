package by.broker.http.servlet;

import by.broker.http.service.StorageStockService;
import by.broker.http.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.broker.http.util.UrlPath.STOCKS;

@WebServlet(STOCKS)
public class ListStorageStockServlet extends HttpServlet {

    private final StorageStockService storageStockService =StorageStockService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("stocks", storageStockService.findAll());
        req.getRequestDispatcher(ServletUtil.getFullPath("stocks"))
                .forward(req,resp);
    }
}
