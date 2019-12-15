public abstract class Animal
{
	private String type;

	public Animal(String type)
	{
		this.type = type;
	}
	public String getType(){return this.type;}
	public abstract void move();
	public abstract String call();
}