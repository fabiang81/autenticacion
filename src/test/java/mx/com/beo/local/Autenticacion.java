package mx.com.beo.local;

/**
* Copyright (c)  2017 Nova Solution Systems S.A. de C.V.
* Mexico D.F.
* Todos los derechos reservados.
*
* @author NombreDelDesarrollador 
*
* ESTE SOFTWARE ES INFORMACIÓN CONFIDENCIAL. PROPIEDAD DE NOVA SOLUTION SYSTEMS.
* ESTA INFORMACIÓN NO DEBE SER DIVULGADA Y PUEDE SOLAMENTE SER UTILIZADA DE ACUERDO CON LOS TÉRMINOS DETERMINADOS POR LA EMPRESA SÍ MISMA.
*/

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import mx.com.beo.App;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class Autenticacion {
	private static final Logger LOGGER = LoggerFactory.getLogger(Autenticacion.class);

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@SuppressWarnings("unchecked")
	@Test
	public void descargaCfdCfdi() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		System.out.println("------------------------------------------------------------------------cambioContrasena");
		
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("iv-user", "1");
		headers.set("iv-creds", "1");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "123456");
		headers.set("nombre-completo", "123456");
		headers.set("tipo-authenticacion", "123456");
		headers.set("contratoAceptado", "123456");
		headers.set("fechaUltimoAcceso", "123456");
		headers.set("tipocanal", "123456");
		headers.set("mail", "123456");
		

		Map<String, Object> body = new HashMap<String, Object>();
  
		body.put("otp", "1");
		body.put("oldPassword", "PDF"); 
		body.put("newPassword","1");
		body.put("confirmNewPassword","1");
		
		
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		LOGGER.info("Cuerpo que se arma " + entity.getBody());
		System.out.println("Cabeceras que se arman" + entity.getHeaders());
		
		ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort("/cambioContrasena"), HttpMethod.POST, entity, Object.class);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("responseStatus", 200);
		Map<String, Object> mapBody = new HashMap<String, Object>();
		LOGGER.info("Cabacera del servicio cosumido" + response.getHeaders());
		LOGGER.info("Cuerpo del servicio cosumido" + response.getBody());
		mapBody = (Map<String, Object>) response.getBody();
		 System.out.println("Mapas--------------------------------------------"+mapBody);
		assertEquals(respuesta.get("responseStatus"), mapBody.get("responseStatus"));
	}
	
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
