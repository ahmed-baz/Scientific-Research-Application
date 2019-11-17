package oracle.adf.research.model.bc.entity;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.TransactionEvent;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Mar 13 00:21:27 EET 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class UsersEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        UserId,
        FirstName,
        LastName,
        Email,
        Gender,
        BirthDate,
        Image,
        Code,
        Active,
        RoleId,
        Password,
        Professors,
        Projects;
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


    public static final int USERID = AttributesEnum.UserId.index();
    public static final int FIRSTNAME = AttributesEnum.FirstName.index();
    public static final int LASTNAME = AttributesEnum.LastName.index();
    public static final int EMAIL = AttributesEnum.Email.index();
    public static final int GENDER = AttributesEnum.Gender.index();
    public static final int BIRTHDATE = AttributesEnum.BirthDate.index();
    public static final int IMAGE = AttributesEnum.Image.index();
    public static final int CODE = AttributesEnum.Code.index();
    public static final int ACTIVE = AttributesEnum.Active.index();
    public static final int ROLEID = AttributesEnum.RoleId.index();
    public static final int PASSWORD = AttributesEnum.Password.index();
    public static final int PROFESSORS = AttributesEnum.Professors.index();
    public static final int PROJECTS = AttributesEnum.Projects.index();

    /**
     * This is the default constructor (do not remove).
     */
    public UsersEOImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("oracle.adf.research.model.bc.entity.UsersEO");
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
     * Gets the attribute value for FirstName, using the alias name FirstName.
     * @return the value of FirstName
     */
    public String getFirstName() {
        return (String) getAttributeInternal(FIRSTNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for FirstName.
     * @param value value to set the FirstName
     */
    public void setFirstName(String value) {
        setAttributeInternal(FIRSTNAME, value);
    }

    /**
     * Gets the attribute value for LastName, using the alias name LastName.
     * @return the value of LastName
     */
    public String getLastName() {
        return (String) getAttributeInternal(LASTNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastName.
     * @param value value to set the LastName
     */
    public void setLastName(String value) {
        setAttributeInternal(LASTNAME, value);
    }

    /**
     * Gets the attribute value for Email, using the alias name Email.
     * @return the value of Email
     */
    public String getEmail() {
        return (String) getAttributeInternal(EMAIL);
    }

    /**
     * Sets <code>value</code> as the attribute value for Email.
     * @param value value to set the Email
     */
    public void setEmail(String value) {
        setAttributeInternal(EMAIL, value);
    }

    /**
     * Gets the attribute value for Gender, using the alias name Gender.
     * @return the value of Gender
     */
    public String getGender() {
        return (String) getAttributeInternal(GENDER);
    }

    /**
     * Sets <code>value</code> as the attribute value for Gender.
     * @param value value to set the Gender
     */
    public void setGender(String value) {
        setAttributeInternal(GENDER, value);
    }

    /**
     * Gets the attribute value for BirthDate, using the alias name BirthDate.
     * @return the value of BirthDate
     */
    public Date getBirthDate() {
        return (Date) getAttributeInternal(BIRTHDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for BirthDate.
     * @param value value to set the BirthDate
     */
    public void setBirthDate(Date value) {
        setAttributeInternal(BIRTHDATE, value);
    }

    /**
     * Gets the attribute value for Image, using the alias name Image.
     * @return the value of Image
     */
    public BlobDomain getImage() {
        return (BlobDomain) getAttributeInternal(IMAGE);
    }

    /**
     * Sets <code>value</code> as the attribute value for Image.
     * @param value value to set the Image
     */
    public void setImage(BlobDomain value) {
        setAttributeInternal(IMAGE, value);
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
     * Gets the attribute value for Active, using the alias name Active.
     * @return the value of Active
     */
    public String getActive() {
        return (String) getAttributeInternal(ACTIVE);
    }

    /**
     * Sets <code>value</code> as the attribute value for Active.
     * @param value value to set the Active
     */
    public void setActive(String value) {
        setAttributeInternal(ACTIVE, value);
    }

    /**
     * Gets the attribute value for RoleId, using the alias name RoleId.
     * @return the value of RoleId
     */
    public Number getRoleId() {
        return (Number) getAttributeInternal(ROLEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for RoleId.
     * @param value value to set the RoleId
     */
    public void setRoleId(Number value) {
        setAttributeInternal(ROLEID, value);
    }

    /**
     * Gets the attribute value for Password, using the alias name Password.
     * @return the value of Password
     */
    public String getPassword() {
        return (String) getAttributeInternal(PASSWORD);
    }

    /**
     * Sets <code>value</code> as the attribute value for Password.
     * @param value value to set the Password
     */
    public void setPassword(String value) {
        setAttributeInternal(PASSWORD, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getProfessors() {
        return (RowIterator) getAttributeInternal(PROFESSORS);
    }


    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getProjects() {
        return (RowIterator) getAttributeInternal(PROJECTS);
    }


    /**
     * @param userId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number userId) {
        return new Key(new Object[] { userId });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
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
        if(operation == DML_INSERT){
            SequenceImpl seq = new SequenceImpl("USERS_SEQ",this.getDBTransaction());
            Number n = seq.getSequenceNumber();
            this.setUserId(n);
        }
        
        super.doDML(operation, e);
    }
}
