

package oracle.adf.research.beans.user;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Properties;

import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichTextEditor;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.JboException;
import oracle.jbo.domain.BlobDomain;


import oracle.jbo.uicli.binding.JUCtrlListBinding;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class RegisterBean {

    private static UploadedFile myFile;
    private RichInputDate date;
    private RichInputText firstName;
    private RichInputText lastName;
    private RichInputText email;
    private RichInputText password;
    private RichSelectOneChoice gender;
    private RichInputText verifyEmail;
    private RichInputText code;
    private RichPopup popUp;
    private RichPopup emailPopUp;
    private RichPopup emailFaild;
    private static BlobDomain imgBlob;
    private RichPopup verifyFailed;

    public RegisterBean() {
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

    public String registerNewUser() {
        System.out.println("function");
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operation = bindings.getOperationBinding("Commit");
        String code = generateRandomCode();
        try {
            if (myFile != null) {
                System.out.println("Uploade File");
                if (myFile.getFilename().toUpperCase().endsWith(".JPG") ||
                    myFile.getFilename().toUpperCase().endsWith(".PNG")) {
                    System.out.println("Set Code");
                    AttributeBinding attribute = (AttributeBinding) bindings.get("Code");
                    attribute.setInputValue(code);
                    System.out.println("Set Image");
                    attribute = (AttributeBinding) bindings.get("Image");
                    System.out.println("Image .......");
                    attribute.setInputValue(imgBlob);
                    System.out.println("Set Password");
                    attribute = (AttributeBinding) bindings.get("Password");
                    attribute.setInputValue(encrypt(password.getValue().toString(), 1));
                    System.out.println("Execute Commit");
                    operation.execute();
                    ArrayList<String> attachesArr = new ArrayList<String>();
                    ArrayList<String> toUserArr = new ArrayList<String>();
                    toUserArr.add(email.getValue().toString());
                    sendMail("<html>Hello " + firstName.getValue().toString() + " " + lastName.getValue().toString() +
                             "<br> Your Verifcation Code is : " + code + "</html>", "(E-Learning) Verification Code",
                             "uwagdy", toUserArr, "uwagdy@gmail.com123", "smtp.gmail.com", "N", attachesArr);


                    return "code";
                } else {
                    FacesMessage mesg =
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error !", "Error while uploading image file !");
                    FacesContext.getCurrentInstance().addMessage(null, mesg);

                }
            } else {
                System.out.println("Else");
                AttributeBinding attribute = (AttributeBinding) bindings.get("Code");
                attribute.setInputValue(code);
                attribute = (AttributeBinding) bindings.get("Password");
                attribute.setInputValue(encrypt(password.getValue().toString(), 1));
                operation.execute();
                return "code";
            }
        } catch (Exception e) {
            System.out.println("" + e);
            return null;
        }
        return null;
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
            this.getEmailPopUp().show(hints);
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

        int n = rand.nextInt(99999) + 10000;
        return String.valueOf(n);
    }


    public void uploadImage() {
        if (myFile != null) {

            if (myFile.getFilename().toUpperCase().endsWith(".JPG") ||
                myFile.getFilename().toUpperCase().endsWith(".PNG")) {

                //Access Attribute for update
                BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
                AttributeBinding attributeName = (AttributeBinding) bindingContainer.get("Image");
                attributeName.setInputValue(createBlobDomain(myFile));
                // Access Iterator Binding for Commit
                //BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
                //DCIteratorBinding employeesDCBinding = (DCIteratorBinding) bindingContainer.get("Users1Iterator");
                //employeesDCBinding.
                //employeesDCBinding.getDataControl().commitTransaction();

            } else {
                FacesMessage mesg =
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error !", "Error while uploading image file !");
                FacesContext.getCurrentInstance().addMessage(null, mesg);

            }
        }
    }


    @SuppressWarnings("oracle.jdeveloper.java.semantic-warning")
    public void setMyFile(UploadedFile myFile) {
        this.myFile = myFile;
    }

    public UploadedFile getMyFile() {
        return myFile;
    }

    private BlobDomain createBlobDomain(UploadedFile file) {

        InputStream in = null;
        BlobDomain blobDomain = null;
        OutputStream out = null;
        System.out.println("Craete Blob Domain");
        System.out.println("File Name : " + file.getFilename());
        try {
            in = file.getInputStream();

            blobDomain = new BlobDomain();
            System.out.println("Step ..1");
            out = blobDomain.getBinaryOutputStream();
            System.out.println("Step ..2");
            byte[] buffer = new byte[8192];
            System.out.println("Step ..3");
            int bytesRead = 0;
            System.out.println("Step ..4");
            while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {

                out.write(buffer, 0, bytesRead);
            }
            System.out.println("Step ..5");
            in.close();
            System.out.println("Step ..6");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
        System.out.println("Blob Domain Created");
        return blobDomain;
    }

    public void setDate(RichInputDate date) {
        this.date = date;
    }

    public RichInputDate getDate() {
        return date;
    }

    public void setFirstName(RichInputText firstName) {
        this.firstName = firstName;
    }

    public RichInputText getFirstName() {
        return firstName;
    }

    public void setLastName(RichInputText lastName) {
        this.lastName = lastName;
    }

    public RichInputText getLastName() {
        return lastName;
    }

    public void setEmail(RichInputText email) {
        this.email = email;
    }

    public RichInputText getEmail() {
        return email;
    }

    public void setPassword(RichInputText password) {
        this.password = password;
    }

    public RichInputText getPassword() {
        return password;
    }

    public void setGender(RichSelectOneChoice gender) {
        this.gender = gender;
    }

    public RichSelectOneChoice getGender() {
        return gender;
    }

    public String verifyAccount() {
        System.out.println("function verify code");
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        try {
            OperationBinding operation = bindings.getOperationBinding("activeEmail");
            operation.getParamsMap().put("email", verifyEmail.getValue());
            operation.getParamsMap().put("code", code.getValue());
            String active = (String) operation.execute();
            if (active.equals("Y")) {
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                this.getPopUp().show(hints);
            } else {
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                this.getVerifyFailed().show(hints);
            }

        } catch (Exception e) {
            //throw new JboException("Make sure that you entered the correct code !!");
            //System.out.println("" + e);
        }
        return null;
    }

    public void setVerifyEmail(RichInputText verifyEmail) {
        this.verifyEmail = verifyEmail;
    }

    public RichInputText getVerifyEmail() {
        return verifyEmail;
    }

    public void setCode(RichInputText code) {
        this.code = code;
    }

    public RichInputText getCode() {
        return code;
    }

    public void setPopUp(RichPopup popUp) {
        this.popUp = popUp;
    }

    public RichPopup getPopUp() {
        return popUp;
    }

    public void setEmailPopUp(RichPopup emailPopUp) {
        this.emailPopUp = emailPopUp;
    }

    public RichPopup getEmailPopUp() {
        return emailPopUp;
    }

    public void setEmailFaild(RichPopup emailFaild) {
        this.emailFaild = emailFaild;
    }

    public RichPopup getEmailFaild() {
        return emailFaild;
    }


    public void setImageFilrBlob(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        myFile = (UploadedFile) valueChangeEvent.getNewValue();
        imgBlob = createBlobDomain(myFile);
    }

    public void setVerifyFailed(RichPopup verifyFailed) {
        this.verifyFailed = verifyFailed;
    }

    public RichPopup getVerifyFailed() {
        return verifyFailed;
    }


}
