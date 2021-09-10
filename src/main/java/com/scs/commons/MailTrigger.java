//package com.scs.commons;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Properties;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
//
//import org.apache.commons.codec.binary.Base64;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.scs.driverfactory.BaseTest;
//import com.scs.exceptions.ConfigurationException;
//
///**
// * class for handling the mail functions for the framework.
// * @author MXC0RO7
// *         
// */
//public class MailTrigger {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(MailTrigger.class);
//
//    /**
//     * Function to decode the Base64 encrypted password from properties file.
//     * 
//     * @param cipherText : password value encrypted with Base64 encryption kept in
//     *                   properties file
//     * @return : plain text which is the actual text.
//     */
//    private static String base64Decoder(String cipherText) {
//        byte[] byteArray = Base64.decodeBase64(cipherText.getBytes());
//        String decodedString = new String(byteArray);
//        return decodedString;
//    }
//
//    /**
//     * Function to send mail.
//     */
//    public static void sendMail() {
//        String fromMailId = FunctionLibrary.readPropertyValue("fromMail");
//        String toMailId = FunctionLibrary.readPropertyValue("toMail");
//        Properties props = new Properties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.smtp.host", "fra-mail.deutsche-boerse.com");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.fallback", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.ssl.checkserveridentity", "true");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "25");
//        // This will handle the complete authentication
//        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                String uid = FunctionLibrary.readPropertyValue("userId");
//                String cipherPwd = FunctionLibrary.readPropertyValue("password");
//                String actaulPwd = base64Decoder(cipherPwd);
//                return new PasswordAuthentication(uid, actaulPwd);
//            }
//        });
//        try {
//            // Create object of MimeMessage class
//            Message message = new MimeMessage(session);
//            // Set the from address
//            message.setFrom(new InternetAddress(fromMailId));
//            // Set the recipient address
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMailId));
//
//            String timeStamp = new SimpleDateFormat("dd-MMMM-yyyy HH.mm.ss a").format(new Date());
//            // Add the subject link
//            message.setSubject("SCS Test Execution Status - " + timeStamp);
//
//            String htmlList = "";
//            /*for (String suiteLink:suiteList.keySet()) {
//                qmetryUrl = "http://qmetry.nee.com/#/test-suite/" + suiteList.get(suiteLink);
//                htmlList = htmlList + "<tr><td><font face=\"Cambria\" color=\"black\" size=\"3\"><a href=" + qmetryUrl
//                        + ">View Execution result for " + suiteLink + "</a></font></td></tr>\n";
//
//            }*/
//            final String htmlCode = "<table>\n"
//                    + "<tr><td align=\"center\" style=\"background-color: #1a8cff;\""
//                    + "height=\"40\"><font face=\"Calibri\" color=\"black\" size=\"5\">"
//                    + "<b>" + "SCS Test Execution Results</b></font></td></tr>\n"
//                    + "<tr><td align=\"center\" style=\"background-color: #1a8cff;\" height=\"2\"></td></tr>\n"
//                    + "<tr><td><br><font face=\"Cambria\" color=\"black\" size=\"3\">Hello Team,</font></td></tr>\n"
//                    + "<tr><td><br><font face=\"Cambria\" color=\"black\" size=\"3\">" + "SCS test automation run"
//                    + "has been completed. Please click on the below link to view the results </font></td></tr>\n"
//                    + htmlList
//                    + "<tr><td><font face=\"Cambria\" color=\"black\" size=\"3\"><br>Thanks & Regards,"
//                    + "<br> DTAS Test Automation Team<br><br></font></td></tr>"
//                    + "<tr><td align=\"center\" style=\"background-color: #1a8cff;\" height=\"2\"></td></tr>"
//                    + "<tr><td align=\"center\" style=\"background-color: #1a8cff;\" height=\"2\"></td></tr>"
//                    + "</table>";
//
//            DataSource source = new FileDataSource(new File(BaseTest.getRepoPath()));
//            
//           
//
//            Multipart multipart = new MimeMultipart(); //1
//            // Create the attachment part
//            BodyPart attachmentBodyPart = new MimeBodyPart(); //2
//            attachmentBodyPart.setDataHandler(new DataHandler(source)); //2
//            attachmentBodyPart.setFileName("SCS__SummaryReport.html"); // 2
//            multipart.addBodyPart(attachmentBodyPart); //3
//            // Create the HTML Part
//            BodyPart htmlBodyPart = new MimeBodyPart(); //4
//            htmlBodyPart.setContent(htmlCode,"text/html"); //5
//            multipart.addBodyPart(htmlBodyPart); // 6
//            // Set the Multipart's to be the email's content
//            message.setContent(multipart); 
//
//            Transport.send(message);
//        } catch (MessagingException e) {
//            LOGGER.error("Error ocuured when sending email:-" + e.getMessage());
//            throw  new ConfigurationException("Error ocuured when sending email :-"+e.getMessage());
//        }
//
//    }
//}
