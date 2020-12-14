package com.epam.esm.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZonedDateTime;

public class AuditDateEntityListener {

    @PrePersist
    public void onCreate(AuditEntity auditEntity) {
        ZonedDateTime currentTime = ZonedDateTime.now().withFixedOffsetZone();
        if (auditEntity.getCreateDate() == null) {
            auditEntity.setCreateDate(currentTime);
        }
        if (auditEntity.getLastUpdateDate() == null) {
            auditEntity.setLastUpdateDate(currentTime);
        }
    }

    @PreUpdate
    public void onUpdate(AuditEntity auditEntity) {
        auditEntity.setLastUpdateDate(ZonedDateTime.now());
    }
}
