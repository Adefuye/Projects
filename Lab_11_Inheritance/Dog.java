public class Dog extends Animal
{
	public Dog()
	{
		super("Dog");
	}
	public void move()
	{
		String type = super.getType();
		System.out.println(type + " runs");
	}
	public String call()
	{
		return "roof roof";
	}
}