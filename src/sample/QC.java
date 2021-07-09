package sample;


import java.io.Serializable;
import java.util.*;

public class QC extends Question implements Serializable {
	private TypeQC Type;
	
	public QC(String Question, TypeQC Type) {
		super(Question);
		this.Type=Type;
	}
	
	public TypeQC getType(){
		return Type;
	}
	
	public float evaluer() {
		float note=0; int i=0;
		if (Repondu) {
			if (Type==TypeQC.QCM) {
				for(Reponse R:Reponses) {
					if (R.isCoche()==R.isCorrect()) note+=((float)1.0/(float)Reponses.size());
					else note-=((float)1.0/(float)Reponses.size());
				}
				if (note<0) note=0;
			} else {
				while ((i<Reponses.size())&&(Reponses.get(i).isCoche()==Reponses.get(i).isCorrect())) {
					i++;
				}
				if (i>=Reponses.size()) note=1;
			}
		}
		return note;
		
	}
}
