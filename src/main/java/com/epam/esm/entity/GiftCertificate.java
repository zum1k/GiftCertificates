package com.epam.esm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "create_date")
    private ZonedDateTime createDate;
    @Column(name = "last_update_date")
    private ZonedDateTime lastUpdateDate;
    @Column(name = "duration")
    private long duration;

    @ManyToMany
    @JoinTable(
            name = "gifts_tags",
            joinColumns = @JoinColumn(name = "gift_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    public GiftCertificate(String name, String description, BigDecimal price, ZonedDateTime createDate, ZonedDateTime lastUpdateDate, long duration) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.duration = duration;
    }
}
