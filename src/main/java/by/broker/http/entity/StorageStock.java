package by.broker.http.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class StorageStock {

    private Long id;
    private String ticker;
    private String name;
    private Long count;
    private BigDecimal costOneStock;
    private String country;
    private BigDecimal dividend;
    private Currency currency;

}
