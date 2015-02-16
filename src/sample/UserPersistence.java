package sample;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class UserPersistence {

    public Map<String, User> loadUsers() throws IOException {
        Map<String, User> users = new HashMap<>();

        InputStream in = getClass().getClassLoader().getResourceAsStream( "users.dat" );
        BufferedReader reader = new BufferedReader( new InputStreamReader( in ) );

        for ( String line = reader.readLine(); line != null; line = reader.readLine() ) {
            StringTokenizer tokenizer = new StringTokenizer( line, "|" );
            User u = new User();
            u.setId( Integer.valueOf( tokenizer.nextToken() ) );
            u.setUsername( tokenizer.nextToken() );
            u.setPasswordHash( PasswordUtil.hashPassword( tokenizer.nextToken() ) );
            u.setFirstName( tokenizer.nextToken() );
            u.setLastName( tokenizer.nextToken() );
            users.put( u.getUsername(), u );
        }

        return users;
    }
}
