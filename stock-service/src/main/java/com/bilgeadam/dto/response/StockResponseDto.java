package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StockResponseDto {
    private Long productid;
    private String name;
    private String brand;
    private Integer stock;
    private Status status;
    private Long updated;
    private Long lastStockInTime;
    private Long lastStockOutTime;
}
