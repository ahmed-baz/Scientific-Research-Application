package oracle.adf.research.beans.courses;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class EditVideoBean {
    public EditVideoBean() {
    }

    public void editVideo(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        try {
            System.out.println("read id ");

            if (valueChangeEvent.getNewValue() != null) {
                UploadedFile fileVal = (UploadedFile) valueChangeEvent.getNewValue();
                String path = uploadFile(fileVal);
                System.out.println(fileVal.getContentType());
                System.out.println("before update in valueChange");
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }
    private String uploadFile(UploadedFile file) {

        UploadedFile myfile = file;
        String path = null;
        if (myfile == null) {

        } else {
            try {
                // All uploaded files will be stored in below path
                path =
                    "/home/uwagdy/jdeveloper/mywork/ELearningApplication/ViewController/public_html/resources/courses/" +
                    myfile.getFilename();
                System.out.println("File name");
                BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
                AttributeBinding TrainerImagePathAttribute = (AttributeBinding) bindingContainer.get("VideoPath");
                TrainerImagePathAttribute.setInputValue(path);
                TrainerImagePathAttribute = (AttributeBinding) bindingContainer.get("VideoUpload");
                TrainerImagePathAttribute.setInputValue("/resources/courses/" + myfile.getFilename());
                //VIDEO_UPLOAD
                System.out.println(" Set InputValue");

                InputStream inputStream = null;
                try {
                    System.out.println("File Output");
                    FileOutputStream out = new FileOutputStream(path);
                    System.out.println("Set input");
                    inputStream = myfile.getInputStream();
                    System.out.println("Buffer Definition");
                    byte[] buffer = new byte[8192];
                    System.out.println("butes Read");
                    int bytesRead = 0;
                    System.out.println("While loop");
                    while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                        System.out.println("loop");
                        out.write(buffer, 0, bytesRead);
                    }
                    System.out.println("flush");
                    out.flush();
                    System.out.println("close");
                    out.close();
                } catch (Exception ex) {
                    // handle exception
                    System.out.println("Error");
                    ex.printStackTrace();
                } finally {
                    try {

                        inputStream.close();
                    } catch (IOException e) {
                    }
                }

            } catch (Exception e) {
                System.out.println("" + e);
            }
        }
        //Returns the path where file is stored
        return path;
    }
}
