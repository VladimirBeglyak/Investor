package by.broker.http.entity;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Detail {

    private Long id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String citizenship;
    private LocalDate birthday;
    private String passportCode;
    private String phoneNumber;

}
