public class Tester
{
	public static void main(String[] args)
	{
		test(1,2,1,3,"+");
		test(1,4,3,2,"-");
		test(1,4,3,2,"*");
		test(1,4,3,2,"/");
	}
public static void test(int n1, int d1, int n2, int d2, String op)
{
	Fraction f1 = new Fraction(n1, d1);
	Fraction f2 = new Fraction(n2, d2);
	Fraction result = null;
	switch(op)
	{
		case "+": { result = f1.add(f2); }break;
		case "-": { result = f1.subtract(f2); }break;
		case "*": { result = f1.multiply(f2); }break;
		case "/": { result = f1.divide(f2); }break;
	}
	System.out.printf("%s%s%s=%s\n",f1,op,f2,result);
}
}
