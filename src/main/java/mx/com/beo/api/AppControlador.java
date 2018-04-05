package mx.com.beo.api;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.beo.exceptions.HeaderNotFoundException;
import mx.com.beo.util.Constantes;
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
	private UtilidadesRest utilidadesRest = new UtilidadesRest();


	private static final Logger LOGGER = LoggerFactory.getLogger(AppControlador.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cambioContrasena", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cambioContrasena(RequestEntity<Object> request) {
		
		LOGGER.info(Constantes.LOG_ENDPOINT_CAMBIO_CONTRASENA);
		
		Set<Entry<String, Object>> entries = null;
		HeadersParser headersParser = new HeadersParser();
		Map<String, Object> mapaHeader = null;
		Map<String, Object> sendRequestBody = null;
		Map<String, Object> respuesta = new HashMap<>();
 
		try {
			
			sendRequestBody = (Map<String, Object>) request.getBody();

			// ValidaHeaders
			mapaHeader = headersParser.validaHeaders(request);
			entries = mapaHeader.entrySet();
		} catch (HeaderNotFoundException headerNotFoundException) {
			LOGGER.error(headerNotFoundException.getMessage());
			Map<String, Object> responseError = new HashMap<>();
			responseError.put(Constantes.RESPONSE_STATUS, 400);
			responseError.put(Constantes.RESPONSE_ERROR, headerNotFoundException.getMessage());
			return new ResponseEntity<>(responseError, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(Constantes.EXCEPTION_HEADERS, e.toString());
			Map<String, Object> responseError = new HashMap<>();
			responseError.put(Constantes.RESPONSE_STATUS, 400);
			responseError.put(Constantes.RESPONSE_ERROR, e.getMessage());
			return new ResponseEntity<>(responseError, HttpStatus.OK);
		}

		for (Entry<String, Object> e : entries) {
			sendRequestBody.put(e.getKey(), e.getValue());
		}

		if (sendRequestBody.get("newPassword").toString()
				.equalsIgnoreCase(sendRequestBody.get("confirmNewPassword").toString())) {

			String url = Urls.CONTRASENA.getPath();
			
			LOGGER.debug(Constantes.LOG_ENDPOINT_CONTRASENA, url);
			
			Set<MediaType> mediaTypeValidos = new HashSet<>();
			mediaTypeValidos.add(MediaType.APPLICATION_JSON);
			mediaTypeValidos.add(MediaType.APPLICATION_JSON_UTF8);
			LOGGER.debug(Constantes.LOG_OK);
			sendRequestBody.remove("cliente");
			LOGGER.info("CambioContraseña---------"+sendRequestBody);
			
			return utilidadesRest.enviarPeticion(url, HttpMethod.POST, mediaTypeValidos, null, sendRequestBody, Urls.URL_BITACORA.getPath(), request.getHeaders());

		} else {
			LOGGER.debug(Constantes.LOG_CONTRASENAS_MODIFICAR);
			long resStatus = 403;
			respuesta.put(Constantes.RESPONSE_STATUS, resStatus);
			respuesta.put(Constantes.RESPONSE_ERROR, "Contraseñas diferentes");
			return new ResponseEntity<>(respuesta, HttpStatus.OK);

		}

	}
 
	@RequestMapping(value = "/accesoCliente", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> accesoCliente(RequestEntity<Object> request) {
		LOGGER.info(Constantes.LOG_ENDPOINT_ACCESO_CLIENTES);
		LOGGER.info("Headers autenticacion: "+request.getHeaders());
		Map<String, Object> mapaHeader = null;
		Set<Entry<String, Object>> entries = null;
		HeadersParser headersParser = new HeadersParser();
		Map<String, Object> sendRequestBody = new HashMap<>();
		Map<String, Object> mapaHeadersAValidar = new HashMap<>();
		Map<String, Object> ticket = new HashMap<>();
		Map<String, Object> headers = new HashMap<>();
		Map<String, Object> consultaDatosBasicos = new HashMap<>();
		Map<String, Object> consultaServicioContratado = new HashMap<>();
		Map<String, Object> perfil = new HashMap<>();
		Map<String, Object> perfilInterno= new HashMap<>();
		Map<String, Object> mapGeneral = new HashMap<>();
		Map<String, Object> mapParameters = new HashMap<>();
		Map<String, String> mapHeaders = request.getHeaders().toSingleValueMap();
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
			Map<String, Object> responseError = new HashMap<>();
			responseError.put(Constantes.RESPONSE_STATUS, 400);
			responseError.put(Constantes.RESPONSE_ERROR, headerNotFoundException.getMessage());
			return new ResponseEntity<>(responseError, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(Constantes.EXCEPTION_HEADERS , e.toString());
			Map<String, Object> responseError = new HashMap<>();
			responseError.put(Constantes.RESPONSE_STATUS, 400);
			responseError.put(Constantes.RESPONSE_ERROR, e.getMessage());
			return new ResponseEntity<>(responseError, HttpStatus.OK);
		}

		for (Entry<String, Object> e : entries) {
			sendRequestBody.put(e.getKey(), e.getValue());
		}

		Map<String, Object> envioNotificacionBody = new HashMap<>();
		Map<String, Object> consultaServiciosContratadosBody = new HashMap<>();
		 
		envioNotificacionBody.put(Constantes.IDPERSONA,
				sendRequestBody.get(Constantes.IDPERSONA));
		envioNotificacionBody.put(Constantes.TICKET,
				sendRequestBody.get(Constantes.TICKET));
		
		
		envioNotificacionBody.put("tipoMensaje", "texto");
		envioNotificacionBody.put("tipoNotificacion", "notificacion");
		envioNotificacionBody.put("from", "nova@nova.com");
		envioNotificacionBody.put("subject", "texto");
		envioNotificacionBody.put("to", "nova1@nova.com");
		mapParameters.put("key", "1234");
		envioNotificacionBody.put("mapParameters", mapParameters);
		envioNotificacionBody.put("canal", sendRequestBody.get("canal"));

		Map<String, Object> envioNotificacion = new HashMap<>();
		envioNotificacion.put(Constantes.ENDPOINT, Urls.URL_ENVIO_NOTIFICACIONES.getPath());
		envioNotificacion.put(Constantes.METHOD, "POST");
		envioNotificacion.put(Constantes.CONNECT_TIMEOUT, 5000);
		envioNotificacion.put(Constantes.READ_TIMEOUT, 5000);
		envioNotificacion.put(Constantes.CONNECT_REQUEST_TIMEOUT, 5000);
		envioNotificacion.put(Constantes.HEADER, headers);
		envioNotificacion.put(Constantes.BODY, envioNotificacionBody);

		
		consultaServiciosContratadosBody.put(Constantes.TICKET,
				sendRequestBody.get(Constantes.TICKET));
		consultaServiciosContratadosBody.put(Constantes.CANAL,
				sendRequestBody.get(Constantes.CANAL));
		consultaServiciosContratadosBody.put(Constantes.IDPERSONA
				,sendRequestBody.get(Constantes.IDPERSONA));
		
		consultaDatosBasicos.put(Constantes.ENDPOINT, Urls.SER_CONSULTA_DATOS_BASICOS.getPath());
		consultaDatosBasicos.put(Constantes.METHOD, "POST");
		consultaDatosBasicos.put(Constantes.CONNECT_TIMEOUT, 5000);
		consultaDatosBasicos.put(Constantes.READ_TIMEOUT, 5000);
		consultaDatosBasicos.put(Constantes.CONNECT_REQUEST_TIMEOUT, 5000);
		consultaDatosBasicos.put(Constantes.HEADER, headers);
		consultaDatosBasicos.put(Constantes.BODY, consultaServiciosContratadosBody);
 
		 
		consultaServicioContratado.put(Constantes.ENDPOINT, Urls.URL_SERVICIOS_CONTRATADOS.getPath());
		consultaServicioContratado.put(Constantes.METHOD, "POST");
		consultaServicioContratado.put(Constantes.CONNECT_TIMEOUT, 5000);
		consultaServicioContratado.put(Constantes.READ_TIMEOUT, 5000);
		consultaServicioContratado.put(Constantes.CONNECT_REQUEST_TIMEOUT, 5000);
		consultaServicioContratado.put(Constantes.HEADER, headers);
		consultaServicioContratado.put(Constantes.BODY, consultaServiciosContratadosBody);
 
		perfilInterno.put(Constantes.TICKET,sendRequestBody.get(Constantes.TICKET));
		perfilInterno.put(Constantes.CANAL,sendRequestBody.get(Constantes.CANAL));
		perfilInterno.put(Constantes.IDPERSONA,sendRequestBody.get(Constantes.IDPERSONA));
		perfilInterno.put(Constantes.NOMBRESISTEMA, "MultivaNet");

		perfil.put(Constantes.ENDPOINT, Urls.URL_PERFIL.getPath());
		perfil.put(Constantes.METHOD, "POST");
		perfil.put(Constantes.CONNECT_TIMEOUT, 5000);
		perfil.put(Constantes.READ_TIMEOUT, 5000);
		perfil.put(Constantes.CONNECT_REQUEST_TIMEOUT, 5000);
		perfil.put(Constantes.HEADER, headers);
		perfil.put(Constantes.BODY, perfilInterno);
		
		LOGGER.info("consultaServicioContratado----------------- "+consultaServicioContratado);
		LOGGER.info("envioNotificacion----------------- "+envioNotificacion);
		LOGGER.info("consultaDatosBasicos----------------- "+consultaDatosBasicos);
		LOGGER.info("perfil----------------- "+perfil);
		
		mapGeneral.put("envioNotificacion", envioNotificacion);
		mapGeneral.put("consultaDatosBasicos", consultaDatosBasicos);
		mapGeneral.put("consultaServicioContratadoGeneral", consultaServicioContratado);
		mapGeneral.put("perfilGeneral", perfil);

		Operaciones operaciones = new Operaciones();
		switch (mapHeaders.get("contrato-aceptado")) {
			case "1":
				LOGGER.debug(Constantes.LOG_CONTRATO_ACEPTADO);
				Map<String, Object> respuesta = utilidadesRest.restMultiples(mapGeneral,"login",Urls.URL_BITACORA.getPath(),request.getHeaders());
  			    return operaciones.obtenerRespuestaLogin(respuesta,mapaHeader);
			case "0":
				LOGGER.debug(Constantes.LOG_CONTRATO_NO_ACEPTADO);
				return operaciones.banderaAcceso(envioNotificacion, mapGeneral, (Map<String, Object>) request.getBody(), mapaHeader, request.getHeaders());			
			default:
				return utilidadesRest.getErrorResponse(400, "Valor invalido en contrato aceptado", "Valor invalido en contrato aceptado");
		}
	}
}
