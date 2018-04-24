package mx.com.beo.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * Copyright (c) 2017 Nova Solution Systems S.A. de C.V. Mexico D.F. Todos los
 * derechos reservados.
 *
 * @author Reynaldo Ivan Martinez Lopez
 * @author Betzabe Colin Olvera
 *
 *         ESTE SOFTWARE ES INFORMACIÓN CONFIDENCIAL. PROPIEDAD DE NOVA SOLUTION
 *         SYSTEMS. ESTA INFORMACIÓN NO DEBE SER DIVULGADA Y PUEDE SOLAMENTE SER
 *         UTILIZADA DE ACUERDO CON LOS TÉRMINOS DETERMINADOS POR LA EMPRESA SÍ
 *         MISMA.
 */

public class Operaciones {
	private static final Logger LOGGER = LoggerFactory.getLogger(Operaciones.class);

	UtilidadesRest utilidadesRest = new UtilidadesRest();

	public ResponseEntity<Object> banderaAcceso(Map<String, Object> envioNotificacion, Map<String, Object> mapGeneral,
			Map<String, Object> requestBody, Map<String, Object> mapHeaders, HttpHeaders httpHeaders) {
		
		
		
		if (existsAndHasValue(requestBody, Constantes.BANDERA_ACCESO, "1")) {
			
			String urlModificaContrato = Urls.URL_MODIFICA_CONTRATO.getPath();
			LOGGER.info("URL DEL CONTRATO-----------" + urlModificaContrato);
			Set<MediaType> mediaTypeValidos = new HashSet<>();
			mediaTypeValidos.add(MediaType.APPLICATION_JSON);
			mediaTypeValidos.add(MediaType.APPLICATION_JSON_UTF8);

			Map<String, Object> modificaContrato = new HashMap<>();
			modificaContrato.put("contratoAceptado", requestBody.get(Constantes.BANDERA_ACCESO));
			modificaContrato.put("usuario", mapHeaders.get(Constantes.CLIENTE));

			ResponseEntity<Object> entity = utilidadesRest.enviarPeticion(urlModificaContrato, HttpMethod.POST,
					mediaTypeValidos, null, modificaContrato, Urls.URL_BITACORA.getPath(), httpHeaders);
			
			@SuppressWarnings("unchecked")
			Map<String, Object> mapaRespuesta = (Map<String, Object>) entity.getBody();
			mapGeneral.put(Constantes.ENVIO_NOTIFICACION, envioNotificacion);
			LOGGER.info(Constantes.MAP_GENERAL ,  mapGeneral);
			LOGGER.info(Constantes.URLS ,  Urls.URL_BITACORA.getPath());
			LOGGER.info(Constantes.HTTPHEADERS ,  httpHeaders);
			if (existsAndHasValue(mapaRespuesta, "codigo", "0")) {
				LOGGER.debug(Constantes.LOG_OK_CONTRATO_MODIFICADO);
				// Se lanza peticiones para realizar login
				Map<String, Object> respuesta = utilidadesRest.restMultiples(mapGeneral,"login",Urls.URL_BITACORA.getPath(),httpHeaders);
				return obtenerRespuestaLogin(respuesta, mapHeaders);
			} else {
				return error400("Error, Al intentar modificar el contrato");
			}
		} else if (existsAndHasValue(requestBody, Constantes.BANDERA_ACCESO, "")) {
			LOGGER.debug(Constantes.LOG_OK_MOSTRAR_CONTRATO);
			Map<String, Object> respuesta = new HashMap<>();
			respuesta.put("mostrarContrato", true);
			respuesta.put(Constantes.RESPONSE_STATUS, 200);
			respuesta.put(Constantes.RESPONSE_ERROR, Constantes.VACIO);
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} else if(existsAndHasValue(requestBody, Constantes.BANDERA_ACCESO, "0")){
			LOGGER.debug(Constantes.LOG_ERROR_CONTRATO , httpHeaders.get("iv-user"));
			return utilidadesRest.getErrorResponse(400, "Contrato no aceptado",
					"Dato invalido en banderaAcceso");
		}
		return null;
	}

	public Boolean existsAndHasValue(Map<String, Object> map, String key, Object value) {
		return map.containsKey(key) && map.get(key).equals(value);
	}

	public ResponseEntity<Object> obtenerRespuestaLogin(Map<String, Object> respuestaMultiple,
			Map<String, Object> headers) {
		Map<String, Object> respuestaLogin = obtieneBody(respuestaMultiple, headers);
		return new ResponseEntity<>(respuestaLogin, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> obtieneBody(Map<String, Object> respuesta, Map<String, Object> headers) {
		Map<String, Object>  mapaRespuesta;
		Map<String, Object>  mapaRespuesta2;
		Map<String, Object>  mapaRespuesta3;
		Map<String, Object>  mapaRespuesta4;

		Map<String, Object> respuestaGeneral = new HashMap<>();

		respuestaGeneral.put("responseStatus", 200);
		respuestaGeneral.put("responseError", "");
		
		mapaRespuesta = obtenerBodyRespuesta(respuesta,respuestaGeneral,Constantes.DATOS_BASICOS);
		if (mapaRespuesta != null) {
			respuestaGeneral.put("nombreRazonSocial", mapaRespuesta.get("nombre"));
			respuestaGeneral.put("listaTelefonos", mapaRespuesta.get("listaTelefonos"));
		}
		mapaRespuesta2 = obtenerBodyRespuesta(respuesta,respuestaGeneral,Constantes.SERVICIOS_CONTRATADOS);
		if (mapaRespuesta2 != null) {
			respuestaGeneral.put(Constantes.SERVICIOS_CONTRATADOS, mapaRespuesta2);
		}
		mapaRespuesta3 = obtenerBodyRespuesta(respuesta,respuestaGeneral,Constantes.ENVIO_NOTIFICACION);
		if (mapaRespuesta3 != null) {
			respuestaGeneral.putAll(mapaRespuesta3); 
		}
		mapaRespuesta4 = obtenerBodyRespuesta(respuesta,respuestaGeneral,Constantes.PERFIL_GENERAL);
		if (mapaRespuesta4 != null) {
			respuestaGeneral.put("facultadesSimples", mapaRespuesta4.get("facultadesSimples"));
		}
		
		respuestaGeneral.put("fechaUltimoAcceso",Utilerias.fechaFormatoServicio((String) headers.get("fechaUltimoAcceso"), Constantes.FORMATO_FECHA));
		respuestaGeneral.put("nombreUsuario", headers.get("nombreUsuario"));
		respuestaGeneral.put("medioAcceso", headers.get("canal"));
		respuestaGeneral.put("mail", headers.get("mailCliente"));
		respuestaGeneral.put("cliente", headers.get("cliente"));

		return respuestaGeneral;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> obtenerBodyRespuesta(Map<String, Object> respuesta,Map<String, Object> respuestaGeneral,String llave) {
		if(respuesta.containsKey(llave)) {		
			Map<String, Object> mapaRespuesta = (Map<String, Object>) respuesta.get(llave);
			return (Map<String, Object>) mapaRespuesta.get(Constantes.BODY);
		}else {
			respuestaGeneral.put("responseStatus", 400);
			respuestaGeneral.put("responseError", "Error al consultar la respuesta del servicio: "+  llave);
			return null;
		}
	}

	/**
	 * 
	 * @return Regresa un JSON que nos trae el error 403 Description Metodo
	 *         donde manejamos el error 403. Este error se ejecuta en caso de
	 *         que se produsca un error al consumir uno de los servicios.
	 */
	public ResponseEntity<Object> error400(String mensaje) {
		return utilidadesRest.getErrorResponse(400, mensaje, mensaje);
	}

}
