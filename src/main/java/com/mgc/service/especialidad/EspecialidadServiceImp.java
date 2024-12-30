package com.mgc.service.especialidad;



import com.mgc.dto.EspecialidadDTO;
import com.mgc.models.Especialidad;
import com.mgc.repository.EspecialidadRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspecialidadServiceImp implements EspecialidadService{
    private final EspecialidadRepository especialidadRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<Especialidad> getAllEspecialidades() {
        return especialidadRepository.findAll();
    }

    @Override
    public List<EspecialidadDTO> convertirAEspecialidadDTO(List<Especialidad> especialidades) {
        return especialidades.stream()
                .map(especialidad -> modelMapper.map(especialidad, EspecialidadDTO.class))
                .toList();

    }
}
