package com.epam.esm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull(message = "Need to enter email")
    @Size(min =15, max = 45, message = "Email size must be in 13 to 45 symbols range")
    @Column(name = "email")
    private String email;
    @NotNull(message = "Need to enter password")
    @Size(min = 9, max = 23, message = "Password size must be in 9 to 23 symbols range")
    @Column(name = "password")
    private String password;
    @NotNull(message = "Need to enter first name")
    @Size(min = 2, max = 45, message = " First name size must be in 2 to 45 symbols range")
    @Column(name = "first_name")
    private String firstName;
    @NotNull(message = "Need to enter last name")
    @Column(name = "last_name")
    @Size(min = 3, max = 45, message = "Last name size must be in 2 to 45 symbols range")
    private String lastName;
    @Column(name = "create_date")
    private ZonedDateTime createDate;
    @Column(name = "last_update_date")
    private ZonedDateTime lastUpdateDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Order> orders = new HashSet<>();

}
