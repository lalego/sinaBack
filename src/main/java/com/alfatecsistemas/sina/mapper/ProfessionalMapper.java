package com.alfatecsistemas.sina.mapper;

import com.alfatecsistemas.sina.domain.OrmaProfessionals;
import com.alfatecsistemas.sina.dto.ProfessionalDto;

public final class ProfessionalMapper {

  /**
   * Private constructor to avoid instantiation of class.
   */
  private ProfessionalMapper() {
    // Nothing to do here
  }

  /**
   * Maps a DTO to an entity.
   * 
   * @param entity The entity.
   */
  public static ProfessionalDto entityToDto(final OrmaProfessionals entity) {
    ProfessionalDto dto = new ProfessionalDto();
    dto.setId(entity.getProfId());
    dto.setName(entity.getProfName());
    dto.setSurname1(entity.getProfSurname1());
    dto.setSurname2(entity.getProfSurname2());
    dto.setIdValue(entity.getProfIdValue());

    return dto;
  }
}
