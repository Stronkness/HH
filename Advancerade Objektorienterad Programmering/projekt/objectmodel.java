package projekt;

/**
 * <h1>objectmodel</h1>
 * <p>this is the class that handle our operation with observer</p>
 * @params objects is list for object
 * @params list hold all observer classes
 * @params count hold current position
 * @author Andre Frisk & Fredrik Kortetjärvi
 */

import java.util.ArrayList;

public class objectmodel {
    ArrayList<ObjectList> objects;
    ArrayList<Observer> list;
    int count = 0;

    /**
     * <h3>objectmodel</h3>
     * <p>create the lists</p>
     *
     */
    objectmodel(){
        objects=new ArrayList<>();
        list = new ArrayList<>();
    }

    /**
     * <h3>cleannum</h3>
     * <p>set count to zero</p>
     */
    public void clearnum(){
        count=0;
    }

    /**
     * <h3>setobj</h3>
     * <p>will add objects and update all observer classes</p>
     * @param object object to add
     */
    public void setobj(ObjectList object){
        object.setnumber(count);
        this.objects.add(object);
        update();
        count++;
    }

    /**
     * <h3>removeobj</h3>
     * <p>will remove specified object</p>
     * @param number index on the object to remove
     */
    public void removeobj(int number){
        this.objects.remove(number);
        for(int i=0;i<objects.size();i++){
            objects.get(i).setnumber(i);
        }
        count = objects.size();
        update();
    }

    /**
     * <h3>attach</h3>
     * <p>add the observer classes</p>
     * @param e observer classes to add
     */
    public void attach(Observer e){
        list.add(e);
    }

    /**
     * <h3>update</h3>
     * <p>will update all the observer classes</p>
     */
    public void update(){
        for (Observer l : list)
        {
            l.update(objects);
        }
    }
}
