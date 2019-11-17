package oracle.adf.research.beans.courses;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.nav.RichButton;

public class CourseStatusTypeBean {
    private String status;
    private RichButton getCodeButton;

    public CourseStatusTypeBean() {
    }

    public void statusType(ValueChangeEvent valueChangeEvent) {
        status = (String) valueChangeEvent.getNewValue();
        if (status.compareTo("Private") == 0) {
            getCodeButton.setDisabled(false);
        } else {
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
