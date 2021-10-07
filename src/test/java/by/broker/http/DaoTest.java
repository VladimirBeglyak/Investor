package by.broker.http;

import by.broker.http.dao.NewsDao;
import by.broker.http.dao.StorageStockDao;
import by.broker.http.entity.News;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class DaoTest {

    private static NewsDao newsDao;
    private static StorageStockDao storageStockDao;

    @Test
    public void testNewsDao(){
        newsDao.save(News.builder()
                .text("First news")
                .date(LocalDateTime.now())
                .storageStock(storageStockDao.findById(2L).get())
                .build());
    }
}
