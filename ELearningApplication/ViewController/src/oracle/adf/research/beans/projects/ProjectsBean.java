package oracle.adf.research.beans.projects;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import oracle.adf.controller.ControllerContext;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class ProjectsBean {
    private RichPopup accessPrivateProjectPopUp;
    private RichInputText projectCode;
    private RichActiveOutputText errorText;
    private RichPopup deletePublicProject;
    private RichPopup editPublicProject;
    private RichPopup privateProjectSave;
    private RichPopup deletePrivateCourse;
    private RichPopup editPrivateProject;

    public ProjectsBean() {
    }

    public void selectProject(SelectionEvent selectionEvent) {
        // Add event code here...
        invokeEL("#{bindings.Projects1.treeModel.makeCurrent}", new Class[] { SelectionEvent.class }, new Object[] {
                 selectionEvent });
        ControllerContext ccontext = ControllerContext.getInstance();

        //set the viewId - the name of the view activity to

        //go to - to display

        String viewId = "ProjectInf";

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

    public String accessPrivateProject() {
        // Add event code here...
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operation = bindings.getOperationBinding("accessProject");
        operation.getParamsMap().put("code", projectCode.getValue());
        Boolean projectFound = (Boolean) operation.execute();
        if (projectFound == true) {
            System.out.println("found");
            errorText.setValue("");
            return "PrivateProjectInf.";
        } else {
            errorText.setValue("Error : Invalid Code");
            System.out.println("not found");
            return null;
        }
        //PrivateProjectInf.
    }

    public void setAccessPrivateProjectPopUp(RichPopup accessPrivateProjectPopUp) {
        this.accessPrivateProjectPopUp = accessPrivateProjectPopUp;
    }

    public RichPopup getAccessPrivateProjectPopUp() {
        return accessPrivateProjectPopUp;
    }

    public void setProjectCode(RichInputText projectCode) {
        this.projectCode = projectCode;
    }

    public RichInputText getProjectCode() {
        return projectCode;
    }

    public String backFromPrivatePage() {
        // Add event code here...
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operation = bindings.getOperationBinding("filterPrivateProjects");
        operation.execute();
        return "SelectProject";
    }

    public void setErrorText(RichActiveOutputText errorText) {
        this.errorText = errorText;
    }

    public RichActiveOutputText getErrorText() {
        return errorText;
    }

    public void setDeletePublicProject(RichPopup deletePublicProject) {
        this.deletePublicProject = deletePublicProject;
    }

    public RichPopup getDeletePublicProject() {
        return deletePublicProject;
    }

    public String deletePublicProject() {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("Commit");
        defaultCourseOperation.execute();
        this.getDeletePublicProject().hide();
        return "SelectProject";
    }
    protected void refreshPage() {
        FacesContext fctx = FacesContext.getCurrentInstance();
        String refreshpage = fctx.getViewRoot().getViewId();
        ViewHandler ViewH = fctx.getApplication().getViewHandler();
        UIViewRoot UIV = ViewH.createView(fctx, refreshpage);
        UIV.setViewId(refreshpage);
        fctx.setViewRoot(UIV);
    }

    public void setEditPublicProject(RichPopup editPublicProject) {
        this.editPublicProject = editPublicProject;
    }

    public RichPopup getEditPublicProject() {
        return editPublicProject;
    }

    public String savePublicProject() {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("Commit");
        defaultCourseOperation.execute();
        this.getEditPublicProject().hide();
        refreshPage();
        return "SelectProject";
    }

    public void setPrivateProjectSave(RichPopup privateProjectSave) {
        this.privateProjectSave = privateProjectSave;
    }

    public RichPopup getPrivateProjectSave() {
        return privateProjectSave;
    }

    public void setDeletePrivateCourse(RichPopup deletePrivateCourse) {
        this.deletePrivateCourse = deletePrivateCourse;
    }

    public RichPopup getDeletePrivateCourse() {
        return deletePrivateCourse;
    }

    public String DeletePrivateCourse() {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("Commit");
        defaultCourseOperation.execute();
        this.getDeletePrivateCourse().hide();
        backFromPrivatePage();
        return null;
    }

    public void setEditPrivateProject(RichPopup editPrivateProject) {
        this.editPrivateProject = editPrivateProject;
    }

    public RichPopup getEditPrivateProject() {
        return editPrivateProject;
    }

    public void EditPrivateProject(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("Commit");
        defaultCourseOperation.execute();
        this.getEditPrivateProject().hide();
        refreshPage();
    }
}
