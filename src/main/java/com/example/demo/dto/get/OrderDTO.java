package com.example.demo.dto.get;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrderDTO {
    private String productName;
    private LocalDate orderDate;
}
