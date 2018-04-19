package es.skills2018.api.resources;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import es.skills2018.api.enums.CustomMediaType;
import es.skills2018.api.request.ContactRequest;
import es.skills2018.api.response.ContactResponse;

@Path("/contact")
public class ContactResource {
	/**
	 * This EndPoint gets called to send a contact message
	 */
	@POST
	@Produces(CustomMediaType.APPLICATION_JSON)
	public Response contact(ContactRequest.Create request) {
		if (request == null || request.getEmail() == null || request.getMessage() == null || request.getName() == null || request.getSubject() == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Invalid params").build();
		}
		ContactResponse.Create response = new ContactResponse.Create(true);
		final String username = "skills2018davidf@gmail.com";
		final String password = "skills2018da";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("skills2018davidf@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("pollitoyeye@gmail.com"));
			message.setSubject("Contacto Web: " + request.getSubject());
			message.setText("Email de: " + request.getEmail() + " (" + request.getName() + ")"
				+ "\n\nCuerpo del mensaje: " + request.getMessage());
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return Response.status(Response.Status.OK).entity(response).header("Access-Control-Allow-Origin", "*").build();
	}
}