package com.mgc.service.profesional;


import com.mgc.dto.ProfesionalDTO;
import com.mgc.models.Profesional;

import java.util.List;

public interface ProfesionalService {
    List<Profesional> getProfesionales();
    List<ProfesionalDTO> convertirAProfesionalDTO(List<Profesional> profesional);
}
