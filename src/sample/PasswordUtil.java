package sample;

public class PasswordUtil {
    private static final int CAESAR_OFFSET = 4;

    public static String hashPassword( String password ) {

        StringBuilder builder = new StringBuilder( password );
        for ( int i = 0; i < builder.length(); i++ ) {
            char c = builder.charAt( i );
            builder.setCharAt( i, (char) ( c + CAESAR_OFFSET ) );
        }

        return builder.toString();
    }
}
