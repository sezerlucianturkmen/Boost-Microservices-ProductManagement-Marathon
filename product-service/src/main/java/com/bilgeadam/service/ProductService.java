package com.bilgeadam.service;

import com.bilgeadam.dto.request.CreateNewProductRequestDto;
import com.bilgeadam.dto.request.CreateNewStockRequestDto;
import com.bilgeadam.dto.request.UpdateProductRequestDto;
import com.bilgeadam.dto.request.UpdateStockRequestDto;
import com.bilgeadam.dto.response.ProductResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.ProductManagerException;
import com.bilgeadam.manager.IStockManager;
import com.bilgeadam.mapper.IProductMapper;
import com.bilgeadam.repository.IProductRepository;
import com.bilgeadam.repository.entity.Product;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService extends ServiceManager<Product,Long> {
    private final IProductRepository productRepository;
    private final IStockManager stockManager;

    public ProductService(IProductRepository productRepository,IStockManager stockManager) {
        super(productRepository);
        this.productRepository = productRepository;
        this.stockManager=stockManager;
    }

    public Product createProduct(CreateNewProductRequestDto dto) {
        Product product=productRepository.save(IProductMapper.INSTANCE.toProduct(dto));
        CreateNewStockRequestDto createNewStockRequestDto=CreateNewStockRequestDto
                .builder()
                .name(dto.getName())
                .brand(dto.getBrand())
                .productid(product.getId())
                .build();

        stockManager.createStock(createNewStockRequestDto);
        return product;
    }

    public List<ProductResponseDto> findAllProduct() {
        List<Product> productList=findAll();
        return productList.stream().map(x-> IProductMapper.INSTANCE.toProductRepsonseDto(x))
                                   .collect(Collectors.toList());
    }

    public boolean updateProduct(UpdateProductRequestDto dto) {
        Optional<Product> product=productRepository.findOptionalById(dto.getId());
        if(product.isPresent()){
            product.get().setName(dto.getName());
            product.get().setBrand(dto.getBrand());
            product.get().setAbout(dto.getAbout());
            product.get().setPrice(dto.getPrice());
            product.get().setUpdated(System.currentTimeMillis());
            save(product.get());

            UpdateStockRequestDto updateStockRequestDto=UpdateStockRequestDto
                    .builder()
                    .brand(dto.getBrand())
                    .name(dto.getName())
                    .productid(product.get().getId())
                    .build();
            stockManager.updateStock(updateStockRequestDto);

            return  true;
        }else{
            throw new ProductManagerException(ErrorType.PRODUCT_NOT_FOUND);
        }
    }

    public boolean deleteProduct(Long productId) {
        Optional<Product> product=productRepository.findOptionalById(productId);
        if(product.isPresent()){
            deleteById(product.get().getId());
            stockManager.delete(product.get().getId());
            return  true;
        }else{
            throw new ProductManagerException(ErrorType.PRODUCT_NOT_FOUND);
        }
    }
}
