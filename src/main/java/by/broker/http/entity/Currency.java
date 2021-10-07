package by.broker.http.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Currency {

    private Long id;
    private String ticker;
    private String name;

}
