public class Tester
{
	public static void main(String[] args)
	{
		String[] arr = {"a","b","c"};
		//ArrayUtil.reverse(arr);
		arr = ArrayUtil.add("d",arr);
		for(int i=0; i<arr.length;i++)
		{
			System.out.print(arr[i]+" ");
		}
		//arr = ArrayUtil.resize(arr);
		
		System.out.print("\n"+arr.length);
	
	}
}