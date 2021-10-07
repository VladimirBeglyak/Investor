package by.broker.http.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClientFilter {
    int limit;
    int offset;
    String firstName;
    String lastName;
    String email;
}
