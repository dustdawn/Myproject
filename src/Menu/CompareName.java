package Menu;

import java.util.Comparator;

public class CompareName implements Comparator<Menu>{
	public int compare(Menu m1,Menu m2){return m1.name.compareTo(m2.name);}
}
