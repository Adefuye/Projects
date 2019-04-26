public class SalaryEmployee extends Employee
{
	private double salary;
	public SalaryEmployee(String name, double salary)
	{
		super(name);
		this.salary = salary;
	}//end constructor
	public double getPayment()
	{
		double payment = salary/12;
		return payment/2;
	}
	public String toString()
	{
		return String.format("%s, salary:$%.02f", super.toString(), salary);
	}
}
