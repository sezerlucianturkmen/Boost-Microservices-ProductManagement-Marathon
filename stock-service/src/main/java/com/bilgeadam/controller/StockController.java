package com.bilgeadam.controller;

import com.bilgeadam.dto.request.AddProductRequestDto;
import com.bilgeadam.dto.request.CreateNewStockRequestDto;
import com.bilgeadam.dto.request.UpdateStockRequestDto;
import com.bilgeadam.dto.response.StockResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(STOCK)
public class StockController {

    private final StockService stockService;

    @PostMapping(ADD)
    @Operation(summary = "This method helps to update and add new products or remove products in stock storage")
    public ResponseEntity<Boolean> addInStock(@RequestBody AddProductRequestDto dto){
        return ResponseEntity.ok(stockService.addInStock(dto));
    }
    @PutMapping("/addproduct/{id}")
    @Operation(summary = " ")
    public ResponseEntity<Boolean> addOneProduct(@PathVariable(value = "id") Long productid){
        return ResponseEntity.ok(stockService.addOneProduct(productid));
    }
    @PutMapping("/removeproduct/{id}")
    @Operation(summary = " ")
    public ResponseEntity<Boolean> removeOneProduct(@PathVariable(value = "id") Long productid){
        return ResponseEntity.ok(stockService.removeOneProduct(productid));
    }


    @GetMapping(FINDALL)
    @Operation(summary = "This method brings all stock cards in detial")
    public ResponseEntity<List<StockResponseDto>> findAllStock(){
        return ResponseEntity.ok(stockService.findAllStock());
    }
    @PostMapping(CREATE) // manager
    @Operation(summary = "Not run directly ! It works simultaneously with PRODUCT CREATE ")
    public ResponseEntity<Boolean> createStock(@RequestBody CreateNewStockRequestDto dto){
        return ResponseEntity.ok(stockService.createStock(dto));
    }

    @PutMapping(UPDATE) //manager
    @Operation(summary = "Not run directly ! It works simultaneously with PRODUCT UPDATE ")
    public ResponseEntity<Boolean> updateStock(@RequestBody UpdateStockRequestDto dto){
        return ResponseEntity.ok(stockService.updateStock(dto));
    }

    @DeleteMapping(DELETE) //manager
    @Operation(summary = "Not run directly ! It works simultaneously with PRODUCT DELETE ")
    public  ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long productId){
        return ResponseEntity.ok(stockService.deleteStock(productId));
    }

}
