package org.petank.client.web.util;

import java.util.ArrayList;
import java.util.List;

import org.petank.client.web.configuration.ConfigurationConstantsController;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;


public class GWTStringUtils {

	public static final String EMPTY = "";
	public static final String SPACE = " ";
	public static final Double ZERO_DOUBLE = new Double(0);
	public static final String ZERO = "0";

	private static final DateTimeFormat yearFormater = DateTimeFormat
			.getFormat(ConfigurationConstantsController.getConstants().yearFormater());
	private static final NumberFormat numberFormater = NumberFormat
			.getFormat(ConfigurationConstantsController.getConstants().numberFormater());
	private static final NumberFormat pourcentageFormater = NumberFormat
			.getFormat(ConfigurationConstantsController.getConstants().pourcentageFormater());

	public final static boolean isEmpty(String value) {
		return (value == null || value.length() == 0);
	}

	public final static boolean isNotEmpty(String value) {
		return !GWTStringUtils.isEmpty(value);
	}

	public final static boolean isDouble(String value) {
		if (GWTStringUtils.isNotEmpty(value)) {
			try {
				Double.parseDouble(value);
			} catch (NumberFormatException exc) {
				return false;
			}
			return true;
		}
		return false;
	}

	public final static boolean isNotDouble(String value) {
		return !GWTStringUtils.isDouble(value);
	}
	
	public final static boolean isInteger(String value) {
		if (GWTStringUtils.isNotEmpty(value)) {
			try {
				Integer.parseInt(value);
			} catch (NumberFormatException exc) {
				return false;
			}
			return true;
		}
		return false;
	}
	
	public final static boolean isNotInteger(String value) {
		return !GWTStringUtils.isInteger(value);
	}
	
	public final static boolean isPositiveInteger(String value) {
		if (GWTStringUtils.isInteger(value)) {
			try {
				int integerValue = Integer.parseInt(value);
				return (integerValue >=0);
			} catch (NumberFormatException exc) {
				return false;
			}
		}
		return false;
	}
	
	public final static boolean isNotPositiveInteger(String value) {
		return !GWTStringUtils.isPositiveInteger(value);
	}

	public final static boolean isYear(String value) {
		if (GWTStringUtils.isNotEmpty(value)) {
			try {
				GWTStringUtils.yearFormater.parse(value);
			} catch (IllegalArgumentException exc) {
				return false;
			}
			return true;
		}
		return false;
	}

	public final static boolean isNotYear(String value) {
		return !GWTStringUtils.isYear(value);
	}
	
	public final static boolean isFormatedNumber(String value) {
		if (GWTStringUtils.isNotEmpty(value)) {
			try {
				return value.matches(ConfigurationConstantsController.getConstants().numberRegexp());
			} catch (Exception  exc) {
				return false;
			}
		}
		return false;
	}
	
	public final static boolean isFormatedNumber(String value, String strRegexp) {
		if( GWTStringUtils.isEmpty(strRegexp) ) {
			return isFormatedNumber(value);
		} else {
			if (GWTStringUtils.isNotEmpty(value)) {
				try {
					return value.matches(strRegexp);
				} catch (Exception  exc) {
					return false;
				}
			}
			return false;
		}		
	}
	
	public final static boolean isNotFormatedNumber(String value) {
		return !GWTStringUtils.isFormatedNumber(value);
	}
	
	public final static boolean isNotFormatedNumber(String value, String strRegexp) {
		return !GWTStringUtils.isFormatedNumber(value, strRegexp);
	}
	
	public final static String formatNumber(Float value) {
		if(value != null) {
			return GWTStringUtils.formatNumber(value.toString());
		} else {
			return GWTStringUtils.EMPTY;
		}
	}

	public final static String formatNumber(String value) {
		String formatedValue = value;
		if (GWTStringUtils.isNotEmpty(value)) {
			try {
				formatedValue = GWTStringUtils.numberFormater.format(Double.parseDouble(value));
			} catch (IllegalArgumentException exc) {
				return GWTStringUtils.EMPTY;
			}
		}
		return formatedValue;
	}
	
	public final static String formatNumber(String value, String strFormat) {
		NumberFormat myNumberFormater = NumberFormat.getFormat(strFormat);
		String formatedValue = value;
		
		if(GWTStringUtils.isEmpty(strFormat)) {
			return formatNumber(value);
		} else {
			if (GWTStringUtils.isNotEmpty(value)) {
				try {
					formatedValue = myNumberFormater.format(Double.parseDouble(value));
				} catch (IllegalArgumentException exc) {
					return GWTStringUtils.EMPTY;
				}
			}
			return formatedValue;
		}		
	}

	public final static String formatPourcentage(String value) {
		String formatedValue = value;
		if (GWTStringUtils.isNotEmpty(value)) {
			try {
				formatedValue = GWTStringUtils.pourcentageFormater.format(Double.parseDouble(value));
			} catch (IllegalArgumentException exc) {
				return formatedValue;
			}
		}
		return formatedValue;
	}
	
	public final static String formatPourcentage(String value, String strFormat) {
		NumberFormat myPourcentageFormater = NumberFormat.getFormat(strFormat);
		String formatedValue = value;
		
		if(GWTStringUtils.isEmpty(strFormat)) {
			return formatPourcentage(value);
		} else {
			if (GWTStringUtils.isNotEmpty(value)) {
				try {
					formatedValue = myPourcentageFormater.format(Double.parseDouble(value));
				} catch (IllegalArgumentException exc) {
					return formatedValue;
				}
			}
			return formatedValue;
		}
	}

	public final static List<Object> arrayToList(Object[] objTab) {
		ArrayList<Object> myList = new ArrayList<Object>();
		for (int i = 0; i < objTab.length; i++) {
			myList.add(objTab[i]);
		}
		return myList;
	}

	/**
	 * Convertit une List en String où les valeurs sont délimitées par un
	 * séparateur
	 * 
	 * @param myList
	 * @param strSeparateur
	 * @return
	 */
	public final static String listToString(List<String> myList, String strSeparateur) {
		String strResult = "";
		for (int i = 0; i < myList.size(); i++) {
			if (i == (myList.size() - 1)) {
				strResult += (String) myList.get(i);
			} else {
				strResult += (String) myList.get(i) + strSeparateur;
			}
		}
		return strResult;
	}
	
	public static final int countChar(String str, char searchChar) {
		char ch;   			// A character in the string.
    int count = 0; 	// Number of times searchChar has been found in str.
    
    for (int i = 0;  i < str.length();  i++ ) {
    	ch = str.charAt(i);  // Get the i-th character in str.
      if ( ch == searchChar ) {
      	count++;
      }  
    }
    return count;
	}
	
	public static int getEstimatePixelSize(String str){
		return isNotEmpty(str) ? 7 * str.length() + 20 : 0;
	}
	
	public static String toValidChars(String strVal) {
		char[] toBeReplaced = { '%' };
		char[] replacement = { '*' };
		String str = strVal;
		
		if( GWTStringUtils.isNotEmpty(str) ) {
			for(int i = 0; i < toBeReplaced.length; i++) {
				str = str.replace(toBeReplaced[i], replacement[i]);
			}
		}
		
		return str;
	}
}
