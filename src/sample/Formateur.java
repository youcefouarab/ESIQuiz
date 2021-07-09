package sample;



import java.io.Serializable;

public class Formateur extends Compte implements Serializable{
	private Formation Formation;
	
	public Formateur(String Login, String Mdp, Formation Formation) {
		super(Login, Mdp);
		this.setFormation(Formation);
	}

	public Formation getFormation() {
		return Formation;
	}

	public void setFormation(Formation Formation) {
		this.Formation = Formation;
	}

}
