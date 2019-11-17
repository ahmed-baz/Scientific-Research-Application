package oracle.adf.research.beans.professors;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

public class DownloadCVFile {
    public DownloadCVFile() {
    }

    public void downloadFile(FacesContext facesContext, OutputStream outputStream) {
        // Add event code here...
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding CvPathAttribute = (AttributeBinding) bindingContainer.get("FilePath");
        String CvPath = (String) CvPathAttribute.getInputValue();
        System.out.println(CvPath);
        File filed = new File(CvPath);
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
