package com.example.Mensajeria.configurer;

import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.model.Envio;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-20T16:24:17-0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.12 (Oracle Corporation)"
)
public class EnvioMapperImpl implements EnvioMapper {

    @Override
    public EnvioDTO envioToEnvioDTO(Envio envio) {
        if ( envio == null ) {
            return null;
        }

        EnvioDTO envioDTO = new EnvioDTO();

        envioDTO.setCiudadOrigen( envio.getCiudadOrigen() );
        envioDTO.setCiudadDestino( envio.getCiudadDestino() );
        envioDTO.setDirDestino( envio.getDirDestino() );
        envioDTO.setNombreReceptor( envio.getNombreReceptor() );
        envioDTO.setCelReceptor( envio.getCelReceptor() );
        envioDTO.setValorEnvio( envio.getValorEnvio() );
        envioDTO.setHoraEntrega( envio.getHoraEntrega() );

        return envioDTO;
    }

    @Override
    public Envio envioDTOToEnvio(EnvioDTO envioDTO) {
        if ( envioDTO == null ) {
            return null;
        }

        Envio envio = new Envio();

        envio.setCiudadOrigen( envioDTO.getCiudadOrigen() );
        envio.setCiudadDestino( envioDTO.getCiudadDestino() );
        envio.setDirDestino( envioDTO.getDirDestino() );
        envio.setNombreReceptor( envioDTO.getNombreReceptor() );
        envio.setCelReceptor( envioDTO.getCelReceptor() );
        envio.setHoraEntrega( envioDTO.getHoraEntrega() );
        envio.setValorEnvio( envioDTO.getValorEnvio() );

        return envio;
    }
}
