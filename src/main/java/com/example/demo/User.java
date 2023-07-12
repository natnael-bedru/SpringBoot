package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private Long id;

    @Column(name= "user_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "_user", cascade = CascadeType.ALL)
    private List<Order> orders;

    // getters and setters done by @Data tag form Lombic dependency
}
