package mx.com.beo.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.beo.exceptions.HeaderNotFoundException;
import mx.com.beo.util.HeadersParser;
import mx.com.beo.util.Operaciones;
import mx.com.beo.util.Urls;
import mx.com.beo.util.UtilidadesRest;

/**
 * Copyright (c) 2017 Nova Solution Systems S.A. de C.V. Mexico D.F. Todos los
 * derechos reservados.
 *
 * @author Reynaldo Ivan Martinez Lopez
 *
 *         ESTE SOFTWARE ES INFORMACIÓN CONFIDENCIAL. PROPIEDAD DE NOVA SOLUTION
 *         SYSTEMS. ESTA INFORMACIÓN NO DEBE SER DIVULGADA Y PUEDE SOLAMENTE SER
 *         UTILIZADA DE ACUERDO CON LOS TÉRMINOS DETERMINADOS POR LA EMPRESA SÍ
 *         MISMA.
 */

@RestController
@RequestMapping(value = "/")
public class AppControlador {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppControlador.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cambioContrasena", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cambioContrasena(RequestEntity<Object> request) {
		LOGGER.info("EndPoint cambioContrasena");
		Map<String, Object> mapaHeader = null;
		Set<Entry<String, Object>> entries = null;
		HeadersParser headersParser = new HeadersParser();
		UtilidadesRest utilidadesRest = new UtilidadesRest();
		Map<String, Object> sendRequestBody = new HashMap<String, Object>();
		Map<String, Object> respuesta = new HashMap<String, Object>();
		sendRequestBody = (Map<String, Object>) request.getBody();
 
		try {
			// ValidaHeaders
			mapaHeader = headersParser.validaHeaders(request);
			entries = mapaHeader.entrySet();
		} catch (HeaderNotFoundException headerNotFoundException) {
			LOGGER.error(headerNotFoundException.getMessage());
			Map<String, Object> responseError = new HashMap<String, Object>();
			responseError.put("responseStatus", 400);
			responseError.put("responseError", headerNotFoundException.getMessage());
			return new ResponseEntity<>(responseError, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("ValidaHeaders exception: " + e.toString());
			Map<String, Object> responseError = new HashMap<String, Object>();
			responseError.put("responseStatus", 400);
			responseError.put("responseError", e.getMessage());
			return new ResponseEntity<>(responseError, HttpStatus.OK);
		}

		for (Entry<String, Object> e : entries) {
			sendRequestBody.put(e.getKey(), e.getValue());
		}

		if (sendRequestBody.get("newPassword").toString()
				.equalsIgnoreCase(sendRequestBody.get("confirmNewPassword").toString())) {

			String url = Urls.Contrasena.getPath();
			LOGGER.debug("EndPoint Contrasena : " + url);
			Set<MediaType> mediaTypeValidos = new HashSet<MediaType>();
			mediaTypeValidos.add(MediaType.APPLICATION_JSON);
			mediaTypeValidos.add(MediaType.APPLICATION_JSON_UTF8);
			LOGGER.debug("OK");
			return utilidadesRest.enviarPeticion(url, HttpMethod.POST, mediaTypeValidos, null, sendRequestBody);

		} else {
			LOGGER.debug("Las contraseñas a modificar son diferentes");
			long resStatus = 403;
			respuesta.put("responseStatus", resStatus);
			respuesta.put("responseError", "Contraseñas diferentes");
			return new ResponseEntity<Object>(respuesta, HttpStatus.OK);

		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/accesoCliente", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> accesoCliente(RequestEntity<Object> request) {
		LOGGER.info("EndPoint accesoCliente");
		Map<String, Object> mapaHeader = null;
		Set<Entry<String, Object>> entries = null;
		HeadersParser headersParser = new HeadersParser();
		UtilidadesRest utilidadesRest = new UtilidadesRest();
		Map<String, Object> sendRequestBody = new HashMap<String, Object>();
		Map<String, Object> mapaHeadersAValidar = new HashMap<String, Object>();
		Map<String, Object> ticket = new HashMap<String, Object>();
		Map<String, Object> headers = new HashMap<String, Object>();
		Map<String, Object> consultaDatosBasicos = new HashMap<String, Object>();
		Map<String, Object> consultaServicioContratado = new HashMap<String, Object>();
		Map<String, Object> perfil = new HashMap<String, Object>();
		Map<String, Object> perfilInterno = new HashMap<String, Object>();
		Map<String, Object> mapGeneral = new HashMap<String, Object>();
		Map<String, Object> mapParameters = new HashMap<String, Object>();
		//sendRequestBody = (Map<String, Object>) request.getBody();
		HttpHeaders headers2 = request.getHeaders();
		Map<String, String> mapHeaders = new HashMap<>();
		mapHeaders = (Map<String, String>) request.getHeaders().toSingleValueMap();
		ticket.put("iv-user", "id_user");
		ticket.put("cookie", "id_creds");
		mapaHeadersAValidar.put("ticket", ticket);
		mapaHeadersAValidar.put("tipocanal", "canal");
		mapaHeadersAValidar.put("iv-user", "idPersona");
		mapaHeadersAValidar.put("numero-cliente", "cliente");
		mapaHeadersAValidar.put("contrato-aceptado", "contrato");
		mapaHeadersAValidar.put("nombre-completo", "nombreUsuario");
		mapaHeadersAValidar.put("fechaultimoacceso", "fechaUltimoAcceso");
		mapaHeadersAValidar.put("mail", "mailCliente");
		
		try {
			// ValidaHeaders
			mapaHeader = headersParser.validaHeaders(mapaHeadersAValidar, request);
			mapaHeader.remove("contrato");
			entries = mapaHeader.entrySet();
		} catch (HeaderNotFoundException headerNotFoundException) {
			LOGGER.error(headerNotFoundException.getMessage());
			Map<String, Object> responseError = new HashMap<String, Object>();
			responseError.put("responseStatus", 400);
			responseError.put("responseError", headerNotFoundException.getMessage());
			return new ResponseEntity<>(responseError, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("ValidaHeaders exception: " + e.toString());
			Map<String, Object> responseError = new HashMap<String, Object>();
			responseError.put("responseStatus", 400);
			responseError.put("responseError", e.getMessage());
			return new ResponseEntity<>(responseError, HttpStatus.OK);
		}

		for (Entry<String, Object> e : entries) {
			sendRequestBody.put(e.getKey(), e.getValue());
		}

		Map<String, Object> envioNotificacionBody = new HashMap<String, Object>();
		envioNotificacionBody.putAll(sendRequestBody);
		mapParameters.put("key", "1234");
		envioNotificacionBody.put("tipoMensaje", "texto");
		envioNotificacionBody.put("tipoNotificacion", "notificacion");
		envioNotificacionBody.put("from", "nova@nova.com");
		envioNotificacionBody.put("subject", "texto");
		envioNotificacionBody.put("to", "nova1@nova.com");
		envioNotificacionBody.put("mapParameters", mapParameters);

		Map<String, Object> envioNotificacion = new HashMap<String, Object>();
		envioNotificacion.put("endpoint", Urls.urlEnvioNotificaciones.getPath());
		envioNotificacion.put("method", "POST");
		envioNotificacion.put("connectTimeout", 5000);
		envioNotificacion.put("readTimeout", 5000);
		envioNotificacion.put("connectionRequestTimeout", 5000);
		envioNotificacion.put("headers", headers);
		envioNotificacion.put("body", envioNotificacionBody);

		consultaDatosBasicos.put("endpoint", Urls.serConsultaDatosBasicos.getPath());
		consultaDatosBasicos.put("method", "POST");
		consultaDatosBasicos.put("connectTimeout", 5000);
		consultaDatosBasicos.put("readTimeout", 5000);
		consultaDatosBasicos.put("connectionRequestTimeout", 5000);
		consultaDatosBasicos.put("headers", headers);
		consultaDatosBasicos.put("body", sendRequestBody);

		consultaServicioContratado.put("endpoint", Urls.urlServiciosContratados.getPath());
		consultaServicioContratado.put("method", "POST");
		consultaServicioContratado.put("connectTimeout", 5000);
		consultaServicioContratado.put("readTimeout", 5000);
		consultaServicioContratado.put("connectionRequestTimeout", 5000);
		consultaServicioContratado.put("headers", headers);
		consultaServicioContratado.put("body", sendRequestBody);

		perfilInterno = sendRequestBody;
		perfilInterno.remove("banderaAcceso");
		perfilInterno.put("nombreSistema", "123");

		perfil.put("endpoint", Urls.urlPerfil.getPath());
		perfil.put("method", "POST");
		perfil.put("connectTimeout", 5000);
		perfil.put("readTimeout", 5000);
		perfil.put("connectionRequestTimeout", 5000);
		perfil.put("headers", headers);
		perfil.put("body", perfilInterno);

		mapGeneral.put("envioNotificacion", envioNotificacion);
		mapGeneral.put("consultaDatosBasicos", consultaDatosBasicos);
		mapGeneral.put("consultaServicioContratadoGeneral", consultaServicioContratado);
		mapGeneral.put("perfilGeneral", perfil);

		Operaciones operaciones = new Operaciones();
		switch (mapHeaders.get("contrato-aceptado")) {
			case "1":
				LOGGER.debug("el contrato ya esta aceptado");
				Map<String, Object> respuesta = utilidadesRest.restMultiples(mapGeneral);
				System.out.println("RESPUESTA DE LOS SERVICIOS----------------   "+respuesta);
				return operaciones.obtenerRespuestaLogin(respuesta,mapaHeader);
			case "0":
				LOGGER.debug("el contrato no esta aceptado");
				return operaciones.banderaAcceso(envioNotificacion, mapGeneral, (Map<String, Object>) request.getBody(), mapaHeader);			
			default:
				return utilidadesRest.getErrorResponse(400, "Valor invalido en contrato aceptado", "Valor invalido en contrato aceptado");
		}
	}
}
