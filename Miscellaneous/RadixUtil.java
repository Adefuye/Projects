public class RadixUtil
{
	public static int base2(String binary)
	{
		char[] binaryArr = binary.toCharArray();
		int decimal= 0;
		int len = binaryArr.length;
		for(int i = 0; i<len; i++)
		{
			if(binaryArr[i] == '1')
			{
				decimal += Math.pow(2, len-(i+1));
			}
		}
		return decimal;
	}
	public static String base2(int decimal)
	{
		return Integer.toBinaryString(decimal);
	}
	public static int base8(String octal)
	{
		return Integer.valueOf(octal, 8);
	}
	public static String base8(int decimal)
	{
		return Integer.toOctalString(decimal);
	}
	public static int base16(String hexadecimal)
	{
		return Integer.valueOf(hexadecimal, 16);
	}
	public static String base16(int decimal)
	{
		return Integer.toHexString(decimal);
	}
}