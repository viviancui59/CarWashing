package ty.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class StringUtil {

	//转换文件名格式   yyyyMMddHHmmssXXX 100-999  
		public static String convertFilename(){
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			
			Date date = new Date();
		
			String now = sdf.format(date);
			
			Random rd = new Random();
			
			int random = rd.nextInt(900) + 100;
			
			return now + random;
			
		}
}
