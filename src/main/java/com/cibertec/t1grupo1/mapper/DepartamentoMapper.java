package com.cibertec.t1grupo1.mapper;

import com.cibertec.t1grupo1.dto.DepartamentoDTO;
import com.cibertec.t1grupo1.model.Departamento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {
    DepartamentoDTO toDTO(Departamento departamento);
    Departamento toEntity(DepartamentoDTO dto);
}