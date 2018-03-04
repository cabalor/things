package start;


import java.util.HashMap;
import java.util.Map;

public class GameContext {

	public static final String PLAYER = "PLAYER";
	
	
	private static Map<String, Object> map = new HashMap<>();
	

	public static Object getMap(String str) {
		return map.get(str);
	}

	public static void setMap(String name, Object obj) {
		map.put(name, obj);
	}
	
	public GameContext(){
	}
	
}
