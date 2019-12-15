public class Fish extends Animal
{
	public Fish()
	{
		super("Fish");
	}
	public void move()
	{
		String type = super.getType();
		System.out.println(type + " swims");
	}
	public String call()
	{
		return "glub glub";
	}
}