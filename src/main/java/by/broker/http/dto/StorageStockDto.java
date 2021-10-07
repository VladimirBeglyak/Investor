package by.broker.http.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StorageStockDto {

    String ticker;
    String name;
    String count;
    String costOneStock;
    String country;
    String dividend;
    String currency;
}
