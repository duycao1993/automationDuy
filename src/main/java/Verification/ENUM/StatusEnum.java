package Verification.ENUM;

public enum StatusEnum {
    Passed("Passed"),
    Failed("Failed");

    private final String value;

    StatusEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
