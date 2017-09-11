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

	ESB_ENVIO(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+((System.getenv("PUERTO")!=null && !System.getenv("PUERTO").equals(""))?":"+System.getenv("PUERTO"):"")+(System.getenv("BASEPATH")!=null?System.getenv("BASEPATH"):"")+"/envioESB"),
	Contrasena(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+":"+System.getenv("PUERTO")+System.getenv("BASEPATH")+"/cambioContrasena"),
	SERENVIONOT(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+":"+System.getenv("PUERTO")+System.getenv("BASEPATH")+"/envioNotificaciones"),

	serConsultaDatosBasicos(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+":"+System.getenv("PUERTO")+System.getenv("BASEPATH")+"/consultaDatosBasicos"),
	
	urlServiciosContratados(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+""+":"+System.getenv("PUERTO")+System.getenv("BASEPATH")+"/consultaServiciosContratados"),
	
	urlPerfil(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+":"+System.getenv("PUERTO")+System.getenv("BASEPATH")+"/consultaPerfiles"),
	
	urlValidaContrato(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+":"+System.getenv("PUERTO")+System.getenv("BASEPATH")+"/validaContrato"),
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

