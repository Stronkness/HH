package labb3.task1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Saver {
	Class<?> classe;
	Method[] method;
	String xml = "";
	Boolean firstTime = true;
	public String save(Object o) {
		classe = o.getClass();
		method = classe.getMethods();
		Annotation anno = classe.getAnnotation(Element.class);

		if(anno != null)
		{
		try {
			recur(o);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		xml += "  </subnodes> \n</node>";
		return xml;
		}
		return "No valid tree";
		
		
	}
private void recur(Object o) throws Exception
	{	
		Object[] val = null;
		for(Method temp : method) 
		{
			
			ElementField value = temp.getAnnotation(ElementField.class);
			SubElements subnodes = temp.getAnnotation(SubElements.class);
			
			if(value != null) 
			{
				
				if(firstTime)
				{
					 xml+= "<node "+ value.name() + "=\"" + temp.invoke(o).toString() +"\">\n"; 
					xml += "  <subnodes>\n";
					firstTime = false;
				}
				else
				{
					xml += "    " + "<node "+ value.name() + "=\"" + temp.invoke(o).toString() +"\">\n"; 
				}
			}
			
			if(subnodes != null) 
			{
				val = (Object[]) temp.invoke(o);
			}
		}
	
		if (val != null) 
		{
			for (Object node : val) 
			{
				recur(node);
			}
		}
	
	}
	

}
