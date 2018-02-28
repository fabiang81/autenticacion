package mx.com.beo.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
			Map<String, Object> requestBody, Map<String, Object> mapHeaders) {
		if (existsAndHasValue(requestBody, "banderaAcceso", "1")) {
			LOGGER.info("Ok, banderaAcceso trae 1");
			String urlNotificacion = Urls.urlEnvioNotificaciones.getPath();
			String urlModificaContrato = Urls.urlModificaContrato.getPath();
			Set<MediaType> mediaTypeValidos = new HashSet<MediaType>();
			mediaTypeValidos.add(MediaType.APPLICATION_JSON);
			mediaTypeValidos.add(MediaType.APPLICATION_JSON_UTF8);

			Map<String, Object> modificaContrato = new HashMap<String, Object>();
			modificaContrato.put("contratoAceptado", requestBody.get("banderaAcceso"));
			modificaContrato.put("usuario", mapHeaders.get("cliente"));

			ResponseEntity<Object> entity = utilidadesRest.enviarPeticion(urlModificaContrato, HttpMethod.POST,
					mediaTypeValidos, null, modificaContrato);
			Map<String, Object> mapaRespuesta = (Map<String, Object>) entity.getBody();

			if (existsAndHasValue(mapaRespuesta, "codigo", "0")) {
				LOGGER.debug("Ok, Se ha modificado el contrato");
				// Se lanza peticiones para realizar login
				mapGeneral.put("envioNotificacion", envioNotificacion);
				Map<String, Object> respuesta = utilidadesRest.restMultiples(mapGeneral);
				return obtenerRespuestaLogin(respuesta, mapHeaders);
			} else {
				return error400("Error, Al intentar modificar el contrato");
			}
		} else if (existsAndHasValue(requestBody, "banderaAcceso", "0")) {
			LOGGER.debug("OK, Se envia mostrar contrato");
			Map<String, Object> respuesta = new HashMap<String, Object>();
			respuesta.put("mostrarContrato", true);
			respuesta.put("responseStatus", 200);
			respuesta.put("responseError", "");
			return new ResponseEntity<Object>(respuesta, HttpStatus.OK);
		} else {
			LOGGER.debug("OK, banderaAcceso otro dato diferente");
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
		return new ResponseEntity<Object>(respuestaLogin, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> obtieneBody(Map<String, Object> respuest, Map<String, Object> headers) {

		Map<String, Object> respuestaGeneral = new HashMap<String, Object>();
		Map<String, Object> consultaDatosBasicos1 = new HashMap<String, Object>();
		Map<String, Object> consultaDatosBasicosBody = new HashMap<String, Object>();

		if (respuest.get("consultaDatosBasicos") != null) {
			consultaDatosBasicos1 = (Map<String, Object>) respuest.get("consultaDatosBasicos");
			consultaDatosBasicosBody = (Map<String, Object>) consultaDatosBasicos1.get("body");
			respuestaGeneral.put("nombreRazonSocial", consultaDatosBasicosBody.get("nombre"));
			respuestaGeneral.put("listaTelefonos", consultaDatosBasicosBody.get("listaTelefonos"));

		}
		Map<String, Object> consultaServicioContratadoGeneral = new HashMap<String, Object>();
		Map<String, Object> consultaServicioContratadoGeneralBody = new HashMap<String, Object>();

		if (respuest.get("consultaServicioContratadoGeneral") != null) {
			consultaServicioContratadoGeneral = (Map<String, Object>) respuest.get("consultaServicioContratadoGeneral");
			consultaServicioContratadoGeneralBody = (Map<String, Object>) consultaServicioContratadoGeneral.get("body");
			respuestaGeneral.put("consultaServiciosContratados", consultaServicioContratadoGeneralBody);
		}

		Map<String, Object> envioNotificacion1 = new HashMap<String, Object>();
		Map<String, Object> envioNotificacionBody = new HashMap<String, Object>();

		if (respuest.get("envioNotificacion") != null) {
			envioNotificacion1 = (Map<String, Object>) respuest.get("envioNotificacion");
			envioNotificacionBody = (Map<String, Object>) envioNotificacion1.get("body");
			respuestaGeneral.putAll(envioNotificacionBody);
		}

		Map<String, Object> perfilGeneral = new HashMap<String, Object>();
		Map<String, Object> perfilGeneralBody = new HashMap<String, Object>();

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
