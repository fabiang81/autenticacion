package mx.com.beo.util;

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


public enum Urls {
	
	 /**
	  * Enum que nos arma la url del servicio notificaciones
	  * 
	  * VARIABLES DE AMBIENTE.
	  * - PROTOCOLO
	  * - HOSTNAME
	  * - PUERTO
	  * - BASEPATH
	  * 
	  */

//    CONTRASENA(System.getenv(Constantes.PROTOCOLO) 
//			  +"://"  + System.getenv(Constantes.HOSTNAME_BEO) + ":" 
//			  + System.getenv(Constantes.BASEPATH_AUTENTICACION) + "autenticacion"
//			  + "/cambioContrasena"),
//	
//	URL_ENVIO_NOTIFICACIONES(System.getenv(Constantes.PROTOCOLO) +"://"
//	          + System.getenv(Constantes.HOSTNAME_BEO) + ":" 
//			  + System.getenv(Constantes.PUERTO)
//			  + System.getenv(Constantes.BASEPATH)
//			  + "/envioNotificaciones"),
//
//	SER_CONSULTA_DATOS_BASICOS(System.getenv(Constantes.PROTOCOLO) + "://"
//	          + System.getenv(Constantes.HOSTNAME_BEO) + ":" + System.getenv(Constantes.PUERTO)
//	          + System.getenv(Constantes.BASEPATH)+"/consultaDatosBasicos"),
//	
//	URL_SERVICIOS_CONTRATADOS(System.getenv(Constantes.PROTOCOLO) +"://"
//	          + System.getenv(Constantes.HOSTNAME_BEO)+""+":"+System.getenv(Constantes.PUERTO)
//	          + System.getenv(Constantes.BASEPATH) + "/consultaServiciosContratados"),
//	
//	URL_PERFIL(System.getenv(Constantes.PROTOCOLO) + "://"
//	          + System.getenv(Constantes.HOSTNAME_BEO) + ":" 
//			  + System.getenv(Constantes.PUERTO) + "/facultamiento"
//			  + "/consultaPerfilesPerf"),
//	
//	URL_MODIFICA_CONTRATO(System.getenv(Constantes.URL_MODIFICA_CONTRATO) != null ? System.getenv(Constantes.URL_MODIFICA_CONTRATO):""),
//	
//	URL_BITACORA((System.getenv(Constantes.BITACORA_URL)!=null?System.getenv(Constantes.BITACORA_URL):"")
//	            +"/bitacoraOperaciones")
//	;

    CONTRASENA("http://localhost:9094" + "/cambioContrasena"),
	
	URL_ENVIO_NOTIFICACIONES("http://localhost:9094" + "/envioNotificaciones"),

	SER_CONSULTA_DATOS_BASICOS("http://localhost:9094" + "/consultaDatosBasicos"),
	
	URL_SERVICIOS_CONTRATADOS("http://localhost:9094" + "/consultaServiciosContratados"),
	
	URL_PERFIL("http://localhost:9094" + "/consultaPerfilesPerf"),
	
	URL_MODIFICA_CONTRATO(System.getenv(Constantes.URL_MODIFICA_CONTRATO) != null ? System.getenv(Constantes.URL_MODIFICA_CONTRATO):""),
	
	URL_BITACORA("http://localhost:9094" + "/bitacoraOperaciones")
	;

	
	private String path;
	 
	private Urls(String path){
		this.path=path; 
	}
	
	public String getPath() {
		return path;
	}
}

