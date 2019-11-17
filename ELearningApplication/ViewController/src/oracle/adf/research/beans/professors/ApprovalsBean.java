package oracle.adf.research.beans.professors;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import oracle.adf.controller.ControllerContext;

import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichListItem;
import oracle.adf.view.rich.component.rich.data.RichListView;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class ApprovalsBean {
    private RichInputText userAcceptEmail;
    private RichPopup profAccept;
    private RichPopup profFailed;
    private RichButton acceptButton;
    private RichListItem listItemSelectRequest;
    private RichListView listViewSelectRequest;
    private RichButton submitButtonProApproval;

    public ApprovalsBean() {
    }

    public void selectProfessorApproval(SelectionEvent selectionEvent) {
        // Add event code here...
        invokeEL("#{bindings.ProfessorsApprovals.treeModel.makeCurrent}", new Class[] { SelectionEvent.class }, new Object[] {
                 selectionEvent });
        ControllerContext ccontext = ControllerContext.getInstance();

        //set the viewId - the name of the view activity to

        //go to - to display

        String viewId = "UserData";

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

    public String acceptProf() {
        // Add event code here...
        //String[]partialTrigeers = new String[1];
        //partialTrigeers[0] = acceptButton.getId();
        //partialTrigeers[1] = submitButtonProApproval.getId();
        //listItemSelectRequest.setPartialTriggers(partialTrigeers);
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operation = bindings.getOperationBinding("acceptProfessor");
        operation.getParamsMap().put("email", userAcceptEmail.getValue());
        String active = (String) operation.execute();
        if (active.equals("Y")) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.getProfAccept().show(hints);
        } else {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.getProfFailed().show(hints);
        }
        /*System.out.println("function accept prof.");
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operation = bindings.getOperationBinding("acceptProfessor");
        operation.getParamsMap().put("email", userAcceptEmail.getValue());
        String active = (String) operation.execute();
        System.out.println("Active : " + active);
        if (active.equals("Y")) {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.getProfAccept().show(hints);
        } else {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.getProfFailed().show(hints);
        }*/
        return null;

    }

    protected void refreshPage() {
        FacesContext fctx = FacesContext.getCurrentInstance();
        String refreshpage = fctx.getViewRoot().getViewId();
        ViewHandler ViewH = fctx.getApplication().getViewHandler();
        UIViewRoot UIV = ViewH.createView(fctx, refreshpage);
        UIV.setViewId(refreshpage);
        fctx.setViewRoot(UIV);
    }

    public void setUserAcceptEmail(RichInputText userAcceptEmail) {
        this.userAcceptEmail = userAcceptEmail;
    }

    public RichInputText getUserAcceptEmail() {
        return userAcceptEmail;
    }

    public void setProfAccept(RichPopup profAccept) {
        this.profAccept = profAccept;
    }

    public RichPopup getProfAccept() {
        return profAccept;
    }

    public void setProfFailed(RichPopup profFailed) {
        this.profFailed = profFailed;
    }

    public RichPopup getProfFailed() {
        return profFailed;
    }

    public void setAcceptButton(RichButton acceptButton) {
        this.acceptButton = acceptButton;
    }

    public RichButton getAcceptButton() {
        return acceptButton;
    }

    public void setListItemSelectRequest(RichListItem listItemSelectRequest) {
        this.listItemSelectRequest = listItemSelectRequest;
    }

    public RichListItem getListItemSelectRequest() {
        return listItemSelectRequest;
    }

    public void setListViewSelectRequest(RichListView listViewSelectRequest) {
        this.listViewSelectRequest = listViewSelectRequest;
    }

    public RichListView getListViewSelectRequest() {
        return listViewSelectRequest;
    }

    public void setSubmitButtonProApproval(RichButton submitButtonProApproval) {
        this.submitButtonProApproval = submitButtonProApproval;
    }

    public RichButton getSubmitButtonProApproval() {
        return submitButtonProApproval;
    }
}
