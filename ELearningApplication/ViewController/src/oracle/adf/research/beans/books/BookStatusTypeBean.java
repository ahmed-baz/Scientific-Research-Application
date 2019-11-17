package oracle.adf.research.beans.books;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichButton;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class BookStatusTypeBean {
    private String status;
    private RichPanelGroupLayout bookStatusType;
    private RichOutputFormatted bookCodeText;
    private RichButton getCodeButton;
    private RichInputText codeEditPublic;
    private RichInputText codeEditPrivate;

    public BookStatusTypeBean() {
    }

    public void getBookStatusType(ValueChangeEvent valueChangeEvent) {
        status = (String) valueChangeEvent.getNewValue();
        if (status.compareTo("Private") == 0) {
            getCodeButton.setDisabled(false);
        } else {
            getCodeButton.setDisabled(true);
        }

    }

    public void setBookStatusType(RichPanelGroupLayout bookStatusType) {
        this.bookStatusType = bookStatusType;
    }

    public RichPanelGroupLayout getBookStatusType() {
        return bookStatusType;
    }

    public void setBookCodeText(RichOutputFormatted bookCodeText) {
        this.bookCodeText = bookCodeText;
    }

    public RichOutputFormatted getBookCodeText() {
        return bookCodeText;
    }

    public void setGetCodeButton(RichButton getCodeButton) {
        this.getCodeButton = getCodeButton;
    }

    public RichButton getGetCodeButton() {
        return getCodeButton;
    }

    public void setCodeEditPublic(RichInputText codeEditPublic) {
        this.codeEditPublic = codeEditPublic;
    }

    public RichInputText getCodeEditPublic() {
        return codeEditPublic;
    }

    public void editStatusPublic(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        status = (String) valueChangeEvent.getNewValue();
        if (status.compareTo("Private") == 0) {
            BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding codeOperation = bindingContainer.getOperationBinding("bookCode");
            String code = (String) codeOperation.execute();
            System.out.println("Book Code Bean " + code);
            codeEditPublic.setValue(code);
            //bookStatusType.setVisible(true);
            //bookCodeText.setDisabled(true);
            //getCodeButton.setDisabled(false);
        } else {
            //bookStatusType.setVisible(false);
            //getCodeButton.setDisabled(true);
            codeEditPublic.setValue("");
        }
    }

    /*-public void editStatusPrivate(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        status = (String) valueChangeEvent.getNewValue();
        if (status.compareTo("Private") == 0) {
            BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding codeOperation = bindingContainer.getOperationBinding("bookCode");
            String code = (String) codeOperation.execute();
            System.out.println("Book Code Bean " + code);
            codeEditPrivate.setVisible(true);
        } else {
            codeEditPrivate.setVisible(false);
        }
    }*/

    public void setCodeEditPrivate(RichInputText codeEditPrivate) {
        this.codeEditPrivate = codeEditPrivate;
    }

    public RichInputText getCodeEditPrivate() {
        return codeEditPrivate;
    }
}
