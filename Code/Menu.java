package menu_test;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.*;

public class Menu extends Application {

	Stage window;
	Scene sceneMenu, sceneServerlist, sceneHelp, sceneLogin, sceneLobby, sceneErrorServerfull, sceneErrorDisconnect, sceneRanking;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Window
		window = primaryStage;
		
		//Begin Menu Scene
		
			//Label Bomberman
			Text labelBombermanMenu = new Text ("Bomberman");
			labelBombermanMenu.setId("labelBomberman");
		
			//VBox
			VBox buttonBoxMenu = new VBox(0);
			buttonBoxMenu.setPrefWidth(350);
		
			//Play Button
			Button buttonPlay = new Button("Play");
			buttonPlay.setOnAction(e -> window.setScene(sceneServerlist));
		
			//Help Button
			Button buttonHelp = new Button("Help");
			buttonHelp.setOnAction(e -> window.setScene(sceneHelp));
		
			//Exit Button
			Button buttonExit = new Button("Exit");
			buttonExit.setOnAction(e -> window.close());
		
			//Button width
			buttonPlay.setMinWidth(buttonBoxMenu.getPrefWidth());
			buttonHelp.setMinWidth(buttonBoxMenu.getPrefWidth());
			buttonExit.setMinWidth(buttonBoxMenu.getPrefWidth());
		
			//Button textsize
			buttonPlay.setStyle("-fx-font-size: 37");
			buttonHelp.setStyle("-fx-font-size: 37");
			buttonExit.setStyle("-fx-font-size: 37");
		
			//Layout
			VBox layoutMenu = new VBox(22);
			layoutMenu.setPadding(new Insets(90));
			layoutMenu.setAlignment(Pos.TOP_CENTER);
			layoutMenu.getChildren().addAll(labelBombermanMenu, buttonPlay, buttonHelp, buttonExit);
			sceneMenu = new Scene(layoutMenu, 1280, 720);
		
		//End Menu Scene
		
		//Begin Serverlist Scene
			
			//Label Bomberman
			Text labelBombermanServerlist = new Text ("Bomberman");
			labelBombermanServerlist.setId("labelBomberman");
		
			//VBox
			VBox buttonBoxServerlist = new VBox();
			buttonBoxServerlist.setPrefWidth(422);
		
			//Back Button
			Button buttonServerlistToMenu = new Button("Back");
			buttonServerlistToMenu.setOnAction(e -> window.setScene(sceneMenu));
			buttonServerlistToMenu.setMinWidth(200);
		
			//Server Buttons
			Button buttonServer1 = new Button("Server 1");
			buttonServer1.setOnAction(e -> window.setScene(sceneLogin));
			Button buttonServer2 = new Button("Server 2");
			buttonServer2.setOnAction(e -> window.setScene(sceneErrorDisconnect));
			Button buttonServer3 = new Button("Server 3");
			buttonServer3.setOnAction(e -> window.setScene(sceneErrorServerfull));
		
			//Button width
			buttonServer1.setMinWidth(buttonBoxServerlist.getPrefWidth());
			buttonServer2.setMinWidth(buttonBoxServerlist.getPrefWidth());
			buttonServer3.setMinWidth(buttonBoxServerlist.getPrefWidth());
			
			//Button fontsize
			buttonServer1.setStyle("-fx-font-size: 25");
			buttonServer2.setStyle("-fx-font-size: 25");
			buttonServer3.setStyle("-fx-font-size: 25");
			buttonServerlistToMenu.setStyle("-fx-font-size: 25");
		
			//Layout
			VBox layoutServerlist = new VBox(22);
			layoutServerlist.setPadding(new Insets(90));
			layoutServerlist.setAlignment(Pos.TOP_CENTER);
			layoutServerlist.getChildren().addAll(labelBombermanServerlist, buttonServer1, buttonServer2, buttonServer3, buttonServerlistToMenu);
			sceneServerlist = new Scene(layoutServerlist, 1280, 720);
		
		//End Serverlist Scene
			
		//Begin Help Scene
			
			//Label Bomberman
			Text labelBombermanHelp = new Text ("Bomberman");
			labelBombermanHelp.setId("labelBomberman");
			
			//Back Button
			Button buttonHelpToMenu = new Button("Back");
			buttonHelpToMenu.setOnAction(e -> window.setScene(sceneMenu));
			buttonHelpToMenu.setMinWidth(200);
		
			//Button fontsize
			buttonHelpToMenu.setStyle("-fx-font-size: 25");
		
			//keybinds Background
			Rectangle keybindBg = new Rectangle(100, 300, 600, 200);
			keybindBg.setArcWidth(20);
			keybindBg.setArcHeight(20);
			keybindBg.setId("rectangleBackground");
			
			//keybinds Text
			Text keybindText = new Text ("move up				W\n"
									   + "move down			S\n"
									   + "move right			D\n"
									   + "move left				A\n"
									   + "place Bomb			SPACE\n");
			keybindText.setId("text");
			
			//Layout
			VBox layoutHelp = new VBox(22);
			layoutHelp.setPadding(new Insets(90));
			layoutHelp.setAlignment(Pos.TOP_CENTER);
			sceneHelp = new Scene(layoutHelp, 1280, 720);
			StackPane paneHelpBg = new StackPane();
			paneHelpBg.getChildren().addAll(keybindBg, keybindText);
			layoutHelp.getChildren().addAll(labelBombermanHelp, paneHelpBg, buttonHelpToMenu);
			
			
		//End Help Scene
			
		//Begin Login Scene
			
			//Label Bomberman
			Text labelBombermanLogin = new Text ("Bomberman");
			labelBombermanLogin.setId("labelBomberman");
			
			//Textfield
			TextField textFieldLogin = new TextField();
			textFieldLogin.setPromptText("choose an username...");
			textFieldLogin.setId("textFieldLogin");
			textFieldLogin.setMaxWidth(422);
			
			//Back Button
			Button buttonLoginToServerlist = new Button("Back");
			buttonLoginToServerlist.setOnAction(e -> window.setScene(sceneServerlist));
			buttonLoginToServerlist.setMinWidth(200);
			
			//OK Button
			Button buttonLoginOk = new Button("OK");
			buttonLoginOk.setOnAction(e -> window.setScene(sceneLobby));
			buttonLoginOk.setMinWidth(200);
			
			//Button Fontsize
			buttonLoginToServerlist.setStyle("-fx-font-size: 25");
			buttonLoginOk.setStyle("-fx-font-size: 25");
			
			//HBox Buttons
			HBox buttonBoxLogin = new HBox(22);
			buttonBoxLogin.getChildren().addAll(buttonLoginToServerlist, buttonLoginOk);
			buttonBoxLogin.setAlignment(Pos.TOP_CENTER);
			
			//Layout
			VBox layoutLogin = new VBox(22);
			layoutLogin.setPadding(new Insets(90));
			layoutLogin.setAlignment(Pos.TOP_CENTER);
			sceneLogin = new Scene(layoutLogin, 1280, 720);
			layoutLogin.getChildren().addAll(labelBombermanLogin, textFieldLogin, buttonBoxLogin);
		
		//End Login Scene
			
		//Begin Lobby Scene
			
			//Label Bomberman
			Text labelBombermanLobby = new Text ("Bomberman");
			labelBombermanLobby.setId("labelBomberman");
			
			//Playerlist Background
			Rectangle playerlistBg = new Rectangle(100, 300, 600, 200);
			playerlistBg.setArcWidth(20);
			playerlistBg.setArcHeight(20);
			playerlistBg.setId("rectangleBackground");
			
			//Playernames
			String player1 = textFieldLogin.getText();
			String player2 = "Player2";
			String player3 = "Player3";
			String player4 = "Player4";
			
			//HBox Player Red
			HBox playerRed = new HBox(22);
			Label labelPlayer1 = new Label(player1);
			labelPlayer1.setId("playerStroke");
			Circle pointRed = new Circle(15);
			pointRed.setFill(Color.RED);
			pointRed.setId("point");
			playerRed.setPadding(new Insets(10,0,0,0));
			playerRed.setAlignment(Pos.TOP_CENTER);
			playerRed.getChildren().addAll(pointRed, labelPlayer1);
			
			//HBox Player Blue
			HBox playerBlue = new HBox(22);
			Label labelPlayer2 = new Label(player2);
			labelPlayer2.setId("text");
			Circle pointBlue = new Circle(15);
			pointBlue.setFill(Color.BLUE);
			pointBlue.setId("point");
			//playerBlue.setPadding(new Insets(22));
			playerBlue.setAlignment(Pos.TOP_CENTER);
			playerBlue.getChildren().addAll(pointBlue, labelPlayer2);

			//HBox Player Green
			HBox playerGreen = new HBox(22);
			Label labelPlayer3 = new Label(player3);
			labelPlayer3.setId("text");
			Circle pointGreen = new Circle(15);
			pointGreen.setFill(Color.GREEN);
			pointGreen.setId("point");
			//playerGreen.setPadding(new Insets(22));
			playerGreen.setAlignment(Pos.TOP_CENTER);
			playerGreen.getChildren().addAll(pointGreen, labelPlayer3);
			
			//HBox Player Yellow
			HBox playerYellow = new HBox(22);
			Label labelPlayer4 = new Label(player4);
			labelPlayer4.setId("text");
			Circle pointYellow = new Circle(15);
			pointYellow.setFill(Color.YELLOW);
			pointYellow.setId("point");
			//playerYellow.setPadding(new Insets(22));
			playerYellow.setAlignment(Pos.TOP_CENTER);
			playerYellow.getChildren().addAll(pointYellow, labelPlayer4);
			
			//Stackpane
			StackPane paneLobbyBg = new StackPane();
			VBox playerNames = new VBox(16);
			playerNames.setMinWidth(422);
			playerNames.getChildren().addAll(playerRed, playerBlue, playerGreen, playerYellow);
			paneLobbyBg.getChildren().addAll(playerlistBg, playerNames);
			
			//Back Button
			Button buttonLobbyToServerlist = new Button("Back");
			buttonLobbyToServerlist.setOnAction(e -> window.setScene(sceneServerlist));
			buttonLobbyToServerlist.setMinWidth(200);
			
			//Start Button (if Admin)
			Button buttonLobbyStart = new Button("Start");
			//buttonLobbyStart.setOnAction(e -> window.setScene(sceneLobby));
			buttonLobbyStart.setMinWidth(200);
			
			//Button Fontsize
			buttonLobbyToServerlist.setStyle("-fx-font-size: 25");
			buttonLobbyStart.setStyle("-fx-font-size: 25");
			
			//HBox Buttons
			HBox buttonBoxLobby = new HBox(22);
			buttonBoxLobby.getChildren().addAll(buttonLobbyToServerlist, buttonLobbyStart);
			buttonBoxLobby.setAlignment(Pos.TOP_CENTER);
			
			//Layout
			VBox layoutLobby = new VBox(22);
			layoutLobby.setPadding(new Insets(90));
			layoutLobby.setAlignment(Pos.TOP_CENTER);
			sceneLobby = new Scene(layoutLobby, 1280,720);
			layoutLobby.getChildren().addAll(labelBombermanLobby, paneLobbyBg, buttonBoxLobby);
			
		//End Lobby Scene
			
		//Begin Ranking Scene
			
			//Label Bomberman
			Text labelBombermanRanking = new Text ("Bomberman");
			labelBombermanRanking.setId("labelBomberman");
			
			//Text Background
			Rectangle rankingBg = new Rectangle(100, 300, 600, 200);
			rankingBg.setArcWidth(20);
			rankingBg.setArcHeight(20);
			rankingBg.setId("rectangleBackground");
			
			//Text
			Label player = new Label("Player");
			player.setId("text");
			Label steps = new Label("S");
			steps.setId("text");
			Label bombs = new Label("B");
			bombs.setId("text");
			Label kills = new Label("K");
			kills.setId("text");
			Label powerUps = new Label("P");
			powerUps.setId("text");
			Label points = new Label("P");
			points.setId("text");
			
			//Back Button
			Button buttonRankingToLobby = new Button("Back");
			buttonRankingToLobby.setOnAction(e -> window.setScene(sceneLobby));
			buttonRankingToLobby.setMinWidth(200);
			buttonRankingToLobby.setStyle("-fx-font-size: 25");
			
			//Grid
			GridPane gridRanking = new GridPane();
			ColumnConstraints col1 = new ColumnConstraints(150);
		    ColumnConstraints col2 = new ColumnConstraints(75);
		    ColumnConstraints col3 = new ColumnConstraints(75);
		    ColumnConstraints col4 = new ColumnConstraints(75);
		    ColumnConstraints col5 = new ColumnConstraints(75);
		    ColumnConstraints col6 = new ColumnConstraints(75);
			gridRanking.setAlignment(Pos.TOP_CENTER);
			gridRanking.setPadding(new Insets(16));
			gridRanking.add(player, 1, 1);
			gridRanking.add(steps, 2, 1);
			gridRanking.add(bombs, 3, 1);
			gridRanking.add(kills, 4, 1);
			gridRanking.add(powerUps, 5, 1);
			gridRanking.add(points, 6, 1);
		    gridRanking.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);
			//gridRanking.getChildren().addAll(player, steps, bombs, kills, powerUps, points);
			
		    //Space Holder
		    Label ranking = new Label("Ranking - Platzhalter");
		    ranking.setId("text");
		    
		    
			//Layout
			VBox layoutRanking = new VBox(22);
			layoutRanking.setPadding(new Insets(90));
			layoutRanking.setAlignment(Pos.TOP_CENTER);
			sceneRanking = new Scene(layoutRanking, 1280, 720);
			StackPane paneRanking = new StackPane();
			paneRanking.getChildren().addAll(rankingBg, ranking);
			layoutRanking.getChildren().addAll(labelBombermanRanking, paneRanking, buttonRankingToLobby);
			
//			//layout
//			VBox layoutRanking = new VBox(22);
//			layoutRanking.setPadding(new Insets(90));
//			layoutRanking.setAlignment(Pos.TOP_CENTER);
//			sceneRanking = new Scene(layoutRanking, 1280, 720);
//			StackPane paneRanking = new StackPane();
//			paneRanking.getChildren().addAll(rankingBg, rankingText);
//			layoutRanking.getChildren().addAll(labelBombermanRanking, paneRanking, buttonRankingToLobby);
			
		//End Ranking Scene	
			
		//Begin Error Server Full Scene
			
			//Label Bomberman
			Text labelBombermanErrorServerfull = new Text ("Bomberman");
			labelBombermanErrorServerfull.setId("labelBomberman");
			
			//Text Background
			Rectangle errorServerfullBg = new Rectangle(100, 300, 600, 200);
			errorServerfullBg.setArcWidth(20);
			errorServerfullBg.setArcHeight(20);
			errorServerfullBg.setId("rectangleBackground");
			
			//Text
			Text errorServerfullText = new Text("Error: Server is full !");
			errorServerfullText.setId("text");
			
			//Back Button
			Button buttonServerfullToServerlist = new Button("Back");
			buttonServerfullToServerlist.setOnAction(e -> window.setScene(sceneServerlist));
			buttonServerfullToServerlist.setMinWidth(200);
			buttonServerfullToServerlist.setStyle("-fx-font-size: 25");
			
			//layout
			VBox layoutErrorServerfull = new VBox(22);
			layoutErrorServerfull.setPadding(new Insets(90));
			layoutErrorServerfull.setAlignment(Pos.TOP_CENTER);
			sceneErrorServerfull = new Scene(layoutErrorServerfull, 1280, 720);
			StackPane paneErrorServerfullBg = new StackPane();
			paneErrorServerfullBg.getChildren().addAll(errorServerfullBg, errorServerfullText);
			layoutErrorServerfull.getChildren().addAll(labelBombermanErrorServerfull, paneErrorServerfullBg, buttonServerfullToServerlist);
			
		//End Error Server Full Scene
			
		//Begin Error Disconnect Scene
		
			//Label Bomberman
			Text labelBombermanErrorDisconnect = new Text ("Bomberman");
			labelBombermanErrorDisconnect.setId("labelBomberman");
			
			//Text Background
			Rectangle errorDisconnectBg = new Rectangle(100, 300, 600, 200);
			errorDisconnectBg.setArcWidth(20);
			errorDisconnectBg.setArcHeight(20);
			errorDisconnectBg.setId("rectangleBackground");
			
			//Text
			Text errorDisconnectText = new Text("Error: Disconnected from Server !");
			errorDisconnectText.setId("text");
			
			//Back Button
			Button buttonDisconnectToServerlist = new Button("Back");
			buttonDisconnectToServerlist.setOnAction(e -> window.setScene(sceneServerlist));
			buttonDisconnectToServerlist.setMinWidth(200);
			buttonDisconnectToServerlist.setStyle("-fx-font-size: 25");
			
			//layout
			VBox layoutErrorDisconnect = new VBox(22);
			layoutErrorDisconnect.setPadding(new Insets(90));
			layoutErrorDisconnect.setAlignment(Pos.TOP_CENTER);
			sceneErrorDisconnect = new Scene(layoutErrorDisconnect, 1280, 720);
			StackPane paneErrorDisconnectBg = new StackPane();
			paneErrorDisconnectBg.getChildren().addAll(errorDisconnectBg, errorDisconnectText);
			layoutErrorDisconnect.getChildren().addAll(labelBombermanErrorDisconnect, paneErrorDisconnectBg, buttonDisconnectToServerlist);
			
		//End Error Disconnect Scene
			
		//Use Stylesheet
		layoutMenu.getStylesheets().add(Menu.class.getResource("style.css").toExternalForm());
		layoutServerlist.getStylesheets().add(Menu.class.getResource("style.css").toExternalForm());
		layoutHelp.getStylesheets().add(Menu.class.getResource("style.css").toExternalForm());
		layoutLogin.getStylesheets().add(Menu.class.getResource("style.css").toExternalForm());
		layoutLobby.getStylesheets().add(Menu.class.getResource("style.css").toExternalForm());
		layoutErrorServerfull.getStylesheets().add(Menu.class.getResource("style.css").toExternalForm());
		layoutErrorDisconnect.getStylesheets().add(Menu.class.getResource("style.css").toExternalForm());
		layoutRanking.getStylesheets().add(Menu.class.getResource("style.css").toExternalForm());
		
		//Draw Window
		window.setScene(sceneMenu);
		window.setTitle("Bomberman");
		window.show();
		
	}
}