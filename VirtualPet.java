/*
	Text based Tamagotchi
	By Emmanuel Adefuye 20/03/2019
*/
import java.util.Scanner;
public class VirtualPet
{
	//Declare class petAttributes
		//name, age, cleanliness, happiness, hunger, petRunAway boolean
	//createPet method 
		//set the attribute variables
	//interaction method
		//while petRunAway is false
			//increase Age
			//player interaction
				//feed, pet, play
			//display status (current cleanliness, happiness, hunger)
			//if cleanliness, happiness, hunger is less than a TBD value
				//petRunAway is true
					// if age is less than a TBD value, display corresponding petRunAway message
				//prompt user to start over
			//else petRunAway is false
				//loop and increase age by 1
	//Print how old the pet is and feeling
	public static void main(String[] args)
	{
		VirtualPet tamagotchi = new VirtualPet();
		tamagotchi.createPet();

	}//end main

	private String name;

	private int age;

	private int cleanliness;

	private int happiness;

	private int hunger;

	private String hungerString;

	private String cleanString;

	private String happyString;

	private boolean petRunAway;

	public void createPet()
	{
		System.out.print("\nGive your pet a name: ");
		Scanner petName = new Scanner(System.in);
		name = petName.next();
		age = 0;
		cleanliness = 2;
		happiness =1;
		hunger = 0;
		hungerString="";
		cleanString="";
		happyString="";
		petRunAway = false;

		interaction();
	}

	public void interaction()//this is where the actual game starts (interaction with the pet)
	{

		while(petRunAway == false)
		{
			Scanner input = new Scanner(System.in);
			age += 1;
			System.out.println(name+" is "+age+" days old!\n(1) Feed\n(2) Clean\n(3) Play");
			System.out.println("\nSelect an action:" );
			int interact = input.nextInt();

				
				if(interact == 1)
				{
					happiness+=1;
					if(happiness >=1 && happiness<=2){happyString = "Happy";}
					if(happiness ==3){happyString = "Very Happy";}
					if(happiness >=4){happyString = "Super-Mega-Ultra Happy";}
					else if(happiness <0){happyString = "Sad";}

					hunger-=1;
					if(hunger >=1 && hunger <=2){hungerString = "Hungry";}
					if(hunger ==3){hungerString = "Starving";}
					if(hunger >=4){hungerString = "Borderline Dead (Calling PETA)";}
					else if(hunger <=0){hungerString = "Full";}

					cleanliness-=1;
					if(cleanliness ==1){cleanString = "Messy";}
					if(cleanliness == 0){cleanString = "Dirty";}
					if(cleanliness < 0){cleanString = "Disgusting!";}
					else if(cleanliness >=2){cleanString = "Clean";}

					System.out.println("\n-------"+name+"'s Current Status-------"+"\n"+name+" gets dirtier!");
				}

				if(interact == 2)
				{
					happiness-=1;
					if(happiness >=1 && happiness<=2){happyString = "Happy";}
					if(happiness ==3){happyString = "Very Happy";}
					if(happiness >=4){happyString = "Super-Mega-Ultra Happy";}
					else if(happiness <=0){happyString = "Sad";}

					hunger+=1;
					if(hunger >=1 && hunger <=2){hungerString = "Hungry";}
					if(hunger ==3){hungerString = "Starving";}
					if(hunger >=4){hungerString = "Borderline Dead (Calling PETA)";}
					else if(hunger <=0){hungerString = "Full";}

					cleanliness+=1;
					if(cleanliness ==1){cleanString = "Messy";}
					if(cleanliness == 0){cleanString = "Dirty";}
					if(cleanliness < 0){cleanString = "Disgusting!";}
					else if(cleanliness >=2){cleanString = "Clean";}

					System.out.println("\n-------"+name+"'s Current Status-------"+"\n"+name+" gets unhappy!");
				}

				if(interact == 3)
				{
					happiness+=1;
					if(happiness >=1 && happiness<=2){happyString = "Happy";}
					if(happiness ==3){happyString = "Very Happy";}
					if(happiness >=4){happyString = "Super-Mega-Ultra Happy";}
					else if(happiness <0){happyString = "Sad";}

					hunger+=1;
					if(hunger >=1 && hunger <=2){hungerString = "Hungry";}
					if(hunger ==3){hungerString = "Starving";}
					if(hunger >=4){hungerString = "Borderline Dead (Calling PETA)";}
					else if(hunger <=0){hungerString = "Full";}

					cleanliness-=1;
					if(cleanliness ==1){cleanString = "Messy";}
					if(cleanliness == 0){cleanString = "Dirty";}
					if(cleanliness < 0){cleanString = "Disgusting!";}
					else if(cleanliness >=2){cleanString = "Clean";}

					System.out.println("\n-------"+name+"'s Current Status-------"+"\n"+name+" gets hungrier!");
				}
			
			System.out.println("Hunger Level: "+hungerString+"\nCleanliness: "+cleanString+"\nHappiness: "+happyString);
			System.out.println("\n------------------------------------------------"+"\n");

			if(age%2 == 0){hunger = hunger*2; cleanliness = cleanliness/2; happiness = happiness/2;}

			if(hunger >=5 || happiness <= -1 || cleanliness <0)
			{
				petRunAway = true;
			}
		}//end while loop

		if(petRunAway == true)
		{
			if(age >= 3 && age < 5){System.out.println("You only took care of "+name+" for "+age+" days, there's room for improvement.");}
			if(age >= 5 && age < 10){System.out.println("Good job, you took care of "+name+" for "+age+" days!");}
			if(age >= 10 && age < 20){System.out.println("Wow, you took care of "+name+" for "+age+" days, you're a great pet owner!");}
			if(age <3){System.out.println(name+" ran away...you didn't care for it well enough");}
			tryAgain();
		}
	}//end interaction

	public void tryAgain()
	{
		System.out.println("Would you like to try again with another pet?\nY or N");
		Scanner tryAgain = new Scanner(System.in);
		String answer = tryAgain.next();
		if(answer.matches("y|Y"))
		{
			createPet();
		}
		else{System.exit(0);}
	}
}//end class
