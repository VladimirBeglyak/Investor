package by.broker.http.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StockDto{

    Long id;
    String ticker;
    String name;
    String cost;
    String dividend;
    String currency;


}
