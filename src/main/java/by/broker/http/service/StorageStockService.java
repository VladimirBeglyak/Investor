package by.broker.http.service;

import by.broker.http.dao.StorageStockDao;
import by.broker.http.dto.StorageStockDto;
import by.broker.http.entity.StorageStock;
import by.broker.http.mapper.CreateStorageStockMapper;
import by.broker.http.mapper.StorageStockByIdMapper;

import java.util.List;
import java.util.Optional;

public class StorageStockService {

    private static StorageStockService INSTANCE=null;

    private StorageStockService(){}

    public static StorageStockService getInstance() {
        if (INSTANCE==null){
            synchronized (StorageStockService.class){
                if (INSTANCE==null){
                    INSTANCE=new StorageStockService();
                }
            }
        }
        return INSTANCE;
    }

    private final StorageStockDao storageStockDao=StorageStockDao.getInstance();
    private final CreateStorageStockMapper createStorageStockMapper=CreateStorageStockMapper.getInstance();
    private final StorageStockByIdMapper storageStockByIdMapper=StorageStockByIdMapper.getInstance();

    public StorageStock save(StorageStockDto dto){
        return storageStockDao.save(createStorageStockMapper.mapFrom(dto));
    }

    public List<StorageStock> findAll(){
        return storageStockDao.findAll();
    }

    public StorageStockDto findById(Long id){
        return storageStockDao.findById(id)
                .map(stock -> storageStockByIdMapper.mapFrom(Optional.of(stock)))
                .get();
    }

    public StorageStock getById(Long id){
        return storageStockDao.findById(id).get();
    }
}
