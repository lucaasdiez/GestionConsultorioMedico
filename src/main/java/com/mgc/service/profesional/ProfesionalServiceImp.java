package com.mgc.service.profesional;


import com.mgc.dto.ProfesionalDTO;
import com.mgc.models.Profesional;
import com.mgc.repository.ProfesionalRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfesionalServiceImp implements ProfesionalService{

    private final ProfesionalRepository profesionalRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Profesional> getProfesionales() {
        return profesionalRepository.findAll();
    }

    @Override
    public List<ProfesionalDTO> convertirAProfesionalDTO(List<Profesional> profesionales) {
        return profesionales.stream()
                .map(profesional -> modelMapper.map(profesional, ProfesionalDTO.class))
                .toList();
    }
}
