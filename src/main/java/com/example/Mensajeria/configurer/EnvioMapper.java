package com.example.Mensajeria.configurer;

import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.dto.PaqueteDTO;
import com.example.Mensajeria.model.Envio;
import com.example.Mensajeria.model.Paquete;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface EnvioMapper {
    EnvioMapper INSTANCE = Mappers.getMapper( EnvioMapper.class );

    EnvioDTO envioToEnvioDTO(Envio envio);

    Envio envioDTOToEnvio(EnvioDTO envioDTO);

}
