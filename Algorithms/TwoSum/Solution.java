class Solution {
    public int[] twoSum(int[] nums, int target)
    {
        int[] sum = new int[2];
        
        for(int i = 0; i < nums.length-1; i++)
        {
            int x = nums[i];
            for(int i2 = 1; i2 < nums.length; i2++)
            {
                int y = nums[i2];
                if(x + y == target && i != i2) //can't be from the same index (unique)
                {
                    sum[0] = i;
                    sum[1] =i2;
                    System.out.println(i +" "+ i2);
                    break;
                }
                else{}
            }
        }
        return sum;
    }
}