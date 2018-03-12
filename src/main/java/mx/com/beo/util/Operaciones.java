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
			
			LOGGER.info(Constantes.LOG_OK_BANDERA_ACCESO);
			
			String urlModificaContrato = Urls.URL_MODIFICA_CONTRATO.getPath();
			
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

			if (existsAndHasValue(mapaRespuesta, "codigo", "0")) {
				LOGGER.debug(Constantes.LOG_OK_CONTRATO_MODIFICADO);
				// Se lanza peticiones para realizar login
				mapGeneral.put(Constantes.ENVIO_NOTIFICACION, envioNotificacion);
				Map<String, Object> respuesta = utilidadesRest.restMultiples(mapGeneral,"login",Urls.URL_BITACORA.getPath(),httpHeaders);
				return obtenerRespuestaLogin(respuesta, mapHeaders);
			} else {
				return error400("Error, Al intentar modificar el contrato");
			}
		} else if (existsAndHasValue(requestBody, Constantes.BANDERA_ACCESO, "0")) {
			LOGGER.debug(Constantes.LOG_OK_MOSTRAR_CONTRATO);
			Map<String, Object> respuesta = new HashMap<>();
			respuesta.put("mostrarContrato", true);
			respuesta.put(Constantes.RESPONSE_STATUS, 200);
			respuesta.put(Constantes.RESPONSE_ERROR, Constantes.VACIO);
			return new ResponseEntity<>(respuesta, HttpStatus.OK);
		} else {
			LOGGER.debug(Constantes.LOG_OK_BANDERA_ACCESO_DATO_DIFERENTE);
			return utilidadesRest.getErrorResponse(400, "Dato invalido en banderaAcceso",
					"Dato invalido en banderaAcceso");
		}
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
	public Map<String, Object> obtieneBody(Map<String, Object> respuest, Map<String, Object> headers) {

		Map<String, Object> respuestaGeneral = new HashMap<>();
		Map<String, Object> consultaDatosBasicos1 = null;
		Map<String, Object> consultaDatosBasicosBody = null;

		if (respuest.get("consultaDatosBasicos") != null) {
			consultaDatosBasicos1 = (Map<String, Object>) respuest.get("consultaDatosBasicos");
			consultaDatosBasicosBody = (Map<String, Object>) consultaDatosBasicos1.get("body");
			respuestaGeneral.put("nombreRazonSocial", consultaDatosBasicosBody.get("nombre"));
			respuestaGeneral.put("listaTelefonos", consultaDatosBasicosBody.get("listaTelefonos"));

		}
		Map<String, Object> consultaServicioContratadoGeneral = null;
		Map<String, Object> consultaServicioContratadoGeneralBody = null;

		if (respuest.get("consultaServicioContratadoGeneral") != null) {
			consultaServicioContratadoGeneral = (Map<String, Object>) respuest.get("consultaServicioContratadoGeneral");
			consultaServicioContratadoGeneralBody = (Map<String, Object>) consultaServicioContratadoGeneral.get("body");
			respuestaGeneral.put("consultaServiciosContratados", consultaServicioContratadoGeneralBody);
		}

		Map<String, Object> envioNotificacion1 = null;
		Map<String, Object> envioNotificacionBody = null;

		if (respuest.get(Constantes.ENVIO_NOTIFICACION) != null) {
			envioNotificacion1 = (Map<String, Object>) respuest.get(Constantes.ENVIO_NOTIFICACION);
			envioNotificacionBody = (Map<String, Object>) envioNotificacion1.get(Constantes.BODY);
			respuestaGeneral.putAll(envioNotificacionBody);
		}

		Map<String, Object> perfilGeneral = null;
		Map<String, Object> perfilGeneralBody = null;

		if (respuest.get("perfilGeneral") != null) {
			perfilGeneral = (Map<String, Object>) respuest.get("perfilGeneral");
			perfilGeneralBody = (Map<String, Object>) perfilGeneral.get("body");
			respuestaGeneral.put("facultadesSimples", perfilGeneralBody.get("facultadesSimples"));
		}

		respuestaGeneral.put("fechaUltimoAcceso",
				Utilerias.fechaFormatoServicio((String) headers.get("fechaUltimoAcceso"), Constantes.FORMATO_FECHA));
		respuestaGeneral.put("nombreUsuario", headers.get("nombreUsuario"));
		respuestaGeneral.put("medioAcceso", headers.get("canal"));
		respuestaGeneral.put("mail", headers.get("mailCliente"));
		respuestaGeneral.put("cliente", headers.get("cliente"));

		return respuestaGeneral;
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
