package com.bilgeadam.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateNewStockRequestDto {
    private Long productid;
    private String name;
    private String brand;
}
