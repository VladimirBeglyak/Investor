package by.broker.http.runner;

import by.broker.http.dao.*;
import by.broker.http.entity.News;
import by.broker.http.mapper.StorageStockByIdMapper;
import by.broker.http.mapper.StorageStockToClientStock;
import by.broker.http.service.StorageStockService;

import java.time.LocalDateTime;
import java.util.List;

public class DaoRunner {
    public static void main(String[] args) {

        CurrencyDao currencyDao = CurrencyDao.getInstance();
        ClientStockDao stockDao = ClientStockDao.getInstance();
        DetailDao detailDao = DetailDao.getInstance();
        ClientDao clientDao = ClientDao.getInstance();
        MoneyDao moneyDao=MoneyDao.getInstance();
        StorageStockDao storageStockDao=StorageStockDao.getInstance();
        StorageStockToClientStock storageStockToClientStock=StorageStockToClientStock.getInstance();
        ClientStockDao clientStockDao=ClientStockDao.getInstance();
        StorageStockByIdMapper storageStockByIdMapper=StorageStockByIdMapper.getInstance();
        StorageStockService storageStockService=StorageStockService.getInstance();
        NewsDao newsDao=NewsDao.getINSTANCE();

//*****************************************************************************************************
//сохранение пользователя и его личных данных


//        Client client = Client.builder()
//                .email("vladimir.beglyak@mail.ru")
//                .password("qwerty")
//                .role(Role.USER)
//                .build();
//
//        Client savedClient = clientDao.save(client);
//        System.out.println(savedClient);
//
//
//
//
//        Detail detail = Detail.builder()
//                .firstName("Vladimir")
//                .lastName("Beglyak")
//                .fatherName("Vladimir")
//                .citizenship("Germany")
//                .birthday(LocalDate.of(1991, 10, 12))
//                .passportCode("3121091E005PB8")
//                .clientId(savedClient)
//                .build();
//
//
//
//        Detail savedDetail = detailDao.save(detail);
//        System.out.println(savedDetail);


//        boolean delete = detailDao.delete(3L);
//        System.out.println(delete);

//*******************************************************************************************************


        /*Optional<Client> maybeClient = clientDao.findById(5L);
        Optional<Stock> stock1 = stockDao.findById(1L);
        Optional<Stock> stock2 = stockDao.findById(2L);

        clientDao.addStockToClient(maybeClient.get(),stock1.get());
        clientDao.addStockToClient(maybeClient.get(),stock2.get());*/

       /* Optional<Client> byId = clientDao.findById(5L);
        System.out.println(byId);*/

//        Optional<Client> byId = clientDao.findById(5L);
//        Client client = byId.get();
//        System.out.println(client);
//
//        Money money = Money.builder()
//                .amount(BigDecimal.valueOf(150000))
//                .currency(currencyDao.findById(3L).orElse(null))
//                .build();
//
//        money.setClient(client);
//        System.out.println(money);
//
//        moneyDao.save(money);
//

//        List<Money> allByClientId = moneyDao.findAllByClientId(5L);
//        System.out.println(allByClientId);
//        for (Money money : allByClientId) {
//            System.out.println(money);
//        }
//
//        Optional<Client> byId = clientDao.findById(5L);
//        System.out.println(byId);


//        Optional<Currency> byId = currencyDao.findById(1L);
//        System.out.println(byId);

//*************************************************************

//        Client savedClient = clientDao.save(Client.builder()
//                .email("vladimir.beglyak@mail.ru")
//                .password("qwerty")
//                .build());
//
//        System.out.println(savedClient);
//
//        savedClient.setDetail(Detail.builder()
//                .firstName("vladimir")
//                .lastName("Beglyak")
//                .fatherName("Vladimir")
//                .birthday(LocalDate.of(1991,10,12))
//                .citizenship("Belarus")
//                .passportCode("3121091E005PB8")
//                .build());
//**************************************************************************************************
//        Optional<Client> maybeClient = clientDao.findById(1L);
//
//        Client client = maybeClient.get();
//        System.out.println(client);
//
//        client.setDetail(Detail.builder()
//                .firstName("vladimir")
//                .lastName("Beglyak")
//                .fatherName("Vladimir")
//                .birthday(LocalDate.of(1991,10,12))
//                .citizenship("Belarus")
//                .passportCode("3121091E005PB8")
//                .build());
//
//        detailDao.save(client.getDetail());
//
//        System.out.println(client);
//
//       clientDao.update(client);
//************************************************************************************************

//        StorageStock savedStock = storageStockDao.save(StorageStock.builder()
//                .ticker("APL")
//                .name("Apple")
//                .amount(3000L)
//                .costOneStock(BigDecimal.valueOf(150))
//                .county("US")
//                .dividend(BigDecimal.valueOf(1.5))
//                .currency(currencyDao.findById(1L).orElse(null))
//                .build());
//
//        System.out.println(savedStock);


//        List<StorageStock> all = storageStockDao.findAll();
//        for (StorageStock storageStock : all) {
//            System.out.println(storageStock);
//        }

/*        Optional<StorageStock> maybeStock = storageStockDao.findById(2L);
        StorageStock storageStock = maybeStock.get();

        ClientStock clientStock = storageStockToClientStock.mapFrom(storageStock);

        ClientStock save = clientStockDao.save(clientStock);
        System.out.println(save);

        save.setAmount(4L);

        clientStockDao.update(save);*/

/*        Optional<Client> byId = clientDao.findById(1L);
        Client client = byId.get();

        Optional<ClientStock> byId1 = clientStockDao.findById(2L);
        ClientStock clientStock = byId1.get();

        clientDao.addStockToClient(client,clientStock);*/

//        List<Client> all = clientDao.findAll();
//
//        for (Client client : all) {
//            for (Client client1 : all) {
//                System.out.println(client1.getStocks());
//            }
//        }

//        Optional<Client> byId = clientDao.findById(2L);
//        System.out.println(byId.get().getStocks());

//        List<StorageStock> all = storageStockDao.findAll();
//        System.out.println(all);

//        Optional<StorageStock> byId = storageStockDao.findById(1L);
//        System.out.println(byId);
//
//        StorageStockDto storageStockDto = storageStockByIdMapper.mapFrom(byId);
//        System.out.println(storageStockDto);

//        StorageStockDto byId = storageStockService.findById(1L);
//        System.out.println(byId);

//        List<StorageStock> all = storageStockService.findAll();
//        System.out.println(all);


//moneyDao.save(Money.builder()
//        .count(BigDecimal.valueOf(2000))
//        .currency(Currency.builder()
//                .id(2L)
//                .build())
//        .client(Client.builder()
//                .id(1L)
//                .build())
//        .statusMoney(StatusMoney.DEPOSIT)
//        .date(LocalDate.now())
//        .build());

//        List<Currency> all = currencyDao.findAll();
//        System.out.println(all);

        double v = Double.parseDouble("1.58");
        System.out.println(v);

//        News first_news = newsDao.save(News.builder()
//                .text("Second news Apple")
//                .date(LocalDateTime.now())
//                .storageStock(storageStockDao.findById(3L).get())
//                .build());


        List<News> all = newsDao.findAll();
        for (News news : all) {
            System.out.println(news);
        }

    }
}
