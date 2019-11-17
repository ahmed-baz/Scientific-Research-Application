package oracle.adf.research.beans.projects;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.domain.BlobDomain;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class UploadProjectBean {
    private RichInputText codeText;
    private RichPopup popUp;
    private RichPanelGroupLayout panelGroup;
    private RichButton getCodeButton;
    private RichButton getCodeEdit;
    private RichInputText codeEdit;
    private RichPanelGroupLayout panelGroupEdit;
    private RichInputText codeEditPrivate;
    private RichPopup error1;

    public UploadProjectBean() {
    }

    public void saveProject(ActionEvent actionEvent) {
        // Add event code here...
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operation = bindings.getOperationBinding("Commit");
        operation.execute();
        operation = bindings.getOperationBinding("filterPrivateProjects");
        operation.execute();
        operation = bindings.getOperationBinding("filterPublicProjects");
        operation.execute();
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getPopUp().show(hints);
    }

    public void getCode(ActionEvent actionEvent) {
        // Add event code here...
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operation = bindings.getOperationBinding("getCode");
        String code = (String) operation.execute();
        System.out.println("Code :" + code);
        codeText.setValue(code);
        codeEdit.setValue(code);
    }

    public void setCodeText(RichInputText codeText) {
        this.codeText = codeText;
    }

    public RichInputText getCodeText() {
        return codeText;
    }

    private String uploadFile(UploadedFile file) {

        UploadedFile myfile = file;
        String path = null;
        if (myfile == null) {

        } else {
            // All uploaded files will be stored in below path
            //path = "/home/uwagdy/jdeveloper/mywork/ELearningApplication/ProjectsFiles/" + myfile.getFilename();
            path = "D:\\FCI\\Project\\ELearningApplication\\ProjectsFiles\\" + myfile.getFilename();

            BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
            AttributeBinding TrainerImagePathAttribute = (AttributeBinding) bindingContainer.get("Path");
            TrainerImagePathAttribute.setInputValue(path);
            System.out.println(" Set InputValue");
            //DCIteratorBinding CourseDCBinding = (DCIteratorBinding) bindingContainer.get("Cv3Iterator");

            //CourseDCBinding.getDataControl().commitTransaction();


            InputStream inputStream = null;
            try {
                FileOutputStream out = new FileOutputStream(path);
                inputStream = myfile.getInputStream();
                byte[] buffer = new byte[8192];
                int bytesRead = 0;
                while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                out.flush();
                out.close();
            } catch (Exception ex) {
                // handle exception
                ex.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }

        }
        //Returns the path where file is stored
        return path;
    }

    public void uploadProjectFile(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("read id ");

        if (valueChangeEvent.getNewValue() != null) {
            //Get File Object from VC Event
            UploadedFile fileVal = (UploadedFile) valueChangeEvent.getNewValue();
            if (fileVal.getFilename().toUpperCase().endsWith(".ZIP") ||
                fileVal.getFilename().toUpperCase().endsWith(".RAR")) {
                //Upload File to path- Return actual server path
                String path = uploadFile(fileVal);
            } else {
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                this.getError1().show(hints);
            }
        }
    }

    public void uploadProjectImage(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        UploadedFile myFile = (UploadedFile) valueChangeEvent.getNewValue();
        if (myFile != null) {

            if (myFile.getFilename().toUpperCase().endsWith(".JPG") ||
                myFile.getFilename().toUpperCase().endsWith(".PNG")) {

                //Access Attribute for update
                BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
                AttributeBinding attributeName = (AttributeBinding) bindingContainer.get("ProjectImage");
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

    public void uploadProjectScreenshot1(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        UploadedFile myFile = (UploadedFile) valueChangeEvent.getNewValue();
        if (myFile != null) {

            if (myFile.getFilename().toUpperCase().endsWith(".JPG") ||
                myFile.getFilename().toUpperCase().endsWith(".PNG")) {

                //Access Attribute for update
                BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
                AttributeBinding attributeName = (AttributeBinding) bindingContainer.get("Screenshot1");
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

    public void uploadProjectScreenshot2(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        UploadedFile myFile = (UploadedFile) valueChangeEvent.getNewValue();
        if (myFile != null) {

            if (myFile.getFilename().toUpperCase().endsWith(".JPG") ||
                myFile.getFilename().toUpperCase().endsWith(".PNG")) {

                //Access Attribute for update
                BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
                AttributeBinding attributeName = (AttributeBinding) bindingContainer.get("Screenshot2");
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

    public void setPopUp(RichPopup popUp) {
        this.popUp = popUp;
    }

    public RichPopup getPopUp() {
        return popUp;
    }

    public void setPanelGroup(RichPanelGroupLayout panelGroup) {
        this.panelGroup = panelGroup;
    }

    public RichPanelGroupLayout getPanelGroup() {
        return panelGroup;
    }

    public void typeChange(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("Value Change Listener + " + valueChangeEvent.getNewValue());
        if (valueChangeEvent.getNewValue().equals("Private")) {
            System.out.println("Private Selected");
            BindingContext bctx = BindingContext.getCurrent();
            BindingContainer bindings = bctx.getCurrentBindingsEntry();
            OperationBinding operation = bindings.getOperationBinding("getCode");
            String code = (String) operation.execute();
            System.out.println("Code :" + code);
            codeText.setValue(code);
            //codeEdit.setValue(code);
            //getCodeButton.setDisabled(false);
            //getCodeEdit.setDisabled(false);

            //panelGroup.setVisible(true);
        } else {
            System.out.println("Public Selected");
            //getCodeButton.setDisabled(true);
            codeText.setValue("");
            //codeEdit.setValue("");
            //panelGroup.setVisible(false);
        }
    }

    public void setGetCodeButton(RichButton getCodeButton) {
        this.getCodeButton = getCodeButton;
    }

    public RichButton getGetCodeButton() {
        return getCodeButton;
    }

    public void setGetCodeEdit(RichButton getCodeEdit) {
        this.getCodeEdit = getCodeEdit;
    }

    public RichButton getGetCodeEdit() {
        return getCodeEdit;
    }

    public void setCodeEdit(RichInputText codeEdit) {
        this.codeEdit = codeEdit;
    }

    public RichInputText getCodeEdit() {
        return codeEdit;
    }

    public void setPanelGroupEdit(RichPanelGroupLayout panelGroupEdit) {
        this.panelGroupEdit = panelGroupEdit;
    }

    public RichPanelGroupLayout getPanelGroupEdit() {
        return panelGroupEdit;
    }

    public void typeChangEditPublic(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("Value Change Listener + " + valueChangeEvent.getNewValue());
        if (valueChangeEvent.getNewValue().equals("Private")) {
            System.out.println("Private Selected");
            BindingContext bctx = BindingContext.getCurrent();
            BindingContainer bindings = bctx.getCurrentBindingsEntry();
            OperationBinding operation = bindings.getOperationBinding("getCode");
            String code = (String) operation.execute();
            System.out.println("Code :" + code);
            codeEdit.setValue(code);
            //getCodeEdit.setDisabled(false);

            //panelGroup.setVisible(true);
        } else {
            System.out.println("Public Selected");
            //getCodeButton.setDisabled(true);
            //codeText.setValue("");
            codeEdit.setValue("");

        }
    }

    public void setCodeEditPrivate(RichInputText codeEditPrivate) {
        this.codeEditPrivate = codeEditPrivate;
    }

    public RichInputText getCodeEditPrivate() {
        return codeEditPrivate;
    }

    public void typeChangeEditPrivate(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("Value Change Listener + " + valueChangeEvent.getNewValue());
        if (valueChangeEvent.getNewValue().equals("Private")) {
            System.out.println("Private Selected");
            BindingContext bctx = BindingContext.getCurrent();
            BindingContainer bindings = bctx.getCurrentBindingsEntry();
            OperationBinding operation = bindings.getOperationBinding("getCode");
            String code = (String) operation.execute();
            System.out.println("Code :" + code);
            codeEditPrivate.setValue(code);
            //getCodeEdit.setDisabled(false);

            //panelGroup.setVisible(true);
        } else {
            System.out.println("Public Selected");
            //getCodeButton.setDisabled(true);
            //codeText.setValue("");
            codeEditPrivate.setValue("");

        }
    }

    public void setError1(RichPopup error1) {
        this.error1 = error1;
    }

    public RichPopup getError1() {
        return error1;
    }
}
