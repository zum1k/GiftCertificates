package com.epam.esm.service;

import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;

import java.util.List;
import java.util.Optional;

public interface TagService {
  /**
   * Adds gift certificate with its tags using values from {@code TagDto}. *
   *
   * @param tagDto {@code TagDto} to add if not exists.
   * @return TagDto if added or exists.
   */
  TagDto addTagIfNotExist(TagDto tagDto);

  /**
   * Remove tag by its id.
   *
   * @param id {@code Tag}'s id to remove.
   * @return {@code TagDto} if tag is removed.
   */
  TagDto remove(long id);

  /**
   * Find tag by its id.
   *
   * @param id {@code Tag}'s id to find.
   * @return {@code TagDto} if tag is find.
   */
  TagDto findOne(long id);

  /**
   * Find all tags.
   *
   * @return List of {@code TagDto} if tags are find.
   */
  List<TagDto> findAll(RequestParametersDto dto);

  /**
   * Find tag by its name.
   *
   * @param tagDto {@code TagDto}'s name to find tag with this name.
   * @return Optional of {@code TagDto} if tag was find.
   */
  Optional<TagDto> findByName(TagDto tagDto);

  /**
   * Find tag by certificate's id.
   *
   * @param certificateId {@code GiftCertificate}'s id to find tags are used in GiftCertificate.
   * @return List of {@code TagDto} if tags are find.
   */
  List<TagDto> findAllByCertificateId(long certificateId);

  int count(int pageSize);
  }


