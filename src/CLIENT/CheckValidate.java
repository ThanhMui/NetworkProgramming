package CLIENT;

import java.util.regex.Pattern;

public class CheckValidate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "20-02-2020";
		System.out.println(checkValiText("huy 's+"));
		System.out.println(checkValiDate("2020-12-31"));
		System.out.println(checkValiDateYear("2020"));
		
		if(checkValiDate(s) || checkValiDateYear(s))
			{
			System.out.println("đúng");
			if(checkValiDate(s))
			System.out.println(daoChuoiNgay(s));
			}
		else
			System.out.println("sai định dạng");
		
	}
	
	public static boolean checkValiText(String s) {
		Pattern pattern = Pattern.compile(
				"^([a-zA-Z\s\\'20]+)$");
		return pattern.matcher(s).matches();
	}
	
	public static boolean checkValiDate(String date) {
		Pattern pattern = Pattern.compile(
//				"^((2020)|(2021)|(2022))-((0([1-9]))|(1([1-2])))-((([0-1])([1-9]))|(3([0-1])))$");
				"^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
		return pattern.matcher(date).matches();
	}
	
	public static boolean checkValiDateYear(String s) {
		Pattern pattern = Pattern.compile(
				"^((2020)|(2020)|(2022))$");
		return pattern.matcher(s).matches();
	}
	
	public static String dateFormat(String s) {
		String finalDate = "";
		if(checkValiDate(s) || checkValiDateYear(s))
		{
//			System.out.println("đúng");
			if(checkValiDate(s))
			finalDate = daoChuoiNgay(s);
		}
		else {
			System.out.println("sai định dạng");
			
			}
		return finalDate;
	}

	public static String daoChuoiNgay(String date) {
		String[] s = date.split("\\-");
		return (s[2] + "-" + s[1] + "-" + s[0]);
	}
}
