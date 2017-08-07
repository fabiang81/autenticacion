/**
* Copyright (c)  2017 Nova Solution Systems S.A. de C.V.
* Mexico D.F.
* Todos los derechos reservados.
*
* @author Aaron Jazhiel Delgado Gonzalez
*
* ESTE SOFTWARE ES INFORMACIÓN CONFIDENCIAL. PROPIEDAD DE NOVA SOLUTION SYSTEMS.
* ESTA INFORMACIÓN NO DEBE SER DIVULGADA Y PUEDE SOLAMENTE SER UTILIZADA DE ACUERDO CON LOS TÉRMINOS DETERMINADOS POR LA EMPRESA SÍ MISMA.
*/

package mx.com.beo.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
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
import mx.com.beo.AutenticacionApp;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutenticacionApp.class, webEnvironment = WebEnvironment.RANDOM_PORT)

@DirtiesContext
public class AccesoClienteTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;
	
	
	@Test
	public void accesoCliente() throws Exception {

		// Para mandar los parametros de headers
		HttpHeaders headers = new HttpHeaders();
 
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		

		// Le mando la cabecera  y la imprimo en el servicio
		headers.set("iv-user", "1"); 
		headers.set("iv-creds", "1");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "123456");
		headers.set("nombre-completo", "123456");
		headers.set("tipo-authenticacion", "123456"); 
		headers.set("contratoAceptado", "123456"); 
		headers.set("fechaUltimoAcceso", "123456");
		headers.set("Tipocanal", "123456");
		headers.set("mail", "123456");
		
		
		String body="{"
				+ "\"banderaAcceso\": \"\""
						+ "}";
		
		
		HttpEntity<String> entity = new HttpEntity<String>(body, headers);

		// Enpoint de mi servicio en este caso hice uno de prueba le mando un
		// header y lo imprimo
		// en el servicio
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/accesoCliente"), HttpMethod.POST, entity,
				String.class);
		// Valido que el json que me regrese es igual
		  
		String respuesta="{\"responseError\":\"\",\"mostrarContrato\":true,\"responseStatus\":200}";
		 
		JSONAssert.assertEquals(respuesta, response.getBody(), true);
		System.out.println("probando que regrese el template accesoCliente" + response.getBody()); //imprimo body
																						

	}
 

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
