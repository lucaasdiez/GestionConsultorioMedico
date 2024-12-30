package com.mgc.service.paciente;


import com.mgc.dto.PacienteDTO;
import com.mgc.exceptions.AlreadyExistExeption;
import com.mgc.exceptions.ResourceNotFoundException;
import com.mgc.models.Paciente;
import com.mgc.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteServiceImp implements PacienteService{

    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;


    @Override
    public Paciente registrarPaciente(PacienteDTO pacienteDTO) {
        return Optional.of(pacienteDTO)
                .filter(pacienteDTO1 -> !pacienteRepository.existsByEmail(pacienteDTO1.getEmail()))
                .map(pacienteDto ->{
                    Paciente paciente = new Paciente();
                    paciente.setNombre(pacienteDto.getNombre());
                    paciente.setApellido(pacienteDto.getApellido());
                    paciente.setEmail(pacienteDto.getEmail());
                    paciente.setTelefono(pacienteDto.getTelefono());
                    paciente.setDocumento(pacienteDto.getDocumento());
                    paciente.setFecha_nacimiento(pacienteDto.getFecha_nacimiento());
                    return pacienteRepository.save(paciente);
                }).orElseThrow(() -> new AlreadyExistExeption("Usuario con email: " + pacienteDTO.getEmail() + " ya existe"));

    }

    @Override
    public Paciente getPacienteById(Integer id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente No Encontrado"));
    }

    @Override
    public Paciente getPacienteByNombre(String nombre) {
        return pacienteRepository.findByNombreIgnoreCase(nombre)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente No Encontrado"));
    }


    @Override
    public PacienteDTO convertirAPacienteDTO(Paciente paciente) {
        return modelMapper.map(paciente, PacienteDTO.class);
    }
}
