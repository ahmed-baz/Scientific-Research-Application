package oracle.adf.research.model.bc.entity;

import oracle.adf.research.model.bc.classes.User;

import oracle.adf.share.ADFContext;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Apr 23 15:12:01 EET 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class BookEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        BookId,
        BookName,
        BookImage,
        Screenshot1,
        Screenshot2,
        CategoryId,
        UserId,
        AutherName,
        BookDate,
        BookPath,
        Book,
        Status,
        Code,
        Category;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int BOOKID = AttributesEnum.BookId.index();
    public static final int BOOKNAME = AttributesEnum.BookName.index();
    public static final int BOOKIMAGE = AttributesEnum.BookImage.index();
    public static final int SCREENSHOT1 = AttributesEnum.Screenshot1.index();
    public static final int SCREENSHOT2 = AttributesEnum.Screenshot2.index();
    public static final int CATEGORYID = AttributesEnum.CategoryId.index();
    public static final int USERID = AttributesEnum.UserId.index();
    public static final int AUTHERNAME = AttributesEnum.AutherName.index();
    public static final int BOOKDATE = AttributesEnum.BookDate.index();
    public static final int BOOKPATH = AttributesEnum.BookPath.index();
    public static final int BOOK = AttributesEnum.Book.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int CODE = AttributesEnum.Code.index();
    public static final int CATEGORY = AttributesEnum.Category.index();

    /**
     * This is the default constructor (do not remove).
     */
    public BookEOImpl() {
    }

    /**
     * Gets the attribute value for BookId, using the alias name BookId.
     * @return the value of BookId
     */
    public Number getBookId() {
        return (Number) getAttributeInternal(BOOKID);
    }

    /**
     * Sets <code>value</code> as the attribute value for BookId.
     * @param value value to set the BookId
     */
    public void setBookId(Number value) {
        setAttributeInternal(BOOKID, value);
    }

    /**
     * Gets the attribute value for BookName, using the alias name BookName.
     * @return the value of BookName
     */
    public String getBookName() {
        return (String) getAttributeInternal(BOOKNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for BookName.
     * @param value value to set the BookName
     */
    public void setBookName(String value) {
        setAttributeInternal(BOOKNAME, value);
    }

    /**
     * Gets the attribute value for BookImage, using the alias name BookImage.
     * @return the value of BookImage
     */
    public BlobDomain getBookImage() {
        return (BlobDomain) getAttributeInternal(BOOKIMAGE);
    }

    /**
     * Sets <code>value</code> as the attribute value for BookImage.
     * @param value value to set the BookImage
     */
    public void setBookImage(BlobDomain value) {
        setAttributeInternal(BOOKIMAGE, value);
    }

    /**
     * Gets the attribute value for Screenshot1, using the alias name Screenshot1.
     * @return the value of Screenshot1
     */
    public BlobDomain getScreenshot1() {
        return (BlobDomain) getAttributeInternal(SCREENSHOT1);
    }

    /**
     * Sets <code>value</code> as the attribute value for Screenshot1.
     * @param value value to set the Screenshot1
     */
    public void setScreenshot1(BlobDomain value) {
        setAttributeInternal(SCREENSHOT1, value);
    }

    /**
     * Gets the attribute value for Screenshot2, using the alias name Screenshot2.
     * @return the value of Screenshot2
     */
    public BlobDomain getScreenshot2() {
        return (BlobDomain) getAttributeInternal(SCREENSHOT2);
    }

    /**
     * Sets <code>value</code> as the attribute value for Screenshot2.
     * @param value value to set the Screenshot2
     */
    public void setScreenshot2(BlobDomain value) {
        setAttributeInternal(SCREENSHOT2, value);
    }

    /**
     * Gets the attribute value for CategoryId, using the alias name CategoryId.
     * @return the value of CategoryId
     */
    public Number getCategoryId() {
        return (Number) getAttributeInternal(CATEGORYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for CategoryId.
     * @param value value to set the CategoryId
     */
    public void setCategoryId(Number value) {
        setAttributeInternal(CATEGORYID, value);
    }

    /**
     * Gets the attribute value for UserId, using the alias name UserId.
     * @return the value of UserId
     */
    public Number getUserId() {
        return (Number) getAttributeInternal(USERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for UserId.
     * @param value value to set the UserId
     */
    public void setUserId(Number value) {
        setAttributeInternal(USERID, value);
    }

    /**
     * Gets the attribute value for AutherName, using the alias name AutherName.
     * @return the value of AutherName
     */
    public String getAutherName() {
        return (String) getAttributeInternal(AUTHERNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for AutherName.
     * @param value value to set the AutherName
     */
    public void setAutherName(String value) {
        setAttributeInternal(AUTHERNAME, value);
    }

    /**
     * Gets the attribute value for BookDate, using the alias name BookDate.
     * @return the value of BookDate
     */
    public Date getBookDate() {
        return (Date) getAttributeInternal(BOOKDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for BookDate.
     * @param value value to set the BookDate
     */
    public void setBookDate(Date value) {
        setAttributeInternal(BOOKDATE, value);
    }

    /**
     * Gets the attribute value for BookPath, using the alias name BookPath.
     * @return the value of BookPath
     */
    public String getBookPath() {
        return (String) getAttributeInternal(BOOKPATH);
    }

    /**
     * Sets <code>value</code> as the attribute value for BookPath.
     * @param value value to set the BookPath
     */
    public void setBookPath(String value) {
        setAttributeInternal(BOOKPATH, value);
    }

    /**
     * Gets the attribute value for Book, using the alias name Book.
     * @return the value of Book
     */
    public BlobDomain getBook() {
        return (BlobDomain) getAttributeInternal(BOOK);
    }

    /**
     * Sets <code>value</code> as the attribute value for Book.
     * @param value value to set the Book
     */
    public void setBook(BlobDomain value) {
        setAttributeInternal(BOOK, value);
    }

    /**
     * Gets the attribute value for Status, using the alias name Status.
     * @return the value of Status
     */
    public String getStatus() {
        return (String) getAttributeInternal(STATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for Status.
     * @param value value to set the Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**
     * Gets the attribute value for Code, using the alias name Code.
     * @return the value of Code
     */
    public String getCode() {
        return (String) getAttributeInternal(CODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for Code.
     * @param value value to set the Code
     */
    public void setCode(String value) {
        setAttributeInternal(CODE, value);
    }

    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getCategory() {
        return (EntityImpl) getAttributeInternal(CATEGORY);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setCategory(EntityImpl value) {
        setAttributeInternal(CATEGORY, value);
    }

    /**
     * @param bookId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number bookId) {
        return new Key(new Object[] { bookId });
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("oracle.adf.research.model.bc.entity.BookEO");
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        User user = (User) ADFContext.getCurrent().getSessionScope().get("USERS");
        this.setUserId(user.getUserId());
    }

    /**
     * Add entity remove logic in this method.
     */
    public void remove() {
        super.remove();
    }

    /**
     * Add locking logic here.
     */
    public void lock() {
        super.lock();
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        if (operation == DML_INSERT) {
            SequenceImpl seq = new SequenceImpl("BOOK_SEQ", this.getDBTransaction());
            Number book_id = seq.getSequenceNumber();
            this.setBookId(book_id);
        }
        super.doDML(operation, e);
    }
}
