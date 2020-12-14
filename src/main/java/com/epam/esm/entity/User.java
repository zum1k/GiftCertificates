package com.epam.esm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EntityListeners({AuditDateEntityListener.class})
@Data
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User implements AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private ZonedDateTime createDate;
    private ZonedDateTime lastUpdateDate;
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Order> orders = new HashSet<>();

}
