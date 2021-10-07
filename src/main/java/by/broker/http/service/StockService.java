package by.broker.http.service;

import by.broker.http.dao.ClientStockDao;
import by.broker.http.mapper.ListStockMapper;

public class StockService {
    private static StockService INSTANCE=null;

    private StockService(){}

    public static StockService getInstance() {
        if (INSTANCE==null){
            synchronized (StockService.class){
                if (INSTANCE==null){
                    INSTANCE=new StockService();
                }
            }
        }
        return INSTANCE;
    }

    private final ClientStockDao stockDao= ClientStockDao.getInstance();
    private final ListStockMapper listStockMapper=ListStockMapper.getInstance();




//    public ClientStock save(StockDto stockDto){
//        return stockDao.save(createStockMapper.mapFrom(stockDto));
//    }

//    public List<StockDto> findAll(){
//        return stockDao.findAll().stream()
//                .map(stock -> listStockMapper.mapFrom(stock))
//                .collect(toList());
//    }
//
//    public StockDto findById(Long id){
//       return stockDao.findById(id)
//               .map(stock -> stockByIdMapper.mapFrom(Optional.of(stock)))
//               .get();
//    }
}
