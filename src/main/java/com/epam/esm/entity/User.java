package com.epam.esm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.List;

@EntityListeners({AuditDateEntityListener.class})
@Data
@AllArgsConstructor
@Table(name = "user")
public class User {
    private long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    @OneToMany
    private List<Order> orderList;
    private ZonedDateTime createDate;
    private ZonedDateTime lastUpdateDate;

}
