package by.broker.http.service;

import by.broker.http.dao.ClientDao;
import by.broker.http.dao.DetailDao;
import by.broker.http.dto.ClientDto;
import by.broker.http.dto.CreateClientDto;
import by.broker.http.dto.StorageStockDto;
import by.broker.http.entity.Client;
import by.broker.http.entity.Detail;
import by.broker.http.mapper.ClientMapper;
import by.broker.http.mapper.CreateClientMapper;
import by.broker.http.validator.CreateClientValidator;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class ClientService {

    private static ClientService INSTANCE;

    private ClientService(){}

    private final ClientDao clientDao=ClientDao.getInstance();
    private final ClientMapper clientMapper = ClientMapper.getInstance();
    private final CreateClientMapper createClientMapper=CreateClientMapper.getINSTANCE();
    private final CreateClientValidator createClientValidator=CreateClientValidator.getInstance();
    private final DetailDao detailDao=DetailDao.getInstance();

    public Optional<ClientDto> login(String email,String password){
        return clientDao.findByEmailAndPassword(email,password)
                .map(client -> clientMapper.mapFrom(client));
    }

    public Long save(CreateClientDto dto){

        Client client = createClientMapper.mapFrom(dto);
        Client savedClient = clientDao.save(client);
        Detail savedDetail = detailDao.save(client.getDetail());
        savedClient.setDetail(savedDetail);
        clientDao.update(savedClient);
        return clientDao.findById(savedClient.getId()).get().getId();

//        ValidationResult validationResult = createClientValidator.isValid(dto);
//        if (!validationResult.isValid()){
//            throw new ValidationException(validationResult.getErrors());
//        }
//        Client clientEntity = createClientMapper.mapFrom(dto);
//        clientDao.save(clientEntity);
//
//        return null;//clientEntity.getId();
    }

    public List<ClientDto> findAll(){
        return clientDao.findAll()
                .stream().map(client -> clientMapper.mapFrom(client))
                .collect(toList());
    }

    public void buyStock(StorageStockDto storageStockDto){
    }

    public static ClientService getInstance() {
        if (INSTANCE==null){
            synchronized (ClientService.class){
                if (INSTANCE==null){
                    INSTANCE=new ClientService();
                }
            }
        }
        return INSTANCE;
    }
}
