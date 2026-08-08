package com.cibertec.t1grupo1.mapper;

import com.cibertec.t1grupo1.dto.AsistenciaDTO;
import com.cibertec.t1grupo1.model.Asistencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AsistenciaMapper {
    @Mapping(target = "empleadoId", source = "empleado.id")
    AsistenciaDTO toDTO(Asistencia asistencia);

    @Mapping(target = "empleado", ignore = true)
    Asistencia toEntity(AsistenciaDTO dto);
}