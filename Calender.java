package health;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Calender {
	
	SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy년 MM월dd일");
			
	Date time = new Date();
			
	String time1 = format1.format(time);
			
}
