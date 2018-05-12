package br.senai.sp.informatica.senaipatrimonio.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.MessagingException;

public class EmailUtils {

	public static Session getConfiguracoesDoEmail() {
		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.EnableSSL.enable", "true");
		props.put("mail.smtp.auth", "true");

		Session configuracao = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Constantes.EMAIL, Constantes.SENHA_DO_EMAIL);
			}

		});

		return configuracao;
	}

	public static void enviarMensagem(String titulo, String corpo, String destinatario)
			throws AddressException, MessagingException {
		Message msg = new MimeMessage(getConfiguracoesDoEmail());

		msg.setFrom(new InternetAddress(Constantes.EMAIL));
		msg.addRecipient(RecipientType.TO, new InternetAddress(destinatario));
		msg.setSubject(titulo);
		msg.setText(corpo);

		Transport.send(msg);
	}

}
