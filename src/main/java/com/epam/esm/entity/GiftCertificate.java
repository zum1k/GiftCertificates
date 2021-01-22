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
@Entity
@Data
@NoArgsConstructor
@Table(name = "gifts")
public class GiftCertificate implements AuditEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "gift_id")
  private long giftId;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "create_date")
  private ZonedDateTime createDate;

  @Column(name = "last_update_date")
  private ZonedDateTime lastUpdateDate;

  @Column(name = "duration")
  private long duration;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "gifts_tags",
      joinColumns = @JoinColumn(name = "gift_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private Set<Tag> tags = new HashSet<>();

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "gifts")
  private Set<Order> orders = new HashSet<>();
}
