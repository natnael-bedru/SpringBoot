package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
//@JsonIgnoreProperties(value = {"order"})
@Table(name="_user")
public class User {
    @Id
    @JsonProperty("id")
    @Column(name= "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @JsonProperty("name")
    @Column(name= "user_name", nullable = false)
    private String name;

    //@NonNull
    @OneToMany(mappedBy = "_user", cascade = CascadeType.ALL)
    private List<Order> orders;

    // getters and setters done by @Data tag form Lombic dependency
}
