package oracle.adf.research.beans.books;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class DefaultBookBean {
    private RichPopup deletePublicBookPopUp;
    private RichPopup editPublicBookPopUp;
    private RichPopup deletePrivateBookPopUp;
    private RichPopup editPrivateBookPopUp;
    private RichInputText editPrivateBookCode;
    private RichSelectOneRadio editStatusPrivateBook;

    public DefaultBookBean() {
    }

    public void setDefaultBook(ActionEvent actionEvent) {
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultBookOperation = bindingContainer.getOperationBinding("defaultBooks");
        defaultBookOperation.execute();
    }

    public void setDeletePublicBookPopUp(RichPopup deletePublicBookPopUp) {
        this.deletePublicBookPopUp = deletePublicBookPopUp;
    }

    public RichPopup getDeletePublicBookPopUp() {
        return deletePublicBookPopUp;
    }

    public String deletePublicBook() {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding DeleteBook = bindingContainer.getOperationBinding("Delete");
        DeleteBook.execute();
        OperationBinding Commit = bindingContainer.getOperationBinding("Commit");
        Commit.execute();
        OperationBinding defaultBookOperation = bindingContainer.getOperationBinding("defaultBooks");
        defaultBookOperation.execute();
        this.getDeletePublicBookPopUp().hide();
        return "Library";
    }

    public void setEditPublicBookPopUp(RichPopup editPublicBookPopUp) {
        this.editPublicBookPopUp = editPublicBookPopUp;
    }

    public RichPopup getEditPublicBookPopUp() {
        return editPublicBookPopUp;
    }

    public String EditPublicBook() {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding Commit = bindingContainer.getOperationBinding("Commit");
        Commit.execute();
        OperationBinding defaultBookOperation = bindingContainer.getOperationBinding("defaultBooks");
        defaultBookOperation.execute();
        this.getEditPublicBookPopUp().hide();
        return null;
    }

    public void setDeletePrivateBookPopUp(RichPopup deletePrivateBookPopUp) {
        this.deletePrivateBookPopUp = deletePrivateBookPopUp;
    }

    public RichPopup getDeletePrivateBookPopUp() {
        return deletePrivateBookPopUp;
    }

    public String deletePrivateBook() {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding DeleteBook = bindingContainer.getOperationBinding("Delete");
        DeleteBook.execute();
        OperationBinding Commit = bindingContainer.getOperationBinding("Commit");
        Commit.execute();
        OperationBinding defaultBookOperation = bindingContainer.getOperationBinding("defaultBooks");
        defaultBookOperation.execute();
        this.getDeletePrivateBookPopUp().hide();
        return "Library";
    }

    public void setEditPrivateBookPopUp(RichPopup editPrivateBookPopUp) {
        this.editPrivateBookPopUp = editPrivateBookPopUp;
    }

    public RichPopup getEditPrivateBookPopUp() {
        return editPrivateBookPopUp;
    }

    public String editPrivateBook() {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding Commit = bindingContainer.getOperationBinding("Commit");
        if(editStatusPrivateBook.getValue().equals("Public")){
            System.out.println("Public");
            AttributeBinding codeBinding = (AttributeBinding) bindingContainer.get("Code");
            codeBinding.setInputValue("");
        }
        Commit.execute();
        OperationBinding defaultBookOperation = bindingContainer.getOperationBinding("defaultBooks");
        defaultBookOperation.execute();
        this.getEditPrivateBookPopUp().hide();
        return null;
    }

    public void setEditPrivateBookCode(RichInputText editPrivateBookCode) {
        this.editPrivateBookCode = editPrivateBookCode;
    }

    public RichInputText getEditPrivateBookCode() {
        return editPrivateBookCode;
    }

    public void setEditStatusPrivateBook(RichSelectOneRadio editStatusPrivateBook) {
        this.editStatusPrivateBook = editStatusPrivateBook;
    }

    public RichSelectOneRadio getEditStatusPrivateBook() {
        return editStatusPrivateBook;
    }
}
