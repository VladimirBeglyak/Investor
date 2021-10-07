package by.broker.http.mapper;

import by.broker.http.dto.StockDto;
import by.broker.http.entity.ClientStock;

public class ListStockMapper implements Mapper<ClientStock, StockDto> {

    private static ListStockMapper INSTANCE=null;

    private ListStockMapper(){}

    public static ListStockMapper getInstance() {
        if (INSTANCE==null){
            synchronized (ListStockMapper.class){
                if (INSTANCE==null){
                    INSTANCE=new ListStockMapper();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public StockDto mapFrom(ClientStock object) {
        return StockDto.builder()
                .id(object.getId())
                .name(object.getName())
                .ticker(object.getTicker())
                .cost(object.getCostOneStock().toString())
                .dividend(object.getDividend().toString())
                //.currency(object.getCurrency().name())
                .build();
    }
}
