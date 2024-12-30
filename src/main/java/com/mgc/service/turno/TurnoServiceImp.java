package com.mgc.service.turno;


import com.mgc.dto.TurnoDTO;
import com.mgc.enums.EstadoTurno;
import com.mgc.exceptions.ResourceNotFoundException;
import com.mgc.exceptions.TurnoNoValidoException;
import com.mgc.models.*;
import com.mgc.repository.ConsultorioRepository;
import com.mgc.repository.PacienteRepository;
import com.mgc.repository.ProfesionalRepository;
import com.mgc.repository.TurnoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoServiceImp implements TurnoService{
    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final ConsultorioRepository consultorioRepository;
    private final ProfesionalRepository profesionalRepository;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Turno registrarTurno(TurnoDTO turnoDTO) {
        Paciente paciente = pacienteRepository.findById(turnoDTO.getPaciente().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
        Consultorio consultorio = consultorioRepository.findById(turnoDTO.getConsultorio().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Consultorio no encontrado"));
        Profesional profesional = profesionalRepository.findById(turnoDTO.getProfesional().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profesional no encontrado"));
        if (esHorarioValido(turnoDTO.getFecha(), turnoDTO.getHora(), consultorio.getId(), profesional.getId() )) {

            Turno turno = new Turno();
            turno.setPaciente(paciente);
            turno.setHora(turnoDTO.getHora());
            turno.setFecha(turnoDTO.getFecha());
            turno.setConsultorio(consultorio);
            turno.setProfesional(profesional);
            turno.setEstadoTurno(EstadoTurno.RESERVADO);
            return turnoRepository.save(turno);
        }else {
            throw new TurnoNoValidoException("El turno solicitado no es valido");
        }
    }

    @Override
    public Turno modificarTurno(TurnoDTO turnoDTO) {
        if(puedeEliminarOModificarTurno(turnoDTO.getId())){
            Turno turno = turnoRepository.findById(turnoDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado"));
            turno.setHora(turnoDTO.getHora());
            turno.setFecha(turnoDTO.getFecha());
            return turnoRepository.save(turno);
        }else {
            throw new IllegalArgumentException("El plazo para eliminar o modificar este turno ha expirado");
        }
    }

    @Override
    public void cancelarTurno(Integer idTurno) {
        if(puedeEliminarOModificarTurno(idTurno)){
            Turno turno = turnoRepository.findById(idTurno)
                    .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado"));
            turno.setEstadoTurno(EstadoTurno.CANCELADO);
            turnoRepository.save(turno);
        }else {
            throw new IllegalArgumentException("El plazo para eliminar o modificar este turno ha expirado");
        }
    }

    @Override
    public List<TurnoDTO> convertirAturnoDTO(List<Turno> turnos) {
        return turnos.stream()
                .map(turno -> modelMapper.map(turno, TurnoDTO.class))
                .toList();
    }

    @Override
    public TurnoDTO convertirAturnoDTO(Turno turno) {
        return modelMapper.map(turno, TurnoDTO.class);
    }


    @Override
    public List<Turno> getTurnosPaciente(String pacienteNombre) {
        return turnoRepository.findAllByPacienteNombreIgnoreCase(pacienteNombre);
    }

    @Override
    public List<Turno> getTurnosProfesionalYEspecialidad(String profesional, String especialidad) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Turno> criteriaQuery =criteriaBuilder.createQuery(Turno.class);
        Root<Turno> root= criteriaQuery.from(Turno.class);

        Join<Turno, Profesional> profesionalJoin = root.join("profesional");
        Join<Profesional, Especialidad> especialidadJoin = profesionalJoin.join("especialidad");

        List<Predicate> predicados =new ArrayList<>();
        if (profesional != null){
            predicados.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(profesionalJoin.get("nombre")),
                    profesional.toLowerCase()));
        }
        if (especialidad != null){
            predicados.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(especialidadJoin.get("nombre")),
                    especialidad.toLowerCase()));
        }
        criteriaQuery.select(root).where(criteriaBuilder.and(predicados.toArray(new Predicate[0])));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    private boolean puedeEliminarOModificarTurno(Integer idTurno) {
        Turno turno = turnoRepository.findById(idTurno)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado"));

        LocalTime horaTurno = turno.getHora();
        LocalTime horaAhora = LocalTime.now();

        Duration diferencia = Duration.between(horaTurno, horaAhora);
        double diferenciaHoras = diferencia.toHours();
        if (diferenciaHoras < 1){
            return false;
        }
        return true;
    }



    private boolean esHorarioValido(LocalDate fecha, LocalTime hora, Integer idConsultorio, Integer idProfesional) {
        if (hora.getHour() < 8 || hora.getHour() >= 23){
            return false;
        }
        DayOfWeek diaSemana = fecha.getDayOfWeek();
        if( diaSemana == DayOfWeek.SUNDAY){
            return false;
        }
        if(verificarDisponibilidadProfesional(idProfesional,fecha, hora)){
            return false;
        }
        if (verificarDisponibilidadConsultorio(idConsultorio,fecha,hora)){
            return false;
        }
        return true;
    }

    private boolean verificarDisponibilidadConsultorio(Integer idConsultorio, LocalDate fecha, LocalTime hora) {
        return turnoRepository.existsByConsultorioIdAndFechaAndHora(idConsultorio, fecha, hora);
    }

    private boolean verificarDisponibilidadProfesional(Integer idProfesional, LocalDate fecha, LocalTime hora) {
        return turnoRepository.existsByProfesionalIdAndFechaAndHora(idProfesional,fecha,hora);
    }
}
