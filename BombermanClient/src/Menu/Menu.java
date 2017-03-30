package Menu;


import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import networkBomberman.BombermanGameClient;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.shape.*;

public class Menu extends Application {

	Stage window;
	Scene sceneMenu, sceneServerlist, sceneHelp, sceneLogin, sceneLobby, sceneLobbyAdmin, sceneErrorServerfull, sceneErrorDisconnect, sceneRanking;
	
	//Variables sent from Server:
	int serverCount = 10;
	String playerRed = "Player1";
	String playerBlue = "Player2";
	String playerGreen = "Player3";
	String playerYellow = "Player4";
	
	//Variables sent to Server:
	TextField textFieldLogin = new TextField();
	NumberTextField armorTime = new NumberTextField();
	NumberTextField explosionTime = new NumberTextField();
	NumberTextField maxBombRadius = new NumberTextField();
	NumberTextField playTime = new NumberTextField();
	//ToggleGroup modes = new ToggleGroup();
	ModesTextField modes = new ModesTextField();
	
	public static void mainMenu(String[] args) {
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
			//System.exit(0);
			buttonExit.setOnAction(e -> System.exit(0));
		
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
			buttonServerlistToMenu.setStyle("-fx-font-size: 25");
		
			//Layout
			VBox layoutServerlist = new VBox(22);
			layoutServerlist.setPadding(new Insets(90));
			layoutServerlist.setAlignment(Pos.TOP_CENTER);
			layoutServerlist.getChildren().add(labelBombermanServerlist);
			sceneServerlist = new Scene(layoutServerlist, 1280, 720);
			
			//Server Buttons
			VBox serverlistPane = new VBox(22);
			serverlistPane.setPadding(new Insets(30));
			serverlistPane.setAlignment(Pos.TOP_CENTER);
			ScrollPane serverlistScrollPane = new ScrollPane(serverlistPane);
			serverlistScrollPane.setId("scrollPane");
			serverlistScrollPane.setMaxWidth(500);
			serverlistScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
			serverlistScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
			
			/*for(int i = 1; i<=serverCount; i++)
			{
				Button buttonServer = new Button("Server " + i);
				buttonServer.setMinWidth(buttonBoxServerlist.getPrefWidth());
				buttonServer.setStyle("-fx-font-size: 25");
				serverlistPane.getChildren().add(buttonServer);
				buttonServer.setOnAction(e -> window.setScene(sceneLogin));
			}*/
			Button buttonServer = new Button("Server 1");
			buttonServer.setMinWidth(buttonBoxServerlist.getPrefWidth());
			buttonServer.setStyle("-fx-font-size: 25");
			serverlistPane.getChildren().add(buttonServer);
			buttonServer.setOnAction(e -> this.startConnection());
			
			layoutServerlist.getChildren().addAll(serverlistScrollPane, buttonServerlistToMenu);
		
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
			//TextField textFieldLogin = new TextField();
			textFieldLogin.setPromptText("choose an username...");
			textFieldLogin.setId("textField");
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
			Rectangle playerlistBg1 = new Rectangle(100, 300, 600, 200);
			playerlistBg1.setArcWidth(20);
			playerlistBg1.setArcHeight(20);
			playerlistBg1.setId("rectangleBackground");
			
			//HBox Player Red
			HBox player11 = new HBox(22);
			Label labelPlayer11 = new Label(playerRed);
			labelPlayer11.setId("playerStroke");
			Circle pointRed1 = new Circle(15);
			pointRed1.setFill(Color.RED);
			pointRed1.setId("point");
			player11.setPadding(new Insets(10,0,0,0));
			player11.setAlignment(Pos.TOP_CENTER);
			player11.getChildren().addAll(pointRed1, labelPlayer11);
			
			//HBox Player Blue
			HBox player21 = new HBox(22);
			Label labelPlayer21 = new Label(playerBlue);
			labelPlayer21.setId("text");
			Circle pointBlue1 = new Circle(15);
			pointBlue1.setFill(Color.BLUE);
			pointBlue1.setId("point");
			//playerBlue.setPadding(new Insets(22));
			player21.setAlignment(Pos.TOP_CENTER);
			player21.getChildren().addAll(pointBlue1, labelPlayer21);

			//HBox Player Green
			HBox player31 = new HBox(22);
			Label labelPlayer31 = new Label(playerGreen);
			labelPlayer31.setId("text");
			Circle pointGreen1 = new Circle(15);
			pointGreen1.setFill(Color.GREEN);
			pointGreen1.setId("point");
			//playerGreen.setPadding(new Insets(22));
			player31.setAlignment(Pos.TOP_CENTER);
			player31.getChildren().addAll(pointGreen1, labelPlayer31);
			
			//HBox Player Yellow
			HBox player41 = new HBox(22);
			Label labelPlayer41 = new Label(playerYellow);
			labelPlayer41.setId("text");
			Circle pointYellow1 = new Circle(15);
			pointYellow1.setFill(Color.YELLOW);
			pointYellow1.setId("point");
			//playerYellow.setPadding(new Insets(22));
			player41.setAlignment(Pos.TOP_CENTER);
			player41.getChildren().addAll(pointYellow1, labelPlayer41);
			
			//Stackpane
			StackPane paneLobbyBg = new StackPane();
			VBox playerNames = new VBox(16);
			playerNames.setMinWidth(422);
			playerNames.getChildren().addAll(player11, player21, player31, player41);
			paneLobbyBg.getChildren().addAll(playerlistBg1, playerNames);
			
			//Back Button
			Button buttonLobbyToServerlist = new Button("Back");
			buttonLobbyToServerlist.setOnAction(e -> window.setScene(sceneServerlist));
			buttonLobbyToServerlist.setMinWidth(200);
			
			//Button Fontsize
			buttonLobbyToServerlist.setStyle("-fx-font-size: 25");
			
			//HBox Buttons
			HBox buttonBoxLobby = new HBox(22);
			buttonBoxLobby.getChildren().addAll(buttonLobbyToServerlist);
			buttonBoxLobby.setAlignment(Pos.TOP_CENTER);
			
			//Layout
			VBox layoutLobby = new VBox(22);
			layoutLobby.setPadding(new Insets(90));
			layoutLobby.setAlignment(Pos.TOP_CENTER);
			sceneLobby = new Scene(layoutLobby, 1280,720);
			layoutLobby.getChildren().addAll(labelBombermanLobby, paneLobbyBg, buttonBoxLobby);
			
		//End Lobby Scene
			
		//Begin LobbyAdmin Scene
			
			//Label Bomberman
			Text labelBombermanLobbyAdmin = new Text ("Bomberman");
			labelBombermanLobbyAdmin.setId("labelBomberman");
			
			//Playerlist Background
			Rectangle playerlistBg2 = new Rectangle(100, 300, 400, 272);
			playerlistBg2.setArcWidth(20);
			playerlistBg2.setArcHeight(20);
			playerlistBg2.setId("rectangleBackground");
			
			//HBox Player Red
			HBox player12 = new HBox(22);
			Label labelPlayer12 = new Label(playerRed);
			labelPlayer12.setId("playerStroke");
			Circle pointRed2 = new Circle(15);
			pointRed2.setFill(Color.RED);
			pointRed2.setId("point");
			player12.setPadding(new Insets(10,0,0,0));
			player12.setAlignment(Pos.TOP_CENTER);
			player12.getChildren().addAll(pointRed2, labelPlayer12);
			
			//HBox Player Blue
			HBox player22 = new HBox(22);
			Label labelPlayer22 = new Label(playerBlue);
			labelPlayer22.setId("text");
			Circle pointBlue2 = new Circle(15);
			pointBlue2.setFill(Color.BLUE);
			pointBlue2.setId("point");
			//playerBlue.setPadding(new Insets(22));
			player22.setAlignment(Pos.TOP_CENTER);
			player22.getChildren().addAll(pointBlue2, labelPlayer22);

			//HBox Player Green
			HBox player32 = new HBox(22);
			Label labelPlayer32 = new Label(playerGreen);
			labelPlayer32.setId("text");
			Circle pointGreen2 = new Circle(15);
			pointGreen2.setFill(Color.GREEN);
			pointGreen2.setId("point");
			//playerGreen.setPadding(new Insets(22));
			player32.setAlignment(Pos.TOP_CENTER);
			player32.getChildren().addAll(pointGreen2, labelPlayer32);
			
			//HBox Player Yellow
			HBox player42 = new HBox(22);
			Label labelPlayer42 = new Label(playerYellow);
			labelPlayer42.setId("text");
			Circle pointYellow2 = new Circle(15);
			pointYellow2.setFill(Color.YELLOW);
			pointYellow2.setId("point");
			//playerYellow.setPadding(new Insets(22));
			player42.setAlignment(Pos.TOP_CENTER);
			player42.getChildren().addAll(pointYellow2, labelPlayer42);
			
			//Stackpane
			StackPane paneLobbyBg2 = new StackPane();
			VBox playerNames2 = new VBox(16);
			playerNames2.setPadding(new Insets(20));
			playerNames2.setMinWidth(422);
			playerNames2.getChildren().addAll(player12, player22, player32, player42);
			paneLobbyBg2.getChildren().addAll(playerlistBg2, playerNames2);
		
			/*
			//Setting Buttons
			ComboBox armorTime = new ComboBox();
			ComboBox explosionTime = new ComboBox();
			ComboBox maxBombRadius = new ComboBox();
			ComboBox playTime = new ComboBox();
			
			armorTime.getItems().addAll("Low", "Medium", "High");
			explosionTime.getItems().addAll("Low", "Medium", "High");
			maxBombRadius.getItems().addAll("Low", "Medium", "High");
			playTime.getItems().addAll("Low", "Medium", "High");
			
			VBox settingButtons = new VBox(22);
			settingButtons.getChildren().addAll(armorTime, explosionTime, maxBombRadius, playTime);
			
			HBox settingAndPlayerList = new HBox(22);
			settingAndPlayerList.getChildren().addAll(paneLobbyBg2, settingButtons);
			settingAndPlayerList.setAlignment(Pos.TOP_CENTER);
			*/
			
			//settings
			//TextField armorTime = new TextField();
			armorTime.setPromptText("Armor duration in seconds");
			armorTime.setId("textField2");
			//armorTime.setMaxWidth(422);
			
			//TextField explosionTime = new TextField();
			explosionTime.setPromptText("Explosion time in seconds");
			explosionTime.setId("textField2");
			//explosionTime.setMaxWidth(422);
			
			//TextField maxBombRadius = new TextField();
			maxBombRadius.setPromptText("Maximum explosion radius in fields");
			maxBombRadius.setId("textField2");
			//maxBombRadius.setMaxWidth(422);
			
			//TextField playTime = new TextField();
			playTime.setPromptText("Overall playtime in minutes");
			playTime.setId("textField2");
			
			/*
			//Radio Buttons Mode A/B
			RadioButton modeA = new RadioButton("Mode A ");
			RadioButton modeB = new RadioButton("Mode B ");
			modeA.setToggleGroup(modes);
			modeB.setToggleGroup(modes);
			HBox buttonModes = new HBox(22);
			buttonModes.getChildren().addAll(modeA, modeB);
			*/
			
			modes.setPromptText("Mode A or B");
			modes.setId("textField2");
			
			VBox settings = new VBox(12);
			settings.getChildren().addAll(modes, armorTime, explosionTime, maxBombRadius, playTime);
			settings.setMinWidth(440);
			
			HBox settingsAndPlayerList = new HBox();
			settingsAndPlayerList.getChildren().addAll(paneLobbyBg2, settings);
			settingsAndPlayerList.setAlignment(Pos.TOP_CENTER);

			//Back Button
			Button buttonLobbyAdminToServerlist = new Button("Back");
			buttonLobbyAdminToServerlist.setOnAction(e -> window.setScene(sceneServerlist));
			buttonLobbyAdminToServerlist.setMinWidth(200);
			
			//Start Button
			Button buttonLobbyStart = new Button("Start");
			buttonLobbyStart.setMinWidth(200);
			//Spielstart an Server senden ...

			//Button Fontsize
			buttonLobbyAdminToServerlist.setStyle("-fx-font-size: 25");
			buttonLobbyStart.setStyle("-fx-font-size: 25");
			
			//HBox Buttons
			HBox buttonBoxLobbyAdmin = new HBox(22);
			buttonBoxLobbyAdmin.getChildren().addAll(buttonLobbyAdminToServerlist, buttonLobbyStart);
			buttonBoxLobbyAdmin.setAlignment(Pos.TOP_CENTER);
			
			//Layout
			VBox layoutLobbyAdmin = new VBox(22);
			layoutLobbyAdmin.setPadding(new Insets(90));
			layoutLobbyAdmin.setAlignment(Pos.TOP_CENTER);
			sceneLobbyAdmin = new Scene(layoutLobbyAdmin, 1280,720);
			layoutLobbyAdmin.getChildren().addAll(labelBombermanLobbyAdmin, settingsAndPlayerList, buttonBoxLobbyAdmin);
			
		//End LobbyAdmin Scene
			
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
		layoutLobbyAdmin.getStylesheets().add(Menu.class.getResource("style.css").toExternalForm());
		
		//Draw Window
		window.setScene(sceneMenu);
		window.setTitle("Bomberman");
		window.show();

	}
	
	private Object startConnection() {
		if(BombermanGameClient.startBombermanGameClient());
			window.setScene(sceneLogin);
		if(!BombermanGameClient.startBombermanGameClient());
			window.setScene(sceneMenu);
		return null;
	}

	public class NumberTextField extends TextField {
	    
	    @Override public void replaceText(int start, int end, String text) {
	           if (text.matches("[0-9]") || text == "") {
	               super.replaceText(start, end, text);
	           }
	       }
	     
	       @Override public void replaceSelection(String text) {
	           if (text.matches("[0-9]") || text == "") {
	               super.replaceSelection(text);
	           }
	       }
	 
	}
	
	public class ModesTextField extends TextField {
	    
	    @Override public void replaceText(int start, int end, String text) {
	           if (text.matches("[A,B,a,b]") || text == "") {
	               super.replaceText(start, end, text);
	           }
	       }
	     
	       @Override public void replaceSelection(String text) {
	           if (text.matches("[A,B,a,b]") || text == "") {
	               super.replaceSelection(text);
	           }
	       }
	 
	}
	
	public String getNickname() {
		return textFieldLogin.getText();
	}
	
	public char getMode()
	{
		String mode = modes.getText();
		return mode.charAt(0);
	}
	
	public int getArmorTime() {
		return Integer.parseInt(armorTime.getText());
	}
	
	public int getExplosionTime() {
		return Integer.parseInt(explosionTime.getText());
	}
	
	public int getMaxBombRadius() {
		return Integer.parseInt(maxBombRadius.getText());
	}
	
	public int getPlayTime() {
		return Integer.parseInt(playTime.getText());
	}
	
	/*
	public boolean getModeA() {
		if (RadioButton modes = (RadioButton) modes.getSelectedToggle()==modeA)
	}*/
}

