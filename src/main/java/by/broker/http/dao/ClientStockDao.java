package by.broker.http.dao;

import by.broker.http.dto.StockFilter;
import by.broker.http.entity.ClientStock;
import by.broker.http.util.ConnectionManager;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientStockDao implements Dao<Long, ClientStock>,FilterDao<ClientStock,StockFilter>

        /*CreateInterface<Stock>,
        FindInterface<Stock, Integer>,
        DeleteInterface<Integer>,
        UpdateInterface<Stock>,
        FilterDao<Stock, StockFilter>*/ {

    private static final String FIND_ALL_STOCKS_BY_CLIENT= """
            SELECT * FROM clients_stocks
            JOIN client_clients_stocks ccs ON clients_stocks.id = ccs.stock_id
            WHERE client_id=?
            """;


    private static final String ADD_STOCK = """
            INSERT INTO clients_stocks
            (ticker, name, count, cost_one_stock, country, dividend,currency) 
            VALUES (?,?,?,?,?,?,?)
            """;
    private static final String FIND_ALL_STOCKS = """
            SELECT 
            *
            FROM clients_stocks
            """;
    private static final String FIND_STOCK_BY_ID = FIND_ALL_STOCKS + """ 
            WHERE id=?
            """;
    private static final String DELETE_STOCK = """
            DELETE FROM clients_stocks
            WHERE id=?
            """;
    private static final String UPDATE_STOCK = """
            UPDATE clients_stocks
            SET ticker=?,
            name=?,
            count=?,
            cost_one_stock=?,
            country=?,
            dividend=?,
            currency=?
            WHERE id=?
            """;

    private static ClientStockDao INSTANCE;

    private ClientStockDao() {
    }

    public static ClientStockDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ClientStockDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClientStockDao();
                }
            }
        }
        return INSTANCE;
    }




    @SneakyThrows
    @Override
    public ClientStock save(ClientStock clientStock) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_STOCK, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, clientStock.getTicker());
            preparedStatement.setObject(2, clientStock.getName());
            preparedStatement.setObject(3, clientStock.getCount());
            preparedStatement.setObject(4, clientStock.getCostOneStock());
            preparedStatement.setObject(5, clientStock.getCounty());
            preparedStatement.setObject(6, clientStock.getDividend());
            preparedStatement.setObject(7, clientStock.getCurrency());


            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                clientStock.setId(generatedKeys.getObject(1, Long.class));
            }

            return clientStock;
        }
    }

    @SneakyThrows
    @Override
    public List<ClientStock> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_STOCKS);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<ClientStock> clientStocks = new ArrayList<>();
            while (resultSet.next()) {
                clientStocks.add(buildStock(resultSet));
            }
            return clientStocks;
        }
    }

    @SneakyThrows
    @Override
    public Optional<ClientStock> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return findById(id,connection);
        }
    }

    @SneakyThrows
    public Optional<ClientStock> findById(Long id, Connection connection){
        try(PreparedStatement preparedStatement=connection.prepareStatement(FIND_STOCK_BY_ID)) {
            preparedStatement.setObject(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            ClientStock clientStock = null;
            if (resultSet.next()) {
                clientStock = buildStock(resultSet);
            }
            return Optional.ofNullable(clientStock);
        }
    }

    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STOCK);
            preparedStatement.setObject(1, id);

            return preparedStatement.executeUpdate() > 0;
        }
    }

    @SneakyThrows
    @Override
    public void update(ClientStock clientStock) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STOCK);
            preparedStatement.setObject(1, clientStock.getTicker());
            preparedStatement.setObject(2, clientStock.getName());
            preparedStatement.setObject(3,clientStock.getCount());
            preparedStatement.setObject(4, clientStock.getCostOneStock());
            preparedStatement.setObject(5,clientStock.getCounty());
            preparedStatement.setObject(6, clientStock.getDividend());
            preparedStatement.setObject(7, clientStock.getCurrency());
            preparedStatement.setObject(8, clientStock.getId());

            preparedStatement.executeUpdate();

        }
    }


    private ClientStock buildStock(ResultSet resultSet) throws java.sql.SQLException {
        return ClientStock.builder()
                .id(resultSet.getObject("id", Long.class))
                .ticker(resultSet.getObject("ticker", String.class))
                .name(resultSet.getObject("name", String.class))
                .count(resultSet.getObject("count",Long.class))
                .costOneStock(resultSet.getObject("cost_one_stock", BigDecimal.class))
                .dividend(resultSet.getObject("dividend", BigDecimal.class))
                .county(resultSet.getObject("country",String.class))
                //.currency(Currency.valueOf(resultSet.getObject("currency", String.class)))
                //.currencies(currencyDao.findAllByStockId(resultSet.getObject("id",Long.class)))
                .currency(resultSet.getObject("currency",String.class))
                .build();
    }

    @SneakyThrows
    @Override
    public List<ClientStock> findAll(StockFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql=new ArrayList<>();
        if (filter.getName()!=null){
            whereSql.add("name LIKE ?");
            parameters.add("%"+filter.getName()+"%");
        }
        if (filter.getCurrency()!=null){
            whereSql.add("currency=?");
            parameters.add(filter.getCurrency());
        }
        parameters.add(filter.getLimit());
        parameters.add(filter.getOffset());
        String where = whereSql.stream()
                .collect(Collectors.joining(" AND "," WHERE "," LIMIT ? OFFSET ? "));

        String sql = FIND_ALL_STOCKS +where;
                /*+ """
                LIMIT ?
                OFFSET ?
                """;*/
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            System.out.println(parameters);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<ClientStock> clientStocks = new ArrayList<>();
            while (resultSet.next()) {
                clientStocks.add(buildStock(resultSet));
            }
            return clientStocks;
        }
    }

    @SneakyThrows
    public List<ClientStock> findAllByClientId(Long id){
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_STOCKS_BY_CLIENT);
        preparedStatement.setObject(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<ClientStock> clientStocks=new ArrayList<>();
        while (resultSet.next()){
            clientStocks.add(buildStock(resultSet));
        }
        return clientStocks;
    }
}
