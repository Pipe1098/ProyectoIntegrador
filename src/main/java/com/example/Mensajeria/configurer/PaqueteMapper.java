package com.example.Mensajeria.configurer;
import com.example.Mensajeria.dto.PaqueteDTO;
import com.example.Mensajeria.model.Paquete;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PaqueteMapper {

    PaqueteMapper INSTANCE = Mappers.getMapper( PaqueteMapper.class );

    PaqueteDTO paqueteToPaqueteDTO(Paquete paquete);

    Paquete paqueteDTOToPaquete(PaqueteDTO paqueteDTO);
}
