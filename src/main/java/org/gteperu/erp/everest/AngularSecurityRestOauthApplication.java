package org.gteperu.erp.everest;




import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.gteperu.erp.everest.utils.Constantes;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;


@SpringBootApplication
@EnableEncryptableProperties
@Configuration
@EnableScheduling
@ComponentScan 
@EntityScan("org.gteperu.erp.everest.domain")
@MapperScan ("org.gteperu.erp.everest.mappers") 
public class AngularSecurityRestOauthApplication  {
	
	private static final Logger LOG = LoggerFactory.getLogger(AngularSecurityRestOauthApplication.class);
	
    public static void main(String[] args) throws IOException {
//    	GetKey();
        SpringApplication.run(AngularSecurityRestOauthApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AngularSecurityRestOauthApplication.class);
    }
	
    public static void GetKey() throws IOException {
	    try{
	    	File archivo = new File(Constantes.rutaEncryptPass);
	    	if (!archivo.exists()) {
	    	    System.out.println("No existe el archivo de desencriptacion");
	    	    return;
	    	}
	    	String ruta_encode =Base64.getEncoder().encodeToString(Constantes.rutaEncryptPass.getBytes("utf-8"));
	    	String ruta_decode= new String(Base64.getDecoder().decode(ruta_encode.getBytes()), "utf-8");
	    	Path path = Paths.get(ruta_decode);
	    	String secret = new String(Files.readAllBytes(path));
	    	secret = secret.trim();
	    	System.setProperty("jasypt.encryptor.password", secret);
	    }catch(Exception e){
	    	LOG.error("GetKey() => ERROR", new RuntimeException(e.getMessage()));
	    	throw e;
	    }
    }
 

}
