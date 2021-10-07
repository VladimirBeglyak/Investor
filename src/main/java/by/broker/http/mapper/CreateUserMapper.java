package by.broker.http.mapper;

import by.broker.http.dto.StockDto;
import by.broker.http.entity.ClientStock;

import java.math.BigDecimal;

public class CreateUserMapper implements Mapper<StockDto, ClientStock>{

    private static CreateUserMapper INSTANCE=null;

    private CreateUserMapper(){}

    public static CreateUserMapper getInstance() {
        if (INSTANCE==null){
            synchronized (CreateUserMapper.class){
                if (INSTANCE==null){
                    INSTANCE=new CreateUserMapper();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public ClientStock mapFrom(StockDto object) {
        return ClientStock.builder()
                .name(object.getName())
                .ticker(object.getTicker())
                .costOneStock(BigDecimal.valueOf(Double.parseDouble(object.getCost())))
                .dividend(BigDecimal.valueOf(Double.parseDouble(object.getDividend())))
                //.currency(Currency.valueOf(object.getCurrency()))
                .build();
    }
}
