package oneapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * Represents language for use with National Language Identifier (NLI) when sending messages in specific languages. Used to determine coding tables.
 * @author nmenkovic
 */

public class Language {

    // Language code: Spanish - "LanguageCode.SP", Portuguese - "LanguageCode.PT", Turkish - "LanguageCode.TR"
    private LanguageCode languageCode = null;
    // Use locking shift table for this language (GSM7)
    private boolean useLockingShift = false;
    // Use single shift table for this language (GSM7)
    private boolean useSingleShift = false;

    /**
     * Initialize Language with languageCode as only parameter.
     * @param languageCode
     */
    public Language(LanguageCode languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * Initialize Language with languageCode, useSingleShift and useLockingShift as parameters.
     *
     * @param languageCode
     * @param useSingleShift
     * @param useLockingShift
     */
    public Language(LanguageCode languageCode, boolean useSingleShift, boolean useLockingShift) {
        this.languageCode = languageCode;
        this.useLockingShift = useLockingShift;
        this.useSingleShift = useSingleShift;
    }

    /**
     * Get language code
     *
     * @return LanguageCode
     */
    public LanguageCode getLanguageCode() {
        return languageCode;
    }

    /**
     * Set language code
     *
     * @param languageCode - (LanguageCode.SP, LanguageCode.PT, LanguageCode.TR)
     */
    public void setLanguageCode(LanguageCode languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * Get use locking shift
     *
     * @return boolean
     */
    public boolean isUseLockingShift() {
        return useLockingShift;
    }

    /**
     * Set use locking shift
     *
     * @param useLockingShift
     */
    public void setUseLockingShift(boolean useLockingShift) {
        this.useLockingShift = useLockingShift;
    }

    /**
     * Get use single shift
     *
     * @return boolean
     */
    public boolean isUseSingleShift() {
        return useSingleShift;
    }

    /**
     * Set use single shift
     *
     * @param useSingleShift
     */
    public void setUseSingleShift(boolean useSingleShift) {
        this.useSingleShift = useSingleShift;
    }

    @Override
    public String toString() {
        return "Language {languageCode=" + languageCode.toString()
                + ", useLockingShift=" + useLockingShift+ ", useSingleShift="+useSingleShift+"}";
    }
}
