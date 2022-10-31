package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateNewProductRequestDto;
import com.bilgeadam.dto.request.CreateNewStockRequestDto;
import com.bilgeadam.dto.request.UpdateProductRequestDto;
import com.bilgeadam.dto.response.ProductResponseDto;
import com.bilgeadam.repository.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")

public interface IProductMapper {
    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);

    Product toProduct(final CreateNewProductRequestDto dto);
    ProductResponseDto toProductRepsonseDto(final Product product);
    Product toProduct(final UpdateProductRequestDto dto);


}
