package by.broker.http.mapper;

import by.broker.http.dto.StorageStockDto;
import by.broker.http.entity.StorageStock;

import java.util.Optional;

public class StorageStockByIdMapper implements Mapper<Optional<StorageStock>, StorageStockDto> {

    private static StorageStockByIdMapper INSTANCE = null;

    private StorageStockByIdMapper() {
    }

    public static StorageStockByIdMapper getInstance() {
        if (INSTANCE == null) {
            synchronized (StorageStockByIdMapper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new StorageStockByIdMapper();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public StorageStockDto mapFrom(Optional<StorageStock> object) {
        return object.map(stock ->
                StorageStockDto.builder()
                        .name(stock.getName())
                        .country(stock.getCountry())
                        .count(String.valueOf(stock.getCount()))
                        .ticker(stock.getTicker())
                        .costOneStock(stock.getCostOneStock().toString())
                        .dividend(stock.getDividend().toString())
                        .currency(stock.getCurrency().getTicker())
                        .build()).get();
    }
}
