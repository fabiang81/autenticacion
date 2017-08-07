package mx.com.beo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
* Copyright (c)  2017 Nova Solution Systems S.A. de C.V.
* Mexico D.F.
* Todos los derechos reservados.
*
* @author Reynaldo ivan 
*
* ESTE SOFTWARE ES INFORMACIÓN CONFIDENCIAL. PROPIEDAD DE NOVA SOLUTION SYSTEMS.
* ESTA INFORMACIÓN NO DEBE SER DIVULGADA Y PUEDE SOLAMENTE SER UTILIZADA DE ACUERDO CON LOS TÉRMINOS DETERMINADOS POR LA EMPRESA SÍ MISMA.
*/

@SpringBootApplication
@EnableAsync
public class AutenticacionApp {

	public static void main(String[] args) {
		SpringApplication.run(AutenticacionApp.class, args);
	}
}
