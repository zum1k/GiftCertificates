package com.epam.esm.service;

import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.GiftCertificateDto;

import java.util.List;

public interface GiftService {
    /**
     * Adds gift certificate with its tags using values from {@code GiftCertificateDto}.     *
     *
     * @param giftCertificateDto {@code GiftCertificateDto} to add.
     * @return GiftCertificateDto with its tags.
     */
    GiftCertificateDto add(GiftCertificateDto giftCertificateDto);

    /**
     * Remove gift certificate by its id.
     *
     * @param id {@code GiftCertificate}'s id to remove.
     * @return {@code GiftCertificateDto} if certificate is removed.
     */

    GiftCertificateDto remove(long id);

    /**
     * Updates gift certificate.
     *
     * @param id                 {@code GiftCertificates}'s id to update.
     * @param giftCertificateDto {@code GiftCertificateDto} with updated values.     *
     * @return {@code GiftCertificateDto} with updated values.     *
     */

    GiftCertificateDto update(long certificateId, GiftCertificateDto giftCertificateDto);

    /**
     * Finds all gift certificates by specific query.
     *
     * @return List of {@code GiftCertificateDto} satisfying to parameters.
     */

    List<GiftCertificateDto> findAll(RequestParametersDto dto);

    /**
     * Finds gift certificate by its id.
     *
     * @param id {@code GiftCertificate}'s id.
     * @return {@code GiftCertificateDto}.
     */
    GiftCertificateDto findById(long id);

}
