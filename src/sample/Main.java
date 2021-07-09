package sample;



import javafx.application.Application;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main extends Application  {
    public static List<Appren>  la=new ArrayList<Appren>() ;
    public static List<Formateur>  lf =new ArrayList<Formateur>();
    public static Compte logedAccount  ;
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        ToggleSwitch toggle = new ToggleSwitch();
        toggle.setLayoutX(270);
        toggle.setLayoutY(505);
        toggle.setOnMousePressed(e->{
        	if(Login.typeint==1) {
        		Login.rad1();
        	} else {
        		Login.rad2();
        	}
        });
        
        root.getChildren().addAll(toggle);
        primaryStage.setScene(new Scene(root,1268,708));
        primaryStage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setResizable(false);
        primaryStage.setTitle("ESIQuiz");
        primaryStage.show();
        
    }


    public static void main(String[] args) {
    	load();
        launch(args);
    }

    public static void load() {
    	FileInputStream file=null;
    	ObjectInputStream obj=null;
    	try {
			file = new FileInputStream("formateurs.data");
			obj = new ObjectInputStream(file);
	    	lf = (ArrayList<Formateur>) obj.readObject();
	    	file = new FileInputStream("apprenants.data");
			obj = new ObjectInputStream(file);
	    	la = (ArrayList<Appren>) obj.readObject();
	    	obj.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public static void save() {
    	la.clear();
    	for (Formateur F:lf) {
    		for (Apprenant A: F.getFormation().getApprenants()) {
    			Appren ap = new Appren();
    			ap.LoginA=A.getLogin();
    			ap.LoginF=F.getLogin();
    			la.add(ap);
    		}
    	}
    	FileOutputStream file = null;
		ObjectOutputStream obj = null;
    	try {
			file = new FileOutputStream("formateurs.data");
			obj = new ObjectOutputStream(file);
			obj.writeObject(lf);
			file = new FileOutputStream("apprenants.data");
			obj = new ObjectOutputStream(file);
			obj.writeObject(la);
			obj.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    
    }

    public static Appren getApprenant(String login) {
        int i=0;
        while ((i<la.size())&&(!la.get(i).LoginA.equals(login))) {
            i++;
        }
        if (i>=la.size()) return null;
        else return la.get(i);
    }

    public static Formateur getFormateur(String Login) {
        int i=0;
        while ((i<lf.size())&&(!lf.get(i).getLogin().equals(Login))) {
            i++;
        }
        if (i>=lf.size()) return null;
        else return lf.get(i);
    }

    public static String getLogin(String Nom, String Prenom) {
        return  new String(Prenom.charAt(0)+Nom);
    }

    public static String getMdp(String Nom, LocalDate DateNaissance) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Nom);
        if (DateNaissance.getDayOfMonth()<10) stringBuilder.append('0');
        stringBuilder.append(DateNaissance.getDayOfMonth());
        if (DateNaissance.getMonth().getValue()<10) stringBuilder.append('0');
        stringBuilder.append(DateNaissance.getMonth().getValue());
        stringBuilder.append(DateNaissance.getYear());
        return stringBuilder.toString();
    }
    
}

class Appren implements Serializable{
	String LoginA;
	String LoginF;
}
