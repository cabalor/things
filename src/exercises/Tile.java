package exercises;

import javax.swing.JButton;

public class Tile extends JButton{

	
	
	 private int id;
	    private boolean matched = false;


	    public void setId(int id){
	        this.id = id;
	    }

	    public int getId(){
	        return this.id;
	    }


	    public void setMatched(boolean matched){
	        this.matched = matched;
	    }

	    public boolean getMatched(){
	        return this.matched;
	    }
	
	
	public Tile() {
		super();
	}
}
