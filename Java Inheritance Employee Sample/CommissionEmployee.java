public class CommissionEmployee extends Employee
{
	private double commisionRate;
	private double totalSales;
	public CommissionEmployee(String name, double rate, double sales)
	{
		super(name);
		this.commisionRate = rate;
		this.totalSales = sales;
	}//end constructor
	public double getPayment()
	{
		double payment = commisionRate * totalSales;
		return payment;
	}
	public String toString()
	{
		return String.format("%s, commission:%.02f%% @ $%.02f sales", super.toString(), commisionRate, totalSales);
	}
}
