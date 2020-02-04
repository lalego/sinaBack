package com.alfatecsistemas.sina.mapper;

import com.alfatecsistemas.sina.domain.SecuUsers;
import com.alfatecsistemas.sina.dto.UserDto;

public final class UserMapper {

  /**
   * Private constructor to avoid instantiation of class.
   */
  private UserMapper() {
    // Nothing to do here
  }

  /**
   * Maps a DTO to an entity.
   *
   * @param entity The entity.
   */
  public static UserDto entityToDto(final SecuUsers entity) {
    if (entity == null) {
      return null;
    }

    UserDto dto = new UserDto();
    dto.setId(entity.getUserId());
    dto.setName(entity.getUserLogin());
    dto.setPassword(entity.getUserPassword());
    dto.setProfessionalId(entity.getProfId());

    return dto;
  }
}
