package by.broker.http.dao;

import by.broker.http.entity.Currency;
import by.broker.http.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyDao implements Dao<Long, Currency>{

    private static final String FIND_BY_STOCK_ID= """
            SELECT * FROM currencies
            JOIN storage_stocks ss on currencies.id = ss.currency_id
            WHERE currency_id=?
            """;

    private static CurrencyDao INSTANCE;

    private CurrencyDao(){}

    private static final String UPDATE_SQL= """
            UPDATE currencies
            SET ticker = ?,
            name=?
            WHERE id=?
            """;

    private static final String ADD_CURRENCY= """
            INSERT INTO currencies (ticker, name) VALUES (?,?)
            """;
    private static final String FIND_ALL_SQL= """
            SELECT 
            id,
            ticker,
            name
            FROM currencies
            """;

    private static final String FIND_BY_ID= FIND_ALL_SQL+ """
            WHERE id=?
            """;
    private static final String DELETE_SQL= """
            DELETE FROM currencies WHERE id=?;
            """;


    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setObject(1,id);

            return preparedStatement.executeUpdate() > 0;
        }
    }


    @SneakyThrows
    @Override
    public Currency save(Currency currency) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CURRENCY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1,currency.getTicker());
            preparedStatement.setObject(2,currency.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                currency.setId(generatedKeys.getObject(1,Long.class));
            }
            return currency;
        }
    }

    @SneakyThrows
    @Override
    public void update(Currency currency) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setObject(1,currency.getTicker());
            preparedStatement.setObject(2,currency.getName());
            preparedStatement.setObject(3,currency.getId());

            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public Optional<Currency> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Currency currency=null;

            if (resultSet.next()){
                currency= buildCurrency(resultSet);
            }
            return Optional.ofNullable(currency);
        }
    }

    @SneakyThrows
    @Override
    public List<Currency> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Currency> currencies=new ArrayList<>();
            while (resultSet.next()){
                currencies.add(buildCurrency(resultSet));
            }
            return currencies;
        }
    }

    private Currency buildCurrency(ResultSet resultSet) throws SQLException {
        return Currency.builder()
                .id(resultSet.getObject("id",Long.class))
                .ticker(resultSet.getObject("ticker",String.class))
                .name(resultSet.getObject("name",String.class))
                .build();
    }

    @SneakyThrows
    public Currency findByStockId(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_STOCK_ID);
            preparedStatement.setObject(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Currency currency=null;
            if (resultSet.next()){
                currency=buildCurrency(resultSet);
            }
            return currency;
        }
    }

    public static CurrencyDao getInstance() {
        if (INSTANCE==null){
            synchronized (CurrencyDao.class){
                if (INSTANCE==null){
                    INSTANCE=new CurrencyDao();
                }
            }
        }
        return INSTANCE;
    }
}
