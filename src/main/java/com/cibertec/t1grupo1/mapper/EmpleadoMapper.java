package com.cibertec.t1grupo1.mapper;

import com.cibertec.t1grupo1.dto.EmpleadoDTO;
import com.cibertec.t1grupo1.model.Empleado;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    @Mapping(target = "departamentoId", source = "departamento.id")
    EmpleadoDTO toDTO(Empleado empleado);

    @Mapping(target = "departamento", ignore = true)
    Empleado toEntity(EmpleadoDTO dto);
}