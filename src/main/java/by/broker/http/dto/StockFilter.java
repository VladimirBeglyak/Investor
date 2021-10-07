package by.broker.http.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StockFilter {
    int limit;
    int offset;
    String name;
    String currency;
}
