package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start( final Stage primaryStage ) {

        // Configure application components
        UserPersistence userPersistence = new UserPersistence();
        AuthenticationService authService = new AuthenticationService( userPersistence );
        Controller controller = new Controller( primaryStage, authService );
        View view = new View( controller );

        // Get started!
        primaryStage.setTitle( "My Store" );
        primaryStage.setScene( view.getLoginScene() );
        primaryStage.show();
    }

    public static void main( String[] args ) { launch( args ); }
}
