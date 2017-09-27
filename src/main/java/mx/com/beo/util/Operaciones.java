package mx.com.beo.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
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

	public Map<String, Object> banderaAcceso(Map<String, Object> envioNotificacion, Map<String, Object> mapGeneral) {

		Map<String, Object> respuestaError = null;
		Map<String, Object> validaContrato = new HashMap<String, Object>();

		

		try {

			if (envioNotificacion.get("banderaAcceso").toString().equalsIgnoreCase("1")) {
				
				LOGGER.info("Ok, banderaAcceso trae 1");

				String url = Urls.SERENVIONOT.getPath();
				String urlValidaContrato = Urls.urlValidaContrato.getPath();

				Set<MediaType> mediaTypeValidos = new HashSet<MediaType>();
				mediaTypeValidos.add(MediaType.APPLICATION_JSON);
				mediaTypeValidos.add(MediaType.APPLICATION_JSON_UTF8);

				ResponseEntity<Object> entity = utilidadesRest.enviarPeticion(url, HttpMethod.POST, mediaTypeValidos,
						null, envioNotificacion);

				@SuppressWarnings("unchecked")
				Map<String, Object> resEnvioNotificacion = (Map<String, Object>) entity.getBody();
				String resEnvioPet = resEnvioNotificacion.get("responseStatus").toString(); 
				if (resEnvioPet.equals("200")) {
					LOGGER.info("Ok, Envio de Notificación");
					validaContrato.put("ContratoAceptado", Integer.parseInt(envioNotificacion.get("banderaAcceso").toString()));
					validaContrato.put("Usuario", envioNotificacion.get("idPersona"));
					 
					ResponseEntity<Object> entityValidaContrato = utilidadesRest.enviarPeticion(urlValidaContrato,
							HttpMethod.POST, mediaTypeValidos, null, validaContrato);

					@SuppressWarnings("unchecked")
					Map<String, Object> resValidaContra = (Map<String, Object>) entityValidaContrato.getBody(); 
					String varValidaContrato=resValidaContra.get("codigo").toString();
				 
					if (varValidaContrato.equals("0")) {
						LOGGER.info("Ok, Valida Contrato");
						@SuppressWarnings("unchecked")
						Map<String, Object> respuest = utilidadesRest.restMultiples(mapGeneral);

						return respuest;
					} else {
						LOGGER.info("Error,Al Valida Contrato");
						return error403();
					}
				} else {
					LOGGER.info("Error, Al Envio de Notificación");
					return error403();
				}
			} else if (envioNotificacion.get("banderaAcceso").toString().equalsIgnoreCase("")) {
				LOGGER.info("OK, banderaAcceso trae \"\"");
				respuestaError = new HashMap<String, Object>();
				long resStatus = 200;
				respuestaError.put("mostrarContrato", true);
				respuestaError.put("responseStatus", resStatus);
				respuestaError.put("responseError", "");

				return respuestaError;
			} else {
				LOGGER.info("OK, banderaAcceso otro dato diferente");
				respuestaError = new HashMap<String, Object>();
				long resStatus = 200;
				respuestaError.put("responseStatus", resStatus);
				respuestaError.put("responseError", "");

				return respuestaError;
			}
		} catch (Exception e) {
			LOGGER.info("Exception, "+e.getMessage());
			return error403();
		}
	}

	/**
	 * 
	 * @return Regresa un JSON que nos trae el error 403 Description Metodo
	 *         donde manejamos el error 403. Este error se ejecuta en caso de
	 *         que se produsca un error al consumir uno de los servicios.
	 */
	public Map<String, Object> error403() {
		long resStatus = 403;
		Map<String, Object> jsonError = new HashMap<String, Object>();
		jsonError.put("responseStatus", resStatus);
		jsonError.put("responseError", "Por Definir");
		return jsonError;
	}

}