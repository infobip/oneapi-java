package oneapi.model;

/**
 * Represents language code. Currently supported languages: Spanish, Portuguese and Turkish.
 * @author nmenkovic
 */
public enum LanguageCode {

    SP("SP"), //Spanish
    PT("PT"), // Portuguese
    TR("TR"); // Turkish

    private String value;

    LanguageCode(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LanguageCode {" + value + "}";
    }
}
