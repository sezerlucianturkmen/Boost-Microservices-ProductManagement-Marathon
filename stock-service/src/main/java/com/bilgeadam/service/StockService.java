package com.bilgeadam.service;

import com.bilgeadam.dto.request.AddProductRequestDto;
import com.bilgeadam.dto.request.CreateNewStockRequestDto;
import com.bilgeadam.dto.request.UpdateStockRequestDto;
import com.bilgeadam.dto.response.StockResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.StockManagerException;
import com.bilgeadam.mapper.IStockMapper;
import com.bilgeadam.repository.IStockRepository;
import com.bilgeadam.repository.entity.Stock;
import com.bilgeadam.repository.enums.Status;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService extends ServiceManager<Stock,Long> {
    private final IStockRepository stockRepository;

    public StockService(IStockRepository stockRepository) {
        super(stockRepository);
        this.stockRepository = stockRepository;
    }

   public boolean addInStock(AddProductRequestDto dto) {
       Optional<Stock> stock=stockRepository.findOptionalByProductid(dto.getProductid());
       if(stock.isPresent()){

           int currentStock=stock.get().getStock();

           if(currentStock>dto.getStock() && dto.getStock()>0){
               stock.get().setStock(dto.getStock());
               stock.get().setLastStockOutTime(System.currentTimeMillis());
               stock.get().setStatus(Status.AVAILABLE);
           } else if (currentStock<dto.getStock() && dto.getStock()>0){
               stock.get().setStock(dto.getStock());
               stock.get().setLastStockInTime(System.currentTimeMillis());
               stock.get().setStatus(Status.AVAILABLE);
           } else if(dto.getStock()==0){
               stock.get().setStock(dto.getStock());
               stock.get().setLastStockOutTime(System.currentTimeMillis());
               stock.get().setStatus(Status.EMPTY);
           }else{
               throw new StockManagerException(ErrorType.STOCK_NOT_DEFINED);
           }

           save(stock.get());
           return true;

       }else{
           throw  new StockManagerException(ErrorType.STOCK_NOT_FOUND);
       }
    }

   public List<StockResponseDto> findAllStock() {
        List<Stock> stockList=findAll();
        return stockList.stream().map(x-> IStockMapper.INSTANCE.toStockResponseDto(x)).collect(Collectors.toList());
    }

    public boolean createStock(CreateNewStockRequestDto dto) {
        Stock stock = stockRepository.save(IStockMapper.INSTANCE.toStock(dto));
        return true;
    }

    public boolean updateStock(UpdateStockRequestDto dto) {
        Optional<Stock> stock=stockRepository.findOptionalByProductid(dto.getProductid());
        if(stock.isPresent()){
            stock.get().setName(dto.getName());
            stock.get().setBrand(dto.getBrand());
            stock.get().setUpdated(System.currentTimeMillis());
            save(stock.get());
            return true;
        }else{
            throw  new StockManagerException(ErrorType.STOCK_NOT_FOUND);
        }
    }

    public boolean deleteStock(Long productId) {
        Optional<Stock> stock=stockRepository.findOptionalByProductid(productId);
        if(stock.isPresent()){
            deleteById(stock.get().getId());
            return true;
        }else{
            throw  new StockManagerException(ErrorType.STOCK_NOT_FOUND);
        }
    }

    public boolean addOneProduct(Long productid) {
        Optional<Stock> stock=stockRepository.findOptionalByProductid(productid);
        if(stock.isPresent()){
            stock.get().setStock(stock.get().getStock()+1);
            stock.get().setLastStockInTime(System.currentTimeMillis());
            save(stock.get());
            return true;
        }else{
            throw  new StockManagerException(ErrorType.STOCK_NOT_FOUND);
        }
    }

    public boolean removeOneProduct(Long productid) {
        Optional<Stock> stock=stockRepository.findOptionalByProductid(productid);
        if(stock.isPresent()){
            if (stock.get().getStock()>0){
                stock.get().setStock(stock.get().getStock()-1);
                stock.get().setLastStockOutTime(System.currentTimeMillis());
                save(stock.get());
                return true;
            }else {
                throw  new StockManagerException(ErrorType.STOCK_NOT_DEFINED);
            }
        }else{
            throw  new StockManagerException(ErrorType.STOCK_NOT_FOUND);
        }
    }
}
