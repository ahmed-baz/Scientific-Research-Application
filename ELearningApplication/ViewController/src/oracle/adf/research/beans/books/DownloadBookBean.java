package oracle.adf.research.beans.books;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;


public class DownloadBookBean {
    public DownloadBookBean() {
    }

    public void downloadBook(FacesContext facesContext, OutputStream outputStream) {
        BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
        AttributeBinding bookPathAttribute = (AttributeBinding) bindingContainer.get("BookPath");
        String bookPath = (String) bookPathAttribute.getInputValue();
        System.out.println(bookPath);
        File book = new File(bookPath);
        FileInputStream fis;
        byte[] b;
        try {
            fis = new FileInputStream(book);
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
