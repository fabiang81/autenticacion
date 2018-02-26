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
public class AutenticacionTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AutenticacionTest.class);

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@SuppressWarnings("unchecked")
	@Test
	public void cambioContrasena() throws Exception {
		HttpHeaders headers = new HttpHeaders(); 
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("iv-user", "1");
		headers.set("cookie", "JSESSIONID=1yO8dxBx6mq6aiboiFwcvBlc_W59rvlcrHGUKyvE.mmxmtzbelectronica; PD-S-SESSION-ID=1_Nh7mjwEuiVa8VtpIkM650frHGbzu3mWIQRreACSikfZFqpYoG44=_AAAAAAA=_ckMIbwznVUzytnBfaF96sprhEcQ=; LFR_SESSION_STATE_20120=1506553957696");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "123456");
		headers.set("nombre-completo", "123456");
		headers.set("tipo-authenticacion", "123456");
		headers.set("contrato-aceptado", "123456");
		headers.set("fechaUltimoAcceso", "123456");
		headers.set("tipocanal", "123456");
		headers.set("mail", "123456");
		

		Map<String, Object> body = new HashMap<String, Object>();
  
		body.put("otp", "1");
		body.put("oldPassword", "999"); 
		body.put("newPassword","123");
		body.put("confirmNewPassword","123");
		
		
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		LOGGER.info("Cuerpo que se arma " + entity.getBody()); 
		
		ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort("/cambioContrasena"), HttpMethod.POST, entity, Object.class);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("responseStatus", 200);
		Map<String, Object> mapBody = new HashMap<String, Object>();
		LOGGER.info("Cabecera del servicio consumido" + response.getHeaders());
		LOGGER.info("Cuerpo del servicio consumido" + response.getBody());
		mapBody = (Map<String, Object>) response.getBody();
		assertEquals(respuesta.get("responseStatus"), mapBody.get("responseStatus"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void cambioContrasenaSinHeader() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		 
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("iv-user", "1");
		headers.set("cookie", "JSESSIONID=1yO8dxBx6mq6aiboiFwcvBlc_W59rvlcrHGUKyvE.mmxmtzbelectronica; PD-S-SESSION-ID=1_Nh7mjwEuiVa8VtpIkM650frHGbzu3mWIQRreACSikfZFqpYoG44=_AAAAAAA=_ckMIbwznVUzytnBfaF96sprhEcQ=; LFR_SESSION_STATE_20120=1506553957696");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "123456");
		headers.set("tipo-authenticacion", "123456");
		headers.set("contrato-aceptado", "123456");
		headers.set("fechaUltimoAcceso", "123456"); 
		headers.set("mail", "123456");
		

		Map<String, Object> body = new HashMap<String, Object>();
  
		body.put("otp", "1");
		body.put("oldPassword", "999"); 
		body.put("newPassword","123");
		body.put("confirmNewPassword","123");
		
		
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		LOGGER.info("Cuerpo que se arma " + entity.getBody());
		 
		ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort("/cambioContrasena"), HttpMethod.POST, entity, Object.class);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("responseStatus", 400);
		Map<String, Object> mapBody = new HashMap<String, Object>();
		LOGGER.info("Cabecera del servicio consumido" + response.getHeaders());
		LOGGER.info("Cuerpo del servicio consumido" + response.getBody());
		mapBody = (Map<String, Object>) response.getBody();
		assertEquals(respuesta.get("responseStatus"), mapBody.get("responseStatus"));
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void cambioContrasenaErrorActualNoExiste() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		 
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("iv-user", "1");
		headers.set("cookie", "JSESSIONID=1yO8dxBx6mq6aiboiFwcvBlc_W59rvlcrHGUKyvE.mmxmtzbelectronica; PD-S-SESSION-ID=1_Nh7mjwEuiVa8VtpIkM650frHGbzu3mWIQRreACSikfZFqpYoG44=_AAAAAAA=_ckMIbwznVUzytnBfaF96sprhEcQ=; LFR_SESSION_STATE_20120=1506553957696");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "123456");
		headers.set("nombre-completo", "123456");
		headers.set("tipo-authenticacion", "123456");
		headers.set("contrato-aceptado", "123456");
		headers.set("fechaUltimoAcceso", "123456");
		headers.set("tipocanal", "123456");
		headers.set("mail", "123456");
		

		Map<String, Object> body = new HashMap<String, Object>();
  
		body.put("otp", "1");
		body.put("oldPassword", "9222"); 
		body.put("newPassword","123");
		body.put("confirmNewPassword","123");
		
		
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		LOGGER.info("Cuerpo que se arma " + entity.getBody());
		 
		ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort("/cambioContrasena"), HttpMethod.POST, entity, Object.class);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("responseStatus", 404);
		Map<String, Object> mapBody = new HashMap<String, Object>();
		LOGGER.info("Cabecera del servicio consumido" + response.getHeaders());
		LOGGER.info("Cuerpo del servicio consumido" + response.getBody());
		mapBody = (Map<String, Object>) response.getBody();
		 assertEquals(respuesta.get("responseStatus"), mapBody.get("responseStatus"));
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void cambioContrasenaErrorNoSonIguales() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		 
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("iv-user", "1");
		headers.set("cookie", "JSESSIONID=1yO8dxBx6mq6aiboiFwcvBlc_W59rvlcrHGUKyvE.mmxmtzbelectronica; PD-S-SESSION-ID=1_Nh7mjwEuiVa8VtpIkM650frHGbzu3mWIQRreACSikfZFqpYoG44=_AAAAAAA=_ckMIbwznVUzytnBfaF96sprhEcQ=; LFR_SESSION_STATE_20120=1506553957696");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "123456");
		headers.set("nombre-completo", "123456");
		headers.set("tipo-authenticacion", "123456");
		headers.set("contrato-aceptado", "123456");
		headers.set("fechaUltimoAcceso", "123456");
		headers.set("tipocanal", "123456");
		headers.set("mail", "123456");
		

		Map<String, Object> body = new HashMap<String, Object>();
  
		body.put("otp", "1");
		body.put("oldPassword", "999"); 
		body.put("newPassword","12");
		body.put("confirmNewPassword","123");
		
		
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		LOGGER.info("Cuerpo que se arma " + entity.getBody());
		 
		ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort("/cambioContrasena"), HttpMethod.POST, entity, Object.class);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("responseStatus", 403);
		Map<String, Object> mapBody = new HashMap<String, Object>();
		LOGGER.info("Cabecera del servicio consumido " + response.getHeaders());
		LOGGER.info("Cuerpo del servicio consumido nuevas contraseñas no son iguales" + response.getBody());
		mapBody = (Map<String, Object>) response.getBody();
		 assertEquals(respuesta.get("responseStatus"), mapBody.get("responseStatus"));
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void accesoCliente() throws Exception {
		HttpHeaders headers = new HttpHeaders();
	 
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("iv-user", "1");
		headers.set("cookie", "JSESSIONID=1yO8dxBx6mq6aiboiFwcvBlc_W59rvlcrHGUKyvE.mmxmtzbelectronica; PD-S-SESSION-ID=1_Nh7mjwEuiVa8VtpIkM650frHGbzu3mWIQRreACSikfZFqpYoG44=_AAAAAAA=_ckMIbwznVUzytnBfaF96sprhEcQ=; LFR_SESSION_STATE_20120=1506553957696");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "19990");
		headers.set("nombre-completo", "111");
		headers.set("tipo-authenticacion", "22333333");
		headers.set("contrato-aceptado", "0");
		headers.set("fechaUltimoAcceso", "44444");
		headers.set("tipocanal", "5555");
		headers.set("mail", "66666");
		 
		Map<String, Object> body = new HashMap<String, Object>();
  
		body.put("banderaAcceso", "1");
		
		
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		LOGGER.info("Cuerpo que se arma " + entity.getBody());
	 
		ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort("/accesoCliente"), HttpMethod.POST, entity, Object.class);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("responseStatus", 200);
		Map<String, Object> mapBody = new HashMap<String, Object>();
		LOGGER.info("Cabecera del servicio consumido" + response.getHeaders());
		LOGGER.info("Cuerpo del servicio consumido" + response.getBody());
		
		mapBody = (Map<String, Object>) response.getBody();
		 
		assertEquals(respuesta.get("responseStatus"), mapBody.get("responseStatus"));
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void accesoClienteError() throws Exception {
		HttpHeaders headers = new HttpHeaders();
	 
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("iv-user", "1");
		headers.set("cookie", "JSESSIONID=1yO8dxBx6mq6aiboiFwcvBlc_W59rvlcrHGUKyvE.mmxmtzbelectronica; PD-S-SESSION-ID=1_Nh7mjwEuiVa8VtpIkM650frHGbzu3mWIQRreACSikfZFqpYoG44=_AAAAAAA=_ckMIbwznVUzytnBfaF96sprhEcQ=; LFR_SESSION_STATE_20120=1506553957696");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "999");
		headers.set("nombre-completo", "111");
		headers.set("tipo-authenticacion", "22333333");
		headers.set("contrato-aceptado", "0");
		headers.set("fechaUltimoAcceso", "44444");
		headers.set("tipocanal", "5555");
		headers.set("mail", "66666");
		 
		Map<String, Object> body = new HashMap<String, Object>();
  
		body.put("banderaAcceso", "1");
		
		
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		LOGGER.info("Cuerpo que se arma " + entity.getBody());
	 
		ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort("/accesoCliente"), HttpMethod.POST, entity, Object.class);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("responseStatus", 400);
		Map<String, Object> mapBody = new HashMap<String, Object>();
		LOGGER.info("Cabecera del servicio consumido" + response.getHeaders());
		LOGGER.info("Cuerpo del servicio consumido" + response.getBody());
		
		mapBody = (Map<String, Object>) response.getBody();
		 
		assertEquals(respuesta.get("responseStatus"), mapBody.get("responseStatus"));
	}

	
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void accesoClienteSinHeaders() throws Exception {
		HttpHeaders headers = new HttpHeaders();
	
		
		
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("iv-user", "1");
		headers.set("cookie", "JSESSIONID=1yO8dxBx6mq6aiboiFwcvBlc_W59rvlcrHGUKyvE.mmxmtzbelectronica; PD-S-SESSION-ID=1_Nh7mjwEuiVa8VtpIkM650frHGbzu3mWIQRreACSikfZFqpYoG44=_AAAAAAA=_ckMIbwznVUzytnBfaF96sprhEcQ=; LFR_SESSION_STATE_20120=1506553957696");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "444");
		headers.set("nombre-completo", "333");
		headers.set("tipo-authenticacion", "2222");
		headers.set("contrato-aceptado", "1");
		headers.set("fechaUltimoAcceso", "11111222");
		headers.set("mail", "111");
		

		Map<String, Object> body = new HashMap<String, Object>();
  
		body.put("banderaAcceso", "");
		
		
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		LOGGER.info("Cuerpo que se arma " + entity.getBody());
		 
		ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort("/accesoCliente"), HttpMethod.POST, entity, Object.class);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("responseStatus", 400);
		Map<String, Object> mapBody = new HashMap<String, Object>();
		LOGGER.info("Cabecera del servicio consumido" + response.getHeaders());
		LOGGER.info("Cuerpo del servicio consumido" + response.getBody());
		
		mapBody = (Map<String, Object>) response.getBody();
		  
		assertEquals(respuesta.get("responseStatus"), mapBody.get("responseStatus"));
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void accesoClienteContratoNoAceptado() throws Exception {
		HttpHeaders headers = new HttpHeaders();
	
		
		
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("iv-user", "1");
		headers.set("cookie", "JSESSIONID=1yO8dxBx6mq6aiboiFwcvBlc_W59rvlcrHGUKyvE.mmxmtzbelectronica; PD-S-SESSION-ID=1_Nh7mjwEuiVa8VtpIkM650frHGbzu3mWIQRreACSikfZFqpYoG44=_AAAAAAA=_ckMIbwznVUzytnBfaF96sprhEcQ=; LFR_SESSION_STATE_20120=1506553957696");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "123456");
		headers.set("nombre-completo", "123456");
		headers.set("tipo-authenticacion", "123456");
		headers.set("contrato-aceptado", "0");
		headers.set("fechaUltimoAcceso", "123456");
		headers.set("tipocanal", "123456");
		headers.set("mail", "123456");
		

		Map<String, Object> body = new HashMap<String, Object>();
  
		body.put("banderaAcceso", "");
		
		
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		LOGGER.info("Cuerpo que se arma " + entity.getBody());
		 
		ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort("/accesoCliente"), HttpMethod.POST, entity, Object.class);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("responseStatus", 400);
		Map<String, Object> mapBody = new HashMap<String, Object>();
		LOGGER.info("Cabecera del servicio consumido" + response.getHeaders());
		LOGGER.info("Cuerpo del servicio consumido" + response.getBody());
		
		mapBody = (Map<String, Object>) response.getBody();
	 
		assertEquals(respuesta.get("responseStatus"), mapBody.get("responseStatus"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void accesoClienteContratobanderaAcceso0() throws Exception {
		HttpHeaders headers = new HttpHeaders();
	
		
		
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("iv-user", "1");
		headers.set("cookie", "JSESSIONID=1yO8dxBx6mq6aiboiFwcvBlc_W59rvlcrHGUKyvE.mmxmtzbelectronica; PD-S-SESSION-ID=1_Nh7mjwEuiVa8VtpIkM650frHGbzu3mWIQRreACSikfZFqpYoG44=_AAAAAAA=_ckMIbwznVUzytnBfaF96sprhEcQ=; LFR_SESSION_STATE_20120=1506553957696");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "123456");
		headers.set("nombre-completo", "123456");
		headers.set("tipo-authenticacion", "123456");
		headers.set("contrato-aceptado", "0");
		headers.set("fechaUltimoAcceso", "123456");
		headers.set("tipocanal", "123456");
		headers.set("mail", "123456");
		

		Map<String, Object> body = new HashMap<String, Object>();
  
		body.put("banderaAcceso", "0");
		
		
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		LOGGER.info("Cuerpo que se arma " + entity.getBody());
		 
		ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort("/accesoCliente"), HttpMethod.POST, entity, Object.class);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("responseStatus", 200);
		Map<String, Object> mapBody = new HashMap<String, Object>();
		LOGGER.info("Cabecera del servicio consumido" + response.getHeaders());
		LOGGER.info("Cuerpo del servicio consumido" + response.getBody());
		
		mapBody = (Map<String, Object>) response.getBody();
		 
		assertEquals(respuesta.get("responseStatus"), mapBody.get("responseStatus"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void accesoClienteContratobanderaAcceso1() throws Exception {
		HttpHeaders headers = new HttpHeaders();
	
		
		
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("iv-user", "1");
		headers.set("cookie", "JSESSIONID=1yO8dxBx6mq6aiboiFwcvBlc_W59rvlcrHGUKyvE.mmxmtzbelectronica; PD-S-SESSION-ID=1_Nh7mjwEuiVa8VtpIkM650frHGbzu3mWIQRreACSikfZFqpYoG44=_AAAAAAA=_ckMIbwznVUzytnBfaF96sprhEcQ=; LFR_SESSION_STATE_20120=1506553957696");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "123456");
		headers.set("nombre-completo", "123456");
		headers.set("tipo-authenticacion", "123456");
		headers.set("contrato-aceptado", "0");
		headers.set("fechaUltimoAcceso", "123456");
		headers.set("tipocanal", "123456");
		headers.set("mail", "123456"); 

		Map<String, Object> body = new HashMap<String, Object>();
  
		body.put("banderaAcceso", "1");
		
		
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		LOGGER.info("Cuerpo que se arma " + entity.getBody());
		 
		ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort("/accesoCliente"), HttpMethod.POST, entity, Object.class);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("responseStatus", 200);
		Map<String, Object> mapBody = new HashMap<String, Object>();
		LOGGER.info("Cabecera del servicio consumido" + response.getHeaders());
		LOGGER.info("Cuerpo del servicio consumido" + response.getBody());
		
		mapBody = (Map<String, Object>) response.getBody();
		 
		assertEquals(respuesta.get("responseStatus"), mapBody.get("responseStatus"));
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void accesoClienteContratobanderaAcceso1SinCodigo() throws Exception {
		HttpHeaders headers = new HttpHeaders();
	
		
		
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("iv-user", "1");
		headers.set("cookie", "JSESSIONID=1yO8dxBx6mq6aiboiFwcvBlc_W59rvlcrHGUKyvE.mmxmtzbelectronica; PD-S-SESSION-ID=1_Nh7mjwEuiVa8VtpIkM650frHGbzu3mWIQRreACSikfZFqpYoG44=_AAAAAAA=_ckMIbwznVUzytnBfaF96sprhEcQ=; LFR_SESSION_STATE_20120=1506553957696");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "123456");
		headers.set("nombre-completo", "123456");
		headers.set("tipo-authenticacion", "123456");
		headers.set("contrato-aceptado", "0");
		headers.set("fechaUltimoAcceso", "123456");
		headers.set("tipocanal", "123456");
		headers.set("mail", "123456");

		Map<String, Object> body = new HashMap<String, Object>();
  
		body.put("banderaAcceso", "1");
		
		
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		LOGGER.info("Cuerpo que se arma " + entity.getBody());
		 
		ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort("/accesoCliente"), HttpMethod.POST, entity, Object.class);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("responseStatus", 200);
		Map<String, Object> mapBody = new HashMap<String, Object>();
		LOGGER.info("Cabecera del servicio consumido" + response.getHeaders());
		LOGGER.info("Cuerpo del servicio consumido" + response.getBody());
		
		mapBody = (Map<String, Object>) response.getBody();
		 
		assertEquals(respuesta.get("responseStatus"), mapBody.get("responseStatus"));
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void accesoClienteNull() throws Exception {
		HttpHeaders headers = new HttpHeaders();
	
		
		
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		headers.set("iv-user", "1");
		headers.set("cookie", "JSESSIONID=1yO8dxBx6mq6aiboiFwcvBlc_W59rvlcrHGUKyvE.mmxmtzbelectronica; PD-S-SESSION-ID=1_Nh7mjwEuiVa8VtpIkM650frHGbzu3mWIQRreACSikfZFqpYoG44=_AAAAAAA=_ckMIbwznVUzytnBfaF96sprhEcQ=; LFR_SESSION_STATE_20120=1506553957696");
		headers.set("iv-groups", "1");
		headers.set("numero-cliente", "123456");
		headers.set("nombre-completo", "123456");
		headers.set("tipo-authenticacion", "123456");
		headers.set("contrato-aceptado", "4");
		headers.set("fechaUltimoAcceso", "123456");
		headers.set("tipocanal", "123456");
		headers.set("mail", "123456");
		
		

		Map<String, Object> body = new HashMap<String, Object>();
  
		body.put("banderaAcceso", "");
		
		
		HttpEntity<Object> entity = new HttpEntity<Object>(body, headers);
		LOGGER.info("Cuerpo que se arma " + entity.getBody());
		 
		ResponseEntity<Object> response = restTemplate.exchange(createURLWithPort("/accesoCliente"), HttpMethod.POST, entity, Object.class);
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("responseStatus", 400);
		Map<String, Object> mapBody = new HashMap<String, Object>();
		LOGGER.info("Cabecera del servicio consumido" + response.getHeaders());
		LOGGER.info("Cuerpo del servicio consumido" + response.getBody());
		
		mapBody = (Map<String, Object>) response.getBody();
		 
		assertEquals(respuesta.get("responseStatus"), mapBody.get("responseStatus"));
	}
	
	
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
