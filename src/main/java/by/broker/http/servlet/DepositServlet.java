package by.broker.http.servlet;

import by.broker.http.dao.CurrencyDao;
import by.broker.http.dao.MoneyDao;
import by.broker.http.dto.ClientDto;
import by.broker.http.entity.Client;
import by.broker.http.entity.Currency;
import by.broker.http.entity.Money;
import by.broker.http.entity.StatusMoney;
import by.broker.http.util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet("/deposit")
public class DepositServlet extends HttpServlet {

    private final MoneyDao moneyDao = MoneyDao.getInstance();
    private final CurrencyDao currencyDao = CurrencyDao.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currencies",currencyDao.findAll());
        req.getRequestDispatcher(ServletUtil.getFullPath("deposit"))
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String currency = req.getParameter("currency");
        ClientDto client = (ClientDto) req.getSession().getAttribute("client");

        moneyDao.save(Money.builder()
                .count(BigDecimal.valueOf(Long.parseLong(req.getParameter("count"))))
                .currency(Currency.builder()
                        .id(Long.parseLong(req.getParameter("currency")))
                        .build())
                .client(Client.builder()
                        .id(Long.parseLong(client.getId()))
                        .build())
                .statusMoney(StatusMoney.DEPOSIT)
                .date(LocalDate.now())
                .build());

        resp.sendRedirect("/private");
    }

}
