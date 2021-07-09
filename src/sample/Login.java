package sample;



import java.awt.*; 
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;


public class Login {

	@FXML private TextField login;
	@FXML private PasswordField pass;

    public static int typeint=0;
    
    public static void rad1(){
        typeint=0;
    }
    public static void rad2(){
        typeint=1;
    }
    
    public void submit(ActionEvent event){
    	
    	if ((login.getText().equals(""))||(pass.getText().equals(""))) {
    		erreur("Donnez login et mot de passe");
    	} else {
    		
	    	String Login = login.getText();
	    	String Mdp = pass.getText();
	    	
	        if (typeint==1){
	            int i=0;
	            while ((i<Main.la.size())&&(!Main.la.get(i).LoginA.equals(Login))) i++;
	            if (i>=Main.la.size()) {
	            	erreur("Compte non-existant");
	            } else {
	            	Apprenant A = Main.getFormateur(Main.la.get(i).LoginF).getFormation().getApprenant(Login);
	            	if (!A.getMdp().equals(Mdp)) {
	            		erreur("Mot de passe incorrecte");
	            	} else {
	            		Main.logedAccount=A;
	            	}
	            }
	        }
	        else {
	        	int i=0;
	            while ((i<Main.lf.size())&&(!Main.lf.get(i).getLogin().equals(Login))) i++;
	            if (i>=Main.lf.size()) {
	            	erreur("Compte non-existant");
	            } else {
	            	Formateur F = Main.getFormateur(Login);
	            	if (!F.getMdp().equals(Mdp)) {
	            		erreur("Mot de passe incorrecte");
	            	} else {
	            		Main.logedAccount=F;
	            	}
	            }
	        }
	        if(Main.logedAccount!=null){
	            System.out.println("connecte");
	            Stage window = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
	            Parent root = null;
	
	            try {
	                if(typeint==1) {
	                	root = FXMLLoader.load(getClass().getResource("ApprenantFX.fxml"));
	                	
	                }
	                else {
	                	root = FXMLLoader.load(getClass().getResource("FormateurFX.fxml"));
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            window.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	            window.setTitle("ESIQuiz");
	            window.setScene(new Scene(root));
	            window.show();
	        }
    	}


    }
    
public void creerFormation(ActionEvent event) {
	
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Scene prec = stage.getScene();
		
        TextField login = new TextField();
        login.setPromptText("Login");
        login.getStyleClass().add("field2");
        PasswordField mdp = new PasswordField();
        mdp.setPromptText("Mot de passe");
        mdp.getStyleClass().add("field2");
        PasswordField mdpconf = new PasswordField();
        mdpconf.setPromptText("Confirmer mot de passe");
        mdpconf.getStyleClass().add("field2");
        TextField nom = new TextField();
        nom.setPromptText("Nom de la formation");
        nom.getStyleClass().add("field2");
        TextArea description = new TextArea();
        description.setPromptText("Description de la formation");
        description.getStyleClass().add("field2");
        description.setPrefHeight(100);
        DatePicker date_d = new DatePicker();
        date_d.setPromptText("Début de la formation");
        date_d.setPrefWidth(400);
        date_d.getStyleClass().add("field2");
        DatePicker date_f = new DatePicker();
        date_f.setPromptText("Fin de la formation");
        date_f.setPrefWidth(400);
        date_f.getStyleClass().add("field2");
        
        Button btn = new Button();
        btn.setLayoutX(920);
        btn.setLayoutY(538);
        btn.setPrefHeight(60);
        btn.setPrefWidth(260);
        btn.setStyle("-fx-font-size:20px;");
        btn.setText("Créer la formation >>");
        btn.setId("formation");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
            	if ((login.getText().equals(""))||(mdp.getText().equals(""))||(mdpconf.getText().equals(""))
            			||(nom.getText().equals(""))||(description.getText().equals(""))
            			||(date_d.getValue().toString().equals(""))||(date_f.getValue().toString().equals(""))) {
            		erreur("Tous les champs sont obligatoires");
            	} else {
            		if (Main.getFormateur(login.getText())!=null) {
                    	erreur("Login existe deja");
                    } else {
                    	if (!mdp.getText().equals(mdpconf.getText())) {
                    		erreur("Verifiez votre mot de passe");
                    	} else {
                    		if (date_d.getValue().compareTo(date_f.getValue())>0) {
                    			erreur("Date debut doit etre inferieure a date fin");
                    		} else {
                    			System.out.println("inscription terminee"); 
                    			Formateur F = new Formateur(login.getText(),mdp.getText(),new Formation(nom.getText(), description.getText(), date_d.getValue(), date_f.getValue()));
                    			Main.lf.add(F);
                    			Main.logedAccount=F;
                    			Main.save();
                    			if (Main.logedAccount!=null){
                    	            System.out.println("connecte");
                    	            stage.setTitle("ESIQuiz");
                    	            try {
										stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("FormateurFX.fxml"))));
									} catch (IOException e) {
										e.printStackTrace();
									}
                    	            stage.show();
                    	        }
                    		}
                    	}
                    }
            	} 
            }
        });
        
        Button retour = new Button();
        retour.setId("retour");
        retour.setText("<< Retour");
        retour.setLayoutX(10);
        retour.setLayoutY(10);
        retour.setPrefHeight(40);
        retour.setPrefWidth(100);
        retour.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent event) {
        		stage.setScene(prec);
        	}
        });
               
        
        VBox V2 = new VBox();
        Label l =new Label("Créer votre formation:");
        l.setStyle("-fx-font-size:32px; -fx-text-fill:#fff;");
        l.setLayoutX(100);
        l.setLayoutY(100);
        Label l1 =new Label("Formateur:");
        l1.setStyle("-fx-font-size:24px; -fx-text-fill:#fff;");
        
        V2.getChildren().addAll(l1,login, mdp, mdpconf);
        V2.setPrefWidth(300);
        V2.setSpacing(40);
        V2.setLayoutX(100);
        V2.setLayoutY(200);
        V2.setAlignment(Pos.CENTER_LEFT);
        
        VBox V4 = new VBox();
        Label l2 =new Label("Formation:");
        l2.setStyle("-fx-font-size:24px; -fx-text-fill:#fff;");
        V4.getChildren().addAll(l2,nom, date_d, date_f, description);
        V4.setPrefWidth(300);
        V4.setSpacing(40);
        V4.setLayoutX(500);
        V4.setLayoutY(200);
        V4.setAlignment(Pos.CENTER_LEFT);
        
        AnchorPane root = new AnchorPane();
        root.getChildren().addAll(l,V2,V4,retour,btn);
        root.setId("mainroot");
        
        stage.setScene(new Scene(root,1280,720));
        stage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.show();
    }

	public void erreur(String message) {
		Stage stage = new Stage();
		GridPane root = new GridPane();
		root.setId("root");
		stage.setScene(new Scene(root,500,100));
		root.setAlignment(Pos.CENTER);
		Label label = new Label(message);
		root.getChildren().add(label);
		label.getStyleClass().add("info");
		stage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		stage.setTitle("Erreur");
		stage.show();
	}
}
