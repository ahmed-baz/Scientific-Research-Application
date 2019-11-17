package oracle.adf.research.classes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.research.model.bc.classes.User;
import oracle.adf.share.ADFContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.BlobDomain;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class SettingsBean {

    private UploadedFile myFile;
    private RichInputText oldPassword;
    private RichInputText newPassword;
    private RichActiveOutputText passwordError;
    private RichInputText firstName;
    private RichInputText lastName;
    private RichPopup popup;

    public SettingsBean() {
    }

    public String saveChanges() throws IOException {
        // Add event code here...
        User user = (User) ADFContext.getCurrent().getSessionScope().get("USERS");
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        if (myFile != null) {

            if (myFile.getFilename().toUpperCase().endsWith(".JPG") ||
                myFile.getFilename().toUpperCase().endsWith(".PNG")) {

                //Access Attribute for update
                AttributeBinding attributeName = (AttributeBinding) bindingContainer.get("Image");
                attributeName.setInputValue(createBlobDomain(myFile));
                // Access Iterator Binding for Commit
                //   BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
                DCIteratorBinding employeesDCBinding = (DCIteratorBinding) bindingContainer.get("Users1Iterator");

                employeesDCBinding.getDataControl().commitTransaction();
                user.setUserFirstName(firstName.getValue().toString());
                user.setUserLastName(lastName.getValue().toString());
                
                

            } else {
                FacesMessage mesg =
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error !", "Error while uploading image file !");
                FacesContext.getCurrentInstance().addMessage(null, mesg);

            }
        }else{
            DCIteratorBinding employeesDCBinding = (DCIteratorBinding) bindingContainer.get("Users1Iterator");

            employeesDCBinding.getDataControl().commitTransaction();
            user.setUserFirstName(firstName.getValue().toString());
            user.setUserLastName(lastName.getValue().toString());
        }
        if(!newPassword.isReadOnly()){
            AttributeBinding newPass = (AttributeBinding) bindingContainer.get("Password");
            newPass.setInputValue(encrypt(newPassword.getValue().toString(), 1));
            DCIteratorBinding employeesDCBinding = (DCIteratorBinding) bindingContainer.get("Users1Iterator");
            employeesDCBinding.getDataControl().commitTransaction();
        }
        this.refreshPage();
        return null;
    }
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

        try {
            in = file.getInputStream();

            blobDomain = new BlobDomain();
            out = blobDomain.getBinaryOutputStream();
            byte[] buffer = new byte[8192];
            int bytesRead = 0;

            while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.fillInStackTrace();
        }

        return blobDomain;
    }
    private void refreshPage() {

        FacesContext fctx = FacesContext.getCurrentInstance();
        String refreshpage = fctx.getViewRoot().getViewId();
        ViewHandler ViewH = fctx.getApplication().getViewHandler();
        UIViewRoot UIV = ViewH.createView(fctx, refreshpage);
        UIV.setViewId(refreshpage);
        fctx.setViewRoot(UIV);

    }

    public void setOldPassword(RichInputText oldPassword) {
        this.oldPassword = oldPassword;
    }

    public RichInputText getOldPassword() {
        return oldPassword;
    }

    public void setNewPassword(RichInputText newPassword) {
        this.newPassword = newPassword;
    }

    public RichInputText getNewPassword() {
        return newPassword;
    }

    public void setPasswordError(RichActiveOutputText passwordError) {
        this.passwordError = passwordError;
    }

    public RichActiveOutputText getPasswordError() {
        return passwordError;
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

    public void checkPassword(ValueChangeEvent valueChangeEvent) throws IOException {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding currentPassword = (AttributeBinding) bindingContainer.get("Password");
        if(currentPassword.getInputValue().equals(encrypt((String) valueChangeEvent.getNewValue(), 1))){
            System.out.println("Match ... ... ...");
            passwordError.setVisible(false);
            newPassword.setReadOnly(false);
        }else{
            System.out.println("Else .. .. ..");
            passwordError.setVisible(true);
            passwordError.setValue("Password not match the old password");
            newPassword.setReadOnly(true);
            
        }
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

    public void professorSettingsSave(ActionEvent actionEvent) {
        // Add event code here...
        // Add event code here...
        
        
    }

    public String professorSaveSettings() {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        /*AttributeBinding userId = (AttributeBinding) bindingContainer.get("UserId");
        User user = (User) ADFContext.getCurrent().getSessionScope().get("USERS");
        userId.setInputValue(user.getUserId());*/
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getPopup().show(hints);
        OperationBinding operation = bindingContainer.getOperationBinding("Commit");
        operation.execute();
        operation = bindingContainer.getOperationBinding("filterApprovals");
        operation.execute();
        return "Settings";
    }

    public void setPopup(RichPopup popup) {
        this.popup = popup;
    }

    public RichPopup getPopup() {
        return popup;
    }
}
