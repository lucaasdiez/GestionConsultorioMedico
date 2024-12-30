package com.mgc.controller;


import com.mgc.dto.EspecialidadDTO;
import com.mgc.service.especialidad.EspecialidadService;
import com.mgc.exceptions.ResourceNotFoundException;
import com.mgc.models.Especialidad;
import com.mgc.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/especialidades")
@RequiredArgsConstructor
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    @GetMapping("/todos")
    public ResponseEntity<ApiResponse> getEspecialidades(){
        try {
            List<Especialidad> especialidades = especialidadService.getAllEspecialidades();
            List<EspecialidadDTO> especialidadesDTO = especialidadService.convertirAEspecialidadDTO(especialidades);
            return ResponseEntity.ok(new ApiResponse("Especialidades", especialidadesDTO));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
