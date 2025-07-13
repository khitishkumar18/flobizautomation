package com.automationframework.utilities;

import java.util.concurrent.ThreadLocalRandom;

public class TestUtils {

    public static String generateRandomMobileNumber() {
        // Choose starting digit: 6, 8, or 9
        int[] allowedStartDigits = {6, 8, 9};
        int firstDigit = allowedStartDigits[ThreadLocalRandom.current().nextInt(allowedStartDigits.length)];

        // Generate remaining 9 digits
        long remainingDigits = ThreadLocalRandom.current().nextLong(1000000000L); // 9 digits
        String formattedRemaining = String.format("%09d", remainingDigits); // Pad with leading 0s if needed

        return firstDigit + formattedRemaining;
    }
}
