package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;



import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
//@AllArgsConstructor
//@JsonIgnoreProperties(value = {"order"})
@Table(name="_user")
public class User {
    @Id
    @JsonIgnore
    @JsonProperty("userId")
    @Column(name= "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @JsonProperty("userName")
    @Column(name= "userName", nullable = false)
    private String userName;

    @NonNull
    @JsonProperty("firstName")
    @Column(name= "firstName", nullable = false)
    private String firstName;

    @NonNull
    @JsonProperty("lastName")
    @Column(name= "lastName", nullable = false)
    private String lastName;

    //@NonNull
    @JsonIgnore
    @ToString.Exclude //https://stackoverflow.com/a/63088075/17410605
    @OneToMany(mappedBy = "_user", cascade = CascadeType.ALL)
    private List<Order> orders;


    // getters and setters done by @Data tag form Lombic dependency

}