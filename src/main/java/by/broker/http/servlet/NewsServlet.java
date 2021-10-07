package by.broker.http.servlet;

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

@WebServlet(NEWS)
public class NewsServlet extends HttpServlet {

    private final NewsService newsService=NewsService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("news",newsService.findAll());
        req.getRequestDispatcher(ServletUtil.getFullPath("all-news"))
                .forward(req,resp);
    }
}
