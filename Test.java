package board;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
						
		Date today = new Date();
		
		String today1 = format1.format(today);
		
		System.out.println(today1);
	}

}
