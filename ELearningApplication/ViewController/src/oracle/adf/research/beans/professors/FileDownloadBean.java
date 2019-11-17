package oracle.adf.research.beans.professors;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.ViewObject;
import oracle.jbo.domain.BlobDomain;

public class FileDownloadBean {
    public FileDownloadBean() {
    }

    public void downloadFile(FacesContext facesContext, OutputStream outputStream) {
        //Read file from particular path, path bind is binding of table field that contains path
        // System.out.println(">>>>>>>>>>"+pathBind.getValue().toString());

        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding CvPathAttribute = (AttributeBinding) bindingContainer.get("FilePath");
        String CvPath = (String) CvPathAttribute.getInputValue();

        System.out.println(CvPath);
        File filed = new File(CvPath); // set path bt3 el file
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
