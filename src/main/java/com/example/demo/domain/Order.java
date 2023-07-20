package com.example.demo.domain;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="_order", uniqueConstraints = @UniqueConstraint(columnNames = { "productName", "userId" }))
@JsonIgnoreProperties(value = {"orderDate"})
public class Order {

    @Id
    @JsonIgnore
    @JsonProperty("orderId")
    @Column(name = "orderId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @JsonProperty("productName")
    @Column(name = "productName", nullable = false)
    private String productName;

    @NonNull
    @JsonProperty("orderDate")
    @Column(name = "orderDate", nullable = false)
    private LocalDate orderDate;

    @NonNull
    @ManyToOne
    @JsonIgnore
    @JsonProperty("userId")
    @ToString.Exclude //https://stackoverflow.com/a/63088075/17410605
    @JoinColumn(name = "userId", nullable = false, foreignKey = @ForeignKey(name = "fk_order_user"))
    private User _user;

    // getters and setters done by @Data tag form Lombic dependency
}