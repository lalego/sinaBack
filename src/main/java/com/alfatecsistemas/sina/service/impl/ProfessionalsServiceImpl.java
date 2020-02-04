package com.alfatecsistemas.sina.service.impl;

import com.alfatecsistemas.sina.dao.ProfessionalDao;
import com.alfatecsistemas.sina.domain.OrmaProfessionals;
import com.alfatecsistemas.sina.dto.FilterProfessionalsDto;
import com.alfatecsistemas.sina.dto.ProfessionalDto;
import com.alfatecsistemas.sina.mapper.ProfessionalMapper;
import com.alfatecsistemas.sina.service.ProfessionalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProfessionalsServiceImpl implements ProfessionalsService {

    @Autowired
    private ProfessionalDao professionalDao;

    public List<ProfessionalDto> getProfessionals(Map<String, String> params) {
        final FilterProfessionalsDto filterProfessionals = maperProfessionals(params);
        List<OrmaProfessionals> professionals = professionalDao.findAll(filterProfessionals);
        return professionals.stream().map(ProfessionalMapper::entityToDto).collect(Collectors.toList());
    }

    public ProfessionalDto getProfessional(Integer profId) {
        OrmaProfessionals professional = professionalDao.findOne(profId);
        return ProfessionalMapper.entityToDto(professional);
    }

    private static FilterProfessionalsDto maperProfessionals(final Map<String, String> params) {
        final FilterProfessionalsDto maperProfessionals = new FilterProfessionalsDto();

        maperProfessionals.setName(trimEmpty(params, "name"));
        maperProfessionals.setSurname1(trimEmpty(params, "surname1"));
        maperProfessionals.setSurname2(trimEmpty(params, "surname2"));
        maperProfessionals.setIdValue(trimEmpty(params, "idValue"));

        return maperProfessionals;
    }

    private static String trimEmpty(final Map<String, String> params, final String name) {
        return StringUtils.isEmpty(params.get(name)) ? null : params.get(name);
    }

}
