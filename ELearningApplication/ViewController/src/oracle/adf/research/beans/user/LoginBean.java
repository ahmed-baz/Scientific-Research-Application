package oracle.adf.research.beans.user;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.research.model.bc.classes.User;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.nav.RichButton;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.JboException;

public class LoginBean {
    private RichInputText userEmail;
    private RichInputText userPassword;
    private User user;
    private RichPopup popUp;
    private RichPopup emptyPopUp;

    public LoginBean() {
    }


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUserEmail(RichInputText userEmail) {
        this.userEmail = userEmail;
    }

    public RichInputText getUserEmail() {
        return userEmail;
    }

    public void setUserPassword(RichInputText userPassword) {
        this.userPassword = userPassword;
    }

    public RichInputText getUserPassword() {
        return userPassword;
    }

    public String checkUserLogin() {
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding loginOperation = bindingContainer.getOperationBinding("checkLogin");

        loginOperation.getParamsMap().put("email", userEmail.getValue());
        loginOperation.getParamsMap().put("password", userPassword.getValue());

        Boolean checkLogin = (Boolean) loginOperation.execute();


        if (loginOperation.getErrors().isEmpty())
            if (checkLogin) {

                user = (User) ADFContext.getCurrent().getSessionScope().get("USERS");
                System.out.println("User Data on Session Bean : User Name = " + user.getActive());
                if (user.getActive().equals("N")) {
                    RichPopup.PopupHints hints = new RichPopup.PopupHints();
                    this.getPopUp().show(hints);
                } else {
                    return "Home-BTF";
                }

            } else {
                RichPopup.PopupHints hints = new RichPopup.PopupHints();
                this.getEmptyPopUp().show(hints);
                System.out.println("Error: "+loginOperation.getErrors());
            }
        return null;
    }


    public void setPopUp(RichPopup popUp) {
        this.popUp = popUp;
    }

    public RichPopup getPopUp() {
        return popUp;
    }

    public void setEmptyPopUp(RichPopup emptyPopUp) {
        this.emptyPopUp = emptyPopUp;
    }

    public RichPopup getEmptyPopUp() {
        return emptyPopUp;
    }
}
