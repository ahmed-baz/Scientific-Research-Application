package oracle.adf.research.model.bc.entity;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Apr 15 01:00:24 EET 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class QualificationsEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        QualId,
        ScienceCode,
        QualTypeCode,
        UniversityId,
        QualDate,
        Notes,
        ProfessorId,
        Professors,
        Sciences,
        University,
        Professors1;
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


    public static final int QUALID = AttributesEnum.QualId.index();
    public static final int SCIENCECODE = AttributesEnum.ScienceCode.index();
    public static final int QUALTYPECODE = AttributesEnum.QualTypeCode.index();
    public static final int UNIVERSITYID = AttributesEnum.UniversityId.index();
    public static final int QUALDATE = AttributesEnum.QualDate.index();
    public static final int NOTES = AttributesEnum.Notes.index();
    public static final int PROFESSORID = AttributesEnum.ProfessorId.index();
    public static final int PROFESSORS = AttributesEnum.Professors.index();
    public static final int SCIENCES = AttributesEnum.Sciences.index();
    public static final int UNIVERSITY = AttributesEnum.University.index();
    public static final int PROFESSORS1 = AttributesEnum.Professors1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public QualificationsEOImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("oracle.adf.research.model.bc.entity.QualificationsEO");
    }


    /**
     * Gets the attribute value for QualId, using the alias name QualId.
     * @return the value of QualId
     */
    public Number getQualId() {
        return (Number) getAttributeInternal(QUALID);
    }

    /**
     * Sets <code>value</code> as the attribute value for QualId.
     * @param value value to set the QualId
     */
    public void setQualId(Number value) {
        setAttributeInternal(QUALID, value);
    }

    /**
     * Gets the attribute value for ScienceCode, using the alias name ScienceCode.
     * @return the value of ScienceCode
     */
    public Number getScienceCode() {
        return (Number) getAttributeInternal(SCIENCECODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ScienceCode.
     * @param value value to set the ScienceCode
     */
    public void setScienceCode(Number value) {
        setAttributeInternal(SCIENCECODE, value);
    }

    /**
     * Gets the attribute value for QualTypeCode, using the alias name QualTypeCode.
     * @return the value of QualTypeCode
     */
    public Number getQualTypeCode() {
        return (Number) getAttributeInternal(QUALTYPECODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for QualTypeCode.
     * @param value value to set the QualTypeCode
     */
    public void setQualTypeCode(Number value) {
        setAttributeInternal(QUALTYPECODE, value);
    }

    /**
     * Gets the attribute value for UniversityId, using the alias name UniversityId.
     * @return the value of UniversityId
     */
    public Number getUniversityId() {
        return (Number) getAttributeInternal(UNIVERSITYID);
    }

    /**
     * Sets <code>value</code> as the attribute value for UniversityId.
     * @param value value to set the UniversityId
     */
    public void setUniversityId(Number value) {
        setAttributeInternal(UNIVERSITYID, value);
    }

    /**
     * Gets the attribute value for QualDate, using the alias name QualDate.
     * @return the value of QualDate
     */
    public Date getQualDate() {
        return (Date) getAttributeInternal(QUALDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for QualDate.
     * @param value value to set the QualDate
     */
    public void setQualDate(Date value) {
        setAttributeInternal(QUALDATE, value);
    }

    /**
     * Gets the attribute value for Notes, using the alias name Notes.
     * @return the value of Notes
     */
    public String getNotes() {
        return (String) getAttributeInternal(NOTES);
    }

    /**
     * Sets <code>value</code> as the attribute value for Notes.
     * @param value value to set the Notes
     */
    public void setNotes(String value) {
        setAttributeInternal(NOTES, value);
    }

    /**
     * Gets the attribute value for ProfessorId, using the alias name ProfessorId.
     * @return the value of ProfessorId
     */
    public Number getProfessorId() {
        return (Number) getAttributeInternal(PROFESSORID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ProfessorId.
     * @param value value to set the ProfessorId
     */
    public void setProfessorId(Number value) {
        setAttributeInternal(PROFESSORID, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getProfessors() {
        return (RowIterator) getAttributeInternal(PROFESSORS);
    }

    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getSciences() {
        return (EntityImpl) getAttributeInternal(SCIENCES);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setSciences(EntityImpl value) {
        setAttributeInternal(SCIENCES, value);
    }

    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getUniversity() {
        return (EntityImpl) getAttributeInternal(UNIVERSITY);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setUniversity(EntityImpl value) {
        setAttributeInternal(UNIVERSITY, value);
    }


    /**
     * @return the associated entity ProfessorsEOImpl.
     */
    public ProfessorsEOImpl getProfessors1() {
        return (ProfessorsEOImpl) getAttributeInternal(PROFESSORS1);
    }

    /**
     * Sets <code>value</code> as the associated entity ProfessorsEOImpl.
     */
    public void setProfessors1(ProfessorsEOImpl value) {
        setAttributeInternal(PROFESSORS1, value);
    }

    /**
     * @param qualId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number qualId) {
        return new Key(new Object[] { qualId });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        SequenceImpl seq = new SequenceImpl("QUALIFICATIONS_SEQ",this.getDBTransaction());
        Number n = seq.getSequenceNumber();
        this.setQualId(n);
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
        super.doDML(operation, e);
    }
}

