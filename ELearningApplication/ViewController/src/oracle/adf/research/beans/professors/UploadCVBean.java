package oracle.adf.research.beans.professors;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class UploadCVBean {
    private RichPopup error1;

    public UploadCVBean() {
    }

    public void uploadCV(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("read id ");

        if (valueChangeEvent.getNewValue() != null) {
            //Get File Object from VC Event
            UploadedFile fileVal = (UploadedFile) valueChangeEvent.getNewValue();
            if (fileVal.getFilename().toUpperCase().endsWith(".PDF")) {
                String path = uploadFile(fileVal);
                /*BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
                AttributeBinding userCV = (AttributeBinding) bindingContainer.get("CvId");
                System.out.println("CV ID From CV Iterator : " + userCV.getInputValue());
                AttributeBinding userCV1 = (AttributeBinding) bindingContainer.get("CvId1");
                System.out.println("CV ID From Professor Iterator : " + userCV.getInputValue());
                userCV1.setInputValue(userCV.getInputValue());*/
            }
        } else {
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            this.getError1().show(hints);
        }
    }

    private String uploadFile(UploadedFile file) {
        UploadedFile myfile = file;
        String path = null;
        if (myfile == null) {
        } else {
            // All uploaded files will be stored in below path
            //path = "/home/uwagdy/jdeveloper/mywork/ELearningApplication/CVFiles/" + myfile.getFilename();
            path = "D:\\FCI\\Project\\ELearningApplication\\CVFiles\\" + myfile.getFilename();
            BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
            AttributeBinding TrainerImagePathAttribute = (AttributeBinding) bindingContainer.get("FilePath");
            TrainerImagePathAttribute.setInputValue(path);
            InputStream inputStream = null;
            try {
                FileOutputStream out = new FileOutputStream(path);
                inputStream = myfile.getInputStream();
                byte[] buffer = new byte[8192];
                int bytesRead = 0;
                while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                out.flush();
                out.close();
            } catch (Exception ex) {
                // handle exception
                ex.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
        return path;
    }

    public void setError1(RichPopup error1) {
        this.error1 = error1;
    }

    public RichPopup getError1() {
        return error1;
    }
}
