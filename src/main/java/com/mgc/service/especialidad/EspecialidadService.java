package com.mgc.service.especialidad;




import com.mgc.dto.EspecialidadDTO;
import com.mgc.models.Especialidad;

import java.util.List;

public interface EspecialidadService {
    List<Especialidad> getAllEspecialidades();
    List<EspecialidadDTO> convertirAEspecialidadDTO(List<Especialidad> especialidad);
}
