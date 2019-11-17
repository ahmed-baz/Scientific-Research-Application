package oracle.adf.research.beans.courses;


import javax.el.ELContext;

import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.context.FacesContext;

import oracle.adf.controller.ControllerContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class SelectedCourseBean {
    public SelectedCourseBean() {
    }

    public void getSelectedCourse(SelectionEvent selectionEvent) {
        invokeEL("#{bindings.PublicCourses.treeModel.makeCurrent}", new Class[] { SelectionEvent.class }, new Object[] {
                 selectionEvent });
        ControllerContext ccontext = ControllerContext.getInstance();

        //set the viewId - the name of the view activity to

        //go to - to display

        String viewId = "SelectedCourse";
        ccontext.getCurrentViewPort().setViewId(viewId);
    }

    public static Object invokeEL(String el, Class[] paramTypes, Object[] params) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ELContext elContext = facesContext.getELContext();
        ExpressionFactory expressionFactory = facesContext.getApplication().getExpressionFactory();
        MethodExpression exp = expressionFactory.createMethodExpression(elContext, el, Object.class, paramTypes);

        return exp.invoke(elContext, params);
    }
}
