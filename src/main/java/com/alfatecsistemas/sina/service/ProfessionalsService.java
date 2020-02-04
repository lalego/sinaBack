package com.alfatecsistemas.sina.service;

import com.alfatecsistemas.sina.dto.ProfessionalDto;

import java.util.List;
import java.util.Map;

public interface ProfessionalsService {

    List<ProfessionalDto> getProfessionals(Map<String, String> params);

    ProfessionalDto getProfessional(Integer profId);
}
