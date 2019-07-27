package Menu;

import java.util.Comparator;

public class CompareNum implements Comparator<Menu>{
	public int compare(Menu m1,Menu m2){
		if(m1.num==m2.num)
			return 0;
		else if(m1.num>m2.num)
			return 1;
		else
			return -1;
	}
}