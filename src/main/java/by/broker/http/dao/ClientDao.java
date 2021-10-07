package by.broker.http.dao;

import by.broker.http.dto.ClientFilter;
import by.broker.http.entity.*;
import by.broker.http.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClientDao implements Dao<Long, Client>,
        FilterDao<Client, ClientFilter> {

    private static final String SAVE_CLIENT = """
            INSERT INTO clients (
            email, 
            password, 
            role
            ) 
            VALUES (?,?,?)
            """;

    private static final String SAVE_STOCK_TO_CLIENT= """
            INSERT INTO client_clients_stocks (client_id, stock_id) 
            VALUES (?,?)
            """;

    private static final String UPDATE_CLIENT = """
            UPDATE clients
            SET email=?,
            password=?,
            detail_id=?
            WHERE id=?
            """;


    private static final String FIND_ALL_CLIENTS_FILTER = """
            SELECT
            clients.id,
                   first_name,
                   last_name,
                   father_name,
                   birthday,
                   passport_id,
                   password,
                   email,
                   role,
                   stock_id,
                   s.id,
                   s.ticker,
                   s.name,
                   s.currency,
                   s.cost,
                   s.dividend
            FROM clients
            JOIN stocks s 
            ON clients.stock_id=s.id
            """;

    private static final String FIND_ALL_CLIENTS = """
            SELECT *
            FROM clients
            """;

    private static final String FIND_BY_ID_CLIENT = FIND_ALL_CLIENTS_FILTER + """
            WHERE clients.id=?
            """;

    private static final String FIND_BY_ID = """
            SELECT * FROM clients
            WHERE id=?
            """;

    private static final String FIND_BY_EMAIL_AND_PASSWORD = """
            SELECT * FROM clients
            WHERE email=?
            AND password=?
            """;
    private static final String DELETE_CLIENT = """
            DELETE FROM clients
            WHERE id=?
            """;

    private static ClientDao INSTANCE;

    private ClientDao() {
    }

    public static ClientDao getInstance() {
        if (INSTANCE == null) {
            synchronized (ClientDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ClientDao();
                }
            }
        }
        return INSTANCE;
    }

    private final MoneyDao moneyDao = MoneyDao.getInstance();
    private final DetailDao detailDao=DetailDao.getInstance();
    private final ClientStockDao clientStockDao=ClientStockDao.getInstance();

    @SneakyThrows
    public void addStockToClient(Client client,ClientStock clientStock){
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_STOCK_TO_CLIENT);
        preparedStatement.setObject(1,client.getId());
        preparedStatement.setObject(2,clientStock.getId());
        preparedStatement.executeUpdate();
    }

    @SneakyThrows
    @Override
    public Client save(Client client) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CLIENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, client.getEmail());
            preparedStatement.setObject(2, client.getPassword());
            preparedStatement.setObject(3, Role.USER.name());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                client.setId(generatedKeys.getObject("id", Long.class));
            }
            return client;
        }
    }


    @SneakyThrows
    @Override
    public List<Client> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CLIENTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(buildClient(resultSet));
            }
            return clients;
        }
    }

    @SneakyThrows
    @Override
    public Optional<Client> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            //System.out.println(preparedStatement);
            Client client = null;
            if (resultSet.next()) {
                client = buildClient(resultSet);
            }
            return Optional.ofNullable(client);
        }
    }

    @SneakyThrows
    @Override
    public void update(Client client) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT);
            preparedStatement.setObject(1, client.getEmail());
            preparedStatement.setObject(2, client.getPassword());
            preparedStatement.setObject(3, client.getDetail().getId());
            preparedStatement.setObject(4, client.getId());
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT);
            preparedStatement.setObject(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    //filter
    @SneakyThrows
    @Override
    public List<Client> findAll(ClientFilter filter) {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();
        if (filter.getFirstName() != null) {
            whereSql.add("first_name LIKE ?");
            parameters.add("%" + filter.getFirstName() + "%");
        }
        if (filter.getLastName() != null) {
            whereSql.add("last_name LIKE ?");
            parameters.add("%" + filter.getLastName() + "%");
        }
        if (filter.getEmail() != null) {
            whereSql.add("email LIKE ?");
            parameters.add("%" + filter.getEmail() + "%");
        }
        parameters.add(filter.getLimit());
        parameters.add(filter.getOffset());
        String where = whereSql.stream()
                .collect(Collectors.joining(" AND ", " WHERE ", " LIMIT ? OFFSET ? "));

        String sql = FIND_ALL_CLIENTS_FILTER + where;
                /*+ """
                LIMIT ?
                OFFSET ?
                """;*/
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            System.out.println(whereSql);
            System.out.println(parameters);
            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(buildClient(resultSet));
            }
            return clients;
        }
    }

    @SneakyThrows
    public Optional<Client> findByEmailAndPassword(String email, String password) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD);
            preparedStatement.setObject(1, email);
            preparedStatement.setObject(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            Client client = null;
            if (resultSet.next()) {
                client = buildClient(resultSet);
            }
            return Optional.ofNullable(client);
        }
    }

    private Client buildClient(ResultSet resultSet) throws SQLException {

        return Client.builder()
                .id(resultSet.getObject("id", Long.class))
                .password(resultSet.getObject("password", String.class))
                .email(resultSet.getObject("email", String.class))
                .role(Role.valueOf(resultSet.getObject("role", String.class)))
                //.role(Role.find(resultSet.getObject("role",String.class)).orElse(null)) этот вариант, если поле role необязательное
//                .detail(detailDao.findById(resultSet.getObject("detail_id", Long.class),
//                        resultSet.getStatement().getConnection()).orElse(null))
                .detail(detailDao.findById(resultSet.getObject("detail_id",Long.class)).orElse(null))
//                .stocks(clientStockDao.findAllByClientId(resultSet.getObject("id", Long.class)))
                .stocks(clientStockDao.findAllByClientId(resultSet.getObject("id",Long.class)))
                .monies(moneyDao.findAllByClientId(resultSet.getObject("id", Long.class)))
                .build();
    }


}
