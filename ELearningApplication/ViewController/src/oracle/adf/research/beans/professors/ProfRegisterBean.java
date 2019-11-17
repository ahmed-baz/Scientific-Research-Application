package oracle.adf.research.beans.professors;

import oracle.adf.model.BindingContext;

import oracle.adf.research.model.bc.classes.User;
import oracle.adf.share.ADFContext;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class ProfRegisterBean {
    private RichPopup popUp;

    public ProfRegisterBean() {
    }

    public String profRegisteration() {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        //AttributeBinding userId = (AttributeBinding) bindingContainer.get("UserId");
        //User user = (User) ADFContext.getCurrent().getSessionScope().get("USERS");
        //System.out.println("User ID : "+user.getUserId());
        //System.out.println("User id : " + userId.getInputValue());
        //userId.setInputValue(user.getUserId());
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getPopUp().show(hints);
        OperationBinding operation = bindingContainer.getOperationBinding("Commit");
        operation.execute();
        operation = bindingContainer.getOperationBinding("filterApprovals");
        operation.execute();
        return null;
    }

    public void setPopUp(RichPopup popUp) {
        this.popUp = popUp;
    }

    public RichPopup getPopUp() {
        return popUp;
    }
}
