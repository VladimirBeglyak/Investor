package by.broker.http.dao;

import by.broker.http.entity.News;
import by.broker.http.util.ConnectionManager;
import by.broker.http.util.LocaleDateFormatter;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewsDao implements Dao<Long, News> {

    private static final String FIND_ALL = """
            SELECT id,text,date,storage_stock_id FROM news;
            """;
    private final StorageStockDao storageStockDao = StorageStockDao.getInstance();

    private static final String SAVE_NEWS = """
            INSERT INTO news(text, date, storage_stock_id) VALUES (?,?,?)
            """;
    private static final String UPDATE_NEWS = """
            UPDATE news SET 
            text=?,
            date=?,
            storage_stock_id=?
            WHERE id=?
            """;
    private static final String FIND_BY_ID = """
            SELECT 
            text,
            date,
            storage_stock_id 
            FROM news
            WHERE id=?
            """;
    private static NewsDao INSTANCE;

    private NewsDao() {
    }

    public static NewsDao getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (NewsDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NewsDao();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    @SneakyThrows
    public News save(News news) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_NEWS, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setObject(1, news.getText());
            preparedStatement.setObject(2, news.getDate());
            preparedStatement.setObject(3, news.getStorageStock().getId());

            preparedStatement.executeUpdate();
        }
        return null;
    }

    @Override
    @SneakyThrows
    public void update(News news) {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NEWS);
            preparedStatement.setObject(1, news.getText());
            preparedStatement.setObject(2, news.getDate());
            preparedStatement.setObject(3, news.getStorageStock().getId());
            preparedStatement.setObject(4, news.getId());

            preparedStatement.executeUpdate();

        }
    }

    @SneakyThrows
    public Optional<News> findById(Long id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);) {
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            News news = null;
            if (resultSet.next()) {
                news = buildNews(resultSet);

            }
            return Optional.ofNullable(news);
        }
    }

    @Override
    @SneakyThrows
    public Optional<News> findById(Long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            return findById(id, connection);
        }
    }

    @Override
    @SneakyThrows
    public List<News> findAll() {
        try (Connection connection = ConnectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<News> news = new ArrayList<>();

            while (resultSet.next()) {
                news.add(buildNews(resultSet));
            }
            return news;
        }
    }

    @SneakyThrows
    private News buildNews(ResultSet resultSet) {
        return News.builder()
                .id(resultSet.getObject("id", Long.class))
                .text(resultSet.getObject("text", String.class))
                .date(resultSet.getObject("date", LocalDateTime.class))
                .storageStock(storageStockDao.findById(resultSet.getObject("storage_stock_id", Long.class)).get())
                .build();
    }
}

