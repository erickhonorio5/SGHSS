package gestao.sghss.domain.validator;

import java.util.Arrays;

public class CPFValidator {

    public static boolean isValid(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) return false;

        var digits = cpf.chars().map(c -> c - '0').toArray();

        if (Arrays.stream(digits).distinct().count() == 1) return false; // evita "11111111111"

        int d1 = calculateDigit(digits, 10);
        int d2 = calculateDigit(digits, 11);

        return d1 == digits[9] && d2 == digits[10];
    }

    private static int calculateDigit(int[] digits, int weightStart) {
        int sum = 0;
        for (int i = 0; i < weightStart - 1; i++) {
            sum += digits[i] * (weightStart - i);
        }
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}

