package oracle.adf.research.beans.user;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import weblogic.servlet.security.ServletAuthentication;


public class LogoutBean {
    public LogoutBean() {
    }

    public String onLogout() {
        // Add event code here...

        FacesContext fctx = FacesContext.getCurrentInstance();

        ExternalContext ectx = fctx.getExternalContext();

        String url =
            ectx.getRequestContextPath() +
            "/adfAuthentication?logout=true&end_url=http://127.0.0.1:7101/ELearningApplication-ViewController-context-root/faces/Login";
        HttpSession session = (HttpSession) ectx.getSession(false);
        session.invalidate();

        HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
        ServletAuthentication.logout(request);
        ServletAuthentication.invalidateAll(request);
        ServletAuthentication.killCookie(request);
        fctx.responseComplete();
        return "Logout";
    }

    public void onLogout(ActionEvent actionEvent) {
        // Add event code here...
        FacesContext fctx = FacesContext.getCurrentInstance();

        ExternalContext ectx = fctx.getExternalContext();
        HttpSession session = (HttpSession) ectx.getSession(false);
        session.invalidate();
        HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
        ServletAuthentication.logout(request);
        ServletAuthentication.invalidateAll(request);
        ServletAuthentication.killCookie(request);
        try {
            ectx.redirect("http://127.0.0.1:7101/ELearningApplication-ViewController-context-root/faces/Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
        fctx.responseComplete();
    }
}
