package com.example.demo.dto.get;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
// what the client sees
public class UserOrderDTO {
    private String userName;
    private List<String> productNames;
}
