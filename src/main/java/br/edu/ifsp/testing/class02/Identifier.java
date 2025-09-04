package br.edu.ifsp.testing.class02;

public class Identifier {

    /**
     * Verifica se um identificador é válido.
     * Regras:
     * - Deve começar com uma letra.
     * - Pode conter apenas letras ou dígitos.
     * - Tamanho entre 1 e 6 caracteres.
     *
     * @param id identificador a ser validado
     * @return true se válido, false caso contrário
     */
    public static boolean isValid(String id) {
        if (id == null) {
            return false;
        }

        int length = id.length();
        if (length < 1 || length > 6) {
            return false;
        }

        // Primeiro caractere deve ser letra
        if (!Character.isLetter(id.charAt(0))) {
            return false;
        }

        // Todos os demais devem ser letras ou dígitos
        for (int i = 1; i < length; i++) {
            char c = id.charAt(i);
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }

        return true;
    }
}