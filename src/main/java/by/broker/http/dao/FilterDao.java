package by.broker.http.dao;

import java.util.List;

public interface FilterDao <T,K>{
    List<T> findAll(K filter);
}
