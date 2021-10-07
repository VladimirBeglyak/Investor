package by.broker.http.mapper;

import by.broker.http.dto.StorageStockDto;
import by.broker.http.entity.Currency;
import by.broker.http.entity.StorageStock;

import java.math.BigDecimal;

public class CreateStorageStockMapper implements Mapper<StorageStockDto, StorageStock>{

    private static CreateStorageStockMapper INSTANCE;

    private CreateStorageStockMapper(){}

    public static CreateStorageStockMapper getInstance() {
        if (INSTANCE==null){
            synchronized (CreateStorageStockMapper.class){
                if (INSTANCE==null){
                    INSTANCE=new CreateStorageStockMapper();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public StorageStock mapFrom(StorageStockDto object) {
        return StorageStock.builder()
                .name(object.getName())
                .ticker(object.getTicker())
                .count(Long.parseLong(object.getCount()))
                .costOneStock(BigDecimal.valueOf(Double.parseDouble(object.getCostOneStock())))
                .dividend(BigDecimal.valueOf(Double.parseDouble(object.getDividend())))
                .country(object.getCountry())
                .currency(Currency.builder()
                        .id(Long.parseLong(object.getCurrency()))
                        .build())
                .build();
    }
}
