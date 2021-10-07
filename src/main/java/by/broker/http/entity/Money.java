package by.broker.http.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
public class Money {

    private Integer id;
    private BigDecimal count;
    private Currency currency;
    private Client client;
    private StatusMoney statusMoney;
    private LocalDate date;

}
