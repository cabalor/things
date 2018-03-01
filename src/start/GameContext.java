package start;


import java.util.HashMap;
import java.util.Map;

public class GameContext {

	public static final String PLAYER = "PLAYER";
	
	
	private Map<String, Object> map = new HashMap<>();
	

	public Object getMap(String str) {
		return this.map.get(str);
	}

	public void setMap(String name, Object obj) {
		this.map.put(name, obj);
	}
	
	public GameContext(){
	}
	
}
