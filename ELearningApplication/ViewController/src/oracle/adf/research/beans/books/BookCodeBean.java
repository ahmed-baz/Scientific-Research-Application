package oracle.adf.research.beans.books;

import oracle.adf.model.BindingContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class BookCodeBean {
    public BookCodeBean() {
    }

    public String getNewCodeBTN() {
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding codeBinding = (AttributeBinding) bindingContainer.get("Code");
        OperationBinding codeOperation = bindingContainer.getOperationBinding("bookCode");
        String code = (String) codeOperation.execute();
        System.out.println("Book Code Bean " + code);
        codeBinding.setInputValue(code);
        return null;
    }
}
