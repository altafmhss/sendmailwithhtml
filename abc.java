package sendmail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class abc {
   public static void main(String[] args) {
	   
	   dataVO vo=new dataVO("Gold", "Silver", "Copper", "Aluminium", "Iron", "Bronze");
	   
	   
      // Recipient's email ID needs to be mentioned.
      String to = "";

      // Sender's email ID needs to be mentioned
      String from = "";
      final String username = "";//change accordingly
      final String password = "";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.mail.yahoo.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
            }
	});

      try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

   	   // Set From: header field of the header.
	   message.setFrom(new InternetAddress(from));

	   // Set To: header field of the header.
	   message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(to));

	   // Set Subject: header field
	   message.setSubject("Testing Subject");
	   
	   String html1="<html><head>"
               + "<title>"+message.getSubject()+"</title>"
               + "</head>"+"<LINK REL='stylesheet' HREF='stylesheet/fac_css.css' TYPE='text/css'>"
               + "<body>"
               +"<table width='900' cellpadding='0' cellspacing='0' border='0'>"
               +"<tr><td class ='text12' width='100%'><br>I ordered all the following items.<br>Please deliver all the same items on the given below address:</td></tr><tr>"
               +"<td height='5'></td></tr>"
               +"<tr><td></td></tr>"
               +"<tr><td height='5'></td></tr>"
               +"<tr><td><table border='1' width='800' cellpadding='2' cellspacing='1' bgColor='#B6AFA9' style='border-collapse: collapse' bordercolor='#EBDA2A' align='left'>"
               +"<tr bgColor=#CD919E class='centerheading' align='left'>"
                       +"<td width='30' style='color: #FFFFFF;'><b>Category</b></td>"
                       +"<td width='35' style='color: #FFFFFF;'><b>Subcategory</b></td>"
                       +"<td width='30' style='color: #FFFFFF;'><b>Quantity</b></td>"
                       +"<td width='30' style='color: #FFFFFF;'><b>Units</b></td>"
                       +"<td width='30' style='color: #FFFFFF;'><b>Rate</b></td>"
                       +"<td width='30' style='color: #FFFFFF;'><b>Total</b></td>"
              + "</tr>"
              +"<tr>"
                   +"<td width='30' style='color: #EEE9E9;'>"+vo.getCategory()+"</b></td>"
                       +"<td width='35' style='color: #EEE9E9;'>"+vo.getSubcategory()+"</td>"
                       +"<td width='30' style='color: #EEE9E9;'>"+vo.getQty()+"</td>"
                       +"<td width='30' style='color: #EEE9E9;'>"+vo.getUnit()+"</td>"
                       +"<td width='30' style='color: #EEE9E9;'>"+vo.getRate()+"</td>"
                       +"<td width='30' style='color: #EEE9E9;'>"+vo.getTotal()+"</td>"
              +"</tr>"
               +"</table>"
   +"</body></html>";
	   // Send the actual HTML message, as big as you like
	   message.setContent(html1,"text/html");

	   // Send message
	   Transport.send(message);

	   System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
	   e.printStackTrace();
	   throw new RuntimeException(e);
      }
   }
}