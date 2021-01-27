package com.epam.esm.entity;

import com.epam.esm.entity.utils.RoleConverter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
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

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "role")
  @Convert(converter = RoleConverter.class)
  private Role role;

  @Column(name = "create_date")
  private ZonedDateTime createDate;

  @Column(name = "last_update_date")
  private ZonedDateTime lastUpdateDate;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JsonBackReference
  private Set<Order> orders = new HashSet<>();
}
