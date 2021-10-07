package by.broker.http.service;

import by.broker.http.dao.NewsDao;
import by.broker.http.entity.News;

import java.time.LocalDateTime;
import java.util.List;

public class NewsService {

    private static NewsService INSTANCE;

    private NewsService() {
    }

    public static NewsService getINSTANCE() {
        if (INSTANCE==null){
            synchronized (NewsService.class){
                if (INSTANCE==null){
                    INSTANCE=new NewsService();
                }
            }
        }
        return INSTANCE;
    }

    private final NewsDao newsDao=NewsDao.getINSTANCE();

    public List<News> findAll(){
        return newsDao.findAll();
    }

    public News save (News news){
        news.setDate(LocalDateTime.now());
        return newsDao.save(news);
    }

}
