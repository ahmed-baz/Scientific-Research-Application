package oracle.adf.research.beans.books;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;

import oracle.jbo.domain.BlobDomain;

import org.apache.myfaces.trinidad.model.UploadedFile;


public class BookImagesBean {
    public BookImagesBean() {
    }

    public void uploadBookCover(ValueChangeEvent valueChangeEvent) {
        UploadedFile bookCover = (UploadedFile) valueChangeEvent.getNewValue();
        BookCover(bookCover);
    }

    public void uploadBookScreenshot1(ValueChangeEvent valueChangeEvent) {
        UploadedFile bookImage1 = (UploadedFile) valueChangeEvent.getNewValue();
        BookImage1(bookImage1);
    }

    public void uploadBookScreenshot2(ValueChangeEvent valueChangeEvent) {
        UploadedFile bookImage2 = (UploadedFile) valueChangeEvent.getNewValue();
        BookImage2(bookImage2);
    }

    public String BookCover(UploadedFile bookCover) {
        if (bookCover != null) {

            if (bookCover.getFilename().toUpperCase().endsWith(".JPG") ||
                bookCover.getFilename().toUpperCase().endsWith(".PNG")) {

                //Access Attribute for update
                BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
                AttributeBinding attributeName = (AttributeBinding) bindingContainer.get("BookImage");
                attributeName.setInputValue(createBlobDomain(bookCover));

            } else {
                FacesMessage mesg =
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error !", "Error while uploading image file !");
                FacesContext.getCurrentInstance().addMessage(null, mesg);

            }
        }
        return "Library-BTFFrag";
    }


    private BlobDomain createBlobDomain(UploadedFile file) {

        InputStream in = null;
        BlobDomain blobDomain = null;
        OutputStream out = null;

        try {
            in = file.getInputStream();

            blobDomain = new BlobDomain();
            out = blobDomain.getBinaryOutputStream();
            byte[] buffer = new byte[8192];
            int bytesRead = 0;

            while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.fillInStackTrace();
        }

        return blobDomain;
    }

    public String BookImage2(UploadedFile bookImage2) {
        if (bookImage2 != null) {

            if (bookImage2.getFilename().toUpperCase().endsWith(".JPG") ||
                bookImage2.getFilename().toUpperCase().endsWith(".PNG")) {

                //Access Attribute for update
                BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
                AttributeBinding attributeName = (AttributeBinding) bindingContainer.get("Screenshot2");
                attributeName.setInputValue(createBlobDomain(bookImage2));

            } else {
                FacesMessage mesg =
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error !", "Error while uploading image file !");
                FacesContext.getCurrentInstance().addMessage(null, mesg);

            }
        }
        return "Library-BTFFrag";
    }

    public String BookImage1(UploadedFile bookImage2) {
        if (bookImage2 != null) {

            if (bookImage2.getFilename().toUpperCase().endsWith(".JPG") ||
                bookImage2.getFilename().toUpperCase().endsWith(".PNG")) {

                //Access Attribute for update
                BindingContainer bindingContainer = BindingContext.getCurrent().getCurrentBindingsEntry();
                AttributeBinding attributeName = (AttributeBinding) bindingContainer.get("Screenshot1");
                attributeName.setInputValue(createBlobDomain(bookImage2));

            } else {
                FacesMessage mesg =
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error !", "Error while uploading image file !");
                FacesContext.getCurrentInstance().addMessage(null, mesg);

            }
        }
        return "Library-BTFFrag";
    }

}
