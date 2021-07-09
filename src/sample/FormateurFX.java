package sample;


import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.IOException;
import java.nio.channels.NetworkChannel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class FormateurFX {
    public Button QuizButton;
    public Button FormationButton;
    public Button NotionButton;
    public Button ApprenantButton ;
    public Button LogoutButton ;
    public Pane pane ;
    
    public void Formation() {
    	
    	Formateur F = (Formateur) Main.logedAccount;
    	AnchorPane root = new AnchorPane();
    	pane.getChildren().clear();
    	VBox V1 = new VBox();
    	VBox V2 = new VBox();
    	VBox V3 = new VBox();
    	VBox V4 = new VBox();
    	
    	Label titre1 = new Label("Formateur:");
    	titre1.setId("titre");
    	titre1.setLayoutX(100);
    	titre1.setLayoutY(70);
    	Label titre2 = new Label("Formation:");
    	titre2.setId("titre");
    	titre2.setLayoutX(100);
    	titre2.setLayoutY(350);
    	root.getChildren().addAll(titre1,titre2);
    	
    	Label login = new Label("Login : ");
    	login.getStyleClass().add("info");
    	TextField Login = new TextField(F.getLogin());
    	Login.setDisable(true);
    	Login.getStyleClass().addAll("info","field");
    	
    	Label mdp = new Label("Mot de passe : ");
    	mdp.getStyleClass().add("info");
    	PasswordField Mdp = new PasswordField();
    	Mdp.setText(F.getMdp());
    	Mdp.setDisable(true);
    	Mdp.getStyleClass().addAll("info","field");
    	
    	Button modif = new Button("Modifier");
    	modif.setPrefSize(150, 50);
    	modif.setOnAction(e->{
    		Login.setDisable(false);
    		Mdp.setDisable(false);
    		Mdp.setText("");
    		Label mdpconf = new Label("Confirmer mot de passe : ");
        	mdpconf.getStyleClass().add("info");
    		PasswordField Mdpconf = new PasswordField();
        	Mdpconf.getStyleClass().addAll("info","field");
        	V2.getChildren().remove(modif);
        	V1.getChildren().add(mdpconf);
        	V2.getChildren().addAll(Mdpconf,modif);
        	V3.setLayoutY(V3.getLayoutY()+80);
        	V4.setLayoutY(V4.getLayoutY()+80);
        	titre2.setLayoutY(titre2.getLayoutY()+80);
    		modif.setText("Valider");
    		modif.setOnAction(e2->{
    			if ((Login.getText().equals(""))||(Mdp.getText().equals(""))||(Mdpconf.getText().equals(""))) {
            		erreur("Tous les champs sont obligatoires");
            	} else {
            		if (!Mdp.getText().equals(Mdpconf.getText())) {
            			erreur("Verifiez votre mot de passe");
            		}else {
            			if ((Main.getFormateur(Login.getText())!=null)&&(Main.getFormateur(Login.getText())!=F)) {
            				erreur("Login existe deja");
            			} else {
                    			System.out.println("modification terminee"); 
                    			F.setLogin(Login.getText());
                    			F.setMdp(Mdp.getText());
                    			Main.save();
                    			Formation();
                    			
                    	}
    				}
            	}
    		});
    	});
    	
    	V1.getChildren().addAll(login,mdp);
    	V1.setLayoutX(150);
    	V1.setLayoutY(140);
    	V1.setPrefWidth(250);
    	V1.setSpacing(35);
    	V1.setAlignment(Pos.BASELINE_RIGHT);
    	V2.getChildren().addAll(Login,Mdp,modif);
    	V2.setLayoutX(420);
    	V2.setLayoutY(140);
    	V2.setPrefWidth(300);
    	V2.setSpacing(20);
    	V2.setAlignment(Pos.CENTER_RIGHT);
    	
    	Label nom = new Label("Nom de la formation : ");
    	nom.getStyleClass().add("info");
    	TextField Nom = new TextField(F.getFormation().getNom());
    	Nom.setDisable(true);
    	Nom.getStyleClass().addAll("info","field");
    	
    	Label date_d = new Label("Début de la formation : ");
    	date_d.getStyleClass().add("info");
    	DatePicker Date_d = new DatePicker();
    	Date_d.setDisable(true);
    	Date_d.setValue(F.getFormation().getDateDebut());
    	Date_d.getStyleClass().addAll("info","field");
    	
    	Label date_f = new Label("Fin de la formation : ");
    	date_f.getStyleClass().add("info");
    	DatePicker Date_f = new DatePicker();
    	Date_f.setDisable(true);
    	Date_f.setValue(F.getFormation().getDateFin());
    	Date_f.getStyleClass().addAll("info","field");
    	
    	Label desc = new Label("Description de la formation : ");
    	desc.getStyleClass().add("info");
    	TextArea Desc = new TextArea(F.getFormation().getDescription());
    	Desc.setDisable(true);
    	Desc.getStyleClass().addAll("info","field");
    	Desc.setPrefHeight(100);
    	
    	Button modif2 = new Button("Modifier");
    	modif2.setPrefSize(150, 50);
    	modif2.setOnAction(e->{
    		Nom.setDisable(false);
    		Date_d.setDisable(false);
    		Date_f.setDisable(false);
    		Desc.setDisable(false);
    		modif2.setText("Valider");
    		modif2.setOnAction(e2->{
    			if ((Nom.getText().equals(""))||(Desc.getText().equals(""))
            			||(Date_d.getValue().toString().equals(""))||(Date_f.getValue().toString().equals(""))) {
            		erreur("Tous les champs sont obligatoires");
            	} else {
                    if (Date_d.getValue().compareTo(Date_f.getValue())>0) {
              			erreur("Date debut doit etre inferieure a date fin");
               		} else {
               			System.out.println("modification terminee");    
               			F.getFormation().setNom(Nom.getText());
               			F.getFormation().setDateDebut(Date_d.getValue());
               			F.getFormation().setDateFin(Date_f.getValue());
               			F.getFormation().setDescription(Desc.getText());
               			Main.save();
            			Formation();
                    }
            	} 
    		});
    	});
    	
    	Button supp = new Button("Supprimer la formation");
    	supp.setPrefSize(250, 50);
    	supp.setOnAction(e->{
    		Main.lf.remove(F);
    		logOut(e);
    	});
    	
    	V3.getChildren().addAll(nom,date_d,date_f,desc);
    	V3.setLayoutX(100);
    	V3.setLayoutY(450);
    	V3.setPrefWidth(300);
    	V3.setSpacing(35);
    	V3.setAlignment(Pos.BASELINE_RIGHT);
    	V4.getChildren().addAll(Nom, Date_d, Date_f,Desc,modif2, new Label(), supp, new Label());
    	V4.setLayoutX(420);
    	V4.setLayoutY(450);
    	V4.setPrefWidth(300);
    	V4.setSpacing(20);
    	V4.setAlignment(Pos.CENTER_RIGHT);
    	
    	root.getChildren().addAll(V1,V2,V3,V4);
    	
    	ScrollPane sp = new ScrollPane();
    	sp.getStyleClass().add("container");
        sp.setPrefHeight(720);
        sp.setPrefWidth(980);
        sp.setContent(root);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        pane.getChildren().add(sp);
    }
    
	public void Notions(){
	
        Formateur F = (Formateur) Main.logedAccount;
        pane.getChildren().clear();
        Label titre = new Label("Notions");
        titre.setId("titre");
        titre.setLayoutX(100);
        titre.setLayoutY(70);
        pane.getChildren().add(titre);
        AnchorPane root = new AnchorPane();
        Button btn =new Button("+");
        btn.getStyleClass().addAll("notion","ajouter");
        btn.setPrefWidth(150);
        btn.setPrefHeight(150);
        btn.setLayoutX(150);
        btn.setOnAction(new EventHandler<ActionEvent>() {
         	public void handle(ActionEvent event) {
         		Notion N = new Notion("");
         		F.getFormation().getNotions().add(N);
         		Notion(N);
         	} 
        });
        root.getChildren().add(btn);
        int cpt=1,x=400,y=0;
        for (Notion N : F.getFormation().getNotions()) {
            btn = new Button(N.getNom());
            btn.getStyleClass().add("notion");
            btn.setPrefWidth(150);
            btn.setPrefHeight(150);
            btn.setLayoutX(x);
            btn.setLayoutY(y);
            btn.setOnAction(new EventHandler<ActionEvent>() {
             	public void handle(ActionEvent event) {
             		ShowNotion(N,0);
             	} 
            });
            Button supp = new Button();
            supp.setPrefSize(50,50);
            supp.getStyleClass().add("del");
            supp.setLayoutY(y);
            supp.setLayoutX(x+150);
            supp.setOnAction(new EventHandler<ActionEvent>() {
            	public void handle(ActionEvent event) {
            		F.getFormation().getNotions().remove(N);
            		Main.save();
            		Notions();
            	}
            });
			Button modif = new Button();
            modif.setPrefSize(50,50);
            modif.getStyleClass().add("edit");
            modif.setLayoutY(y+60);
            modif.setLayoutX(x+150);
            modif.setOnAction(new EventHandler<ActionEvent>() {
            	public void handle(ActionEvent event) {
            		Notion(N);
            	}
            });
            root.getChildren().addAll(btn,modif,supp);
            x+=250;
            cpt++;
            if (cpt==3) {
            	cpt=0;
            	y+=200;
            	x=150;
            }
        }
        Label l = new Label();
        l.setLayoutY(y+200);
        root.getChildren().add(l);
        ScrollPane sp = new ScrollPane();
        sp.getStyleClass().add("container");
        sp.setLayoutY(150);
        sp.setPrefHeight(720-150);
        sp.setPrefWidth(980);
        sp.setContent(root);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        pane.getChildren().add(sp);
	}
	
	public void Notion(Notion N) {
    	
		Formateur F = (Formateur) Main.logedAccount;
    	Stage stage= new Stage();
    	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                event.consume();
            }
        });
    	GridPane root = new GridPane();
    	TextField nom = new TextField(N.getNom());
    	root.setId("root");
    	nom.getStyleClass().addAll("field","info");
    	nom.setPromptText("Nom de la notion");
    	
    	VBox V= new VBox();
    	Button annuler = new Button("Annuler");
    	annuler.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			if (N.getNom().equals("")) F.getFormation().getNotions().remove(N);
    			stage.close();
    		}
    	});
        Button fin = new Button("Sauvegarder");
    	fin.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			if (nom.getText().equals("")) {
    				erreur("Nom de la notion obligatoire");
    			} else {
    				if ((F.getFormation().getNotion(nom.getText())!=null)&&(F.getFormation().getNotion(nom.getText())!=N)) {
        				erreur("Notion existe deja");
        			} else {
        				if (N.getNom().equals("")) {
        					N.setNom(nom.getText());
        					ShowNotion(N,0);
        				}
        				else {
        					N.setNom(nom.getText());
        					Notions();
        				}
        				Main.save();
            			stage.close();
        			}
    			}
    			
			}
    	});
    	
    	HBox H2 = new HBox();
    	H2.getChildren().addAll(fin,annuler);
    	H2.setAlignment(Pos.CENTER_RIGHT);
    	H2.setSpacing(20);
    	V.getChildren().addAll(nom,H2);
    	V.setSpacing(20);
    	V.setAlignment(Pos.CENTER_RIGHT);
    	root.getChildren().add(V);
    	root.setAlignment(Pos.CENTER);
    	stage.setScene(new Scene(root,500,200));
    	stage.setTitle("Notion - ESIQuiz");
    	stage.setResizable(false);
    	stage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    	stage.show();
    }
    
    public void ShowNotion(Notion N, double scroll) {
    	
 		pane.getChildren().clear();
 		AnchorPane root = new AnchorPane();
        ScrollPane sp = new ScrollPane();
    	sp.getStyleClass().add("container");
    	sp.setLayoutX(100);
        sp.setLayoutY(200);
        sp.setPrefHeight(720-200);
        sp.setPrefWidth(980-100);
        sp.setContent(root);
        sp.setVvalue(scroll);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        pane.getChildren().add(sp);
 		Label titre = new Label("Questions de "+N.getNom());
        titre.setId("titre");
        titre.setLayoutX(100);
        titre.setLayoutY(70);
        pane.getChildren().add(titre);
        Button retour = new Button("<< Retour");
        retour.setId("retour");
        retour.setLayoutX(10);
        retour.setLayoutY(10);
        retour.setPrefHeight(40);
        retour.setPrefWidth(100);
        retour.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent event) {
        		Notions();
        	}
        });
        HBox H = new HBox();
    	H.setLayoutX(600);
    	H.setLayoutY(130);
    	H.setSpacing(25);
        Label ajQCM = new Label("+QCM");
        ajQCM.setAlignment(Pos.CENTER);
        ajQCM.setPrefSize(80, 40);
        ajQCM.getStyleClass().add("aj");
        Label ajQCU = new Label("+QCU");
        ajQCU.setAlignment(Pos.CENTER);
        ajQCU.setPrefSize(80, 40);
        ajQCU.getStyleClass().add("aj");
        Label ajQO = new Label("+QO");
        ajQO.setAlignment(Pos.CENTER);
        ajQO.setPrefSize(80, 40);
        ajQO.getStyleClass().add("aj");
        ajQCM.setOnMouseClicked(e->{
        		QC Q = new QC("",TypeQC.QCM);
        		N.ajoutQuestion(Q);
        		Question(N,Q, sp.getVvalue());
        
        });
        ajQCU.setText("+ QCU");
        ajQCU.setOnMouseClicked(e->{ 
        		QC Q = new QC("",TypeQC.QCU);
        		N.ajoutQuestion(Q);
        		Question(N,Q, sp.getVvalue());
        	
        });
        ajQO.setText("+ QO");
        ajQO.setOnMouseClicked(e->{ 
        		QO Q = new QO("");
        		N.ajoutQuestion(Q);
        		Question(N,Q, sp.getVvalue());
        	
        });
        H.getChildren().addAll(ajQCM,ajQCU,ajQO);
        pane.getChildren().add(retour);
        
 		if (N.getQuestions().size()==0) {
 			Label l = new Label("Aucune question");
            root.getChildren().add(l);
            l.getStyleClass().add("info");
            l.setLayoutX(100);
 		} else {
 			int y=0;
 			for (Question q: N.getQuestions()) {
 				Button supp = new Button();
                supp.setPrefSize(50,50);
                supp.getStyleClass().add("del");
                supp.setLayoutY(y);
                supp.setOnAction(new EventHandler<ActionEvent>() {
                	public void handle(ActionEvent event) {
                		N.suppQuestion(q);
                		Main.save();
                		ShowNotion(N,sp.getVvalue());
                	}
                });
 				Button modif = new Button();
                modif.setPrefSize(50,50);
                modif.getStyleClass().add("edit");
                modif.setLayoutY(y);
                modif.setLayoutX(60);
                modif.setOnAction(new EventHandler<ActionEvent>() {
                	public void handle(ActionEvent event) {
                		Question(N,q, sp.getVvalue());
                	}
                });
                String type;
                if (q instanceof QO) type="QO:  ";
                else {
                	if (q.getType()==TypeQC.QCM) type="QCM:  ";
                	else type= "QCU:  ";
                }
 				Label qst = new Label(type+q.getQuestion());
 				qst.setMaxWidth(600);
 				qst.setMaxHeight(60);
 				qst.setWrapText(true);
 				qst.getStyleClass().add("info");
                qst.setLayoutY(y+10);
                qst.setLayoutX(150);
                y+=50;
                root.getChildren().addAll(qst,modif,supp);
                for (Reponse r:q.getReponses()) {
                	y+=40;
            		Text rep = new Text(r.getReponse());
            		rep.getStyleClass().add("info");
            		if (r.isCorrect()) rep.setFill(Color.GREEN);
            		else rep.setFill(Color.RED);
                    rep.setLayoutY(y+20);
                    rep.setLayoutX(250);
                    root.getChildren().add(rep);
                    
                }
                y+=80;
                
            }
 			Label l = new Label();
            l.setLayoutY(y);
            root.getChildren().add(l);
 		}
 		pane.getChildren().add(H);
    }
    
    public void Question(Notion N, Question Q, double scroll) {
    	
    	Stage stage= new Stage();
    	AnchorPane root = new AnchorPane();
    	root.setId("root");
    	
    	ScrollPane sp = new ScrollPane();
    	sp.getStyleClass().add("container");
        sp.setPrefHeight(700);
        sp.setPrefWidth(500);
        sp.setContent(root);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    	VBox V2 = new VBox();
    	V2.setAlignment(Pos.CENTER_RIGHT);
    	V2.setSpacing(22);
    	V2.setPrefWidth(500);
    	V2.setLayoutX(100);
    	V2.setLayoutY(100);
    	
    	VBox V3 = new VBox();
    	V3.setAlignment(Pos.CENTER_RIGHT);
    	V3.setSpacing(20);
    	V3.setLayoutX(610);
    	if (Q instanceof QO) V3.setLayoutY(240);
    	else V3.setLayoutY(310);
    	
    	root.getChildren().addAll(V2,V3);
    	
    	TextField question = new TextField(Q.getQuestion());
    	question.setPromptText("Question");
    	question.getStyleClass().addAll("field","info");
    	V2.getChildren().add(question);
    	
    	HBox H1 = new HBox();
    	H1.setSpacing(15);
    	H1.setAlignment(Pos.CENTER_RIGHT);
    	
    	HBox H2 = new HBox();
    	H2.setSpacing(15);
    	H2.setAlignment(Pos.CENTER_RIGHT);
    	
    	Button ajRepCor = new Button();
    	ajRepCor.setText("+ Reponse Correcte");
    	Button ajRepInc = new Button();
    	ajRepInc.setText("+ Reponse Incorrecte");
    	Button fin = new Button();
    	fin.setText("Sauvegarder");
    	Button annuler = new Button();
    	annuler.setText("Annuler");
    	
    	ArrayList<Rep> Reponses= new ArrayList<Rep>();
	    if (Q.getReponses().size()!=0) {
	    	if (Q instanceof QC) V3.setLayoutY(170);
	    	int i=0,j=0,k=0;
	    	for (Reponse R:Q.getReponses()) {
	    		Rep rep = new Rep();
	    		rep.Reponse.setText(R.getReponse());
	    		if (R.isCorrect()) rep.Reponse.setPromptText("Reponse Correcte");
	    		else rep.Reponse.setPromptText("Reponse Incorrecte");
	    		rep.Reponse.getStyleClass().addAll("field","info");
	    		Reponses.add(rep);
	    		rep.Cor=R.isCorrect();
    			Button supp = new Button();
    			supp.setPrefSize(50,50);
                supp.getStyleClass().add("del");
    			supp.setOnAction(new EventHandler<ActionEvent>() {
    				public void handle(ActionEvent event) {
    					Reponses.remove(rep);
    					V2.getChildren().remove(rep.Reponse);
    					V3.getChildren().remove(supp);
    				}
    			});
    			Label vide = new Label();
    			vide.setPrefSize(50,50);
    			
    			V2.getChildren().add(rep.Reponse);
    			if ((Q instanceof QO)&&(i>0)) V3.getChildren().add(supp);
    			else if ((Q instanceof QC)&&(R.isCorrect())&&(j>0)) V3.getChildren().add(supp);
    			else if ((Q instanceof QC)&&(!R.isCorrect())&&(k>0)) V3.getChildren().add(supp);
    			else if (Q instanceof QC) V3.getChildren().add(vide);
	    		i++;
	    		if (R.isCorrect()) j++;
	    		else k++;
	    	}
	    } else {
	    	Rep rep = new Rep();
	    	rep.Reponse.getStyleClass().addAll("field","info");
	    	rep.Reponse.setPromptText("Reponse Correcte");
	    	Reponses.add(rep);
	    	V2.getChildren().add(Reponses.get(Reponses.size()-1).Reponse);
	    	Reponses.get(Reponses.size()-1).Cor=true;
	    	if (!(Q instanceof QO)) {
	    		rep = new Rep();
	    		rep.Reponse.getStyleClass().addAll("field","info");
		    	rep.Reponse.setPromptText("Reponse Incorrecte");
	    		Reponses.add(rep);
	    		V2.getChildren().add(Reponses.get(Reponses.size()-1).Reponse);
	    		Reponses.get(Reponses.size()-1).Cor=false;
	    	}
	    	
	    }
	    
	    if ((Q instanceof QO) || ((Q instanceof QC)&&(Q.getType()==TypeQC.QCM))) H1.getChildren().add(ajRepCor);
    	if (!(Q instanceof QO)) H1.getChildren().add(ajRepInc);
    	H2.getChildren().addAll(fin,annuler);
    	Label l = new Label();
    	V2.getChildren().addAll(H1,H2,l);
    	
	    ajRepCor.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			Rep rep = new Rep();
    			rep.Reponse.getStyleClass().addAll("field","info");
    			rep.Reponse.setPromptText("Reponse Correcte");
    			rep.Cor=true;
    			Reponses.add(rep);
    			Button supp = new Button();
    			supp.setPrefSize(50,50);
                supp.getStyleClass().add("del");
    			supp.setOnAction(new EventHandler<ActionEvent>() {
    				public void handle(ActionEvent event) {
    					Reponses.remove(rep);
    					V2.getChildren().remove(rep.Reponse);
    					V3.getChildren().remove(supp);
    				}
    			});
    			V2.getChildren().remove(H1);
    			V2.getChildren().remove(H2);
    			V2.getChildren().remove(l);
    			V2.getChildren().addAll(rep.Reponse,H1,H2,l);
    			V3.getChildren().add(supp);
    		}
    	});
	   
    	ajRepInc.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			Rep rep = new Rep();
    			rep.Reponse.getStyleClass().addAll("field","info");
    			rep.Reponse.setPromptText("Reponse Incorrecte");
    			rep.Cor=false;
    			Reponses.add(rep);
    			Button supp = new Button();
    			supp.setPrefSize(50,50);
                supp.getStyleClass().add("del");
    			supp.setOnAction(new EventHandler<ActionEvent>() {
    				public void handle(ActionEvent event) {
    					Reponses.remove(rep);
    					V2.getChildren().remove(rep.Reponse);
    					V3.getChildren().remove(supp);
    				}
    			});
    			V2.getChildren().remove(H1);
    			V2.getChildren().remove(H2);
    			V2.getChildren().remove(l);
    			V2.getChildren().addAll(rep.Reponse,H1,H2,l);
    			V3.getChildren().add(supp);
    		}
    	});
 
    	fin.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
	    		if (question.getText().equals("")) {
	    			erreur("Question obligatoire");
	    		} else {
	    			boolean vide=false;
	    			for (Rep rep:Reponses) {
	    				if (rep.Reponse.getText().equals("")) vide = true;
	    			}
	    			if (vide) {
	    				erreur("Reponses obligatoires");
	    			} else {
	    				if ((N.getQuestion(question.getText())!= null)&&(N.getQuestion(question.getText())!=Q)) {
	    					erreur("Question existe deja");
	    				} else {
	    					Random rand = new Random();
	    					boolean change = rand.nextBoolean();
	    					if ((Q instanceof QC)&&(change)) {
	    						Rep r = new Rep();
	    						if (Reponses.size()==2) {
			    					r.Cor=Reponses.get(0).Cor;
			    					r.Reponse.setText(Reponses.get(0).Reponse.getText());
			    					Reponses.set(0, Reponses.get(1));
			    					Reponses.set(1, r);
	    						} else {
	    							int i = rand.nextInt(Reponses.size());
			    					while ((i==0)||(i==1)) i = rand.nextInt(Reponses.size());
			    					r.Cor=Reponses.get(i).Cor;
			    					r.Reponse.setText(Reponses.get(i).Reponse.getText());
			    					Reponses.set(i, Reponses.get(0));
			    					Reponses.set(0, r);
			    					i = rand.nextInt(Reponses.size());
			    					while ((i==0)||(i==1)) i = rand.nextInt(Reponses.size());
			    					r = new Rep();
			    					r.Cor=Reponses.get(i).Cor;
			    					r.Reponse.setText(Reponses.get(i).Reponse.getText());
			    					Reponses.set(i, Reponses.get(1));
			    					Reponses.set(1, r);
	    						}
	    					}
	    					
		    				Q.setQuestion(question.getText());
			    			Q.getReponses().clear();
			    			for (Rep r: Reponses) {
			    				Q.getReponses().add(new Reponse(r.Reponse.getText(),r.Cor));
			    			}
			    			Main.save();
			    			ShowNotion(N,scroll);
			    			stage.close();
	    				}
	    			}
	    		}
	    		
			}
    	});
    	
    	annuler.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			if (Q.getReponses().size()==0) N.getQuestions().remove(Q);
    			stage.close();
    		}
    	});
    	
    	stage.setScene(new Scene(sp,700,500));
    	stage.setResizable(false);
    	stage.setTitle("Question - ESIQuiz");
    	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                event.consume();
            }
        });
    	stage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    	stage.show();
    }

    public void Quizs(){
    	
    	Formateur F = (Formateur) Main.logedAccount;
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
        Button btn =new Button("+");
        btn.getStyleClass().addAll("notion","ajouter");
        btn.setPrefWidth(300);
        btn.setPrefHeight(200);
        btn.setOnAction(new EventHandler<ActionEvent>() {
         	public void handle(ActionEvent event) {
         		Quiz Q = new Quiz();
         		F.getFormation().getQuizs().add(Q);
         		Quiz(Q);
         	} 
        });
        h.getChildren().add(btn);
        v.getChildren().add(h);
        int cpt=1;
        for (Quiz Q : F.getFormation().getQuizs()) {
        	Pane quiz = new Pane();
        	quiz.setPrefWidth(300);
            quiz.setPrefHeight(200);
        	quiz.getStyleClass().add("quiz");
            Label qtitre = new Label(Q.getNom());
            qtitre.getStyleClass().add("qtitre");
            qtitre.setPrefWidth(150);
            qtitre.setLayoutX(30);
            qtitre.setLayoutY(15);
            Label dated = new Label("Date d'ouverture:  "+Q.getDateOuvert().toString());
            dated.setLayoutX(50);
            dated.setLayoutY(75);
            Label datef = new Label("Date d'expiration:  "+Q.getDateExp().toString());
            datef.setLayoutX(50);
            datef.setLayoutY(105);
            
			if (Q.getDateOuvert().compareTo(LocalDate.now())>0) {
				Button modif = new Button();
	            modif.setPrefSize(50,50);
	            modif.getStyleClass().add("edit");
	            modif.setLayoutX(180);
	            modif.setLayoutY(10);
	            modif.setOnAction(new EventHandler<ActionEvent>() {
	            	public void handle(ActionEvent event) {
	            		Quiz(Q);
	            	}
	            });
				Button supp = new Button();
				supp.setPrefSize(50,50);
				supp.setLayoutX(240);
				supp.setLayoutY(10);
				supp.getStyleClass().add("del");
				supp.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						F.getFormation().getQuizs().remove(Q);
						for (Apprenant a:F.getFormation().getApprenants()) {
							a.getQuizs().remove(a.getQuiz(Q.getNom()));
						}
						Main.save();
						Quizs();
					}
				});
				quiz.getChildren().addAll(supp,modif);
			}
            btn = new Button("Voir");
            btn.setPrefHeight(40);
            btn.setPrefWidth(70);
            btn.setLayoutX(210);
            btn.setLayoutY(140);
            btn.setOnAction(new EventHandler<ActionEvent>() {
             	public void handle(ActionEvent event) {
             		ShowQuiz(Q,0);
             	} 
            });
            quiz.getChildren().addAll(qtitre,btn,dated,datef);
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
    
    public void Quiz(Quiz Q) {
    	Formation F = ((Formateur) Main.logedAccount).getFormation();
    	Stage stage= new Stage();
    	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                event.consume();
            }
        });
    	AnchorPane root = new AnchorPane();
    	root.setId("root");
    	ScrollPane sp = new ScrollPane();
    	sp.getStyleClass().add("container");
        sp.setPrefHeight(700);
        sp.setPrefWidth(500);
        sp.setContent(root);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    	stage.setScene(new Scene(sp,700,500));
    	stage.setResizable(false);
    	stage.setTitle("Quiz - ESIQuiz");
    	
    	TextField nom = new TextField(Q.getNom());
    	nom.getStyleClass().addAll("field","info");
    	nom.setPromptText("Nom du quiz");
    	DatePicker date_d = new DatePicker();
    	date_d.setValue(Q.getDateOuvert());
    	date_d.setPromptText("Date d'ouverture");
    	date_d.getStyleClass().addAll("field","info");
    	date_d.setPrefWidth(400);
    	DatePicker date_f = new DatePicker();
    	date_f.setValue(Q.getDateExp());
    	date_f.setPromptText("Date d'expiration");
    	date_f.getStyleClass().addAll("field","info");
    	date_f.setPrefWidth(400);
    	
    	Button fin = new Button();
    	if (Q.getNom().equals("")) fin.setText("Suivant >>");
    	else fin.setText("Sauvegarder");
    	fin.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
	    		if ((nom.getText().equals(""))||(date_d.getValue()==null)||(date_f.getValue()==null)) {
	    			erreur("Tous les champs sont obligatoires");
	    		} else {
	    			if ((F.getQuiz(nom.getText())!=null)&&(F.getQuiz(nom.getText())!=Q)) {
	    				erreur("Nom du quiz existe deja");
	    			} else {
		    			if (date_d.getValue().compareTo(date_f.getValue())>0) {
	            			erreur("Date d'ouverture doit etre inferieure a date d'expiration");
	            		} else {
	            			if (date_d.getValue().compareTo(LocalDate.now())<=0) {
	            				erreur("Date d'ouverture doit etre superieure a date d'aujourdhui");
	            			} else {
	            				
	            				if (Q.getNom().equals("")) {
					    			root.getChildren().clear();
					    			VBox v = new VBox();
					    			v.setPrefWidth(400);
					    	    	v.setSpacing(20);
					    	    	v.setLayoutX(150);
					    	    	v.setLayoutY(150);
					    	    	Label l = new Label("Notions:");
					    	    	l.setLayoutX(100);
					    	    	l.setLayoutY(70);
					    	    	l.setId("titre");
					    	    	root.getChildren().add(l);
					    			root.getChildren().add(v);
					    			ArrayList<ToggleButton> notions = new ArrayList<ToggleButton>();
					    			ArrayList<TextField> nbq = new ArrayList<TextField>();
					    	    	for (Notion N: F.getNotions()) {
					    	    		TextField t = new TextField();
					    	    		t.setPromptText("Nombre de questions");
					    	    		t.getStyleClass().addAll("field");
					    	    		t.setDisable(true);
					    	    		nbq.add(t);
					    	    		ToggleButton n = new ToggleButton(N.getNom());
					    	    		n.setOnAction(e->{
					    	    			if (t.isDisabled()) t.setDisable(false);
					    	    			else t.setDisable(true);
					    	    		});
					    	    		if (N.getQuestions().size()<1) {
					    	    			n.setDisable(true);
					    	    			t.setPromptText("Aucune question");
					    	    		}
					    	    		notions.add(n);
					    	    		HBox h = new HBox();
					    	    		h.setSpacing(20);
					    	    		h.getChildren().addAll(n,t);
					    	    		v.getChildren().add(h);
					    	    	}
					    	    	HBox h = new HBox();
					    	    	h.setAlignment(Pos.CENTER_RIGHT);
					    	    	h.setSpacing(15);
					    	    	Button fin = new Button("Générer Quiz");
					    	    	fin.setOnAction(f-> {
					    	    		int i=0;
					    				while ((i<notions.size())&&(!notions.get(i).isSelected())) i++;
					    				if (i>=notions.size()) {
					    					erreur("Choisissez les notions");
					    				} else {
					    					for(i=0;i<nbq.size();i++) {
					    						if(!nbq.get(i).isDisabled()) {
							    	    			int j=0;
							    	    			while ((j<nbq.get(i).getText().length())&&(nbq.get(i).getText().charAt(j)>='0')&&(nbq.get(i).getText().charAt(j)<='9')) j++;
							    	    			if (j<nbq.get(i).getText().length()) {
							    	    				erreur("Donner le nombre de questions");
							    	    				break;
							    	    			} else {
							    	    				j=0;
							    	    				while ((j<nbq.get(i).getText().length())&&(nbq.get(i).getText().charAt(j)=='0')) j++;
							    	    				if (j>=nbq.get(i).getText().length()) {
							    	    					erreur("Donner le nombre de questions");
							    	    					break;
							    	    				} else {
							    	    					if (F.getNotion(notions.get(i).getText()).getQuestions().size()<Integer.parseInt(nbq.get(i).getText())) {
							    	    						erreur("Nombre de questions excede la limite");
							    	    						break;
							    	    					}
							    	    				}
							    	    			}
					    						}
						    	    		}
					    					if (i>=nbq.size()) {
					    						Random rand= new Random();
					    						for (ToggleButton n:notions) {
					    							if (!nbq.get(notions.indexOf(n)).isDisabled()) {
					    								Notion N = new Notion(n.getText());
					    								Q.getNotions().add(N);
						    							int nb = Integer.parseInt(nbq.get(notions.indexOf(n)).getText());
						    							while (nb>0) {
						    								Question qst=null, q=null;
						    								int k=rand.nextInt(F.getNotion(n.getText()).getQuestions().size());
						    								while(N.getQuestion((q=F.getNotion(n.getText()).getQuestions().get(k)).getQuestion())!=null) {
						    									k=rand.nextInt(F.getNotion(n.getText()).getQuestions().size());
						    								}
								    						if (q instanceof QO) qst=new QO(q.getQuestion());
							    							else qst=new QC(q.getQuestion(),q.getType());
							    							for (Reponse r:q.getReponses()) {
							    								qst.getReponses().add(new Reponse(r.getReponse(), r.isCorrect()));
							    							}
							    							N.getQuestions().add(qst);
							    							nb--;
						    							}
					    							}
					    							
					    						}
					    						Q.setNom(nom.getText());
								    			Q.setDateOuvert(date_d.getValue());
								    			Q.setDateExp(date_f.getValue());
								    			for(Apprenant A:F.getApprenants()) {
						    						A.getQuizs().add(Q.creerQuizPerso());
						    					}
								    			Main.save();
								    			ShowQuiz(Q,0);
							    				stage.close();
					    					}
					    				}
					    	    	});
					    	    	Button annuler = new Button();
					    	    	annuler.setText("Annuler");
					    	    	annuler.setOnAction(new EventHandler<ActionEvent>() {
					    	    		public void handle(ActionEvent event) {
					    	    			if (Q.getNom().equals("")) F.getQuizs().remove(Q);
					    	    			stage.close();
					    	    		}
					    	    	});
					    	    	h.getChildren().addAll(fin, annuler);
					    	    	v.getChildren().addAll(h,new Label());
					    	    	
	            				} else {
	            					String An = Q.getNom();
					    	    	Q.setNom(nom.getText());
					    			Q.setDateOuvert(date_d.getValue());
					    			Q.setDateExp(date_f.getValue());
					    			for(Apprenant A:F.getApprenants()) {
					    				int i = A.getQuizs().indexOf(A.getQuiz(An));
			    						A.getQuizs().set(i,Q.creerQuizPerso());
			    					}
					    			Main.save();
					    			Quizs();
				    				stage.close();
	            				}		    					
		    				}
		   				}
	  				}	
	      		}
	    	}	
    	});
    	
    	VBox V2 = new VBox();
    	V2.setPrefWidth(400);
    	V2.setSpacing(20);
    	V2.setLayoutX(150);
    	V2.setLayoutY(100);
    	
    	HBox H = new HBox();
    	H.setAlignment(Pos.CENTER_RIGHT);
    	H.setSpacing(15);
    	
    	Button annuler = new Button();
    	annuler.setText("Annuler");
    	annuler.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			if (Q.getNom().equals("")) F.getQuizs().remove(Q);
    			stage.close();
    		}
    	});
  
    	V2.getChildren().addAll(nom, date_d,date_f);
    
    	H = new HBox();
    	H.setAlignment(Pos.CENTER_RIGHT);
    	H.setSpacing(15);
    	H.getChildren().addAll(fin,annuler);
    	
    	V2.getChildren().addAll(new Label(),H,new Label());
    	root.getChildren().addAll(V2);
    	stage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    	stage.show();
    	
    }
    
    public void ShowQuiz(Quiz Q, double scroll) {
    	Formation F = ((Formateur) Main.logedAccount).getFormation();
    	pane.getChildren().clear();
    	Label titre = new Label("Quiz:  "+Q.getNom());
        titre.setId("titre");
        HBox h = new HBox();
        h.setLayoutY(70);
        h.setPrefWidth(980);
        h.setAlignment(Pos.CENTER);
        h.getChildren().add(titre);
        pane.getChildren().add(h);
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
        sp.setVvalue(scroll);
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
        		Quizs();
        	}
        });
    	
    	pane.getChildren().add(retour);
    	
    	if (Q.getDateOuvert().compareTo(LocalDate.now())>0) {
    		Button aj = new Button("+Question");
    		aj.getStyleClass().add("aj");
    		root.getChildren().add(aj);
    		aj.setLayoutX(780);
    		aj.setLayoutY(150);
    		aj.setOnAction(e->{
    			Stage stage2= new Stage();
    	    	AnchorPane root2 = new AnchorPane();
    	    	root2.setId("root");
    	    	ScrollPane sp2 = new ScrollPane();
    	        sp2.getStyleClass().add("container");
    	        sp2.setPrefHeight(700);
    	        sp2.setPrefWidth(500);
    	        sp2.setContent(root2);
    	        sp2.setHbarPolicy(ScrollBarPolicy.NEVER);
    	        sp2.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    	    	stage2.setScene(new Scene(sp2,700,500));
    	    	stage2.setResizable(false);
    	    	stage2.setTitle("Ajouter Question - ESIQuiz");
    	    	Label l = new Label("Notion");
    	    	l.setId("titre");
    	    	l.setLayoutX(50);
    	    	l.setLayoutY(80);
    	    	root2.getChildren().add(l);
    	    	int y = 180;
    	    	for (Notion n : F.getNotions()) {
    	    		if (((Q.getNotion(n.getNom())!=null)&&(Q.getNotion(n.getNom()).getQuestions().size()<n.getQuestions().size()))
    	    				||((Q.getNotion(n.getNom())==null)&&(n.getQuestions().size()>0))) {
	    	            Button btn = new Button(n.getNom());
	    	            btn.getStyleClass().add("notion");
	    	            btn.setPrefWidth(500);
	    	            btn.setPrefHeight(50);
	    	            btn.setLayoutX(100);
	    	            btn.setLayoutY(y);
	    	            btn.setOnAction(new EventHandler<ActionEvent>() {
	    	             	public void handle(ActionEvent event) {
	    	             		if (Q.getNotion(n.getNom())==null) Q.getNotions().add(new Notion(n.getNom()));
	    	             		Question qst = null, nq = null;
	    	                	Random rand = new Random();
	    	                	int i = rand.nextInt(F.getNotion(n.getNom()).getQuestions().size());
	    	                	while (Q.getNotion(n.getNom()).getQuestion((qst=n.getQuestions().get(i)).getQuestion())!=null) {
	    	                		i = rand.nextInt(F.getNotion(n.getNom()).getQuestions().size());
	    	                	}
	    	                	if (qst instanceof QO) nq = new QO(qst.getQuestion());
	    	                	else nq= new QC(qst.getQuestion(),qst.getType());
	    	                	for (Reponse r : qst.getReponses()) {
	    	                		nq.getReponses().add(new Reponse(r.getReponse(),r.isCorrect()));
	    	                	}
	    	                	Q.getNotion(n.getNom()).getQuestions().add(nq);
	    	                	for (Apprenant A:F.getApprenants()) {
	    	                   		if (qst instanceof QO) nq = new QO(qst.getQuestion());
	    		                	else nq= new QC(qst.getQuestion(),qst.getType());
	    		                	for (Reponse r : qst.getReponses()) {
	    		                		nq.getReponses().add(new Reponse(r.getReponse(),r.isCorrect()));
	    		                	}
	    		                	if (A.getQuiz(Q.getNom()).getNotion(n.getNom())==null) A.getQuiz(Q.getNom()).getNotions().add(new Notion(n.getNom())); 
	    		                	A.getQuiz(Q.getNom()).getNotion(n.getNom()).getQuestions().add(nq);
	    	                	}
	    	                	Main.save();
	    	                	ShowQuiz(Q,0);
	    	                	stage2.close();
	    	             	} 
	    	            });
	    	            root2.getChildren().add(btn);
	    	            y+=100;
    				}
    	        }
    	    	l=new Label();
    	    	l.setLayoutY(y);
    	    	root2.getChildren().add(l);
    	    	stage2.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    	    	stage2.show();
    		});
    		pane.getChildren().add(aj);
    	}
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
 				if (Q.getDateOuvert().compareTo(LocalDate.now())>0) {
	 				Button remplacer= new Button();
	 				remplacer.setPrefSize(50,50);
	                remplacer.getStyleClass().add("remplacer");
	                remplacer.setLayoutY(y);
	                remplacer.setLayoutX(110);
	                remplacer.setOnAction(new EventHandler<ActionEvent>() {
	                	public void handle(ActionEvent event) {
	                		if (n.getQuestions().size()==F.getNotion(n.getNom()).getQuestions().size()) {
	                			erreur("Toutes les questions sont utilisees");
	                		} else {
			                	Question qst = null, nq = null;
			                	Random rand = new Random();
			                	int i = rand.nextInt(F.getNotion(n.getNom()).getQuestions().size());
			                	while (n.getQuestion((qst=F.getNotion(n.getNom()).getQuestions().get(i)).getQuestion())!=null) {
			                		i = rand.nextInt(F.getNotion(n.getNom()).getQuestions().size());
			                	}
			                	if (qst instanceof QO) nq = new QO(qst.getQuestion());
			                	else nq= new QC(qst.getQuestion(),qst.getType());
			                	for (Reponse r : qst.getReponses()) {
			                		nq.getReponses().add(new Reponse(r.getReponse(),r.isCorrect()));
			                	}
			                	i = n.getQuestions().indexOf(q);
			                	n.getQuestions().set(i, nq);
			                	for (Apprenant A:F.getApprenants()) {
			                   		if (qst instanceof QO) nq = new QO(qst.getQuestion());
				                	else nq= new QC(qst.getQuestion(),qst.getType());
				                	for (Reponse r : qst.getReponses()) {
				                		nq.getReponses().add(new Reponse(r.getReponse(),r.isCorrect()));
				                	}
				                	Question qqq=A.getQuiz(Q.getNom()).getNotion(n.getNom()).getQuestion(q.getQuestion());
				                	i = A.getQuiz(Q.getNom()).getNotion(n.getNom()).getQuestions().indexOf(qqq);
				                	A.getQuiz(Q.getNom()).getNotion(n.getNom()).getQuestions().set(i, nq);
			                		
			                	}
			                	Main.save();
			                	ShowQuiz(Q, sp.getVvalue());
	                		}
	                	}
	                });
	 				Button supp = new Button();
	                supp.setPrefSize(50,50);
	                supp.getStyleClass().add("del");
	                supp.setLayoutX(50);
	                supp.setLayoutY(y);
	                supp.setOnAction(new EventHandler<ActionEvent>() {
	                	public void handle(ActionEvent event) {
	                		n.getQuestions().remove(q);
	                    	if(n.getQuestions().size()==0) Q.getNotions().remove(n);
	                    	if(Q.getNotions().size()==0) {
	                    		F.getQuizs().remove(Q);
	                    		Quizs();
	                    	}
	                    	else ShowQuiz(Q,sp.getVvalue());
	                    	for (Apprenant A:F.getApprenants()) {
		                   		Notion ns = A.getQuiz(Q.getNom()).getNotion(n.getNom());
		                   		ns.getQuestions().remove(ns.getQuestion(q.getQuestion()));
		                   		if(ns.getQuestions().size()==0) A.getQuiz(Q.getNom()).getNotions().remove(n);
		                    	if(A.getQuiz(Q.getNom()).getNotions().size()==0) A.getQuizs().remove(Q);
		                	}
	                    	Main.save();
	                	}
	                });
	                root.getChildren().addAll(remplacer,supp);
 				}

                String type;
                if (q instanceof QO) type="QO:  ";
                else {
                	if (q.getType()==TypeQC.QCM) type="QCM:  ";
                	else type= "QCU:  ";
                }
 				Label qst = new Label(type+q.getQuestion());
 				if (Q.getDateOuvert().compareTo(LocalDate.now())>0) qst.setMaxWidth(650);
 				else qst.setMaxWidth(750);
 				qst.setMaxHeight(60);
 				qst.setWrapText(true);
 				qst.getStyleClass().add("info");
                qst.setLayoutY(y+10);
                if (Q.getDateOuvert().compareTo(LocalDate.now())>0) qst.setLayoutX(200);
                else qst.setLayoutX(100);
                y+=50;
                root.getChildren().add(qst);
                for (Reponse r:q.getReponses()) {
                	y+=40;
            		Text rep = new Text(r.getReponse());
            		rep.getStyleClass().add("info");
            		if (r.isCorrect()) rep.setFill(Color.GREEN);
            		else rep.setFill(Color.RED);
                    rep.setLayoutY(y+20);
                    if (Q.getDateOuvert().compareTo(LocalDate.now())>0) rep.setLayoutX(250);
                    else rep.setLayoutX(150);
                    root.getChildren().add(rep);
                    
                }
                y+=80;
                
            }
 			
        }
    	Label l = new Label();
        l.setLayoutY(y);
        root.getChildren().add(l);
    }
    
    public void Apprenants(){
        
        Formateur F = (Formateur) Main.logedAccount;
        pane.getChildren().clear();
        
        Label titre = new Label("Apprenants");
        titre.setId("titre");
        titre.setLayoutX(100);
        titre.setLayoutY(70);
        pane.getChildren().add(titre);
        Button classer = new Button("Classement");
        classer.setPrefWidth(200);
        classer.setPrefHeight(50);
        classer.setLayoutX(655);
        classer.setLayoutY(70);
        classer.setOnAction(e->{
        	Classement();
        });
        pane.getChildren().add(classer);
        VBox v=new VBox();
        v.setSpacing(50);
        v.getStyleClass().add("container");
        HBox h = new HBox();
        h.getStyleClass().add("container");
        h.setSpacing(50);
        Button btn =new Button("+");
        btn.getStyleClass().addAll("notion","ajouter");
        btn.setPrefWidth(200);
        btn.setPrefHeight(220);
        btn.setOnAction(new EventHandler<ActionEvent>() {
         	public void handle(ActionEvent event) {
         		Apprenant A = new Apprenant();
         		F.getFormation().getApprenants().add(A);
         		Apprenant(A);
         	} 
        });
        h.getChildren().add(btn);
        v.getChildren().add(h);
        int cpt=1;
        for (Apprenant A : F.getFormation().getApprenants()) {
        	Pane a = new Pane();
        	a.setPrefWidth(200);
            a.setPrefHeight(220);
        	a.getStyleClass().add("quiz");
            Label qtitre = new Label(A.getNom()+"\n"+A.getPrenom());
            qtitre.setStyle("-fx-font-size:24px;");
            qtitre.setPrefWidth(120);
            qtitre.setLayoutX(20);
            qtitre.setLayoutY(15);
            
            Button supp = new Button();
			supp.setPrefSize(50,50);
			supp.setLayoutX(140);
			supp.setLayoutY(10);
			supp.getStyleClass().add("del");
			supp.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					F.getFormation().getApprenants().remove(A);
					Main.save();
					Apprenants();
				}
			});
			a.getChildren().add(supp);
            btn = new Button("Voir Activité");
            btn.setPrefHeight(40);
            btn.setPrefWidth(150);
            btn.setLayoutX(25);
            btn.setLayoutY(160);
            btn.setOnAction(new EventHandler<ActionEvent>() {
             	public void handle(ActionEvent event) {
             		Activite(A);
             	} 
            });
            a.getChildren().addAll(qtitre,btn);
            btn = new Button("Modifier");
            btn.setPrefHeight(40);
            btn.setPrefWidth(150);
            btn.setLayoutX(25);
            btn.setLayoutY(110);
            btn.setOnAction(new EventHandler<ActionEvent>() {
             	public void handle(ActionEvent event) {
             		Apprenant(A);
             	} 
            });
            a.getChildren().add(btn);
            h.getChildren().add(a);
            cpt++;
            if (cpt==3) {
            	cpt=0;
            	h= new HBox();
            	h.getStyleClass().add("container");
            	h.setSpacing(50);
            	v.getChildren().add(h);
            }
            
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

    public void Activite(Apprenant A) {
    	Formateur F = (Formateur) Main.logedAccount;
    	pane.getChildren().clear();
 		Label titre = new Label("Activité de "+A.getNom()+" "+A.getPrenom());
        titre.setId("titre");
        titre.setLayoutX(100);
        titre.setLayoutY(70);
        pane.getChildren().add(titre);
        Button retour = new Button("<< Retour");
        retour.setId("retour");
        retour.setLayoutX(10);
        retour.setLayoutY(10);
        retour.setPrefHeight(40);
        retour.setPrefWidth(100);
        retour.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent event) {
        		Apprenants();
        	}
        });
        pane.getChildren().add(retour);
        
        A.calcMoyenne();
        
        TableView table = new TableView();
        
        TableColumn<String,QuizPerso> quiz = new TableColumn<>("Quiz");
        quiz.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        quiz.setPrefWidth(882/6);
        TableColumn<String,QuizPerso> date_o = new TableColumn<>("Ouverture");
        date_o.setCellValueFactory(new PropertyValueFactory<>("DateOuvert"));
        date_o.setPrefWidth(882/6);
        TableColumn<String,QuizPerso> date_e = new TableColumn<>("Expiration");
        date_e.setCellValueFactory(new PropertyValueFactory<>("DateExp"));
        date_e.setPrefWidth(882/6);
        TableColumn<String,QuizPerso> taux_a = new TableColumn<>("% Accomplissement");
        taux_a.setCellValueFactory(new PropertyValueFactory<>("TauxAccomp"));
        taux_a.setPrefWidth(882/6);
        TableColumn<String,QuizPerso> taux_r = new TableColumn<>("% Reussite");
        taux_r.setCellValueFactory(new PropertyValueFactory<>("TauxReussite"));
        taux_r.setPrefWidth(882/6);
        TableColumn<Boolean,QuizPerso> soumis = new TableColumn<>("Soumis");
        soumis.setCellValueFactory(new PropertyValueFactory<>("Soumis"));
        soumis.setPrefWidth(882/6);
        
        table.getColumns().addAll(quiz,date_o,date_e,soumis,taux_a,taux_r);
        
        for (QuizPerso Q:A.getQuizs()) {
        	table.getItems().addAll(Q);
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

	public void Apprenant(Apprenant A) {
    	
    	Formation F = ((Formateur) Main.logedAccount).getFormation();
    	Stage stage= new Stage();
    	AnchorPane root = new AnchorPane();
    	root.setId("root");
    	ScrollPane sp = new ScrollPane();
        sp.getStyleClass().add("container");
        sp.setPrefHeight(700);
        sp.setPrefWidth(500);
        sp.setContent(root);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    	stage.setScene(new Scene(sp,700,500));
    	stage.setResizable(false);
    	stage.setTitle("Apprenant - ESIQuiz");
    	
    	TextField nom = new TextField(A.getNom());
    	nom.setPromptText("Nom");
    	nom.getStyleClass().addAll("field","info");
    	TextField prenom = new TextField(A.getPrenom());
    	prenom.setPromptText("Prénom");
    	prenom.getStyleClass().addAll("field","info");
    	DatePicker date_n = new DatePicker();
    	date_n.setValue(A.getDateNaissance());
    	date_n.getStyleClass().addAll("field","info");
    	date_n.setPromptText("Date de Naissance");
    	date_n.setPrefWidth(400);
    	TextField adresse = new TextField(A.getAdresse());
    	adresse.setPromptText("Adresse");
    	adresse.getStyleClass().addAll("field","info");
    	
    	TextField login = new TextField(A.getLogin());
    	login.setPrefWidth(300);
		login.setPromptText("Login");
    	login.getStyleClass().addAll("field","info");
    	PasswordField Mdp = new PasswordField();
    	Mdp.setPrefWidth(300);
    	Mdp.setPromptText("Mot de passe");
    	Mdp.setText(A.getMdp());
    	Mdp.getStyleClass().addAll("field","info");
    	
    	Button fin = new Button();
    	fin.setText("Sauvegarder");
    	fin.setPadding(new Insets(10,30,10,30));
    	fin.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
	    		if ((nom.getText().equals(""))||(prenom.getText().equals(""))||(date_n.getValue().toString().equals(""))||(adresse.getText().equals(""))
	    				||((!A.getNom().equals(""))&&(login.getText().equals("")))||((!A.getNom().equals(""))&&(Mdp.getText().equals("")))) {
	    			erreur("Tous les champs sont obligatoires");
	    		} else {
	    			String log=login.getText();
	    			if (A.getLogin().equals("")) log = Main.getLogin(nom.getText(), prenom.getText());
	    			String mdp = Mdp.getText();
	    			if (A.getMdp().equals("")) mdp=Main.getMdp(nom.getText(), date_n.getValue());
	    			if ((F.getApprenant(log)!=null)&&(F.getApprenant(log)!=A)) {
	    				erreur("Login existe deja");
	    			} else {
		    			A.setLogin(log);
		    			A.setMdp(mdp);
		    			A.setNom(nom.getText());
		    			A.setPrenom(prenom.getText());
		    			A.setDateNaissance(date_n.getValue());
		    			A.setAdresse(adresse.getText());
		    			if (A.getQuizs().size()==0) {
		    				for(Quiz q:F.getQuizs()) {
		    					A.getQuizs().add(q.creerQuizPerso());
		    				}
		    			}
		    			Main.save();
		    			Apprenants();
		    			stage.close();
	    			}
	    		}
			}
    	});
    	Button annuler = new Button();
    	annuler.setPadding(new Insets(10,30,10,30));
    	annuler.setText("Annuler");
    	annuler.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			if (A.getNom().equals("")) F.getApprenants().remove(A);
    			stage.close();
    		}
    	});
    	
    	Button genl = new Button("Générer");
    	//genl.setPadding(new Insets(10,30,10,30));
    	genl.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			login.setText(Main.getLogin(nom.getText(), prenom.getText()));
    		}
    	});
    	
    	Button genm = new Button("Générer");
    	//genm.setPadding(new Insets(10,30,10,30));
    	genm.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
    			Mdp.setText(Main.getMdp(nom.getText(), date_n.getValue()));
    		}
    	});
    	
    	VBox V = new VBox();
    	V.setSpacing(40);
    	V.setPrefWidth(400);
    	V.setAlignment(Pos.CENTER_RIGHT);
    	V.setLayoutX(150);
    	V.setLayoutY(80);
    	
    	
    	V.getChildren().addAll(nom, prenom,date_n,adresse);
    	
    	if (!A.getNom().equals("")) {
    		HBox H = new HBox();
    		H= new HBox();
    		H.setSpacing(20);
    		H.getChildren().addAll(login,genl);
    		V.getChildren().add(H);
    		H= new HBox();
    		H.setSpacing(20);
    		H.getChildren().addAll(Mdp,genm);
    		V.getChildren().add(H);
    	}
    	HBox H = new HBox();
    	H.setSpacing(20);
    	H.getChildren().addAll(fin,annuler);
    	H.setAlignment(Pos.CENTER_RIGHT);
    	
    	V.getChildren().addAll(H,new Label());
    	root.getChildren().add(V);
    	V.requestFocus();
    	
    	stage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                event.consume();
            }
        });
    	stage.show();
    	
		
	}
	
	public void Classement() {
		Formation F = ((Formateur) Main.logedAccount).getFormation();
    	Stage stage= new Stage();
    	AnchorPane root = new AnchorPane();
    	root.setId("root");
    	ScrollPane sp = new ScrollPane();
        sp.getStyleClass().add("container");
        sp.setPrefHeight(700);
        sp.setPrefWidth(500);
        sp.setContent(root);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    	stage.setScene(new Scene(sp,700,500));
    	stage.setResizable(false);
    	stage.setTitle("Classement - ESIQuiz");
    	
    	TableView table = new TableView();
        
        TableColumn<String,Apprenant> nom = new TableColumn<>("Nom");
        nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        nom.setPrefWidth(200);
        TableColumn<String,Apprenant> prenom = new TableColumn<>("Prenom");
        prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        prenom.setPrefWidth(200);
        TableColumn<String,Apprenant> moy = new TableColumn<>("Moyenne");
        moy.setCellValueFactory(new PropertyValueFactory<>("Moyenne"));
        moy.setPrefWidth(200);
        
        table.getColumns().addAll(nom,prenom,moy);
        
        for (Apprenant A :F.getApprenants()) {
        	A.calcMoyenne();
        	table.getItems().addAll(A);
        }
        
        table.setPrefWidth(600);
        table.setPrefHeight(400);
        table.setLayoutX(50);
        table.setLayoutY(50);
        
        table.getSortOrder().add(moy);
        moy.setComparator(moy.getComparator().reversed());
        
        root.getChildren().add(table);
    	
    	stage.getScene().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    	stage.show();
	}
    
    public void logOut(ActionEvent event){
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
    
    class Rep {
    	public TextField Reponse = new TextField();
    	public boolean Cor;
    }
}
