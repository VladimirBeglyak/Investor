package by.broker.http.mapper;

import by.broker.http.dto.ClientDto;
import by.broker.http.entity.Client;

public class ClientMapper implements Mapper<Client, ClientDto>{

    private static ClientMapper INSTANCE;

    private ClientMapper(){}

    public static ClientMapper getInstance() {
        if (INSTANCE==null){
            synchronized (ClientMapper.class){
                if (INSTANCE==null){
                    INSTANCE=new ClientMapper();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public ClientDto mapFrom(Client object) {
        return ClientDto.builder()
                .id(String.valueOf(object.getId()))
                .email(object.getEmail())
                .detail(object.getDetail())
                .role(object.getRole().name())
                .stocks(object.getStocks())
                .monies(object.getMonies())
                .build();
    }
}
