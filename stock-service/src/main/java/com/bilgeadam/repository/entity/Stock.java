package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.Status;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "tblstock")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productid;
    private String name;
    private String brand;
    @Builder.Default
    private Integer stock=0;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status=Status.EMPTY;
    private Long updated;
    private Long lastStockInTime;
    private Long lastStockOutTime;


}
