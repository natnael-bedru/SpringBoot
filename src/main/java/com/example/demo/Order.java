package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="_order")
//@JsonIgnoreProperties(value = {"order_id"})
public class Order {
    @Id
    @Column(name = "order_id")
    @JsonIgnore
    // @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @JsonProperty("order_date")
    @Column(name = "order_date", nullable = false)
    private LocalDate date;

    //@NonNull
    @ManyToOne
   // @JsonProperty("user_id")
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_order_user"))
    private User _user;

    // getters and setters done by @Data tag form Lombic dependency
}