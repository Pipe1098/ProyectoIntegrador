package com.example.Mensajeria.mappers;

import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.model.Envio;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-08T11:18:14-0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.19 (Amazon.com Inc.)"
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
        envioDTO.setValorDeclarado( envio.getValorDeclarado() );
        envioDTO.setPeso( envio.getPeso() );
        envioDTO.setCedula( envio.getCliente().getCedula() );
        envioDTO.setCedulaEmpleado( envio.getEmpleado().getCedula() );

        return envioDTO;
    }
}
