package sample;



import java.io.Serializable;

public abstract class Compte implements Serializable {
	public String Login;
	public String Mdp;
	
	public Compte(String Login, String Mdp) {
		this.Login=Login;
		this.Mdp=Mdp;
	}
	
	//public abstract boolean seConnecter(String Login, String Mdp, Compte Comptes[]);
	
	public void setLogin(String Login) {
		this.Login=Login;
	}
	
	public String getLogin() {
		return this.Login;
	}
	
	public String getMdp() {
		return this.Mdp;
	}

	public void setMdp(String Mdp) {
		this.Mdp=Mdp;
		
	}
}
