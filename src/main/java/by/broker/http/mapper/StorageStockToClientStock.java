package by.broker.http.mapper;

import by.broker.http.entity.ClientStock;
import by.broker.http.entity.StorageStock;

public class StorageStockToClientStock implements Mapper<StorageStock, ClientStock>{


    private static StorageStockToClientStock INSTANCE = null;

    private StorageStockToClientStock() {
    }

    public static StorageStockToClientStock getInstance() {
        if (INSTANCE == null) {
            synchronized (StorageStockToClientStock.class) {
                if (INSTANCE == null) {
                    INSTANCE = new StorageStockToClientStock();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public ClientStock mapFrom(StorageStock object) {
        return ClientStock.builder()
                .ticker(object.getTicker())
                .name(object.getName())
                .costOneStock(object.getCostOneStock())
                .county(object.getCountry())
                .dividend(object.getDividend())
                .currency(object.getCurrency().getTicker())
                .build();
    }
}
