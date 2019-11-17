package oracle.adf.research.beans.courses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

public class DownloadPrivateCourseBean {
    public DownloadPrivateCourseBean() {
    }

    public void downloadPrivateVideo(FacesContext facesContext, OutputStream outputStream) {

        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding videoPathAttribute = (AttributeBinding) bindingContainer.get("VideoPath");
        String videoPath = (String) videoPathAttribute.getInputValue();

        System.out.println(videoPath);
        File filed = new File(videoPath);
        FileInputStream fis;
        byte[] b;
        try {
            fis = new FileInputStream(filed);
            int n;
            while ((n = fis.available()) > 0) {
                b = new byte[n];
                int result = fis.read(b);
                outputStream.write(b, 0, b.length);
                if (result == -1)
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.flush();
        } catch (IOException e) {
        }


        }
}
