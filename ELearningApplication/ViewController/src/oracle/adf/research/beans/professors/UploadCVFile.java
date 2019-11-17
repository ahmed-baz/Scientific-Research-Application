package oracle.adf.research.beans.professors;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.util.ResetUtils;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.Key;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class UploadCVFile {
    public UploadCVFile() {
    }

    /*public void CVuploadFileVCE(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
    }*/
    public void CVuploadFileVCE(ValueChangeEvent vce) {


        if (vce.getNewValue() != null) {
            //Get File Object from VC Event

            UploadedFile fileVal = (UploadedFile) vce.getNewValue();
            if (fileVal.getFilename().toUpperCase().endsWith(".PDF")) {
                //Upload File to path- Return actual server path
                String path = uploadFile(fileVal);
                System.out.println(fileVal.getContentType());
            }
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
            System.out.println(" Set InputValue");


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
        //Returns the path where file is stored
        return path;
    }
}
