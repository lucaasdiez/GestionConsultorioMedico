package com.mgc.controller;

import com.mgc.dto.EspecialidadDTO;
import com.mgc.dto.ProfesionalDTO;
import com.mgc.exceptions.ResourceNotFoundException;
import com.mgc.models.Especialidad;
import com.mgc.models.Profesional;
import com.mgc.response.ApiResponse;
import com.mgc.service.profesional.ProfesionalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/profesionales")
@RequiredArgsConstructor
public class ProfesionalController {
    private final ProfesionalService profesionalService;

    @GetMapping("/todos")
    public ResponseEntity<ApiResponse> getEspecialidades(){
        try {
            List<Profesional> profesionales = profesionalService.getProfesionales();
            List<ProfesionalDTO> profesionalesDTO = profesionalService.convertirAProfesionalDTO(profesionales);
            return ResponseEntity.ok(new ApiResponse("Especialidades", profesionalesDTO));
        }catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
