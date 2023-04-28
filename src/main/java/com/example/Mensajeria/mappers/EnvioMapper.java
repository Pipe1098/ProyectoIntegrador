package com.example.Mensajeria.mappers;

import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.model.Envio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface EnvioMapper {
    EnvioMapper INSTANCE = Mappers.getMapper( EnvioMapper.class );

    EnvioDTO envioToEnvioDTO(Envio envio);

   // Envio envioDTOToEnvio(EnvioDTO envioDTO);

}
