package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateNewStockRequestDto;
import com.bilgeadam.dto.request.UpdateStockRequestDto;
import com.bilgeadam.dto.response.StockResponseDto;
import com.bilgeadam.repository.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IStockMapper {

    IStockMapper INSTANCE = Mappers.getMapper(IStockMapper.class);

    Stock toStock(final CreateNewStockRequestDto dto);
    Stock toStock(final UpdateStockRequestDto dto);
    StockResponseDto toStockResponseDto(final Stock stock);
}
