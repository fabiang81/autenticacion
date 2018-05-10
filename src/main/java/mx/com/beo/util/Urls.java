package mx.com.beo.util;

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

    CONTRASENA(System.getenv(Constantes.PROTOCOLO)      +"://"+System.getenv(Constantes.HOSTNAME_BEO)+((System.getenv(Constantes.PUERTO)!=null && !System.getenv(Constantes.PUERTO).equals(""))?":"+System.getenv(Constantes.PUERTO):"")+(System.getenv(Constantes.BASEPATH_AUTENTICACION)!=null?System.getenv(Constantes.BASEPATH_AUTENTICACION):"")+ "/cambioContrasena"), 
    
	URL_ENVIO_NOTIFICACIONES(System.getenv(Constantes.PROTOCOLO)      +"://"+System.getenv(Constantes.HOSTNAME_BEO)+((System.getenv(Constantes.PUERTO)!=null && !System.getenv(Constantes.PUERTO).equals(""))?":"+System.getenv(Constantes.PUERTO):"")+(System.getenv(Constantes.BASEPATH_PERSONA)!=null?System.getenv(Constantes.BASEPATH_PERSONA):"")+ "/envioNotificaciones"),

	SER_CONSULTA_DATOS_BASICOS(System.getenv(Constantes.PROTOCOLO)      +"://"+System.getenv(Constantes.HOSTNAME_BEO)+((System.getenv(Constantes.PUERTO)!=null && !System.getenv(Constantes.PUERTO).equals(""))?":"+System.getenv(Constantes.PUERTO):"")+(System.getenv(Constantes.BASEPATH_PERSONA)!=null?System.getenv(Constantes.BASEPATH_PERSONA):"")+"/consultaDatosBasicos"),
	
	URL_SERVICIOS_CONTRATADOS(System.getenv(Constantes.PROTOCOLO)      +"://"+System.getenv(Constantes.HOSTNAME_BEO)+((System.getenv(Constantes.PUERTO)!=null && !System.getenv(Constantes.PUERTO).equals(""))?":"+System.getenv(Constantes.PUERTO):"")+(System.getenv(Constantes.BASEPATH_PERSONA)!=null?System.getenv(Constantes.BASEPATH_PERSONA):"")+ "/consultaServiciosContratados"),
	
	URL_PERFIL(System.getenv(Constantes.PROTOCOLO)      +"://"+System.getenv(Constantes.HOSTNAME_BEO)+((System.getenv(Constantes.PUERTO)!=null && !System.getenv(Constantes.PUERTO).equals(""))?":"+System.getenv(Constantes.PUERTO):"")+(System.getenv(Constantes.BASEPATH_FACULTAMIENTO)!=null?System.getenv(Constantes.BASEPATH_FACULTAMIENTO):"")+ "/consultaPerfiles"),
	
	URL_MODIFICA_CONTRATO(System.getenv(Constantes.URL_MODIFICA_CONTRATO)),

	
	URL_BITACORA((System.getenv(Constantes.BITACORA_URL)!=null?System.getenv(Constantes.BITACORA_URL):"")
	            +"/bitacoraOperaciones");
	
	private String path;
	 
	private Urls(String path){
		this.path=path; 
	}
	
	public String getPath() {
		return path;
	}
}

