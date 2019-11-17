package oracle.adf.research.beans.professors;

import java.math.BigDecimal;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.controller.ControllerContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.ViewCriteriaManager;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;

public class ProfessorBean {
    private RichInputText profName;

    public ProfessorBean() {
    }

    public void selectProfessor(SelectionEvent selectionEvent) {
        // Add event code here...
        invokeEL("#{bindings.Professors1.treeModel.makeCurrent}", new Class[] { SelectionEvent.class }, new Object[] {
                 selectionEvent });
        ControllerContext ccontext = ControllerContext.getInstance();

        //set the viewId - the name of the view activity to

        //go to - to display

        String viewId = "Professor";

        ccontext.getCurrentViewPort().setViewId(viewId);
    }

    public static Object invokeEL(String el, Class[] paramTypes, Object[] params) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        MethodExpression exp = expressionFactory.createMethodExpression(elContext, el, Object.class, paramTypes);

        return exp.invoke(elContext, params);
    }

    public static Object evaluateEL(String el) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        ValueExpression exp = expressionFactory.createValueExpression(elContext, el, Object.class);

        return exp.getValue(elContext);
    }

    public void setProfName(RichInputText profName) {
        this.profName = profName;
    }

    public RichInputText getProfName() {
        return profName;
    }

    public void searchProf(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("City : " + profName.getValue());
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding search = bindings.getOperationBinding("searchByName");
        search.getParamsMap().put("name", profName.getValue());
        search.execute();
    }

    public void searchProfessor(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("City : " + profName.getValue());
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding search = bindings.getOperationBinding("searchByName");
        search.getParamsMap().put("name", profName.getValue());
        search.execute();
    }
}
