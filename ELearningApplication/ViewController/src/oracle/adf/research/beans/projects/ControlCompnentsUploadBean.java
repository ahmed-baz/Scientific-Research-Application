package oracle.adf.research.beans.projects;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.nav.RichButton;

public class ControlCompnentsUploadBean {
    private RichButton getCodeButton;

    public ControlCompnentsUploadBean() {
    }

    public void typeChanged(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("value change event : " + valueChangeEvent.getNewValue());
        if(valueChangeEvent.getNewValue().equals("Private")){
            System.out.println("Private");
            getCodeButton.setDisabled(false);
        }else{
            System.out.println("Public");
            getCodeButton.setDisabled(true);
        }
    }

    public void setGetCodeButton(RichButton getCodeButton) {
        this.getCodeButton = getCodeButton;
    }

    public RichButton getGetCodeButton() {
        return getCodeButton;
    }
}
