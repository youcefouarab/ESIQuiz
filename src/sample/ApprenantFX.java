package sample;



import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import java.io.IOException;
import java.nio.channels.NetworkChannel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


public class ApprenantFX {
    public Button QuizButton;
    public Button ProfilButton;
    public Button NoteButton ;
    public Button LogoutButton ;
    public Pane pane ;
    
    public void Profil() {
    	pane.getChildren().clear();
    	Apprenant A = ((Apprenant) Main.logedAccount);
    	Label titre1 = new Label("Profil:");
    	titre1.setId("titre");
    	titre1.setLayoutX(100);
    	titre1.setLayoutY(70);
    	Label titre2 = new Label("Formation:");
    	titre2.setId("titre");
    	titre2.setLayoutX(100);
    	titre2.setLayoutY(550);
    	Label nom = new Label("Nom:  "+A.getNom());
    	nom.getStyleClass().add("info");
    	Label prenom = new Label("Prénom:  "+A.getPrenom());
    	prenom.getStyleClass().add("info");
    	Label date = new Label("Date de naissance:  "+A.getDateNaissance().toString());
    	date.getStyleClass().add("info");
    	Label adresse = new Label("Adresse:  "+A.getAdresse());
    	adresse.getStyleClass().add("info");
    	Label login = new Label("Login:  "+A.getLogin());
    	login.getStyleClass().add("info");
    	Label mdp = new Label("Mot de passe:  ");
    	mdp.getStyleClass().add("info");
    	PasswordField Mdp = new PasswordField();
    	Mdp.setText(A.getMdp());
    	Mdp.setDisable(true);
    	Mdp.getStyleClass().addAll("info","field");
    	VBox V1 = new VBox();
    	VBox V2 = new VBox();
    	Button modif = new Button("Modifier");
    	modif.setOnAction(e->{
    		Mdp.setDisable(false);
    		Mdp.setText("");
    		modif.setText("Valider");
    		modif.setOnAction(e2->{
    			if (Mdp.getText().equals("")) {
            		erreur("Mot de passe obligatoire");
            	} else {
            		System.out.println("modification terminee");
        			A.setMdp(Mdp.getText());
        			Main.save();
        			Profil();
            	}
    		});
    	});
    	HBox H1 = new HBox();
    	H1.getChildren().addAll(mdp,Mdp,modif);
    	H1.setSpacing(10);
    	H1.setAlignment(Pos.BASELINE_LEFT);
    	
    	V1.getChildren().addAll(nom,prenom,date,adresse,login,H1);
    	V1.setLayoutX(150);
    	V1.setLayoutY(140);
    	V1.setPrefWidth(980-150);
    	V1.setSpacing(30);
    	
    	Formation F = Main.getFormateur(Main.getApprenant(A.getLogin()).LoginF).getFormation();
    	nom = new Label("Nom de la formation:  "+F.getNom());
    	nom.getStyleClass().add("info");
    	date = new Label("Date de debut:  "+F.getDateDebut().toString());
    	date.getStyleClass().add("info");
    	Label datef = new Label("Date de fin:  "+F.getDateFin().toString());
    	datef.getStyleClass().add("info");
    	Label desc = new Label("Description:  "+F.getDescription());
    	desc.getStyleClass().add("info");
    	desc.setWrapText(true);
    	V2.getChildren().addAll(nom,date,datef,desc,new Label());
    	V2.setLayoutX(150);
    	V2.setLayoutY(630);
    	V2.setPrefWidth(980-150);
    	V2.setSpacing(30);
    	AnchorPane root = new AnchorPane();
    	root.getChildren().addAll(titre1,titre2,V1,V2);
    	ScrollPane sp = new ScrollPane();
    	sp.setContent(root);
        sp.getStyleClass().add("container");
        sp.setPrefHeight(720);
        sp.setPrefWidth(980);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        pane.getChildren().add(sp);
    }

    public void Notes(){
    	Apprenant A = ((Apprenant) Main.logedAccount);
    	A.calcMoyenne();
    	pane.getChildren().clear();
 		Label titre = new Label("Activité de "+A.getNom()+" "+A.getPrenom());
        titre.setId("titre");
        titre.setLayoutX(100);
        titre.setLayoutY(70);
        pane.getChildren().add(titre);
        
        TableView table = new TableView();
        
        TableColumn<String,QuizPerso> quiz = new TableColumn<>("Quiz");
        quiz.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        quiz.setPrefWidth(882/7);
        TableColumn<String,QuizPerso> date_o = new TableColumn<>("Ouverture");
        date_o.setCellValueFactory(new PropertyValueFactory<>("DateOuvert"));
        date_o.setPrefWidth(882/7);
        TableColumn<String,QuizPerso> date_e = new TableColumn<>("Expiration");
        date_e.setCellValueFactory(new PropertyValueFactory<>("DateExp"));
        date_e.setPrefWidth(882/7);
        TableColumn<String,QuizPerso> taux_a = new TableColumn<>("% Accomplissement");
        taux_a.setCellValueFactory(new PropertyValueFactory<>("TauxAccomp"));
        taux_a.setPrefWidth(882/7);
        TableColumn<String,QuizPerso> taux_r = new TableColumn<>("% Reussite");
        taux_r.setCellValueFactory(new PropertyValueFactory<>("TauxReussite"));
        taux_r.setPrefWidth(882/7);
        TableColumn<String,QuizPerso> soumis = new TableColumn<>("Soumis");
        soumis.setCellValueFactory(new PropertyValueFactory<>("Soumis"));
        soumis.setPrefWidth(882/7);
        
        TableColumn<QuizPerso, Void> voir = new TableColumn("");
        Callback<TableColumn<QuizPerso, Void>, TableCell<QuizPerso, Void>> cellFactory = new Callback<TableColumn<QuizPerso, Void>, TableCell<QuizPerso, Void>>() {
            @Override
            public TableCell<QuizPerso, Void> call(final TableColumn<QuizPerso, Void> param) {
                final TableCell<QuizPerso, Void> cell = new TableCell<QuizPerso, Void>() {
                    private final Button btn = new Button("Voir");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Quiz(getTableView().getItems().get(getIndex()));
                        });
                        btn.setPrefWidth(880/7);
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        voir.setCellFactory(cellFactory);
        voir.setPrefWidth(882/7);
        
        table.getColumns().addAll(quiz,date_o,date_e,soumis,taux_a,taux_r,voir);
        
        for (QuizPerso Q:A.getQuizs()) {
        	if ((Q.estSoumis())||(Q.getDateExp().compareTo(LocalDate.now())<=0)) table.getItems().addAll(Q);
        }
        
        table.setPrefWidth(885);
        table.setLayoutX(50);
        table.setLayoutY(150);
        
        pane.getChildren().add(table);
        
        Label moy = new Label("Moyenne:  "+ A.getMoyenne()+"%");
        moy.getStyleClass().add("info");
        moy.setLayoutX(700);
        moy.setLayoutY(600);
        pane.getChildren().add(moy);
    }
    
    public void Quizs(){
        Apprenant A = (Apprenant) Main.logedAccount;
        pane.getChildren().clear();
 		Label titre = new Label("Quizs");
        titre.setId("titre");
        titre.setLayoutX(100);
        titre.setLayoutY(70);
        pane.getChildren().add(titre);
        VBox v=new VBox();
        v.setSpacing(50);
        v.getStyleClass().add("container");
        HBox h = new HBox();
        h.getStyleClass().add("container");
        h.setSpacing(50);
        v.getChildren().add(h);
        int cpt=0;
        int nb=0;
	        for (QuizPerso Q : A.getQuizs()) {
	        	if ((!Q.estSoumis())&&(Q.getDateExp().compareTo(LocalDate.now())>0)) {
	        		nb++;
		        	Pane quiz = new Pane();
		        	quiz.setPrefWidth(300);
		            quiz.setPrefHeight(250);
		        	quiz.getStyleClass().add("quiz");
		            Label qtitre = new Label(Q.getNom());
		            qtitre.getStyleClass().add("qtitre");
		            qtitre.setPrefWidth(240);
		            qtitre.setLayoutX(30);
		            qtitre.setLayoutY(15);
		            Label dated = new Label("Date d'ouverture:  "+Q.getDateOuvert().toString());
		            dated.setLayoutX(50);
		            dated.setLayoutY(75);
		            Label datef = new Label("Date d'expiration:  "+Q.getDateExp().toString());
		            datef.setLayoutX(50);
		            datef.setLayoutY(105);
		            Label taux = new Label("Taux d'accomplissement:  "+(int)Q.getTauxAccomp()+"%");
		            taux.setLayoutX(50);
		            taux.setLayoutY(135);       
		            
		            Button btn = new Button("Reprendre");
		            btn.setPrefHeight(40);
		            btn.setPrefWidth(100);
		            btn.setLayoutX(180);
		            btn.setLayoutY(190);
		            btn.setOnAction(new EventHandler<ActionEvent>() {
		             	public void handle(ActionEvent event) {
		             		Quiz(Q);
		             	} 
		            });
		            if (Q.getDateOuvert().compareTo(LocalDate.now())>0) btn.setDisable(true);
		            quiz.getChildren().addAll(qtitre,btn,dated,datef,taux);
		            h.getChildren().add(quiz);
		            cpt++;
		            if (cpt==2) {
		            	cpt=0;
		            	h= new HBox();
		            	h.getStyleClass().add("container");
		            	h.setSpacing(50);
		            	v.getChildren().add(h);
		            }
	        	}
	        }
        
	        if (nb==0) {
	        	Label l = new Label("Aucun quiz");
	            pane.getChildren().add(l);
	            l.getStyleClass().add("info");
	            v.getChildren().add(l);
	        }
        
        ScrollPane sp = new ScrollPane();
        sp.getStyleClass().add("container");
        sp.setLayoutX(150);
        sp.setLayoutY(150);
        sp.setPrefHeight(720-150);
        sp.setPrefWidth(830);
        sp.setContent(v);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        pane.getChildren().add(sp);
        v.getChildren().add(new Label());


    }
    
    public void Quiz(QuizPerso Q){
    	Apprenant A = (Apprenant) Main.logedAccount;
    	pane.getChildren().clear();
    	Label titre = new Label("Quiz:  "+Q.getNom());
        titre.setId("titre");
        HBox h = new HBox();
        h.setLayoutY(70);
        h.setPrefWidth(980);
        h.setAlignment(Pos.CENTER);
        h.getChildren().add(titre);
        pane.getChildren().add(h);
        if ((Q.estSoumis()||(Q.getDateExp().compareTo(LocalDate.now())<=0))) {
        	Label label = new Label("Taux de reussite");
        	Label note = new Label((int)Q.getTauxReussite()+"%");
        	Label s = new Label();
        	if (Q.estSoumis()) s.setText("(soumis)");
        	else s.setText("(non soumis)");
        	if (Q.getTauxReussite()<50) note.setStyle("-fx-font-size:50px; -fx-text-fill:#fc5d6d;");
        	else if(Q.getTauxReussite()>65) note.setStyle("-fx-font-size:50px; -fx-text-fill:#6be88a;");
        	else note.setStyle("-fx-font-size:50px; -fx-text-fill:#ebd35e;");
            VBox V = new VBox();
            V.setLayoutY(60);
            V.setLayoutX(800);
            V.setAlignment(Pos.CENTER);
            V.getChildren().addAll(label,note,s);
            pane.getChildren().add(V);
        }
        Label dated = new Label("Date d'ouverture:  "+Q.getDateOuvert().toString());
        Label datef = new Label("Date d'expiration:  "+Q.getDateExp().toString());
        h = new HBox();
        h.setLayoutY(130);
        h.setPrefWidth(980);
        h.setAlignment(Pos.CENTER);
        h.setSpacing(20);
        h.getChildren().addAll(dated,new Label("-"),datef);
        pane.getChildren().add(h);
    	AnchorPane root= new AnchorPane();
    	ScrollPane sp = new ScrollPane();
    	sp.getStyleClass().add("container");
        sp.setLayoutY(200);
        sp.setPrefHeight(720-200);
        sp.setPrefWidth(980);
        sp.setContent(root);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        pane.getChildren().add(sp);
        Button retour = new Button("<< Retour");
        retour.setId("retour");
        retour.setLayoutX(10);
        retour.setLayoutY(10);
        retour.setPrefHeight(40);
        retour.setPrefWidth(100);
        retour.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent event) {
        		if ((Q.estSoumis())||(Q.getDateExp().compareTo(LocalDate.now())<=0)) Notes();
        		else Quizs();
        	}
        });
    	
    	pane.getChildren().add(retour);
    	int y=0;
    	for (Notion n: Q.getNotions()
        ) {
        	Label notion = new Label("Notion:  "+n.getNom());
        	h=new HBox();
        	h.setPrefWidth(980);
            h.setAlignment(Pos.CENTER);
        	h.setLayoutY(y);
        	notion.setStyle("-fx-font-size:26px;");
        	h.getChildren().add(notion);
        	root.getChildren().add(h);
        	y+=60;
 			for (Question q: n.getQuestions()) {
                String type;
                if (q instanceof QO) type="QO:  ";
                else {
                	if (q.getType()==TypeQC.QCM) type="QCM:  ";
                	else type= "QCU:  ";
                }
 				Label qst = new Label(type+q.getQuestion());
 				qst.setMaxWidth(750);
 				qst.setMaxHeight(60);
 				qst.setWrapText(true);
 				qst.getStyleClass().add("info");
                qst.setLayoutY(y+10);
                qst.setLayoutX(100);
                root.getChildren().add(qst);
                if (q instanceof QO) {
                	y+=70;
                	TextField rep = new TextField(((QO) q).getReponse());
                	rep.setPromptText("Reponse");
            		rep.getStyleClass().addAll("info","field");
                    rep.setLayoutY(y+20);
                    rep.setLayoutX(170);
                    if ((Q.estSoumis()||(Q.getDateExp().compareTo(LocalDate.now())<=0))) {
                    	rep.setDisable(true);
                    	HBox H = new HBox();
                    	
                    		Label cor = new Label();
                        	cor.setLayoutX(50);
                        	cor.setLayoutY(y+35);
                        	if (q.evaluer()>0) {
                        		cor.getStyleClass().add("correct");
                        		cor.setText("Correct");
                        	}
                        	else {
                        		cor.getStyleClass().add("incorrect");
                        		cor.setText("Incorrect");
                        	}
                        	root.getChildren().add(cor);
                    	
                    	H.setPrefWidth(480);
                    	H.setLayoutX(480);
                    	H.setLayoutY(y+35);
                    	H.setSpacing(30);
                    	root.getChildren().add(H);
                    	for (Reponse r:q.getReponses()) {
	                    	cor = new Label(r.getReponse());
	                    	cor.getStyleClass().add("correct");                 	
	                    	H.getChildren().add(cor);
                    	}
                    } else {
	                    rep.setOnKeyReleased(e->{
	                    	q.setRepondu(true);
	                    	((QO)q).setReponse(rep.getText());
	                    	if (rep.getText().equals("")) q.setRepondu(false);
	                    	Q.calcTauxAccomp();
	                    	Main.save();
	                    });
                	}
                    root.getChildren().add(rep);
                } else {
                	ToggleGroup group = new ToggleGroup();
                	for (Reponse r:q.getReponses()) {
                		y+=70;
                    	ToggleButton rep = new ToggleButton(r.getReponse());
                    	if (r.isCoche()) rep.setSelected(true);
                		rep.getStyleClass().add("info");
                        rep.setLayoutY(y+20);
                        rep.setLayoutX(170);
                        root.getChildren().add(rep);
                        if (q.getType()==TypeQC.QCU) {
                        	group.getToggles().add(rep);
                        	if ((Q.estSoumis()||(Q.getDateExp().compareTo(LocalDate.now())<=0))) {
                        		rep.setDisable(true);
                            	Label cor = new Label();
                            	cor.setLayoutX(50);
                            	cor.setLayoutY(y+35);
                            	if (r.isCorrect()) {
                            		cor.getStyleClass().add("correct");
                            		cor.setText("Correct");
                            	}
                            	if ((r.isCoche())&&(!r.isCorrect())) {
                            		cor.getStyleClass().add("incorrect");
                            		cor.setText("Incorrect");
                            	}
                            	root.getChildren().add(cor);
                        	} else {
                        		 
		                        	rep.setOnAction(e->{
		                        		for (Reponse R:q.getReponses()) {
		                        			if (R!=r) R.Decocher();
		                        		}
		                        		if (r.isCoche()) {
		                        			r.Decocher();
		                        			q.setRepondu(false);
		                        		}
		                        		else {
		                        			r.Cocher();
		                        			q.setRepondu(true);
		                        		}
		                        		Q.calcTauxAccomp();
		                        		Main.save();
		                        	});
                        		
                        	}
                        } else {
                        	if ((Q.estSoumis()||(Q.getDateExp().compareTo(LocalDate.now())<=0))) {
                    			rep.setDisable(true);
                            	Label cor = new Label();
                            	cor.setLayoutX(50);
                            	cor.setLayoutY(y+35);
                            	if (r.isCorrect()) {
                            		cor.getStyleClass().add("correct");
                            		cor.setText("Correct");
                            	}
                            	if ((r.isCoche())&&(!r.isCorrect())) {
                            		cor.getStyleClass().add("incorrect");
                            		cor.setText("Incorrect");
                            	}
                            	root.getChildren().add(cor);
                    		} else {
	                        	rep.setOnAction(e->{
	                        		if (r.isCoche()) {
	                        			r.Decocher();
	                        			int i=0;
	                        			while ((i<q.getReponses().size())&&(!q.getReponses().get(i).isCoche())) i++;
	                        			if (i>=q.getReponses().size()) q.setRepondu(false);
	                        			else q.setRepondu(true);
	                        		}
	                        		else {
	                        			r.Cocher();
	                        			q.setRepondu(true);
	                        		}
	                        		Q.calcTauxAccomp();
	                        		Main.save();
	                        	});
                    		}
                        }
                    }
                }
                y+=100;
                
            }
 			y+=20;
        }
    	
        
        if ((!Q.estSoumis())&&(Q.getDateExp().compareTo(LocalDate.now())>0)) {
        	Button soumettre = new Button("Soumettre");
        	soumettre.setPrefHeight(50);
        	soumettre.setPrefWidth(150);
        	soumettre.getStyleClass().add("notion");
        	soumettre.setLayoutY(y+20);
        	soumettre.setLayoutX(415);
        	soumettre.setOnAction(e->{
        		Q.soumettre();
        		Quiz(Q);
        		A.calcMoyenne();
        		Main.save();
        	});
        	root.getChildren().add(soumettre);
        }
        
        Label l = new Label();
        l.setLayoutY(y+100);
        root.getChildren().add(l);
     }
    
    public void logOut(ActionEvent event){
    	
    	Login.typeint=0;
    	
        System.out.println("deconnecte");
        Main.logedAccount=null;
        Main.save();
        Stage window = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        AnchorPane root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        
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
        root.getChildren().add(toggle);
        
        window.setTitle("ESIQuiz");
        window.setScene(new Scene(root));
        window.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        window.show();

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
