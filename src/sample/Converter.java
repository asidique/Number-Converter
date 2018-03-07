package sample;
//This class takes a decimal number as an input and return a converted form of the decimal.
//The possible conversions are: IEEE754 Floating Point, Binary, Hexadecimal, Octal


public class Converter {
	
	private static int exp = 0;
	
	//Recursive decimal to binary converter. Uses modulo to do all the work.
	public static String DecimalToBinary(int dec) {
		String binaryString = "";
		if(dec <= 0) {
			return "";
		}
	
		else if(dec%2 == 0) {
			binaryString = "0" + binaryString;
		}
		
		else if(dec%2 == 1) {
			binaryString = "1" + binaryString;
		}
		
		binaryString = DecimalToBinary(dec/2) + binaryString;
		return binaryString;
	}

	//Recursive Decimal to Hex
	public static String DecimalToHex(int dec) {
		String hexString = "";
		if(dec <= 0) {
			return "";
		}
		if(dec%16 == 10) {
			hexString = "A" + hexString;
		} else if(dec%16 == 11) {
			hexString = "B" + hexString;
		} else if(dec%16 == 12) {
			hexString = "C" + hexString;
		} else if(dec%16 == 13) {
			hexString = "D" + hexString;
		} else if(dec%16 == 14) {
			hexString = "E" + hexString;
		} else if(dec%16 == 15) {
			hexString = "F" + hexString;
		} else {
			hexString = String.valueOf((dec%16));
		}
		hexString = DecimalToHex(dec/16) + hexString;


		return (hexString);
	}

	public static String DecimalToIEEE(String flo) {
		String sign = "0";
		if(!flo.contains(".")) {
			flo = flo.concat(".0");
		}
		int whole  = Integer.parseInt(flo.substring(0, flo.indexOf('.')));
		if(whole < 0) {
			sign = "1";
			whole = Math.abs(whole);
			//Checks if negative. If it is, assign signed bit and make it positive for decimal calcuations;
		}
		int decimal = Integer.parseInt(flo.substring(flo.indexOf('.')+1));
		String wholeBinary = Converter.DecimalToBinary(whole);
		String decBinary = Converter.floatToBinary(Double.parseDouble(new String("0."+decimal)));
		String mantissa = wholeBinary.substring(1).concat(decBinary.substring(0, 24-wholeBinary.length())); // NEED TO CONVERT THIS TO 23 BITS
		int exp = wholeBinary.length()-1;
		String expBinary = Converter.DecimalToBinary(exp+127);
		while(expBinary.length() < 8) {
			expBinary = "0".concat(expBinary);
		}
		return sign.concat(expBinary.concat(mantissa));
	}
	
	
	//Recursive binary to decimal converter. Cuts input by 1 every call and adds.
	public static int BinaryToDecimal(String bin) {
		int dec = 0;
		if(bin.length() == 0) {
			return 0;
		}
		
		dec = bin.charAt(0) == '0' ? 0 : (int)Math.pow(2, bin.length()-1);

		dec += BinaryToDecimal(bin.substring(1, bin.length()));
				
		return dec;
	}

	public static String BinaryToHex(String bin) {
		return DecimalToHex(BinaryToDecimal(bin));
	}

	public static String BinaryToIEEE(String bin) {
		return DecimalToIEEE(String.valueOf(BinaryToDecimal(bin)));
	}

	public static String HexToBinary(String hex) {
		return DecimalToBinary(HexToDecimal(hex));
	}

	//Recursive Hex To Decimal
	public static int HexToDecimal(String hex) {
		int dec = 0;
		int val = 0;
	
		if(hex.length() == 0) {
			return 0;
		}
		
		if(hex.startsWith("0x")) {
			hex = hex.substring(2);
		}
		
		if(hex.charAt(0) > 65) {
			val = Character.toUpperCase(hex.charAt(0))-55;
		} else {
			val = Integer.parseInt(Character.toString(hex.charAt(0)));
		}
			
		dec = hex.charAt(0) == '0' ? 0 : val*(int)Math.pow(16, hex.length()-1);
		dec += HexToDecimal(hex.substring(1, hex.length()));
				
		return dec;
	}



	public static String HexToIEEE(String hex) {
		return DecimalToIEEE(String.valueOf(HexToDecimal(hex)));
	}

	public static String IEEEToDecimal(String IEEE) {
		char sign = IEEE.charAt(0);
		String exp = IEEE.substring(1, 9);
		String mantissa = IEEE.substring(9, IEEE.length());
		String result = "";
		int signVal = 1;
		if(sign == '1') {
			signVal=-1;
		}
		double val = 0;
		int expVal = Converter.BinaryToDecimal(exp) - 127;


		return String.valueOf(signVal*(1+BinaryToFraction(mantissa))*Math.pow(2, expVal));
	}

	public static String IEEEToBinary(String IEEE) {
		return DecimalToBinaryFloat((IEEEToDecimal(IEEE)));

	}

	private static String DecimalToBinaryFloat(String dec) {
		String sign = "0";
		if(!dec.contains(".")) {
			dec = dec.concat(".0");
		}
		int whole  = Integer.parseInt(dec.substring(0, dec.indexOf('.')));
		if(whole < 0) {
			sign = "-";
			whole = Math.abs(whole);
		}
		double decimal = Double.parseDouble("0.".concat(dec.substring(dec.indexOf('.')+1)));

		return sign.concat(String.valueOf(DecimalToBinary(whole)).concat(".".concat(floatToBinary(decimal))));
	}

	private static double BinaryToFraction(String bin) {
		double res = 0;
		if(bin.length() == 0) {
			return 0;
		}

		res = (bin.charAt(bin.length()-1) == '0' ? 0 : Math.pow(2, -1*bin.length()));
		res += BinaryToFraction(bin.substring(0, bin.length()-1));

		return res;
	}


	//Used for IEEE conversions
	private static String floatToBinary(double a) {
		String ret = "";
		for(int i = 0; i < 23; i++) {
			a *= 2;
			if(i == 22) {
				if(a*2 > 1) {
					ret = ret.concat("1");
				} else {
					ret = ret.concat("0");
				}
			} else {
				if(a >= 1) {
					a -= 1;
					ret = ret.concat("1");
				} else if (a > 2) {
					System.out.println("Theres an error with floatToBinary");
				} else {
					ret = ret.concat("0");
				}
			}
		}
		return ret;
	}


}