package com.epam.esm.entity.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public class GiftCertificateDto {
    private long giftId;
    @NotNull(message = "Need to enter name")
    @Size(min = 2, max = 45, message = "Name size must be in 2 to 45 sumbols range")
    private String name;
    @NotNull(message = "Need to enter description")
    @Size(min = 2, max = 45, message = "Description size must be in 2 to 45 symbols range")
    private String description;
    @NotNull(message = "Need to enter price")
    @Min(value = 1, message = "Price can\'t be less than \'1\'")
    private BigDecimal price;
    private ZonedDateTime createDate;
    private ZonedDateTime lastUpdateDate;
    @NotNull
    @Min(1)
    private long duration;
    private List<TagDto> tags;

    public GiftCertificateDto(String name, String description, BigDecimal price, long duration, List<TagDto> tags) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.tags = tags;
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public long getGiftId() {
        return this.giftId;
    }

    @SuppressWarnings("all")
    public String getName() {
        return this.name;
    }

    @SuppressWarnings("all")
    public String getDescription() {
        return this.description;
    }

    @SuppressWarnings("all")
    public BigDecimal getPrice() {
        return this.price;
    }

    @SuppressWarnings("all")
    public ZonedDateTime getCreateDate() {
        return this.createDate;
    }

    @SuppressWarnings("all")
    public ZonedDateTime getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    @SuppressWarnings("all")
    public long getDuration() {
        return this.duration;
    }

    @SuppressWarnings("all")
    public List<TagDto> getTags() {
        return this.tags;
    }

    @SuppressWarnings("all")
    public void setGiftId(final long giftId) {
        this.giftId = giftId;
    }

    @SuppressWarnings("all")
    public void setName(final String name) {
        this.name = name;
    }

    @SuppressWarnings("all")
    public void setDescription(final String description) {
        this.description = description;
    }

    @SuppressWarnings("all")
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    @SuppressWarnings("all")
    public void setCreateDate(final ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    @SuppressWarnings("all")
    public void setLastUpdateDate(final ZonedDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @SuppressWarnings("all")
    public void setDuration(final long duration) {
        this.duration = duration;
    }

    @SuppressWarnings("all")
    public void setTags(final List<TagDto> tags) {
        this.tags = tags;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GiftCertificateDto)) return false;
        final GiftCertificateDto other = (GiftCertificateDto) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getGiftId() != other.getGiftId()) return false;
        if (this.getDuration() != other.getDuration()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$createDate = this.getCreateDate();
        final Object other$createDate = other.getCreateDate();
        if (this$createDate == null ? other$createDate != null : !this$createDate.equals(other$createDate)) return false;
        final Object this$lastUpdateDate = this.getLastUpdateDate();
        final Object other$lastUpdateDate = other.getLastUpdateDate();
        if (this$lastUpdateDate == null ? other$lastUpdateDate != null : !this$lastUpdateDate.equals(other$lastUpdateDate)) return false;
        final Object this$tags = this.getTags();
        final Object other$tags = other.getTags();
        if (this$tags == null ? other$tags != null : !this$tags.equals(other$tags)) return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof GiftCertificateDto;
    }

    @Override
    @SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $giftId = this.getGiftId();
        result = result * PRIME + (int) ($giftId >>> 32 ^ $giftId);
        final long $duration = this.getDuration();
        result = result * PRIME + (int) ($duration >>> 32 ^ $duration);
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $createDate = this.getCreateDate();
        result = result * PRIME + ($createDate == null ? 43 : $createDate.hashCode());
        final Object $lastUpdateDate = this.getLastUpdateDate();
        result = result * PRIME + ($lastUpdateDate == null ? 43 : $lastUpdateDate.hashCode());
        final Object $tags = this.getTags();
        result = result * PRIME + ($tags == null ? 43 : $tags.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("all")
    public String toString() {
        return "GiftCertificateDto(giftId=" + this.getGiftId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ", createDate=" + this.getCreateDate() + ", lastUpdateDate=" + this.getLastUpdateDate() + ", duration=" + this.getDuration() + ", tags=" + this.getTags() + ")";
    }

    @SuppressWarnings("all")
    public GiftCertificateDto() {
    }
    //</editor-fold>
}
