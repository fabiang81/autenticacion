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
	  */

	//ESB_ENVIO(System.getenv(Constantes.PROTOCOLO) +"://"+System.getenv(Constantes.HOSTNAME_BEO)+((System.getenv(Constantes.PUERTO)!=null && !System.getenv(Constantes.PUERTO).equals(""))?":"+System.getenv(Constantes.PUERTO):"")+(System.getenv(Constantes.BASEPATH)!=null?System.getenv(Constantes.BASEPATH):"")+"/envioESB"),
	Contrasena(System.getenv(Constantes.PROTOCOLO) +"://"+System.getenv(Constantes.HOSTNAME_BEO)+":"+System.getenv(Constantes.PUERTO)+System.getenv(Constantes.BASEPATH)+"/cambioContrasena"),
	urlEnvioNotificaciones(System.getenv(Constantes.PROTOCOLO) +"://"+System.getenv(Constantes.HOSTNAME_BEO)+":"+System.getenv(Constantes.PUERTO)+System.getenv(Constantes.BASEPATH)+"/envioNotificaciones"),

	serConsultaDatosBasicos(System.getenv(Constantes.PROTOCOLO) +"://"+System.getenv(Constantes.HOSTNAME_BEO)+":"+System.getenv(Constantes.PUERTO)+System.getenv(Constantes.BASEPATH)+"/consultaDatosBasicos"),
	
	urlServiciosContratados(System.getenv(Constantes.PROTOCOLO) +"://"+System.getenv(Constantes.HOSTNAME_BEO)+""+":"+System.getenv(Constantes.PUERTO)+System.getenv(Constantes.BASEPATH)+"/consultaServiciosContratados"),
	
	urlPerfil(System.getenv(Constantes.PROTOCOLO) +"://"+System.getenv(Constantes.HOSTNAME_BEO)+":"+System.getenv(Constantes.PUERTO)+System.getenv(Constantes.BASEPATH)+"/consultaPerfiles"),
	
	//urlValidaContrato(System.getenv(Constantes.PROTOCOLO) +"://"+System.getenv(Constantes.HOSTNAME_BEO)+":"+System.getenv(Constantes.PUERTO)+System.getenv(Constantes.BASEPATH)+"/validaContrato"),
	urlModificaContrato(System.getenv("URL_MODIFICA_CONTRATO")!=null?System.getenv("URL_MODIFICA_CONTRATO"):""),
	URL_BITACORA(System.getenv(Constantes.BITACORA_URL)!=null?System.getenv(Constantes.BITACORA_URL):""+"/bitacoraOperaciones")
	
	;


	private String path;
	 
	private Urls(String path){
		this.path=path; 
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

