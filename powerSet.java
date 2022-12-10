import java.util.*;

public class powerSet{

    public static void main(String[] args) {
        String[] input = {"", "", ""};
        if(args.length > 0){
            for(int i = 0; i < args.length; i++){
                input[i] = args[i];
            }
        }

		LinkedHashSet hashSet = new LinkedHashSet();
		int len = input.length;
		int elements = (int) Math.pow(2, len);

		for (int i = 0; i < elements; i++) {
			String string = Integer.toBinaryString(i);
			int value = string.length();
			String pset = string;
			for (int k = value; k < len; k++) {
				pset = "0" + pset;
			}

			LinkedHashSet set = new LinkedHashSet();
			for (int j = 0; j < pset.length(); j++) {
				if (pset.charAt(j) == '1')
					set.add(input[j]);
			}
			hashSet.add(set);
		}
		System.out.println(hashSet);
	}
}