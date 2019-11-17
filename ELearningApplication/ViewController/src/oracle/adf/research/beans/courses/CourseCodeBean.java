package oracle.adf.research.beans.courses;

import oracle.adf.model.BindingContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class CourseCodeBean {
    public CourseCodeBean() {
    }

    public String getCourseCode() {

        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding codeBinding = (AttributeBinding) bindingContainer.get("Code");
        OperationBinding codeOperation = bindingContainer.getOperationBinding("courseCode");
        String code = (String) codeOperation.execute();
        System.out.println("Book Code Bean " + code);
        codeBinding.setInputValue(code);
        return null;
    }
}
