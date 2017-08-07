package mx.com.beo.util;


 public enum Urls {
	 
	Contrasena(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+":"+System.getenv("PUERTO")+System.getenv("BASEPATH")+"/cambioContrasena"),
	
	serEnvioNot(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+":"+System.getenv("PUERTO")+System.getenv("BASEPATH")+"/envioNotificaciones"),

	serConsultaDatosBasicos(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+":"+System.getenv("PUERTO")+System.getenv("BASEPATH")+"/consultaDatosBasicos"),
	
	urlServiciosContratados(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+""+":"+System.getenv("PUERTO")+System.getenv("BASEPATH")+"/consultaServiciosContratados"),
	
	urlPerfil(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+":"+System.getenv("PUERTO")+System.getenv("BASEPATH")+"/consultaPerfiles"),
	
	urlValidaContrato(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+":"+System.getenv("PUERTO")+System.getenv("BASEPATH")+"/Validacontrato"),
//	
//	;
//	 Contrasena(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+"/"+System.getenv("BASEPATH")+"/cambioContrasena"),
//	
//	serEnvioNot(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+"/"+System.getenv("BASEPATH")+"/envioNotificaciones"),
//
//	serConsultaDatosBasicos(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+"/"+System.getenv("BASEPATH")+"/consultaDatosBasicos"),
//	
//	urlServiciosContratados(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+"/"+System.getenv("BASEPATH")+"/consultaServiciosContratados"),
//	
//	urlPerfil(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+"/"+System.getenv("BASEPATH")+"/consultaPerfiles"),
//	
//	urlValidaContrato(System.getenv("PROTOCOLO") +"://"+System.getenv("HOSTNAME")+"/"+System.getenv("BASEPATH")+"/Validacontrato")
	
	;
	
	
	
	private String value;
	 
	private Urls(String value){
		this.value=value; 
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	
	
	  

}
 