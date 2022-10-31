package com.bilgeadam.manager;

import com.bilgeadam.dto.request.CreateNewProductRequestDto;
import com.bilgeadam.dto.request.CreateNewStockRequestDto;
import com.bilgeadam.dto.request.UpdateStockRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bilgeadam.constants.ApiUrls.*;
@FeignClient(url ="${myapplication.feign.stock}/stock" , name = "stock-service",decode404 = true)
public interface IStockManager {


    @PostMapping(CREATE)
    public ResponseEntity<Boolean> createStock(@RequestBody CreateNewStockRequestDto dto);

    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateStock(@RequestBody UpdateStockRequestDto dto);

    @DeleteMapping(DELETE)
    public  ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long productId);
}
