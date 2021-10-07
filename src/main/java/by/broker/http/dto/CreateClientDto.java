package by.broker.http.dto;

import by.broker.http.entity.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateClientDto {

    String email;
    String password;
    String role= Role.USER.name();
    String firstName;
    String lastName;
    String fatherName;
    String citizenship;
    String birthday;
    String passportCode;
    String phoneNumber;

}
