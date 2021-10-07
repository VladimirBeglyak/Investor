package by.broker.http.servlet;

import by.broker.http.entity.News;
import by.broker.http.service.NewsService;
import by.broker.http.util.ServletUtil;
import by.broker.http.util.UrlPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.broker.http.util.UrlPath.*;

@WebServlet(ADD_NEWS)
public class AddNewsToStockServlet extends HttpServlet {

    private final NewsService newsService=NewsService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ServletUtil.getFullPath("add-news"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        newsService.save(News.builder()
                .text(req.getParameter("addNews"))
                .build());
        resp.sendRedirect(STOCKS);
    }
}
