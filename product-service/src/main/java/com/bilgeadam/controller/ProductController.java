package com.bilgeadam.controller;

import com.bilgeadam.dto.request.CreateNewProductRequestDto;
import com.bilgeadam.dto.request.UpdateProductRequestDto;
import com.bilgeadam.dto.response.ProductResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.ProductManagerException;
import com.bilgeadam.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bilgeadam.constants.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT)
public class ProductController {

    private final ProductService productService;

    @PostMapping(CREATE)
    @Operation(summary = "This method helps to create new product and activate the stockmanager")
    public ResponseEntity<Boolean> createProduct(@RequestBody CreateNewProductRequestDto dto){
        try{
            productService.createProduct(dto);
            return ResponseEntity.ok(true);
        }catch (Exception e){
          throw new ProductManagerException(ErrorType.PRODUCT_NOT_CREATED);
        }
    }

    @GetMapping(FINDALL)
    @Operation(summary = "This method brings all products in database")
    public ResponseEntity<List<ProductResponseDto>> findAllProduct(){
        return ResponseEntity.ok(productService.findAllProduct());
    }

    @PutMapping(UPDATE)
    @Operation(summary = "This method helps to update existing product and activate the stockmanager")
    public ResponseEntity<Boolean> updateProduct(@RequestBody UpdateProductRequestDto dto){
        return ResponseEntity.ok(productService.updateProduct(dto));
    }

    @DeleteMapping(DELETE)
    @Operation(summary = "This method helps to delete existing product and activate the stockmanager")
    public  ResponseEntity<Boolean> delete(@PathVariable(value = "id") Long productId){
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }


}
