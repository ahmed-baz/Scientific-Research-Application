package oracle.adf.research.beans.user;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import oracle.adf.model.BindingContext;
import oracle.adf.research.model.bc.classes.User;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class RestoreAccount {
    private RichInputText userEmail;
    private RichPopup emailFaild;
    private RichPopup emailFound;

    public RestoreAccount() {
    }

    public String checkEmail() throws IOException {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding loginOperation = bindingContainer.getOperationBinding("checkEmail");

        loginOperation.getParamsMap().put("email", userEmail.getValue());

        Boolean checkLogin = (Boolean) loginOperation.execute();


        if (loginOperation.getErrors().isEmpty())
            if (checkLogin) {

                BindingContext bctx = BindingContext.getCurrent();
                BindingContainer bindings = bctx.getCurrentBindingsEntry();
                String code = generateRandomCode();
                ArrayList<String> attachesArr = new ArrayList<String>();
                ArrayList<String> toUserArr = new ArrayList<String>();
                toUserArr.add(userEmail.getValue().toString());
                AttributeBinding attribute = (AttributeBinding) bindingContainer.get("Password");
                System.out.println("Password : " + encrypt(attribute.getInputValue().toString(), 2));
                attribute.setInputValue(encrypt(code, 1));
                OperationBinding operation = bindings.getOperationBinding("Commit");
                operation.execute();
                sendMail("Your Password : " + code, "(E-Learning) Restore Password", "uwagdy", toUserArr,
                         "uwagdy@gmail.com123", "smtp.gmail.com", "N", attachesArr);


            } else {
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                this.getEmailFaild().show(hints);
            }
        return null;
    }

    public void setUserEmail(RichInputText userEmail) {
        this.userEmail = userEmail;
    }

    public RichInputText getUserEmail() {
        return userEmail;
    }

    public void setEmailFaild(RichPopup emailFaild) {
        this.emailFaild = emailFaild;
    }

    public RichPopup getEmailFaild() {
        return emailFaild;
    }

    public String closePopUp() {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getEmailFaild().hide();
        return null;
    }

    public void setEmailFound(RichPopup emailFound) {
        this.emailFound = emailFound;
    }

    public RichPopup getEmailFound() {
        return emailFound;
    }
    Properties emailProperties;
    Session mailSession;
    MimeMessage emailMessage;
    String toWhom;
    String messagae;
    String subject1;


    public String sendMail(String msg, String subject, String FromUser, ArrayList<String> ToUser, String pwd,
                           String hostName, String isAnyAtchmnt, ArrayList<String> fileNameNPath) {
        // Setting Properties
        String host = "localhost";
        Properties emailProperties = new Properties();
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.host", hostName);
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        //emailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //Login Credentials
        final String user = FromUser; //change accordingly
        final String password = pwd; //change accordingly
        //Authenticating...
        Session session = Session.getInstance(emailProperties, new javax.mail.Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        //1) create MimeBodyPart object and set your message content
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(user));
            for (String email : ToUser) {
                System.out.println("Mail Id is-" + email);
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            }
            message.setSubject(subject);

            BodyPart messageBody = new MimeBodyPart();

            messageBody.setContent(msg, "text/html");

            // If there is any attachment to send
            //5) create Multipart object and add MimeBodyPart objects to this object
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBody);

            if ("Y".equalsIgnoreCase(isAnyAtchmnt)) {
                //2) create new MimeBodyPart object and set DataHandler object to this object


                for (String path : fileNameNPath) {
                    MimeBodyPart messageBodyPart2 = new MimeBodyPart();
                    System.out.println("Exact path--->" + path);
                    DataSource source = new FileDataSource(path);
                    messageBodyPart2.setDataHandler(new DataHandler(source));
                    // System.out.println("FileName is-"+path.substring(path.lastIndexOf("//")+1, path.length()));
                    messageBodyPart2.setFileName(path.substring(path.lastIndexOf("//") + 2, path.length()));
                    multipart.addBodyPart(messageBodyPart2);
                }

                //6) set the multiplart object to the message object
                message.setContent(multipart);
                message.saveChanges();
            }
            //If there is plain eMail- No Attachment
            else {
                message.setContent(msg, "text/html"); //for a html email
            }
        } catch (MessagingException e) {
        }

        Transport transport = null;


        try {
            transport = session.getTransport("smtp");
        } catch (NoSuchProviderException e) {
            System.out.println("No such Provider Exception");
        }

        try {
            System.out.println("Email sent successfully.");
            transport.connect(hostName, FromUser, pwd);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.getEmailFound().show(hints);
            return "Y";
        } catch (MessagingException e) {
            System.out.println("Messaging Exception" + e);
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.getEmailFaild().show(hints);
            return "N";
        }

    }

    public String generateRandomCode() {
        Random rand = new Random();

        int n = rand.nextInt(999999999) + 10000000;
        return String.valueOf(n);
    }

    public String encrypt(String value, int type) throws IOException {

        // type = 1 for encryption , 2 for decryption
        String result = null;
        if (type == 1) {
            result = new sun.misc.BASE64Encoder().encode(value.getBytes());
        } else {
            byte[] decode = new sun.misc.BASE64Decoder().decodeBuffer(value);
            result = new String(decode);
        }
        return result;
    }
}
