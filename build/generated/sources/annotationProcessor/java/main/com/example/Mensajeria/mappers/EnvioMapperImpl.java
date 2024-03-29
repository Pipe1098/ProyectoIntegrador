package com.example.Mensajeria.mappers;

import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.model.Cliente;
import com.example.Mensajeria.model.Empleado;
import com.example.Mensajeria.model.Envio;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-10T17:11:28-0500",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 11.0.19 (Amazon.com Inc.)"
)
public class EnvioMapperImpl implements EnvioMapper {

    @Override
    public EnvioDTO envioToEnvioDTO(Envio envio) {
        if ( envio == null ) {
            return null;
        }

        EnvioDTO envioDTO = new EnvioDTO();

        envioDTO.setCedula( envioClienteCedula( envio ) );
        envioDTO.setCedulaEmpleado( envioEmpleadoCedula( envio ) );
        envioDTO.setCiudadOrigen( envio.getCiudadOrigen() );
        envioDTO.setCiudadDestino( envio.getCiudadDestino() );
        envioDTO.setDirDestino( envio.getDirDestino() );
        envioDTO.setNombreReceptor( envio.getNombreReceptor() );
        envioDTO.setCelReceptor( envio.getCelReceptor() );
        envioDTO.setValorDeclarado( envio.getValorDeclarado() );
        envioDTO.setPeso( envio.getPeso() );

        return envioDTO;
    }

    private String envioClienteCedula(Envio envio) {
        if ( envio == null ) {
            return null;
        }
        Cliente cliente = envio.getCliente();
        if ( cliente == null ) {
            return null;
        }
        String cedula = cliente.getCedula();
        if ( cedula == null ) {
            return null;
        }
        return cedula;
    }

    private String envioEmpleadoCedula(Envio envio) {
        if ( envio == null ) {
            return null;
        }
        Empleado empleado = envio.getEmpleado();
        if ( empleado == null ) {
            return null;
        }
        String cedula = empleado.getCedula();
        if ( cedula == null ) {
            return null;
        }
        return cedula;
    }
}
