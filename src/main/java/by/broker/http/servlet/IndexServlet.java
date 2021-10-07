package by.broker.http.servlet;

import by.broker.http.service.StorageStockService;
import by.broker.http.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    private final StorageStockService storageStockService=StorageStockService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("storage_stocks",storageStockService.findAll());
        req.getRequestDispatcher(ServletUtil.getFullPath("index"))
                .forward(req,resp);
    }
}
