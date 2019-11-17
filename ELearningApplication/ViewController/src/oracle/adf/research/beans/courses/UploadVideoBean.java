package oracle.adf.research.beans.courses;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class UploadVideoBean {
    private RichPopup error1;

    public UploadVideoBean() {
    }

    public void uploadVideos(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        try {
            System.out.println("read id ");

            if (valueChangeEvent.getNewValue() != null) {
                //Get File Object from VC Event
                UploadedFile fileVal = (UploadedFile) valueChangeEvent.getNewValue();
                if (fileVal.getFilename().toUpperCase().endsWith(".MP4")) {
                    //Upload File to path- Return actual server path
                    String path = uploadVideo(fileVal);
                    System.out.println(fileVal.getContentType());
                    System.out.println("before update in valueChange");
                } else {
                    RichPopup.PopupHints hints = new RichPopup.PopupHints();
                    this.getError1().show(hints);
                }
            }
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }


    private String uploadVideo(UploadedFile file) {

        UploadedFile myfile = file;
        String path = null;
        if (myfile == null) {

        } else {
            try {
                // All uploaded files will be stored in below path
                /*  path =
                    "/home/uwagdy/jdeveloper/mywork/ELearningApplication/ViewController/public_html/resources/courses/" +
                    myfile.getFilename(); */
                path =
                    "D:\\FCI\\Project\\ELearningApplication\\ViewController\\public_html\\resources\\Courses\\" +
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

    public void setError1(RichPopup error1) {
        this.error1 = error1;
    }

    public RichPopup getError1() {
        return error1;
    }
}
