package by.broker.http.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Client {

    private Long id;
    private String email;
    private String password;
    private Role role;
    private Detail detail;
    private List<ClientStock> stocks;
    private List<Money> monies;

}
