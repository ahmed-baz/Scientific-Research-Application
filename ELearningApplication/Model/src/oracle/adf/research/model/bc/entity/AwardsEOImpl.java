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
// ---    Sun Apr 15 00:56:00 EET 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class AwardsEOImpl extends EntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        AwardId,
        AwardText,
        AwardDate,
        Notes,
        ProfessorId,
        Professors,
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


    public static final int AWARDID = AttributesEnum.AwardId.index();
    public static final int AWARDTEXT = AttributesEnum.AwardText.index();
    public static final int AWARDDATE = AttributesEnum.AwardDate.index();
    public static final int NOTES = AttributesEnum.Notes.index();
    public static final int PROFESSORID = AttributesEnum.ProfessorId.index();
    public static final int PROFESSORS = AttributesEnum.Professors.index();
    public static final int PROFESSORS1 = AttributesEnum.Professors1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public AwardsEOImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("oracle.adf.research.model.bc.entity.AwardsEO");
    }


    /**
     * Gets the attribute value for AwardId, using the alias name AwardId.
     * @return the value of AwardId
     */
    public Number getAwardId() {
        return (Number) getAttributeInternal(AWARDID);
    }

    /**
     * Sets <code>value</code> as the attribute value for AwardId.
     * @param value value to set the AwardId
     */
    public void setAwardId(Number value) {
        setAttributeInternal(AWARDID, value);
    }

    /**
     * Gets the attribute value for AwardText, using the alias name AwardText.
     * @return the value of AwardText
     */
    public String getAwardText() {
        return (String) getAttributeInternal(AWARDTEXT);
    }

    /**
     * Sets <code>value</code> as the attribute value for AwardText.
     * @param value value to set the AwardText
     */
    public void setAwardText(String value) {
        setAttributeInternal(AWARDTEXT, value);
    }

    /**
     * Gets the attribute value for AwardDate, using the alias name AwardDate.
     * @return the value of AwardDate
     */
    public Date getAwardDate() {
        return (Date) getAttributeInternal(AWARDDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for AwardDate.
     * @param value value to set the AwardDate
     */
    public void setAwardDate(Date value) {
        setAttributeInternal(AWARDDATE, value);
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
     * @param awardId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number awardId) {
        return new Key(new Object[] { awardId });
    }

    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        SequenceImpl seq = new SequenceImpl("AWARDS_SEQ",this.getDBTransaction());
        Number n = seq.getSequenceNumber();
        this.setAwardId(n);
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
