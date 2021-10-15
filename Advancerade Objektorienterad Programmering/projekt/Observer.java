package projekt;

/**
 * <h1>Observer</h1>
 * <p>Observer interface for all out observers</p>
 * @author Andre Frisk & Fredrik Kortetjärvi
 */


import java.util.ArrayList;

public interface Observer {
	void update(ArrayList<ObjectList> objects);
}
