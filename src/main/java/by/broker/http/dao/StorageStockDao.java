package by.broker.http.dao;

import by.broker.http.entity.StorageStock;
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

public class StorageStockDao implements Dao<Long, StorageStock>{

    private static final String FIND_ALL_STOCKS_BY_CLIENT= """
            SELECT * FROM client_clients_stocks
            JOIN clients c ON c.id = client_clients_stocks.client_id
            WHERE client_id=?
            """;


    private final CurrencyDao currencyDao=CurrencyDao.getInstance();

    private static final String SAVE_STOCK = """
            INSERT INTO storage_stocks
            (ticker, name, count, cost_one_stock, country, dividend,currency_id) 
            VALUES (?,?,?,?,?,?,?)
            """;

    private static final String FIND_ALL_STOCKS = """
            SELECT *
            FROM storage_stocks
            """;
    private static final String FIND_STOCK_BY_ID = FIND_ALL_STOCKS + """ 
            WHERE id=?
            """;
    private static final String DELETE_STOCK = """
            DELETE FROM storage_stocks
            WHERE id=?
            """;
    private static final String UPDATE_STOCK = """
            UPDATE storage_stocks
            SET ticker=?,
            name=?,
            count=?,
            cost_one_stock=?,
            country=?,
            dividend=?,
            currency_id=?
            WHERE id=?
            """;

    private static StorageStockDao INSTANCE;

    private StorageStockDao() {
    }

    public static StorageStockDao getInstance() {
        if (INSTANCE == null) {
            synchronized (StorageStockDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new StorageStockDao();
                }
            }
        }
        return INSTANCE;
    }

    @SneakyThrows
    @Override
    public StorageStock save(StorageStock storageStock) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_STOCK, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, storageStock.getTicker());
            preparedStatement.setObject(2, storageStock.getName());
            preparedStatement.setObject(3, storageStock.getCount());
            preparedStatement.setObject(4, storageStock.getCostOneStock());
            preparedStatement.setObject(5, storageStock.getCountry());
            preparedStatement.setObject(6, storageStock.getDividend());
            preparedStatement.setObject(7, storageStock.getCurrency().getId());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                storageStock.setId(generatedKeys.getObject(1, Long.class));
            }
            return storageStock;
        }
    }

    @SneakyThrows
    @Override
    public List<StorageStock> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_STOCKS);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<StorageStock> storageStocks = new ArrayList<>();
            while (resultSet.next()) {
                storageStocks.add(buildStorageStock(resultSet));
            }
            return storageStocks;
        }
    }

    @SneakyThrows
    @Override
    public Optional<StorageStock> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return findById(id,connection);
        }
    }

    @SneakyThrows
    public Optional<StorageStock> findById(Long id, Connection connection){
        try(PreparedStatement preparedStatement=connection.prepareStatement(FIND_STOCK_BY_ID)) {
            preparedStatement.setObject(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            StorageStock storageStock = null;
            if (resultSet.next()) {
                storageStock = buildStorageStock(resultSet);
            }
            return Optional.ofNullable(storageStock);
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
    public void update(StorageStock storageStock) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STOCK);
            preparedStatement.setObject(1, storageStock.getTicker());
            preparedStatement.setObject(2, storageStock.getName());
            preparedStatement.setObject(3, storageStock.getCount());
            preparedStatement.setObject(4, storageStock.getCostOneStock());
            preparedStatement.setObject(4, storageStock.getCountry());
            preparedStatement.setObject(5, storageStock.getDividend());
            preparedStatement.setObject(6, storageStock.getCurrency().getId());
            preparedStatement.setObject(7, storageStock.getId());


            preparedStatement.executeUpdate();

        }
    }


    private StorageStock buildStorageStock(ResultSet resultSet) throws java.sql.SQLException {
        return StorageStock.builder()
                .id(resultSet.getObject("id", Long.class))
                .ticker(resultSet.getObject("ticker", String.class))
                .name(resultSet.getObject("name", String.class))
                .count(resultSet.getObject("count",Long.class))
                .costOneStock(resultSet.getObject("cost_one_stock", BigDecimal.class))
                .dividend(resultSet.getObject("dividend", BigDecimal.class))
                .country(resultSet.getObject("country",String.class))
                //.currency(Currency.valueOf(resultSet.getObject("currency", String.class)))
                .currency(currencyDao.findById(resultSet.getObject("currency_id",Long.class)).orElse(null))
                .build();
    }


//    @SneakyThrows
//    @Override
//    public List<StorageStock> findAll(StockFilter filter) {
//        List<Object> parameters = new ArrayList<>();
//        List<String> whereSql=new ArrayList<>();
//        if (filter.getName()!=null){
//            whereSql.add("name LIKE ?");
//            parameters.add("%"+filter.getName()+"%");
//        }
//        if (filter.getCurrency()!=null){
//            whereSql.add("currency=?");
//            parameters.add(filter.getCurrency());
//        }
//        parameters.add(filter.getLimit());
//        parameters.add(filter.getOffset());
//        String where = whereSql.stream()
//                .collect(Collectors.joining(" AND "," WHERE "," LIMIT ? OFFSET ? "));
//
//        String sql = FIND_ALL_STOCKS +where;
//                /*+ """
//                LIMIT ?
//                OFFSET ?
//                """;*/
//        try (Connection connection = ConnectionManager.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            for (int i = 0; i < parameters.size(); i++) {
//                preparedStatement.setObject(i + 1, parameters.get(i));
//            }
//            System.out.println(parameters);
//            System.out.println(preparedStatement);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<StorageStock> storageStocks = new ArrayList<>();
//            while (resultSet.next()) {
//                storageStocks.add(buildStorageStock(resultSet));
//            }
//            return storageStocks;
//        }
//    }

    @SneakyThrows
    public List<StorageStock> findAllByClientId(Long id){
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_STOCKS_BY_CLIENT);
        preparedStatement.setObject(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<StorageStock> storageStocks=new ArrayList<>();
        while (resultSet.next()){
            storageStocks.add(buildStorageStock(resultSet));
        }
        return storageStocks;
    }
}
