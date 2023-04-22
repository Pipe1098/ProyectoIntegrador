package com.example.Mensajeria.configurer;

import com.example.Mensajeria.dto.EnvioDTO;
import com.example.Mensajeria.model.Cliente;
import com.example.Mensajeria.model.Envio;
import com.example.Mensajeria.model.Paquete;
import com.example.Mensajeria.service.EnvioService.EstadoEnvio;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-21T19:49:33-0500",
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
        envioDTO.setValorDeclarado( envio.getValorDeclarado() );
        envioDTO.setPeso( envio.getPeso() );
        envioDTO.setCedulaEmpleado( envio.getCedulaEmpleado() );

        return envioDTO;
    }

    @Override
    public Envio envioDTOToEnvio(EnvioDTO envioDTO) {
        if ( envioDTO == null ) {
            return null;
        }

        String ciudadOrigen = null;
        String ciudadDestino = null;
        String dirDestino = null;
        String nombreReceptor = null;
        String celReceptor = null;
        double valorDeclarado = 0.0d;
        double peso = 0.0d;
        String cedula = null;

        ciudadOrigen = envioDTO.getCiudadOrigen();
        ciudadDestino = envioDTO.getCiudadDestino();
        dirDestino = envioDTO.getDirDestino();
        nombreReceptor = envioDTO.getNombreReceptor();
        celReceptor = envioDTO.getCelReceptor();
        valorDeclarado = envioDTO.getValorDeclarado();
        peso = envioDTO.getPeso();
        cedula = envioDTO.getCedula();

        String codigo = null;
        Cliente newCliente = null;
        LocalDateTime fecha = null;
        EstadoEnvio estadoEnvio = null;
        Double valorEnvio = null;
        Paquete paquete = null;

        Envio envio = new Envio( codigo, newCliente, cedula, ciudadOrigen, ciudadDestino, dirDestino, nombreReceptor, celReceptor, fecha, estadoEnvio, valorDeclarado, peso, valorEnvio, paquete );

        envio.setCedulaEmpleado( envioDTO.getCedulaEmpleado() );

        return envio;
    }
}
