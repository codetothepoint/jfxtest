package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;

public class View {

    private static final int VIEW_WIDTH = 300;
    private static final int VIEW_HEIGHT = 250;

    private Controller controller;
    private Scene loginScene;

    public View( Controller c ) {
        controller = c;
    }

    public Scene getLoginScene(  ) {
        if ( loginScene != null ) return loginScene;

        GridPane grid = getStandardGrid();

        Text title = new Text( "Welcome" );
        title.setFont( Font.font( "Tahoma", FontWeight.NORMAL, 20 ) );
        grid.add( title, 0, 0, 2, 1 );

        Label userName = new Label( "User Name:" );
        grid.add( userName, 0, 1 );

        final TextField userTextField = new TextField();
        grid.add( userTextField, 1, 1 );

        Label pw = new Label( "Password:" );
        grid.add( pw, 0, 2 );

        final PasswordField pwBox = new PasswordField();
        grid.add( pwBox, 1, 2 );

        Button btn = new Button( "Sign in" );
        HBox hbBtn = new HBox( 10 );
        hbBtn.setAlignment( Pos.BOTTOM_RIGHT );
        hbBtn.getChildren().add( btn );
        grid.add( hbBtn, 1, 4 );

        btn.setOnAction( new EventHandler<ActionEvent>() {

            @Override
            public void handle( ActionEvent e ) {
                controller.handleLogin( userTextField.getText(), pwBox.getText() );
            }
        } );

        loginScene = new Scene( grid, VIEW_WIDTH, VIEW_HEIGHT );
        return loginScene;
    }

    public Scene getWelcomeScene( User user, Map<String, User> allUsers ) {
        BorderPane root = new BorderPane();
        root.setTop( new Label( MessageFormat.format( "Welcome, {0} {1}!", user.getFirstName(), user.getLastName() ) ) );
        root.setCenter( getUserGrid( allUsers.values() ) );
        return new Scene( root, VIEW_WIDTH, VIEW_HEIGHT );
    }

    private Pane getUserGrid( Collection<User> users ) {
        VBox rows = new VBox();

        for ( User user : users ) {
            rows.getChildren().add( getProductRow( user ) );
        }

        return rows;
    }

    private Pane getProductRow( User user ) {
        HBox row = new HBox();

        row.getChildren().add( new Label( String.valueOf( user.getId() ) ) );
        row.getChildren().add( new Label( user.getFirstName() ) );
        row.getChildren().add( new Label( user.getLastName() ) );

        return row;
    }

    private GridPane getStandardGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment( Pos.CENTER );
        grid.setHgap( 10 );
        grid.setVgap( 10 );
        grid.setPadding( new Insets( 25, 25, 25, 25 ) );
        return grid;
    }
}
