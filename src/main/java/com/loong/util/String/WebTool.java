package com.loong.util.String;

/**
 * 此类中收集Java编程中WEB开发常用到的一些工具。
 * 为避免生成此类的实例，构造方法被申明为private类型的。
 */
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Date;

public class WebTool {
	/**
	 * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
	 */
	private WebTool() {
	}

	/**
	 * <pre>
	 * 例：
	 * String strVal="This is a dog";
	 * String strResult=WebTool.replace(strVal,"dog","cat");
	 * 结果：
	 * strResult equals "This is cat"
	 * 
	 * @param strSrc 要进行替换操作的字符串
	 * @param strOld 要查找的字符串
	 * @param strNew 要替换的字符串
	 * @return 替换后的字符串
	 * 
	 * <pre>
	 */
	public static final String replace(String strSrc, String strOld, String strNew) {
		return strSrc.replace(strOld, strNew);
	}

	/**
	 * 用于将字符串中的特殊字符转换成Web页中可以安全显示的字符串
	 * 可对表单数据据进行处理对一些页面特殊字符进行处理如'<','>','"',''','&'
	 * 
	 * @param strSrc
	 *            要进行替换操作的字符串
	 * @return 替换特殊字符后的字符串
	 * @since 1.0
	 */

	public static String htmlEncode(String strSrc) {
		if (strSrc == null)
			return "";

		char[] arr_cSrc = strSrc.toCharArray();
		StringBuffer buf = new StringBuffer(arr_cSrc.length);
		char ch;

		for (int i = 0; i < arr_cSrc.length; i++) {
			ch = arr_cSrc[i];

			if (ch == '<')
				buf.append("&lt;");
			else if (ch == '>')
				buf.append("&gt;");
			else if (ch == '"')
				buf.append("&quot;");
			else if (ch == '\'')
				buf.append("&#039;");
			else if (ch == '&')
				buf.append("&amp;");
			else
				buf.append(ch);
		}

		return buf.toString();
	}

	/**
	 * 用于将字符串中的特殊字符转换成Web页中可以安全显示的字符串
	 * 可对表单数据据进行处理对一些页面特殊字符进行处理如'<','>','"',''','&'
	 * 
	 * @param strSrc
	 *            要进行替换操作的字符串
	 * @param quotes
	 *            为0时单引号和双引号都替换，为1时不替换单引号，为2时不替换双引号，为3时单引号和双引号都不替换
	 * @return 替换特殊字符后的字符串
	 * @since 1.0
	 */
	public static String htmlEncode(String strSrc, int quotes) {

		if (strSrc == null)
			return "";
		if (quotes == 0) {
			return htmlEncode(strSrc);
		}

		char[] arr_cSrc = strSrc.toCharArray();
		StringBuffer buf = new StringBuffer(arr_cSrc.length);
		char ch;

		for (int i = 0; i < arr_cSrc.length; i++) {
			ch = arr_cSrc[i];
			if (ch == '<')
				buf.append("&lt;");
			else if (ch == '>')
				buf.append("&gt;");
			else if (ch == '"' && quotes == 1)
				buf.append("&quot;");
			else if (ch == '\'' && quotes == 2)
				buf.append("&#039;");
			else if (ch == '&')
				buf.append("&amp;");
			else
				buf.append(ch);
		}

		return buf.toString();
	}

	/**
	 * 和htmlEncode正好相反
	 * 
	 * @param strSrc
	 *            要进行转换的字符串
	 * @return 转换后的字符串
	 * @since 1.0
	 */
	public static String htmlDecode(String strSrc) {
		if (strSrc == null)
			return "";
		strSrc = strSrc.replaceAll("&lt;", "<");
		strSrc = strSrc.replaceAll("&gt;", ">");
		strSrc = strSrc.replaceAll("&quot;", "\"");
		strSrc = strSrc.replaceAll("&#039;", "'");
		strSrc = strSrc.replaceAll("&amp;", "&");
		return strSrc;
	}

	/**
	 * 字符串编码的转换
	 * 
	 * @param strVal
	 * @param strOldEncoding
	 * @param strNewEncoding
	 * @return strOldEncoding to strNewEncoding
	 */
	public static String convertEncoding(String strVal, String strOldEncoding, String strNewEncoding) {
		try {
			if (strVal == null) {
				return "";
			} else {
				strVal = strVal.trim();
				strVal = new String(strVal.getBytes(strOldEncoding), strNewEncoding);
			}
		} catch (Exception exp) {
			return "";
		}
		return strVal;
	}

	/**
	 * 把null值和""值转换成&nbsp; 主要应用于页面表格格的显示
	 *
	 * @param str
	 *            要进行处理的字符串
	 * @return 转换后的字符串
	 */
	public static String str4Table(String str) {
		if (str == null)
			return "&nbsp;";
		else if (str.equals(""))
			return "&nbsp;";
		else
			return str;
	}

	/**
	 * null 处理
	 *
	 * @param str
	 *            要进行转换的字符串
	 * @return 如果str为null值，返回空串"",否则返回str
	 */
	public static String null2Blank(String str) {
		if (str == null)
			return "";
		else
			return str;
	}

	/**
	 * null 处理
	 *
	 * @param d
	 *            要进行转换的日期对像
	 * @return 如果d为null值，返回空串"",否则返回d.toString()
	 */

	public static String null2Blank(Date d) {
		if (d == null)
			return "";
		else
			return d.toString();
	}

	/**
	 * 把null转换为字符串"0"
	 * 
	 * @param str
	 * @return
	 */
	public static String null2SZero(String str) {
		str = WebTool.null2Blank(str);
		if (str.equals(""))
			return "0";
		else
			return str;
	}

	/**
	 * 对字符串进行md5加密
	 * 
	 * @param s
	 *            要加密的字符串
	 * @return md5加密后的字符串
	 */
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串从strEncoding编码转换为Unicode编码
	 * 
	 * @param text
	 * @return
	 */
	public static String StringToUnicode(String text, String strEncoding) {
		String result = "";
		int input;
		StringReader isr;
		try {
			isr = new StringReader(new String(text.getBytes(), strEncoding));
		} catch (UnsupportedEncodingException e) {
			return "-1";
		}
		try {
			while ((input = isr.read()) != -1) {
				result = result + "&#x" + Integer.toHexString(input) + ";";

			}
		} catch (IOException e) {
			return "-2";
		}
		isr.close();
		return result;

	}

	/**
	 * 
	 * @param inStr
	 * @return
	 */
	public static String gb2utf(String inStr) {
		char temChr;
		int ascInt;
		int i;
		String result = new String("");
		if (inStr == null) {
			inStr = "";
		}
		for (i = 0; i < inStr.length(); i++) {
			temChr = inStr.charAt(i);
			ascInt = temChr + 0;
			if (Integer.toHexString(ascInt).length() > 2) {
				result = result + "&#x" + Integer.toHexString(ascInt) + ";";
			} else {
				result = result + temChr;
			}

		}
		return result;
	}

	/**
	 *
	 * @param gbString
	 * @return
	 */
	public static String gbEncoding(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
		return unicodeBytes;
	}

	/**
	 * @param dataStr
	 * @return
	 */
	public static StringBuffer decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer;
	}

}
