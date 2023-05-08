package com.example.Mensajeria.mappers;

import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.model.Envio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface EnvioMapper {
    EnvioMapper INSTANCE = Mappers.getMapper( EnvioMapper.class );
    @Mapping(target = "cedula", source = "cliente.cedula")
    @Mapping(target = "cedulaEmpleado", source = "empleado.cedula")
    EnvioDTO envioToEnvioDTO(Envio envio);

}
