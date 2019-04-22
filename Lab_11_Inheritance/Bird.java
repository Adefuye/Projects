public class Bird extends Animal
{
	public Bird()
	{
		super("Bird");
	}
	public void move()
	{
		String type = super.getType();
		System.out.println(type + " flies");
	}
	public String call()
	{
		return "chirp chirp";
	}
}