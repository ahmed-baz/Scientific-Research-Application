package oracle.adf.research.beans.courses;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class DefaultCourseBean {
    private RichPopup deleteCouresPopup;
    private RichPopup deleteVideoPopUp;
    private RichPopup editVideo;
    private RichPopup addVideo;
    private RichPopup deletePrivateCourse;
    private RichPopup deletePrivateVideo;
    private RichPopup editPrivateVideo;
    private RichPopup addPrivateVideo;

    public DefaultCourseBean() {
    }

    public void setDefaultCourse(ActionEvent actionEvent) {
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("defaultCoursess");
        defaultCourseOperation.execute();
    }

    public String deleteCourse() {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getDeleteCouresPopup().hide();
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("Commit");
        defaultCourseOperation.execute();
        return "Courses";
    }

    public void setDeleteCouresPopup(RichPopup deleteCouresPopup) {
        this.deleteCouresPopup = deleteCouresPopup;
    }

    public RichPopup getDeleteCouresPopup() {
        return deleteCouresPopup;
    }

    public void setDeleteVideoPopUp(RichPopup deleteVideoPopUp) {
        this.deleteVideoPopUp = deleteVideoPopUp;
    }

    public RichPopup getDeleteVideoPopUp() {
        return deleteVideoPopUp;
    }

    public String deleteVideo() {
        // Add event code here...

        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("Commit");
        defaultCourseOperation.execute();
        this.getDeleteVideoPopUp().hide();
        return null;
    }

    public void setEditVideo(RichPopup editVideo) {
        this.editVideo = editVideo;
    }

    public RichPopup getEditVideo() {
        return editVideo;
    }

    public String updateVideo() {
        // Add event code here...

        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("Commit");
        defaultCourseOperation.execute();
        this.getEditVideo().hide();
        refreshPage();
        return null;
    }

    public void setAddVideo(RichPopup addVideo) {
        this.addVideo = addVideo;
    }

    public RichPopup getAddVideo() {
        return addVideo;
    }

    public String addNewVideo() {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("Commit");
        defaultCourseOperation.execute();
        this.getAddVideo().hide();
        refreshPage();
        return null;
    }

    public String addVideo() {
        // Add event code here...CreateInsert1
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getAddVideo().show(hints);
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("CreateInsert");
        defaultCourseOperation.execute();
        //refreshPage();
        return null;
    }

    public void saveNewVideo(ActionEvent actionEvent) {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("Commit");
        defaultCourseOperation.execute();
        defaultCourseOperation = bindingContainer.getOperationBinding("defaultCoursess");
        defaultCourseOperation.execute();
        this.getAddVideo().hide();
        refreshPage();
    }

    public void setDeletePrivateCourse(RichPopup deletePrivateCourse) {
        this.deletePrivateCourse = deletePrivateCourse;
    }

    public RichPopup getDeletePrivateCourse() {
        return deletePrivateCourse;
    }

    public String deletePrivateCourse() {
        // Add event code here...
        //RichPopup.PopupHints hints = new RichPopup.PopupHints();
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("Commit");
        defaultCourseOperation.execute();
        this.getDeletePrivateCourse().hide();
        return "Courses";
    }

    public void setDeletePrivateVideo(RichPopup deletePrivateVideo) {
        this.deletePrivateVideo = deletePrivateVideo;
    }

    public RichPopup getDeletePrivateVideo() {
        return deletePrivateVideo;
    }

    public String deletePrivateVideo() {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("Commit");
        defaultCourseOperation.execute();
        this.getDeletePrivateVideo().hide();
        return null;
    }

    public void setEditPrivateVideo(RichPopup editPrivateVideo) {
        this.editPrivateVideo = editPrivateVideo;
    }

    public RichPopup getEditPrivateVideo() {
        return editPrivateVideo;
    }

    public String editPrivateVideo() {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("Commit");
        defaultCourseOperation.execute();
        this.getEditPrivateVideo().hide();
        refreshPage();
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

    public void setAddPrivateVideo(RichPopup addPrivateVideo) {
        this.addPrivateVideo = addPrivateVideo;
    }

    public RichPopup getAddPrivateVideo() {
        return addPrivateVideo;
    }

    public String addPrivateVideo() {
        // Add event code here...
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        this.getAddPrivateVideo().show(hints);
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("CreateInsert");
        defaultCourseOperation.execute();
        return null;
    }

    public String savePrivateVideo() {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding defaultCourseOperation = bindingContainer.getOperationBinding("Commit");
        defaultCourseOperation.execute();
        defaultCourseOperation = bindingContainer.getOperationBinding("defaultCoursess");
        defaultCourseOperation.execute();
        this.getAddPrivateVideo().hide();
        refreshPage();
        return null;
    }
}
