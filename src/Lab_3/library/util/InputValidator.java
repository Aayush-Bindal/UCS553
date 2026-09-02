package Lab_3.library.util;

public final class InputValidator {
    private InputValidator() {
    }

    public static boolean isValidResourceId(int resourceId) {
        return resourceId > 0;
    }

    public static boolean isValidFineDays(int fineDays) {
        return fineDays >= 0;
    }
}
