package com.example.Mensajeria.mappers;

import com.example.Mensajeria.dto.PaqueteDTO;
import com.example.Mensajeria.model.Paquete;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-10T09:03:49-0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.19 (Amazon.com Inc.)"
)
public class PaqueteMapperImpl implements PaqueteMapper {

    @Override
    public PaqueteDTO paqueteToPaqueteDTO(Paquete paquete) {
        if ( paquete == null ) {
            return null;
        }

        PaqueteDTO paqueteDTO = new PaqueteDTO();

        paqueteDTO.setPeso( paquete.getPeso() );
        paqueteDTO.setValorDeclarado( paquete.getValorDeclarado() );
        paqueteDTO.setTipoPaquete( paquete.getTipoPaquete() );

        return paqueteDTO;
    }

    @Override
    public Paquete paqueteDTOToPaquete(PaqueteDTO paqueteDTO) {
        if ( paqueteDTO == null ) {
            return null;
        }

        Paquete paquete = new Paquete();

        paquete.setTipoPaquete( paqueteDTO.getTipoPaquete() );
        paquete.setPeso( paqueteDTO.getPeso() );
        paquete.setValorDeclarado( paqueteDTO.getValorDeclarado() );

        return paquete;
    }
}
