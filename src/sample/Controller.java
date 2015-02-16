package sample;

import javafx.stage.Stage;

import java.util.Map;

public class Controller {

    Stage stage;
    View view;
    AuthenticationService authService;

    public Controller( Stage s, AuthenticationService service ) {
        stage = s;
        authService = service;
        view = new View( this );
    }

    public void handleLogin( String username, String password ) {
        User user = authService.authenticate( username, password );

        // If there was an error authenticating, go back to login screen.
        if ( user == null ) {
            stage.setScene( view.getLoginScene() );
            return;
        }

        Map<String, User> allUsers = authService.getAllUsers();

        stage.setScene( view.getWelcomeScene( user, allUsers ) );
    }
}
