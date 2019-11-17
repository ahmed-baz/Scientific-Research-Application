package oracle.adf.research.beans.professors;

import java.util.ArrayList;
import java.util.Properties;

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
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class AcceptBean {
    private RichInputText userAcceptEmail;
    private RichDialog proFaild;
    private RichPopup profAccept;
    private RichPopup profFaild;

    public AcceptBean() {
    }

    public String acceptProf() {
        // Add event code here...
        System.out.println("function verify code");
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        try {
            OperationBinding operation = bindings.getOperationBinding("acceptProfessor");
            operation.getParamsMap().put("email", userAcceptEmail.getValue());
            String active = (String) operation.execute();
            if (active.equals("Y")) {
                //User user = (User) ADFContext.getCurrent().getSessionScope().get("USERS");
                AttributeBinding attribute = (AttributeBinding) bindings.get("Email");
                ArrayList<String> attachesArr = new ArrayList<String>();
                ArrayList<String> toUserArr = new ArrayList<String>();
                toUserArr.add(attribute.getInputValue().toString());
                
                sendMail("<html>Congratulation<br> Your Accepted to be professor .</html>", "(E-Learning) Accept",
                         "uwagdy", toUserArr, "uwagdy@gmail.com123", "smtp.gmail.com", "N", attachesArr);
                //RichPopup.PopupHints hints = new RichPopup.PopupHints();
                //this.getProfAccept().show(hints);
            } else {
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                this.getProfFaild().show(hints);
            }

        } catch (Exception e) {
            //throw new JboException("Make sure that you entered the correct code !!");
            //System.out.println("" + e);
        }
        return null;

    }

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
            this.getProfAccept().show(hints);
            return "Y";
        } catch (MessagingException e) {
            System.out.println("Messaging Exception" + e);
            //RichPopup.PopupHints hints = new RichPopup.PopupHints();
            //this.getProfFaild().show(hints);
            return "N";
        }

    }

    public void setUserAcceptEmail(RichInputText userAcceptEmail) {
        this.userAcceptEmail = userAcceptEmail;
    }

    public RichInputText getUserAcceptEmail() {
        return userAcceptEmail;
    }

    public void setProFaild(RichDialog proFaild) {
        this.proFaild = proFaild;
    }

    public RichDialog getProFaild() {
        return proFaild;
    }

    public void setProfAccept(RichPopup profAccept) {
        this.profAccept = profAccept;
    }

    public RichPopup getProfAccept() {
        return profAccept;
    }

    public void setProfFaild(RichPopup profFaild) {
        this.profFaild = profFaild;
    }

    public RichPopup getProfFaild() {
        return profFaild;
    }
}
