package mx.com.beo.api;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import mx.com.beo.models.CambioContrasenaBean;
import mx.com.beo.models.ConsultaDatoBasicoBean;
import mx.com.beo.models.ConsultaServicioContratadoBean;
import mx.com.beo.models.EnvioNotificacioneBean;
import mx.com.beo.models.MapParameterBean;
import mx.com.beo.models.PerfilBean;
import mx.com.beo.models.TicketBean;  
import mx.com.beo.models.ValidaContratoBean;
import mx.com.beo.services.AutenticacionServicio;
import mx.com.beo.util.Urls; 


/**
 * Copyright (c)  2017 Nova Solution Systems S.A. de C.V.
 * Mexico D.F.
 * Todos los derechos reservados.
 *
 * @author Reynaldo ivan 
 * 
 * Descripcion. Esta clase contiene los @RestController 
 * 
 *  - /cambioContrasena
 *  - /accesoCliente
 * ESTE SOFTWARE ES INFORMACIÓN CONFIDENCIAL. PROPIEDAD DE NOVA SOLUTION SYSTEMS.
 * ESTA INFORMACIÓN NO DEBE SER DIVULGADA Y PUEDE SOLAMENTE SER UTILIZADA DE ACUERDO CON LOS TÉRMINOS DETERMINADOS POR LA EMPRESA SÍ MISMA.
 */


@RestController          
public class AutenticationController {

	// Inyeccion de dependencias del servicio
	@Autowired
	private AutenticacionServicio nameService;
	
	private static final Logger LOGGER = Logger.getLogger( AutenticationController.class.getName() );

//	@Autowired
//	private Urls urls;

	


	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/prueba")
	public ResponseEntity<Object> prueba(RequestEntity<Object> requestEntity) {
		
		//Imprimo la cabecera para validar que realmente me este llegando
		 
		JSONObject jsonResponse=null;
	    jsonResponse=new JSONObject();
		long resStatus=403;
		jsonResponse.put("responseStatus",resStatus);
		jsonResponse.put("responseError","Por Definir");
		return new ResponseEntity<Object>(jsonResponse, HttpStatus.OK);
	}
	

	/**
	 * Servicio que realiza el cambio de contraseña
	 * @param RequestEntity<Object>: Este parametro nos trae el JSON del Body y los Headers
	 * @return ResponseEntity<Object>
	 * Descripción: Este metodo realiza el cambio de contraseña.
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cambioContrasena")
	public ResponseEntity<Object> cambioContrasena(RequestEntity<Object> requestEntity) {


		Map<String, Object> map = new HashMap<String, Object>();
		map = (Map<String, Object>) requestEntity.getBody();
		JSONObject jsonResponse=null;
		try{
			
			
			JSONObject varRestValidaHeaders=nameService.getValidaHeaders(requestEntity);
			String varRes=varRestValidaHeaders.get("respuestaStatus")+"";
			if(varRes.equals("200")){
				TicketBean ticketBean=new  TicketBean(requestEntity.getHeaders().getFirst("iv-user").toString(),
						requestEntity.getHeaders().getFirst("iv-creds").toString());
	    
				String serContrasena=Urls.Contrasena.getValue(); 
				
				System.out.println("cambio contraseña:  url "+serContrasena);
				
								CambioContrasenaBean bean =new CambioContrasenaBean(
										map.get("otp").toString(), 
										map.get("oldPassword").toString(),
										map.get("newPassword").toString(),
										map.get("confirmNewPassword").toString(),
										serContrasena,
										ticketBean, 
										requestEntity.getHeaders().getFirst("numero-cliente"), 
										requestEntity.getHeaders().getFirst("Tipocanal")); 

								if(map.get("newPassword").toString().equalsIgnoreCase(map.get("confirmNewPassword").toString())){

									JSONObject jsonObject= nameService.servicioCredenciales(bean); 
									LOGGER.info("Cambio Contraseña OK");
									return new ResponseEntity<Object>(jsonObject, HttpStatus.OK);

								}else{
									LOGGER.warning("Las contraseñas son diferentes");
									jsonResponse=new JSONObject();
									long resStatus=403;
									jsonResponse.put("responseStatus",resStatus);
									jsonResponse.put("responseError","Por Definir");
									return new ResponseEntity<Object>(jsonResponse, HttpStatus.OK);

								}
				
			}else{
				LOGGER.warning("Headers vacios");
				return new ResponseEntity<Object>(varRestValidaHeaders, HttpStatus.CREATED);
			}
			 
				 
		}catch (Exception e) {
			LOGGER.warning(""+e.getMessage());
			return new ResponseEntity<Object>(nameService.error403(), HttpStatus.OK);
		}

	}

	/**
	 * Descripción: Este metodo realiza las validaciones para el acceso del cliente. Si el contrato aun no esta aceptado nos
	 * Indica que presentemos el contrato en caso de que ya este aceptado nos presenta la información.
	 * @param requestEntity : trae todos los headers y el body.
	 * @return ResponseEntity<Object>
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/accesoCliente")
	public ResponseEntity<Object> accesoCliente(RequestEntity<Object> requestEntity){


		Map<String, Object> map = new HashMap<String, Object>(); 
		map = (Map<String, Object>) requestEntity.getBody();   

		try{

			JSONObject varRestValidaHeaders=nameService.getValidaHeaders(requestEntity);
			String varRes=varRestValidaHeaders.get("respuestaStatus")+"";
			if(varRes.equals("200")){

				//		TODO----------------Llenado de datos en--ENVIO DE NOTIFICACION-------------------------------------------------------------
				TicketBean ticketBean=new TicketBean(requestEntity.getHeaders().getFirst("iv-user"),requestEntity.getHeaders().getFirst("iv-creds"));

				MapParameterBean mapParameterBean=new MapParameterBean("1");

				
				String serEnvioNot=Urls.serEnvioNot.getValue();
				LOGGER.info(serEnvioNot);
				
				
				 
				EnvioNotificacioneBean beanEnvioNot=new EnvioNotificacioneBean(
						requestEntity.getHeaders().getFirst("numero-cliente"),
						ticketBean,
						requestEntity.getHeaders().getFirst("Tipocanal"),
						requestEntity.getHeaders().getFirst("tipo-authenticacion"),
						requestEntity.getHeaders().getFirst("mail"),
						//        TODO-------------que datos se van a pasar---------------------------------------------------------------------------								
						"r@i.com", mapParameterBean, "1",serEnvioNot);

				String serConsultaDatosBasicos=Urls.serConsultaDatosBasicos.getValue(); 
				LOGGER.info(serConsultaDatosBasicos);
				//--------------------------Llenar datos de ConsultaDatoBasicoBean-------------------- 
				ConsultaDatoBasicoBean  basicoBean=new ConsultaDatoBasicoBean(
						serConsultaDatosBasicos,
						requestEntity.getHeaders().getFirst("numero-cliente"),
						requestEntity.getHeaders().getFirst("iv-user"), 
						requestEntity.getHeaders().getFirst("iv-creds"), 
						requestEntity.getHeaders().getFirst("Tipocanal")
						);

				String urlServiciosContratados= Urls.urlServiciosContratados.getValue() ; 
				LOGGER.info(urlServiciosContratados);
				//			------------------------------Llenado de datos  ConsultaServicioContratadoBean-----------------------------------
				ConsultaServicioContratadoBean consultaServicioContratadoBean=new ConsultaServicioContratadoBean(
						requestEntity.getHeaders().getFirst("numero-cliente"), 
						requestEntity.getHeaders().getFirst("iv-user"),
						requestEntity.getHeaders().getFirst("iv-creds"), 
						requestEntity.getHeaders().getFirst("Tipocanal"), 
						urlServiciosContratados);


				String urlPerfil=Urls.urlPerfil.getValue(); 
				LOGGER.info(urlPerfil);
				//		---------------------Creación de Objeto perfiles----------------------------------- 
				PerfilBean perfil=new PerfilBean(
						urlPerfil,
						basicoBean.getIdUser(), 
						basicoBean.getIdCreds(), 
						basicoBean.getCanal(),
						"MULTIVANET",
						basicoBean.getIdPersona());


				String urlValidaContrato=Urls.urlValidaContrato.getValue(); 
				//					 -----------------------------Llenado de datos en-Valida contrato-----------------------------------------------------
				ValidaContratoBean validaContrato=new ValidaContratoBean(
						requestEntity.getHeaders().getFirst("numero-cliente"),
						map.get("banderaAcceso").toString(),
						urlValidaContrato);
 
				//			------------------------condición que nos valida si el contrato ya esta aceptado------------------------------------------

				if(requestEntity.getHeaders().getFirst("contratoAceptado").equalsIgnoreCase("1")){


					/**
					 * contratoAceptado trae 1.
					 * Eso quiere decur que el contrato ya esta aceptado
					 * El siguiente metodo consume los servicios que nos trarn la informacióin.
					 */  
					Map<String,Object> jsonResp=nameService.getContratoAceptado(requestEntity, beanEnvioNot, basicoBean,
							consultaServicioContratadoBean,perfil); 
  
					return new ResponseEntity<Object>(jsonResp, HttpStatus.CREATED);


				}else{
					LOGGER.info("contrato Aceptado no trae 1");
					Map<String,Object> jsonResp=nameService.banderaAcceso(requestEntity, beanEnvioNot, basicoBean,
							consultaServicioContratadoBean,perfil,validaContrato);  

					return new ResponseEntity<Object>(jsonResp, HttpStatus.CREATED);

				}

			}else{
				LOGGER.warning("Headers vacios");
				return new ResponseEntity<Object>(varRestValidaHeaders, HttpStatus.CREATED);
			}


		}catch (Exception e) {
			 
			LOGGER.warning(""+e.getMessage());
			return new ResponseEntity<Object>(nameService.error403(), HttpStatus.CREATED);
		}

	}


}
