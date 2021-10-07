package by.broker.http.mapper;

import by.broker.http.dto.CreateClientDto;
import by.broker.http.entity.Client;
import by.broker.http.entity.Detail;
import by.broker.http.entity.Role;
import by.broker.http.util.LocaleDateFormatter;

public class CreateClientMapper implements Mapper<CreateClientDto, Client>{

    private static CreateClientMapper INSTANCE;

    private CreateClientMapper(){}

    public static CreateClientMapper getINSTANCE() {
        if (INSTANCE==null){
            synchronized (CreateClientMapper.class){
                if (INSTANCE==null){
                    INSTANCE=new CreateClientMapper();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Client mapFrom(CreateClientDto object) {
        return Client.builder()
                .email(object.getEmail())
                .password(object.getPassword())
                .role(Role.valueOf(object.getRole()))
                .detail(Detail.builder()
                        .firstName(object.getFirstName())
                        .lastName(object.getLastName())
                        .fatherName(object.getFatherName())
                        .citizenship(object.getCitizenship())
                        .passportCode(object.getPassportCode())
                        .birthday(LocaleDateFormatter.format(object.getBirthday()))
                        .phoneNumber(object.getPhoneNumber())
                        .build())
                .build();
    }
}
