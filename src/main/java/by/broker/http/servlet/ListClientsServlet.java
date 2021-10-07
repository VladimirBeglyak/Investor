package by.broker.http.servlet;

import by.broker.http.service.ClientService;
import by.broker.http.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.broker.http.util.UrlPath.ALL_CLIENTS;

@WebServlet(ALL_CLIENTS)
public class ListClientsServlet extends HttpServlet {

    private final ClientService clientService=ClientService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allClients",clientService.findAll());
        req.getRequestDispatcher(ServletUtil.getFullPath("all-clients"))
                .forward(req,resp);
    }
}
