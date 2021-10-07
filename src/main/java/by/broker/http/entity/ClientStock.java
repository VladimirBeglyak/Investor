package by.broker.http.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ClientStock {

    private Long id;
    private String ticker;
    private String name;
    private Long count;
    private BigDecimal costOneStock;
    private Operation operation;
    private String county;
    private BigDecimal dividend;
    private String currency;

}
