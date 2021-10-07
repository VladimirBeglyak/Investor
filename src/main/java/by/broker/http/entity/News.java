package by.broker.http.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class News {

    private Long id;
    private String text;
    private LocalDateTime date;
    private StorageStock storageStock;

}
