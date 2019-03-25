public class ArrayUtil
{
	public static void reverse(String[] array)
	{
		int length = array.length;
		for(int i=0; i<length/2;i++)
		{
			String temp = array[i];
			array[i] = array[length-1-i];
			array[length - 1 - i] = temp;
		}
	}//end reverse

	public static String[] resize(String[] array)
	{
		String[] array2x = new String[array.length *2];
		for(int i=0; i<array.length; i++)
		{
			array2x[i] = array[i];
		}
		return array2x;
	}//end resize

	public static String[] add(String element, String[] array)
	{
		String[] newArr = resize(array);
		for(int i =0; i<newArr.length; i++)
		{
			if(newArr[i] == null)
			{
				newArr[i] = element;
				break;
			}
			
		}
		return newArr;
	}
}