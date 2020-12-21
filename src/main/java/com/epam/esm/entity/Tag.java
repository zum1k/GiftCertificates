package com.epam.esm.entity;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@EntityListeners({AuditDateEntityListener.class})
@Entity
@Table(name = "tags")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Tag implements AuditEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "tag_id")
  private long tagId;

  @Column(name = "name")
  private String name;

  @Column(name = "create_date")
  private ZonedDateTime createDate;

  @Column(name = "last_update_date")
  private ZonedDateTime lastUpdateDate;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
  private Set<GiftCertificate> certificate = new HashSet<>();
}
