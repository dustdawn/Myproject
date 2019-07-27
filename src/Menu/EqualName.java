package Menu;

import Menu.Menu.Equalable;

class EqualName implements Equalable<Menu>{
	public boolean equals(Menu m1,Menu m2){return m1.name.equals(m2.name);}
}
