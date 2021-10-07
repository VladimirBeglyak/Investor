package by.broker.http.dao;

import by.broker.http.entity.Detail;
import by.broker.http.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetailDao implements Dao<Long, Detail> {

    private static final String DELETE_DETAIL = """
            DELETE FROM details
            WHERE id=?
            """;
    private static final String SAVE_DETAIL = """
            INSERT INTO details (first_name,last_name,father_name,citizenship,birthday,passport_code,phone_number) VALUES (?,?,?,?,?,?,?)
            """;
    private static final String UPDATE_DETAIL = """
            UPDATE details
            SET first_name=?,
            last_name=?,
            father_name=?,
            citizenship=?,
            birthday=?,
            passport_code=?,
            phone_number=?
            WHERE id=?
            """;
    private static final String FIND_ALL_DETAILS = """
            SELECT * FROM details
            """;

    private static final String FIND_BY_ID = """
            SELECT * FROM details
            WHERE id=?;
            """;
//    private static final String FIND_ALL_BY_CLIENT_ID= """
//            SELECT * FROM details
//            WHERE client_id=?
//            """;

    private static DetailDao INSTANCE = null;

    private DetailDao() {
    }

    public static DetailDao getInstance() {
        if (INSTANCE == null) {
            synchronized (DetailDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DetailDao();
                }
            }
        }
        return INSTANCE;
    }


    @SneakyThrows
    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            //connection.setAutoCommit(false);
            //try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DETAIL);
            preparedStatement.setObject(1, id);
            //     connection.commit();
            return preparedStatement.executeUpdate() > 0;
            //    } catch (Exception e){
            //    connection.rollback();
            //  }
            //   }
            //    return false;
        }
    }

    @SneakyThrows
    @Override
    public Detail save(Detail detail) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_DETAIL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, detail.getFirstName());
            preparedStatement.setObject(2, detail.getLastName());
            preparedStatement.setObject(3, detail.getFatherName());
            preparedStatement.setObject(4, detail.getCitizenship());
            preparedStatement.setObject(5, detail.getBirthday());
            preparedStatement.setObject(6, detail.getPassportCode());
            preparedStatement.setObject(7, detail.getPhoneNumber());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                detail.setId(generatedKeys.getLong("id"));
            }
            return detail;
        }
    }

    @SneakyThrows
    @Override
    public void update(Detail detail) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DETAIL);
            preparedStatement.setObject(1, detail.getFirstName());
            preparedStatement.setObject(2, detail.getLastName());
            preparedStatement.setObject(3, detail.getFatherName());
            preparedStatement.setObject(4, detail.getCitizenship());
            preparedStatement.setObject(5, detail.getBirthday());
            preparedStatement.setObject(6, detail.getPassportCode());
            preparedStatement.setObject(7, detail.getPhoneNumber());


            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public Optional<Detail> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Detail detail = null;
            if (resultSet.next()) {
                detail = buildDetail(resultSet);
            }
            return Optional.ofNullable(detail);

        }
    }

    private Detail buildDetail(ResultSet resultSet) throws SQLException {
        return Detail.builder()
                .id(resultSet.getObject("id", Long.class))
                .firstName(resultSet.getObject("first_name", String.class))
                .lastName(resultSet.getObject("last_name", String.class))
                .fatherName(resultSet.getObject("father_name", String.class))
                .citizenship(resultSet.getObject("citizenship", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .passportCode(resultSet.getObject("passport_code", String.class))
                .phoneNumber(resultSet.getObject("phone_number",String.class))
                .build();
    }

    @SneakyThrows
    @Override
    public List<Detail> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_DETAILS);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Detail> details = new ArrayList<>();
            while (resultSet.next()) {
                details.add(buildDetail(resultSet));
            }
            return details;
        }
    }

//    @SneakyThrows
//    public Detail findAllByClientId(Long id) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_CLIENT_ID);
//            preparedStatement.setObject(1,id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            Detail detail = null;
//            if (resultSet.next()) {
//                detail=buildDetail(resultSet);
//            }
//            return detail;
//        }
//    }
}
