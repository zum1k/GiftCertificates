package com.epam.esm.service.tag;

import com.epam.esm.entity.Tag;
import com.epam.esm.entity.dto.RequestParametersDto;
import com.epam.esm.entity.dto.TagDto;
import com.epam.esm.exception.EntityNotDeletedException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.CriteriaSpecification;
import com.epam.esm.repository.specification.TagsByCertificateIdCriteriaSpecifications;
import com.epam.esm.repository.specification.TagsByNameCriteriaSpecifications;
import com.epam.esm.repository.tag.TagRepository;
import com.epam.esm.service.mapper.tag.TagMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
  private static final String TAG = "Tag";

  private final TagRepository tagRepository;
  private final TagMapper tagMapper;

  @Transactional
  @Override
  public TagDto addTagIfNotExist(TagDto tag) {
    log.info("add tag");
    Optional<TagDto> tagOptional = findByName(tag);
    if (tagOptional.isEmpty()) {
      tag.setId(0);
      return tagMapper.toDto(tagRepository.add(tagMapper.toEntity(tag)).get());
    }
    return tagOptional.get();
  }

  @Transactional
  @Override
  public TagDto remove(long id) {
    log.info("remove tag {}", id);
    Optional<Tag> tagOptional = tagRepository.remove(id);
    return tagMapper.toDto(tagOptional.orElseThrow(() -> new EntityNotDeletedException(TAG, id)));
  }

  @Override
  public TagDto findOne(long id) {
    log.info("find tag {}", id);
    Tag tag = tagRepository.findById(id);
    return tagMapper.toDto(tag);
  }

  @Override
  public List<TagDto> findAll(RequestParametersDto dto) {
    log.info("find tags ");
    int page = dto.getPage();
    int pageSize = dto.getPageLimit();
    List<Tag> tags = tagRepository.findAll(page, pageSize);
    if (tags.isEmpty()) {
      throw new EntityNotFoundException(TAG, 0);
    }
    return tagMapper.toDtoList(tags);
  }

  @Transactional
  @Override
  public Optional<TagDto> findByName(TagDto tagDto) {
    log.info("find by name {}", tagDto.getName());
    CriteriaSpecification<Tag> specification =
        new TagsByNameCriteriaSpecifications(tagDto.getName());
    Optional<Tag> optionalTag = tagRepository.findTagByName(specification);
    if (optionalTag.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(tagMapper.toDto(optionalTag.get()));
  }

  @Override
  public List<TagDto> findAllByCertificateId(long certificateId) {
    CriteriaSpecification<Tag> specification =
        new TagsByCertificateIdCriteriaSpecifications(certificateId);
    List<Tag> tags = tagRepository.findTagsByCertificateId(specification);
    return tagMapper.toDtoList(tags);
  }

  @Override
  public long count(RequestParametersDto dto) {
    log.info("count tag pages");
    int pageSize = dto.getPageLimit();
    long elementsAmount = tagRepository.count();
    return elementsAmount % pageSize == 0
        ? elementsAmount / pageSize
        : elementsAmount / pageSize + 1;
  }

}
