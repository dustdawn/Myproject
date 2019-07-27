package Menu;

import Menu.Menu.Equalable;

public class EqualNum implements Equalable<Menu>{
	public boolean equals(Menu m1,Menu m2){return m1.name.equals(m2.name);}
}
