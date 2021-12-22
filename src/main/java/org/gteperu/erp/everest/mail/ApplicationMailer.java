/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gteperu.erp.everest.mail;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.gteperu.erp.everest.utils.ConstantesMail;
import org.gteperu.erp.everest.domain._Clientes;
import org.gteperu.erp.everest.domain._Company;
import org.gteperu.erp.everest.domain._DocumentoCpe;
import org.gteperu.erp.everest.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailService")
public class ApplicationMailer {

	private JavaMailSender mailSender;
	private static final long serialVersionUID = 7363373287232431632L;

	
	public void ApplicationMailer(JavaMailSender mailSender) {

		this.mailSender = mailSender;
	}

	@SuppressWarnings("unused")
	public void enviarMailRegistro(String emailResponsable, String mensaje,String mail,String password,String emaconta) {

		MailConfig mc=new MailConfig();
		ParametrosMail p=new ParametrosMail();
		p.setMailPassword(password);
		p.setMailUsername(mail);
		mailSender=mc.javaMailService(p);
		MimeMessage message = mailSender.createMimeMessage();
		try {

			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED,
					"UTF-8");
			helper.setSubject("Confirmación de registro en Focus Rp");
			helper.setTo(emailResponsable);
			String html = "<table border='0' cellspacing='0' cellpadding='0' style='max-width:600px'><tbody><tr><td width='709' colspan='2'><table width='100%' border='0'cellspacing='0' cellpadding='0'><tbody><tr><td width='57%' align='left'>&nbsp;</td>              <td width='43%' align='right'>&nbsp;</td></tr></tbody></table></td></tr><tr height='16'></tr><tr><td colspan='2'><table bgcolor='#00639A' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width:332px;max-width:600px;border:1px solid #e0e0e0;border-bottom:0;border-top-left-radius:3px;border-top-right-radius:3px'><tbody><tr><td height='72px' colspan='3'></td></tr><tr><td width='32px'></td>"
					+ "<td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:24px;color:#ffffff;line-height:1.25'>"
					+ "Por favor, active su cuenta haciendo clic en el enlace que aparece en la parte inferior.</td>"
					+ "<td width='32px'></td></tr><tr><td height='18px' colspan='3'></td></tr></tbody></table></td></tr><tr><td colspan='2'><table bgcolor='#FAFAFA' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width:332px;max-width:600px;border:1px olid #f0f0f0;border-bottom:1px solid #c0c0c0;border-top:0;border-bottom-left-radius:3px;border-bottom-right-radius:3px'><tbody><tr height='16px'><td width='30' rowspan='3'></td><td width='832'></td><td width='1' rowspan='3'></td></tr><tr><td><table style='min-width:300px' border='0' cellspacing='0' cellpadding='0'><tbody><tr><td width='834' style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'>Hola, "
					+ emailResponsable + ":</td></tr><tr>"
					+ "  <td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'><p>Para confirmar la activación de su cuenta, haga click en el siguiente Vínculo. <br>"
					+ "    <a href='" + mensaje + "' style='text-decoration:none;color:#4285f4' target='_blank'>"
					+ mensaje + "</a>.</p>" + "    <p>Gracias por registrarse en Focus Rp<br>"
					+ "<br>"
					+ "Usuario:" + emailResponsable + "" + "" + "<br>" + "<br>"
					+ "Para mayor información puede escribirnos al correo:<br>"+emaconta
					+ "      <br>"
					+ "    </p></td></tr><tr height='32px'></tr><tr><td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'>Atentamente,<br>    El equipo de Focus Rp.</td></tr><tr height='16px'></tr><tr>      <td align='center' style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:12px;color:#b9b9b9;line-height:1.5'>Este correo electrónico no admite respuestas. Para hacernos llegar tus comentarios sobre esta alerta, <a href='https://support.google.com/accounts/contact/device_alert_feedback?hl=es' style='text-decoration:none;color:#4285f4' target='_blank'>haz clic aquí</a>.<br>        Para obtener más información, visita el <a href='https://support.google.com/accounts/answer/2733203' style='text-decoration:none;color:#4285f4' target='_blank'>Centro de ayuda de Cuentas de Everes ERP</a>.</td></tr></tbody></table></td></tr><tr height='32px'></tr></tbody></table></td></tr><tr height='16'></tr><tr>"
					+ "        <td align='center' style='max-width:600px;font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:10px;color:#bcbcbc;line-height:1.5'>Te hemos enviado este correo electrónico de anuncio de servicio obligatorio para informarte sobre una serie de cambios importantes que afectan a tu cuenta o producto de Focus Rp.<br>"
					+ "        <div style='direction:ltr;text-align:center'>© 2016 Focus Rp Inc., Lima, Perú.</div></td>"
					+ "        <td align='center' style='max-width:600px;font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:10px;color:#bcbcbc;line-height:1.5'>&nbsp;</td>"
					+ "</tr>" + "</tbody>" + "</table>";

			helper.setText(html, true);
			// FacesContext facesContext = FacesContext.getCurrentInstance();
			// ExternalContext externalContext =
			// facesContext.getExternalContext();
			// FileSystemResource res = new FileSystemResource(new
			// File(((ServletContext)
			// externalContext.getContext()).getRealPath("/img/notification-exclamation.gif")));
			// helper.addInline("logo", res);
			mailSender.send(message);
		} catch (MessagingException m) {
			m.printStackTrace();
		} catch (Exception e) {
			System.out.println("erororor" + e.getMessage());
			// log.error("UtilitarioServiceImpl Exception" +
			// Constantes.errorMetodo + e.getMessage());
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void enviarMailAprovarOrdenCompra(String emailResponsable, String mensaje, String cabe,String mail,String password) {

		MailConfig mc=new MailConfig();
		ParametrosMail p=new ParametrosMail();
		p.setMailPassword(password);
		p.setMailUsername(mail);
		mailSender=mc.javaMailService(p);
		MimeMessage message = mailSender.createMimeMessage();
		try {

			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED,
					"UTF-8");
			helper.setSubject("Aprobar ordenes de compra");
			helper.setTo(emailResponsable);
			String html = "<table border='0' cellspacing='0' cellpadding='0' style='max-width:80%'><tbody><tr><td width='80%' colspan='2'><table width='100%' border='0'cellspacing='0' cellpadding='0'><tbody><tr><td width='80%' align='left'>&nbsp;</td>              "
					+ "<td width='80%' align='right'>&nbsp;</td></tr></tbody></table></td></tr><tr height='16'></tr><tr><td colspan='2'><table bgcolor='#00639A' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width:332px;max-width:600px;border:1px solid #e0e0e0;border-bottom:0;border-top-left-radius:3px;border-top-right-radius:3px'><tbody><tr><td height='72px' colspan='3'></td></tr><tr><td width='32px'></td>"
					+ "<td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:12px;color:#ffffff;line-height:1.25'>"
					+ cabe + "</td>"
					+ "<td width='32px'></td></tr><tr><td height='18px' colspan='3'></td></tr></tbody></table></td></tr><tr><td colspan='2'><table bgcolor='#FAFAFA' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width:332px;max-width:600px;border:1px olid #f0f0f0;border-bottom:1px solid #c0c0c0;border-top:0;border-bottom-left-radius:3px;border-bottom-right-radius:3px'><tbody><tr height='16px'><td width='30' rowspan='3'></td><td width='832'></td><td width='1' rowspan='3'></td></tr><tr><td><table style='min-width:300px' border='0' cellspacing='0' cellpadding='0'><tbody><tr><td width='834' style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'>Hola, "
					+ emailResponsable + ":</td></tr><tr>"
					+ "  <td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'><p>"
					+ " " + mensaje + "</p>" + "      " + "      <br>"
					+ "    </p></td></tr><tr height='32px'></tr><tr><td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'>Atentamente,<br>    El equipo de Focus Rp.</td></tr><tr height='16px'></tr><tr>      <td align='center' style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:12px;color:#b9b9b9;line-height:1.5'>Este correo electrónico no admite respuestas. Para hacernos llegar tus comentarios sobre esta alerta, <a href='http://www.Focus Rp.com' style='text-decoration:none;color:#4285f4' target='_blank'>haz clic aquí</a>.<br>        Para obtener más información, visita el <a href='http://www.Focus Rp.com' style='text-decoration:none;color:#4285f4' target='_blank'>Centro de ayuda de Cuentas de Everes ERP</a>.</td></tr></tbody></table></td></tr><tr height='32px'></tr></tbody></table></td></tr><tr height='16'></tr><tr>"
					+ "        <td align='center' style='max-width:600px;font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:10px;color:#bcbcbc;line-height:1.5'>Te hemos enviado este correo electrónico de anuncio de servicio obligatorio para informarte sobre una serie de cambios importantes que afectan a tu cuenta o producto de Focus Rp.<br>"
					+ "        <div style='direction:ltr;text-align:center'>© 2016 Focus Rp Inc., Lima, Perú.</div></td>"
					+ "        <td align='center' style='max-width:600px;font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:10px;color:#bcbcbc;line-height:1.5'>&nbsp;</td>"
					+ "</tr>" + "</tbody>" + "</table>";

			helper.setText(html, true);
			// FacesContext facesContext = FacesContext.getCurrentInstance();
			// ExternalContext externalContext =
			// facesContext.getExternalContext();
			// FileSystemResource res = new FileSystemResource(new
			// File(((ServletContext)
			// externalContext.getContext()).getRealPath("/img/notification-exclamation.gif")));
			// helper.addInline("logo", res);
			mailSender.send(message);
		} catch (MessagingException m) {
			m.printStackTrace();
		} catch (Exception e) {
			System.out.println("erororor" + e.getMessage());
			// log.error("UtilitarioServiceImpl Exception" +
			// Constantes.errorMetodo + e.getMessage());
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	public void enviarMailRecuperaPassword(String emailResponsable, String mensaje,String mail,String password) {


		MailConfig mc=new MailConfig();
		ParametrosMail p=new ParametrosMail();
		p.setMailPassword(password);
		p.setMailUsername(mail);
		mailSender=mc.javaMailService(p);
		MimeMessage message = mailSender.createMimeMessage();
		try {

			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED,
					"UTF-8");
			helper.setSubject("Solicitud de cambio de  Contraseña");
			helper.setTo(emailResponsable);
			String html = "<table border='0' cellspacing='0' cellpadding='0' style='max-width:600px'><tbody><tr><td width='709' colspan='2'><table width='100%' border='0'cellspacing='0' cellpadding='0'><tbody><tr><td width='57%' align='left'>&nbsp;</td>              <td width='43%' align='right'>&nbsp;</td></tr></tbody></table></td></tr><tr height='16'></tr><tr><td colspan='2'><table bgcolor='#00639A' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width:332px;max-width:600px;border:1px solid #e0e0e0;border-bottom:0;border-top-left-radius:3px;border-top-right-radius:3px'><tbody><tr><td height='72px' colspan='3'></td></tr><tr><td width='32px'></td>"
					+ "<td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:24px;color:#ffffff;line-height:1.25'>Restablecer Contraseña de usuario Focus Rp</td>"
					+ "<td width='32px'></td></tr><tr><td height='18px' colspan='3'></td></tr></tbody></table></td></tr><tr><td colspan='2'><table bgcolor='#FAFAFA' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width:332px;max-width:600px;border:1px olid #f0f0f0;border-bottom:1px solid #c0c0c0;border-top:0;border-bottom-left-radius:3px;border-bottom-right-radius:3px'><tbody><tr height='16px'><td width='30' rowspan='3'></td><td width='832'></td><td width='1' rowspan='3'></td></tr><tr><td><table style='min-width:300px' border='0' cellspacing='0' cellpadding='0'><tbody><tr><td width='834' style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'>Hola, "
					+ emailResponsable + ":</td></tr><tr>"
					+ "  <td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'><p>Por favor, para continuar con el restablecimiento de su Contraseña, ingrese al siguiente enlace:<br>"
					+ "    <a href='" + mensaje + "' style='text-decoration:none;color:#4285f4' target='_blank'>"
					+ mensaje + "</a>.</p>"
					+ "    <p>Si el enlace anterior no funciona, cópielo y péguelo en la dirección de su navegador. Este enlace tiene una vigencia de 1 día.<br />"
					+ "Si Ud. ha recibido este mensaje por error por favor haga caso omiso de este correo.<br />"
					+ "      " + "      <br>"
					+ "    </p></td></tr><tr height='32px'></tr><tr><td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'>Atentamente,<br>    El equipo de Focus Rp.</td></tr><tr height='16px'></tr><tr>      <td align='center' style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:12px;color:#b9b9b9;line-height:1.5'>Este correo electrónico no admite respuestas. Para hacernos llegar tus comentarios sobre esta alerta, <a href='http://www.Focus Rp.com' style='text-decoration:none;color:#4285f4' target='_blank'>haz clic aquí</a>.<br>        Para obtener más información, visita el <a href='http://www.Focus Rp.com' style='text-decoration:none;color:#4285f4' target='_blank'>Centro de ayuda de Cuentas de Everes ERP</a>.</td></tr></tbody></table></td></tr><tr height='32px'></tr></tbody></table></td></tr><tr height='16'></tr><tr>"
					+ "        <td align='center' style='max-width:600px;font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:10px;color:#bcbcbc;line-height:1.5'>Te hemos enviado este correo electrónico de anuncio de servicio obligatorio para informarte sobre una serie de cambios importantes que afectan a tu cuenta o producto de Focus Rp.<br>"
					+ "        <div style='direction:ltr;text-align:center'>© 2016 Focus Rp Inc., Lima, Perú.</div></td>"
					+ "        <td align='center' style='max-width:600px;font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:10px;color:#bcbcbc;line-height:1.5'>&nbsp;</td>"
					+ "</tr>" + "</tbody>" + "</table>";

			helper.setText(html, true);
			// FacesContext facesContext = FacesContext.getCurrentInstance();
			// ExternalContext externalContext =
			// facesContext.getExternalContext();
			// FileSystemResource res = new FileSystemResource(new
			// File(((ServletContext)
			// externalContext.getContext()).getRealPath("/img/notification-exclamation.gif")));
			// helper.addInline("logo", res);
			mailSender.send(message);
		} catch (MessagingException m) {
			m.printStackTrace();
		} catch (Exception e) {
			System.out.println("erororor" + e.getMessage());
			// log.error("UtilitarioServiceImpl Exception" +
			// Constantes.errorMetodo + e.getMessage());
			e.printStackTrace();
		}
	}

	
	
	@SuppressWarnings("unused")
	public int enviarMailCPE(String emailResponsable, String subject, File file, File file_docxml, File file_cdrxml,String mail,String password,_DocumentoCpe documento,_Company emp,_Clientes cliente) {
		int f = 0;
		MailConfig mc=new MailConfig();
		ParametrosMail p=new ParametrosMail();
		p.setMailPassword(password);
		p.setMailUsername(mail);
		
		mailSender=mc.javaMailService(p);
		MimeMessage message = mailSender.createMimeMessage();
		String nombre_app=Constantes.msgNombreApp;
		String mensaje=Constantes.msgNombreEmpresa;
		String nombre_estimado = cliente.getRazon_social();
		String fech_emision = documento.getFecha_registro();
		String fech_venc = documento.getFecha_vto().toString();
		String total="";
		String tcambio="0.00";
		String totalCambio="";
		if(documento.getCod_moneda().equals("PEN")){
			total = "S/. "+String.valueOf(documento.getTotal());
			tcambio = String.valueOf(documento.getTipo_cambio());
			totalCambio = "US$ "+String.valueOf((double)Math.round((documento.getTotal()/documento.getTipo_cambio()) * 100d) / 100d);
			
		}else{
			total = "US$ "+String.valueOf(documento.getTotal());
			tcambio = String.valueOf(documento.getTipo_cambio());
			totalCambio = "S/. "+String.valueOf((double)Math.round((documento.getTotal()*documento.getTipo_cambio()) * 100d) / 100d);
		}
		String obs = documento.getObservacion();
		String atte = emp.getRazon_social_empresa();
		if(obs==null){
			obs="Ninguna";
		}
		String ruc = emp.getNro_documento_empresa();
		
		mensaje="<p>"
					+ "<span>Estimados:&#160;" + nombre_estimado 
						+ "<p>"
							+ "<span>"
							+ "Le enviamos el resumen del documento generado"
							+ "</span>"
							+ "<br/>"
						+ "</p>"
						+ "<ul>"
//							+ "<li style='text-transform:uppercase;font-weight:bold'>" + subject + "</li>"
							+ "<li>Fecha de emisión : <label style=' font-weight:bold'>"+ fech_emision +"</label></li>"
							+ "<li>Fecha de vencimiento : <label style=' font-weight:bold'>"+ fech_venc +"</label></li>"
							+ "<li>Total : <label style=' font-weight:bold'>"+ total +"</label></li>"
						//	+ "<li>Tipo de cambio : <label style=' font-weight:bold'>"+ tcambio +"</label></li>"
						//	+ "<li>Monto transferido al cambio: <label style=' font-weight:bold'>"+ totalCambio +"</label></li>"
							+ "<li>Observaciones : <label style=' font-weight:bold'>"+ obs +"</label></li>"
						+ "</ul>"
					+ "</span>"
				+ "</p>";
						//+ "<p>"
						//+ "<font face=\"inherit\">"
						//+ "Adjunto los archivos mencionados.";
		
		try {

			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED,
					"UTF-8");
			helper.setSubject(subject);
			helper.setTo(emailResponsable);
			helper.setCc(emailResponsable);

			StringBuilder cont = new StringBuilder();
			cont.append(
					"<table border='0' cellspacing='0' cellpadding='0' style='max-width:500px'>"
							+ "<tbody>"
								+ "<tr>"
									+ "<td width='709' colspan='2'>"
										+ "<table width='100%' border='0' cellspacing='0' cellpadding='0'>"
											+ "<tbody>"
												+ "<tr>"
													+ "<td width='57%' align='left'>&nbsp;</td>              "
													+ "<td width='43%' align='right'>&nbsp;</td>"
												+ "</tr>"
											+ "</tbody>"
										+ "</table>"
									+ "</td>"
								+ "</tr>"
								+ "<tr height='16'> </tr>"
								+ "<tr style=''>"
									+ "<td colspan='2'>"
										+ "<table width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width:332px;max-width:500px;border-bottom:0;border-top-left-radius:3px;border-top-right-radius:3px'>"
											+ "<tbody>"
												+ "<tr>"
													+ "<td height='72px' colspan='3'></td>"
												+ "</tr>"
												+ "<tr>"
													+ "<td width='32px'></td>\n"
													+ "<td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:24px;color:#84e1be;line-height:1.25;text-transform:uppercase;font-weight:bold'>"
														+ "\n"
														+ "" + subject + "\n"
													+ "</td>"
														+ "\n"
													+ "<td width='32px'></td>"
												+ "</tr>"
												+ "<tr>"
													+ "<td height='18px' colspan='3'>"
														+ "<hr style='#CCCCCC' size='1'>"
													+ "</td>"
												+ "</tr>"
											+ "</tbody>"
										+ "</table>"
									+ "</td>"
								+ "</tr>"
								+ "<tr>"
									+ "<td colspan='2'>"
										+ "<table width='100%' border='0' cellspacing='0' cellpadding='0' style='background: rgb(255,255,255);background: linear-gradient(0deg,rgba(82, 228, 174, 0) 0%,rgb(186, 251, 253) 30%,rgb(255,255,255) 90%);min-width:332px;max-width:500px;border:1px olid #f0f0f0;border-bottom:1px solid #c0c0c0;border-top:0;border-bottom-left-radius:3px;border-bottom-right-radius:3px'>"
											+ "<tbody>"
												+ "<tr height='16px'>"
													+ "<td width='30' rowspan='3'></td>"
													+ "<td width='832'></td>"
													+ "<td width='1' rowspan='3'></td>"
												+ "</tr>"
												+ "<tr>"
													+ "<td>"
														+ "<table style='min-width:300px' border='0' cellspacing='0' cellpadding='0'>"
															+ "<tbody>"
																+ "<tr>"
																	+ "\n"
																	+ "\n"
																	+ "\n"
																	+ "<td width='834' style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'>"
																		+ ""
																		+ ""
																	+ "</td>"
																+ "</tr>"
																+ "<tr>"
																	+ "\n"
																	+ "<td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'>"
																		+ mensaje 
																		+ "\n" 
																	+ "</td>"
																	+ "\n"
																+ "</tr>"
																	+ "\n"
																+ "<tr height='32px'></tr>"
																+ "<tr>"
																	+ "\n"
																	+ "<td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'>"
																		+ "Se adjunta en este mensaje el documento electrónico en formatos PDF y XML. La representación impresa en PDF tiene la misma validez que una emitida de manera tradicional."
																		+ "<br>"
																		+ "<br>"
																		+ "Atentamente :"
																		+ "<br>"
																		+ "<label style='text-transform:uppercase;font-weight:bold'>"+ atte +"</label>"
																		+ "<br>"
																		+ "<label style='text-transform:uppercase;font-weight:bold'>RUC "+ ruc +"</label>"
																	+ "</td>"
																+ "</tr>"
																+ "<tr height='16px'></tr>"
																+ "<tr>      "
																	+ "<td align='center' style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:12px;color:#b9b9b9;line-height:1.5'>"
																		
																	+ "</td>"
																+ "</tr>"
															+ "</tbody>"
														+ "</table>"
													+ "</td>"
												+ "</tr>"
								+ "<tr height='32px'></tr>"
							+ "</tbody>"
					+ "</table>"
					+ "</td>"
				+ "</tbody>"
			+ "\n" 
		+ "</table>");

			helper.setText(cont.toString(), true);
			
		/*	if (file.exists()) {
			    if (file.isFile()) System.out.println("Es un archivo");
			    if (file.isDirectory()) System.out.println("Es una carpeta");
	 		}*/
			
			if (file.exists()) {
			FileSystemResource ss = new FileSystemResource(file);
			helper.addAttachment(ss.getFilename(), file);
			}
		 	if (file_docxml.exists()) {
			FileSystemResource ss2 = new FileSystemResource(file_docxml);
			helper.addAttachment(ss2.getFilename(), file_docxml);
			}
			if (file_cdrxml.exists()) {
			FileSystemResource ss3 = new FileSystemResource(file_cdrxml);
			helper.addAttachment(ss3.getFilename() , file_cdrxml);
			}
		 
			mailSender.send(message);
		} catch (MessagingException m) {
			m.printStackTrace();
		} catch (Exception e) {
			System.out.println("enviarMailCPE " + e.getMessage());
		 	e.printStackTrace();
			if(e.getMessage().contains("Username and Password not accepted")) {
				return 5;
			}
			
		} finally {
			f = 1;
		}
		return f;
	}
	
	@SuppressWarnings("unused")
	public int enviarMailDocumentos(String emailResponsable, String subject, String mensaje, File file,String mail,String password) {
		int f = 0;
		MailConfig mc=new MailConfig();
		ParametrosMail p=new ParametrosMail();
		p.setMailPassword(password);
		p.setMailUsername(mail);
		mailSender=mc.javaMailService(p);
		MimeMessage message = mailSender.createMimeMessage();
		try {

			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_RELATED,
					"UTF-8");
			helper.setSubject(subject);
			helper.setTo(emailResponsable);
			helper.setCc(emailResponsable);

			StringBuilder cont = new StringBuilder();
			cont.append(
					"<table border='0' cellspacing='0' cellpadding='0' style='max-width:600px'><tbody><tr><td width='709' colspan='2'><table width='100%' border='0'  cellspacing='0' cellpadding='0'><tbody><tr><td width='57%' align='left'>&nbsp;</td>              <td width='43%' align='right'>&nbsp;</td></tr></tbody></table></td></tr><tr height='16'></tr><tr><td colspan='2'><table bgcolor='#00639A' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width:332px;max-width:600px;border:1px solid #e0e0e0;border-bottom:0;border-top-left-radius:3px;border-top-right-radius:3px'><tbody><tr><td height='72px' colspan='3'></td></tr><tr><td width='32px'></td>\n"
							+ "                                    <td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:24px;color:#ffffff;line-height:1.25'>\n"
							+ "                                        " + subject + "\n"
							+ "                                    </td>\n"
							+ "                                    <td width='32px'></td></tr><tr><td height='18px' colspan='3'></td></tr></tbody></table></td></tr><tr><td colspan='2'><table bgcolor='#FAFAFA' width='100%' border='0' cellspacing='0' cellpadding='0' style='min-width:332px;max-width:600px;border:1px olid #f0f0f0;border-bottom:1px solid #c0c0c0;border-top:0;border-bottom-left-radius:3px;border-bottom-right-radius:3px'><tbody><tr height='16px'><td width='30' rowspan='3'></td><td width='832'></td><td width='1' rowspan='3'></td></tr><tr><td><table style='min-width:300px' border='0' cellspacing='0' cellpadding='0'><tbody><tr>\n"
							+ "                                                                        \n"
							+ "                                                                        \n"
							+ "                                                     <td width='834' style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'>"
							+ ""
							+ ""
							+ "</td></tr><tr>\n"
							+ "                                                    <td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'>"
							+ mensaje + "\n" + "                                                    </td>\n"
							+ "                                                </tr>\n"
							+ "                                                <tr height='32px'></tr><tr>\n"
							+ "                                                    <td style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:13px;color:#202020;line-height:1.5'>Atentamente,<br>    El equipo de Focus Rp.</td></tr><tr height='16px'></tr><tr>      <td align='center' style='font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:12px;color:#b9b9b9;line-height:1.5'>Este correo electrónico no admite respuestas. Para hacernos llegar tus comentarios sobre esta alerta, <a href='https://support.google.com/accounts/contact/device_alert_feedback?hl=es' style='text-decoration:none;color:#4285f4' target='_blank'>haz clic aquí</a>.<br>        Para obtener más información, visita el <a href='https://support.google.com/accounts/answer/2733203' style='text-decoration:none;color:#4285f4' target='_blank'>Centro de ayuda de Cuentas de Focus Erp</a>.</td></tr></tbody></table></td></tr><tr height='32px'></tr></tbody></table></td></tr><tr height='16'></tr><tr>\n"
							+ "                    <td align='center' style='max-width:600px;font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:10px;color:#bcbcbc;line-height:1.5'>Esta es una notificación técnica generada automáticamente. Por favor, no responda. Ha recibido este correo electrónico porque ya sea en una versión gratuita de Focus Rp Cloud o ha adquirido una suscripción comercial/clave de licencia. Para mantenerse al día, le podemos enviar noticias importantes acerca de los productos y la información sobre nuestros productos o descuentos de vez en cuando (las preferencias para comunicación técnica pueden ser administradas por separado).<br>\n"
							+ "                        <div style='direction:ltr;text-align:center'>© 2015 Focus Rp Inc., Lima, Perú.</div>\n"
							+ "                    </td>\n"
							+ "                    <td align='center' style='max-width:600px;font-family:Roboto-Regular,Helvetica,Arial,sans-serif;font-size:10px;color:#bcbcbc;line-height:1.5'>&nbsp;</td>\n"
							+ "                </tr>\n" + "            </tbody>\n" + "        </table>");

			helper.setText(cont.toString(), true);
			FileSystemResource ss = new FileSystemResource(file);

			helper.addAttachment(ss.getFilename(), file);

			mailSender.send(message);
		} catch (MessagingException m) {
			m.printStackTrace();
		} catch (Exception e) {
			System.out.println("erororor" + e.getMessage());
			// log.error("UtilitarioServiceImpl Exception" +
			// Constantes.errorMetodo + e.getMessage());
			e.printStackTrace();
			if(e.getMessage().contains("Username and Password not accepted")) {
				return 5;
			}
			
		} finally {
			f = 1;
		}
		return f;
	}
}
