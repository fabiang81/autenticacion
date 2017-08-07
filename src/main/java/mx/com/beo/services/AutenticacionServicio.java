package mx.com.beo.services;

import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.http.RequestEntity; 

import mx.com.beo.models.CambioContrasenaBean;
import mx.com.beo.models.ConsultaDatoBasicoBean;
import mx.com.beo.models.ConsultaServicioContratadoBean;
import mx.com.beo.models.EnvioNotificacioneBean;
import mx.com.beo.models.PerfilBean;
import mx.com.beo.models.ValidaContratoBean;

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
public interface AutenticacionServicio {
	
	public JSONObject servicioCredenciales(CambioContrasenaBean bean);
	
	public JSONObject consultaDatosBasicos(ConsultaDatoBasicoBean basicoBean );
	
	
	public JSONObject consultaServiciosContratados(ConsultaServicioContratadoBean basicoBean );
	
	public JSONObject envioNotificaciones(EnvioNotificacioneBean envioNotificacioneBean );
	
	public JSONObject cambioBandera(ValidaContratoBean bean);
	
	public JSONObject consultaPerfiles(PerfilBean basicoBean );
	
	
	public JSONObject getValidaHeaders(RequestEntity<Object> requestEntity);
	
	
	public Map<String, Object> getContratoAceptado(RequestEntity<Object> requestEntity,
			EnvioNotificacioneBean beanEnvioNot, ConsultaDatoBasicoBean basicoBean,
			ConsultaServicioContratadoBean consultaServicioContratadoBean, PerfilBean perfil);
	 
	public Map<String,Object> banderaAcceso(RequestEntity<Object> requestEntity,
			EnvioNotificacioneBean beanEnvioNot,
			ConsultaDatoBasicoBean basicoBean,
			ConsultaServicioContratadoBean consultaServicioContratadoBean,
			PerfilBean perfil,
			ValidaContratoBean validaContrato);
	
	public JSONObject error403();
}
