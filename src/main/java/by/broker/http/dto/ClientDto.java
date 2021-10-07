package by.broker.http.dto;

import by.broker.http.entity.ClientStock;
import by.broker.http.entity.Detail;
import by.broker.http.entity.Money;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ClientDto {

    String id;
    String email;
    Detail detail;
    String role;
    List<ClientStock> stocks;
    List<Money> monies;

}
