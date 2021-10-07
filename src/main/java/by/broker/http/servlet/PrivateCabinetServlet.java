package by.broker.http.servlet;

import by.broker.http.dto.ClientDto;
import by.broker.http.entity.ClientStock;
import by.broker.http.entity.Money;
import by.broker.http.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.broker.http.util.UrlPath.PRIVATE_CABINET;

@WebServlet(PRIVATE_CABINET)
public class PrivateCabinetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ClientDto client = (ClientDto) req.getSession().getAttribute("client");
        List<ClientStock> stocks = client.getStocks();

        Long summaryMoney=0L;
        for (ClientStock stock : stocks) {
            summaryMoney+=stock.getCount()+Long.parseLong(stock.getCostOneStock().toString());
        }
        req.setAttribute("summaryMoney",summaryMoney);

        Long summaryCurrencies=0L;
        List<Money> monies=client.getMonies();
        for (Money money : monies) {
            summaryCurrencies+=money.getCount().longValue();
        }
        req.setAttribute("summaryCurrencies",summaryCurrencies);

        req.getRequestDispatcher(ServletUtil.getFullPath("private-cabinet"))
                .forward(req,resp);

    }
}
