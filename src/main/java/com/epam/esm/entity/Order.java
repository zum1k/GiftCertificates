package com.epam.esm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@EntityListeners({AuditDateEntityListener.class})
@Data
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order implements AuditEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private long orderId;

  private BigDecimal price;

  @Column(name = "purchase_date")
  private ZonedDateTime purchaseDate;

  @Column(name = "create_date")
  private ZonedDateTime createDate;

  @Column(name = "last_update_date")
  private ZonedDateTime lastUpdateDate;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  @EqualsAndHashCode.Exclude
  private User user;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "orders_gifts",
      joinColumns = @JoinColumn(name = "order_id"),
      inverseJoinColumns = @JoinColumn(name = "gift_id"))
  private Set<GiftCertificate> gifts = new HashSet<>();
}
