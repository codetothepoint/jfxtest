package sample;

import java.io.IOException;
import java.util.Map;

public class AuthenticationService {

    UserPersistence userPersistence;
    Map<String, User> users;

    public AuthenticationService( UserPersistence persistence ) {
        userPersistence = persistence;
        try {
            users = userPersistence.loadUsers();
        } catch ( IOException e ) {
            throw new RuntimeException( "Unable to load user data", e );
        }
    }

    public Map<String, User> getAllUsers() {
        return users;
    }

    public User authenticate( String username, String password ) {

        User user = users.get( username );
        if ( user == null ) return null;

        if ( user.getPasswordHash().equals( PasswordUtil.hashPassword( password ) ) ) {
            return user;
        }

        return null;
    }
}
