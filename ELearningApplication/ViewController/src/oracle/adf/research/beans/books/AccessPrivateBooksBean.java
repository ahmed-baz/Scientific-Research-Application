package oracle.adf.research.beans.books;

import oracle.adf.controller.ControllerContext;
import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class AccessPrivateBooksBean {
    private String code;
    private RichOutputText codeMessage;

    public AccessPrivateBooksBean() {
    }

    public String getSelectedPrivateBook() {
        String msg = "Invalid Code";
        if (code != null) {
            BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
            OperationBinding codeOperation = bindingContainer.getOperationBinding("checkBookCode");
            codeOperation.getParamsMap().put("code", code);
            Boolean checkMycode = (Boolean) codeOperation.execute();
            System.out.println("Is Found : " + checkMycode);
            System.out.println("Code : " + code);

            if (checkMycode == true) {
                this.codeMessage.setValue(null);
                ControllerContext ccontext = ControllerContext.getInstance();
                String viewId = "PrivateBook";
                ccontext.getCurrentViewPort().setViewId(viewId);
            } else {
                System.out.println("Else .. ");
                this.codeMessage.setValue(msg);
            }
        } else {
            this.codeMessage.setValue(msg);
        }
        return null;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCodeMessage(RichOutputText codeMessage) {
        this.codeMessage = codeMessage;
    }

    public RichOutputText getCodeMessage() {
        return codeMessage;
    }
}
