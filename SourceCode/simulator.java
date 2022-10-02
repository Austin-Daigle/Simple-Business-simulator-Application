import javax.swing.JOptionPane;
import java.util.Random;
import java.awt.Dimension;
import java.net.URL;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//Version 1.0
//This is the final official release 

public class simulator {
	
	static DecimalFormat numberFormat = new DecimalFormat("#.00");
	
	public static String userName;
	static int inputData = 0;

	//images
	public static URL moneyGraphicURL = simulator.class.getResource("/resources/money-icon.png");
	public static URL robberyGraphicURL = simulator.class.getResource("/resources/robbery-icon.jpg");
	public static URL coverGraphicURL = simulator.class.getResource("/resources/cover-icon.jpg");
	public static URL altCoverGraphicURL = simulator.class.getResource("/resources/coverscreen-icon.jpg");
	public static URL actionGraphicURL = simulator.class.getResource("/resources/action-icon.jpg");
	public static URL tutorialGraphicURL = simulator.class.getResource("/resources/tutorial-icon.jpg");
	public static URL personGraphicURL = simulator.class.getResource("/resources/person-icon.png");
	public static URL automatedInventoryURL = simulator.class.getResource("/resources/automanagingserice.png");
	public static URL bankErrorURL = simulator.class.getResource("/resources/bankerror-icon.jpg");
	public static ImageIcon moneyGraphic = new ImageIcon(moneyGraphicURL);
	public static ImageIcon robberyGraphic = new ImageIcon(robberyGraphicURL);
	public static ImageIcon coverGraphic = new ImageIcon(coverGraphicURL);	
	public static ImageIcon altCoverGraphic = new ImageIcon(altCoverGraphicURL);
	public static ImageIcon actionGraphic = new ImageIcon(actionGraphicURL);
	public static ImageIcon tutorialGraphic = new ImageIcon(tutorialGraphicURL);
	public static ImageIcon personGraphic = new ImageIcon(personGraphicURL);
	public static ImageIcon automatedInventory = new ImageIcon(automatedInventoryURL);
	public static ImageIcon bankError = new ImageIcon(bankErrorURL);
	
	//How many times the program will execute
	public static int cycle; //The amount of time that the program executes
	public static boolean user_shutdown = false; //shutdown command for sim
	
	//general finance & merchandise
	public static double money = 1000; //total current money
	public static double starting_Cash = money; //comparison rate of starting cash
	public static int merchandise = 350; //inventory amount of merchandise
	public static int starting_merchandise = merchandise; //inventory amount of merchandise
	public static double cost = 25; //cost to purchase items
 	public static double originalRetailPrice = 40; //selling price
 	public static double finalRetailPrice = originalRetailPrice; //Selling price after sale(s).
	public static int percentOffItem = 0; //User input percent off
	
	
	//general operation
	public static int workers = 2; //Amount of workers 
	public static int startingPayForWorkers = 99;
	public static double costOfTraining = 175;
	public static int workersSkillLevel = 1; //adjust skill level for workers
	public static int randomEvent; //Random event variable that triggers different outcomes

	
	//statistics of profits,investments, and losses
	public static int sales = 0; //given sales during a cycles (amount of stuff sold)
	public static int totalSales = 0; //total sales for the whole simulator
	public static double profits = 0; // given earning during a cycle
	public static double totalProfits = 0; //all profits during the whole simulator
	public static double losses = 0;
	public static double totalLosses = 0;
	public static double investment = 0;
	public static double totalInvestments = 0;

	//Theft standard deviation variables
	public static int oddsOfTheftRange = 1000;
	public static int theftProbabilityResults;
	public static double percentLostToTheft;
	
	//Utilities
	public static double waterBill = 50;
	public static double gasAndElectricBill = 95;
	public static double insurance = 25;
	public static double rent = 500;

	public static double employeePayroll = (workers*(startingPayForWorkers+(((Math.pow(2,workersSkillLevel))/2))));
	public static double totalBills = waterBill + gasAndElectricBill+insurance+rent+employeePayroll;
	
	public static String playerName = "";

	
	
	
	public static void main(String[] args)
	{
		JOptionPane.showMessageDialog(null, "Welcome to Austin's Business Simulator"
				+"\nVersion: 1.0v"
				+"\nClick \"Ok\" to continue",
				
				
				"Welcome",JOptionPane.PLAIN_MESSAGE, coverGraphic);
		
		
		String playerName = JOptionPane.showInputDialog(null, "What is your name?");
		
		if(playerName.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Error: No user name"
					+ " found. User name set to \"User\".","Error: Username",JOptionPane.PLAIN_MESSAGE);
			playerName = "User";
		}
		
		 int tutorial = JOptionPane.showConfirmDialog(null, "Hello "+playerName
				 +", would you like to see the tutorial?", "Tutorial", JOptionPane.YES_NO_OPTION);
	        
		 
		 
		 
		 if (tutorial == JOptionPane.YES_OPTION) 
		 	{
				JTextArea tutorialTextArea = new JTextArea(
						"The objective of the business simulator is to end with a profit (An amount above $"+(starting_Cash+(starting_merchandise*cost))+").\n"
						+"There are four user-interactable actions that can be pursued\n"
						+"during the simulator. You as a business owner have the following"
						+" options available. You can remain passive" 
						+"(do nothing), you can buy inventory, put inventory on sale, or\n"
						+"you could train your employees. A cycle is an in-simulator unit\n"
						+"of time.\n"
						+ "\n"
						+"Putting items on sales accelerates the rate and amount of sales\n"
						+"but also the thefts. Training your employees increases your sales\n"
						+"marginally, the initial cost of training is $175 plus an\n"
						+"additional fifteen dollars per level. Employee pay also increases per"
						+" level.\n"
						+ "\n"
						+ "Theft and random events\n" 
						+"may happen during rounds. You may also have pleasant"
						+" surprises, but that is not likely. Shoplifting and robbery are more" 
						+"likely to happen to you because of the area that you are in (Just look"
			  			+" at your rent.)\n"
						+"\n"
			  			+"Updates of all events (good, bad,& neutral) will happen during the cycle.\n"
						+"Don’t worry about always keeping inventory, you have been given a\n"
						+"semi-automatic service that auto-manages when inventory get low\n"
						+"(below 10 items).This service is free however if you drop below\n" 
						+"0 items you will receive a $50 fee. So, you don’t always need to buy\n" 
						+"merchandise but it certainly helps. Remember that every action"
						+"(input) you make will affect the way the simulation will unfold.\n\n"
						+"Each item of merhcandise is sold for $40 and purchased for $25.\n\n"
						
						+"Tip: consider your input to your outputs, something will bring" 
						+"in more sale but will cost more in the long run.\n"
						//-------------------------------------------- //scaled line 
						);
				System.out.println(playerName);
				JScrollPane scrollPane = new JScrollPane(tutorialTextArea);  
				tutorialTextArea.setLineWrap(true);  
				tutorialTextArea.setWrapStyleWord(true); 
				scrollPane.setPreferredSize(new Dimension(165, 180));
				JOptionPane.showMessageDialog(null, scrollPane, "Tutorial",  
				                                       JOptionPane.INFORMATION_MESSAGE, tutorialGraphic);
		
			/*
		   "The objective of the business simulator is to end with a profit\n.
			There are four user-interactable actions that can be pursued\n 
			during the simulator. You as a business owner has the following\n
			options available as user inputs. You can remain passive\n 
			(do nothing), you can buy inventory, put inventory on sale, or\n
			you could train your employees. A cycle is an in-simulator unit\n
			of time.\n
			Putting items on sales accelerates the rate and amount of sales\n 
			(and thefts). Training your employees increases your sales\n 
			marginally, the initial cost of training is $175 plus an\n 
			additional fifteen per level. Employee pay also increases per\n 
			level and the given number of workers. Theft and random events\n 
			may happen between during rounds. You may also have pleasant\n 
			surprises but that is not likely. Shoplifting and robbery are more\n 
			likely to happen to you because of the area that you are in (Just look\n
  			at your rent.\n 
  			Update of all events (good, bad, neutral) will happen during the cycle.\n
			Don’t worry about always keeping inventory, you have been given a\n 
			semi-automatic service that auto-orders when inventory get low\n 
			(below 10 items).This service is free however if you drop below\n 
			0 items you will receive a fee. So, you don’t always need to buy\n 
			merchandise but it certainly helps. Remember that every action\n 
			(input) you make will affect the way the simulation will unfold.\n\n

			Tip: consider your input to your outputs, something will bring\n 
			in more sale but will cost more in the long run.\n"

			 */
			 
	        }
		 else 
	        {
	        JOptionPane.showMessageDialog(null, "Ok then...Suit yourself");
	        }
		 
		 
		 
		sim();
	}

	//Clear Variables between cycles
	public static void clearVars()
	{
		losses = 0;
		percentLostToTheft = 0;
		investment = 0;
		percentOffItem = 0;
		sales = 0;
		profits = 0;

	}
	
	//put loop here
	public static void sim()
	{
		while(cycle<10)
		{
			cycle++;
			generateOptions();	
		}
		if(user_shutdown == false)
		{
		terminateSim();	
		}
	}
		

	
	//Generate the options and stats menu
	public static void generateOptions()
	{
		clearVars();
		
		//User Choice Custom inputs
		Object[] userSelections = {"Train Employees",
		                    "Restock Inventory",
		                    "Put Merchandise on Sale",
		                    "Remain Passive",
		                    //add security option
		                    "End Simulator"};
		
		//User prompt
		int userChoice = JOptionPane.showOptionDialog(null,
		    "Choose an action:"+"\n\nCurrent Status:"  
		    +"\nMoney: $"+(numberFormat.format(money))
		    +"\nWorker's Skill: Level "+workersSkillLevel
		    +"\nMerchandise Inventory: "+merchandise+" items",
		    "Options"
		    +"\n ",
		    JOptionPane.YES_NO_CANCEL_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    null,
		    userSelections,
		    userSelections[2]);
		//add statistics: money, skill level, workers, security, inventory
	    
	    //user interface response
	    if(userChoice == 0) //train
	    {
	    	trainWorkers();

	    }
	    if(userChoice == 1) //buy items
	    {
	    	buyMerch();

	    }
	    if(userChoice == 2)//put items on sale
	    {
	    	storeSale();
	   
	    }
	    if(userChoice == 3) //do nothing (random)
	    {
	    	passiveMode();
	    
	    }
	    if(userChoice == 4)
	    {
	    	verifyProgramShutdown();
	    }
	    }
	public static void verifyProgramShutdown()
	{
		 int endProgramUserInput = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION);
	        if (endProgramUserInput == JOptionPane.YES_OPTION) 
	        {
		    	user_shutdown = true;
		    	cycle = 10;
	        }
	        else 
	        {
	        	generateOptions();
	        }
	}

	//USER CHOICE:  train your workers
	public static void trainWorkers()
	{

		Object[] employeeTrainingOptions = {"Ok",
        "Return to Options Menu"};
		int trainEmployeesGui = JOptionPane.showOptionDialog(null,
		"It is going to cost $"+costOfTraining+" to train your employees to have a experience level of "
		+(workersSkillLevel+1+"?")
		+"\nAre your sure you want to upgrade?",
		"Confirmation",
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,
		employeeTrainingOptions,
		employeeTrainingOptions[1]);
    	
    	
		if(trainEmployeesGui==0)
		{
			investment = investment + costOfTraining;
	    	workersSkillLevel++;
	    	money = money - costOfTraining;
	    	JOptionPane.showMessageDialog(null,"You have just spent $"+costOfTraining
	    			+" to increase your worker's skill to "+workersSkillLevel+".");
		}
		if(trainEmployeesGui==1)
		{
			generateOptions();
		}
    	updateRuningCosts();
    	sales();
	}
	
	public static void updateRuningCosts()
	{
		costOfTraining = 175+(25*(workersSkillLevel));
		employeePayroll = (workers*(startingPayForWorkers+(((Math.pow(2,workersSkillLevel))/2))));
		totalBills = waterBill + gasAndElectricBill+insurance+rent+employeePayroll;
	}

	//USER CHOICE: buy merchandise
	public static void buyMerch()
	{



		    String buyInventoryGui = JOptionPane.showInputDialog("Input the unit quantity of merchandise that "
		    		+ "you wish to buy.\n"
		    		+ "Please enter an integer value above zero and below one billion.\nEntering a non-"
		    		+ "integer value will close the program and clicking \ncancel will close this program.");

		    
		    if(buyInventoryGui.equals("")){

		    	buyMerch();

		        
		    }
		
		   inputData = (Integer.valueOf(buyInventoryGui));
		   
		   Object[] buyInventoryErrorGuiEntries = {"Back","Return to Options Menu"};
		   
		   int buyInventoryGuiErrorOptions;
		   if(inputData==0)
		   {
			   
			   buyInventoryGuiErrorOptions = JOptionPane.showOptionDialog(null,
					    "You can't buy zero items, please enter a valid number.",
					    "Input Error",
					    JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.ERROR_MESSAGE,
					    //JOptionPane.QUESTION_MESSAGE,
					    null,
					    buyInventoryErrorGuiEntries,
					    buyInventoryErrorGuiEntries[1]);

			   if(buyInventoryGuiErrorOptions==0)
			   {
				   buyMerch(); //load merchandise purchasing screen
			   }
			   if(buyInventoryGuiErrorOptions==1)
			   {
				   generateOptions(); //load main menu
			   }			   
			   if(buyInventoryGuiErrorOptions==2)
			   {
				   verifyProgramShutdown();
			   }			   
			   
		   }
		   
		   if(inputData<0)
		   {
			   
			   buyInventoryGuiErrorOptions = JOptionPane.showOptionDialog(null,
					    "You can't buy negative items, please enter a valid number.",
					    "Input Error",
					    JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.ERROR_MESSAGE,
					    //JOptionPane.QUESTION_MESSAGE,
					    null,
					    buyInventoryErrorGuiEntries,
					    buyInventoryErrorGuiEntries[1]);
			   if(buyInventoryGuiErrorOptions==0)
			   {
				   buyMerch(); //load merchandise purchasing screen
			   }
			   if(buyInventoryGuiErrorOptions==1)
			   {
				   generateOptions(); //load main menu
			   }			   
			   if(buyInventoryGuiErrorOptions==2)
			   {
				   verifyProgramShutdown();
			   }			   

		   }
		   if(inputData>=1000000000)
		   {
			   
			   buyInventoryGuiErrorOptions = JOptionPane.showOptionDialog(null,
					    "The selected Value has exceeded the maximum value that can be"
					    + "computed. Please enter a value above zero and below one billion.",
					    "Input Error",
					    JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.ERROR_MESSAGE,
					    //JOptionPane.QUESTION_MESSAGE,
					    null,
					    buyInventoryErrorGuiEntries,
					    buyInventoryErrorGuiEntries[1]);
			   if(buyInventoryGuiErrorOptions==0)
			   {
				   buyMerch(); //load merchandise purchasing screen
			   }
			   if(buyInventoryGuiErrorOptions==1)
			   {
				   generateOptions(); //load main menu
			   }			   
			   if(buyInventoryGuiErrorOptions==2)
			   {
				   verifyProgramShutdown();
			   }	
			   	   
		   }
		   else if((!(inputData>=1000000000))&&((!(inputData<0))&&(!(inputData==0))))
		   {
			   money = money - (cost*(inputData)); 
			   merchandise = merchandise + inputData;
			   investment = investment + (cost*(inputData)); 
			   JOptionPane.showMessageDialog(null, "You have just purchased "+inputData+" items which has ended up totaling $"
			   		+(cost*inputData)+".");
			   sales();
		   }
		
		
		
	}

	
	
	//USER CHOICE: put the merchandise on sale
	public static void storeSale()
	{

		String percentOffGui;
		
		Object[] percentagesAvailable = {"1%", "2%", "3%","4%","5%","6%","7%","8%","9%","10%",
		"11%","12%","13%","14%","15%","16%","17%","18%","19%","20%",
		"21%","22%","23%","24%","25%","26%","27%","28%","29%","30%",
		"31%","32%","33%","34%","35%","36%","37%","38%","39%","40%",
		"41%","42%","43%","44%","45%","46%","47%","48%","49%","50%",
		"51%","52%","53%","54%","55%","56%","57%","58%","59%","60%",
		"61%","62%","63%","64%","65%","66%","67%","68%","69%","70%",
		"71%","72%","73%","74%","75%","76%","77%","78%","79%","80%",
		"81%","82%","83%","84%","85%","86%","87%","88%","89%","90%",
		"91%","92%","93%","94%","95%","96%","97%","98%","99%","100%",
		};
		
		
		percentOffGui = (String)JOptionPane.showInputDialog(
		                    null,
		                    "Select the percentage to go on sale at"
		                    + "from the list below. Hitting cancel will\n"
		                    + "close the program.",
		                    "Customized Selector",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    percentagesAvailable,
		                   	"");
		System.out.println(percentOffGui);
		if(percentOffGui==null)
		{

			
		int percentOffGuiError = 0;
			
			Object[] percentOffGuiErrorOptions = {"Ok",
            "Return to Options Menu"};
		percentOffGuiError = JOptionPane.showOptionDialog(null,
			"Please Choose a percent from the selector or return to the options menu.",
			"Confirmation",
			JOptionPane.YES_NO_CANCEL_OPTION,
			JOptionPane.ERROR_MESSAGE,
			null,
			percentOffGuiErrorOptions,
			percentOffGuiErrorOptions[1]);
		System.out.println("guierror:"+percentOffGuiError);
		System.out.println("percentOFF"+percentOffGui);	//error starts here!

			if(percentOffGuiError==0)
			{
				storeSale();
			}
			if(percentOffGuiError==1)
			{
				System.out.println("clear to menu");
				generateOptions();
			}

			/*	JOptionPane.showMessageDialog(null,
		    "Error: No sale percentage found..",
		    "Input Error",
		    JOptionPane.ERROR_MESSAGE); */
			System.out.println("percentOFF"+percentOffGui); //before error
		}
	
		
		if(percentOffGui==null)
		{
			System.out.println("error found! <<<<<<");
		}
		

		
		System.out.println("percentOFF"+percentOffGui);  //final print before error
		
		
		if(percentOffGui.length()==2)
		{
			inputData = (Integer.valueOf(percentOffGui.substring(0, 1)));		
		}
		if(percentOffGui.length()==3)
		{
			inputData = (Integer.valueOf(percentOffGui.substring(0, 2)));

		}
		if(percentOffGui.length()==4)
		{
			inputData = (Integer.valueOf(percentOffGui.substring(0, 3)));

		}

			percentOffItem = inputData;
			JOptionPane.showMessageDialog(null,"For the next cycle all Merchandise will now be"
					+ "\non sale at "+percentOffItem+"% OFF."
					+ " Each Item will now cost $"+(numberFormat.format(originalRetailPrice-(originalRetailPrice*(0.01*percentOffItem))))+".");

			sales();
	}
	
	//USER CHOICE: passive mode
	public static void passiveMode()
	{

		Object[] options = {"Yes",
                "Return to Options Menu"};
		int passivity = JOptionPane.showOptionDialog(null,
				"Are you shure you want to remain passive for this cycle?",
				"Confirmation",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		if(passivity==0)
		{
			sales();
		}
		if(passivity==1)
		{
			generateOptions();
		}
		
		
		

	}

	
	public static void randomEvents()
	{
		Random events = new Random();
		randomEvent = events.nextInt(6);
		
		switch(randomEvent)
		{
		case 1: theft();
		break;
		case 2: 
			profits = profits + 500;
			money = money + 500;
			JOptionPane.showMessageDialog(null,
					"A Banking error in your favor has resulted in your earning $1000.",
					"Random Event",
					JOptionPane.WARNING_MESSAGE, bankError);
		break;
		case 3: theft();
		break;
		case 4: if(money > 0 && money < 999)
					{
			JOptionPane.showMessageDialog(null,
					"Your store was robbed and you lost $."+money+".",
				    "Random Event",
				    JOptionPane.WARNING_MESSAGE, robberyGraphic);
					money = money - money;
					losses = losses + money;

					}
				if(money>5000)
					{
					JOptionPane.showMessageDialog(null,
							"Your store was robbed and you lost $5000.",
						    "Random Event",
						    JOptionPane.WARNING_MESSAGE, robberyGraphic);
					money = money - 5000;
					losses = losses + 5000;

					}
				if(money<0 || money == 0)
					{
				
					JTextArea alternateRobberyTextArea = new JTextArea(
							"Your shop has been robbed! "
							+ "When the crook checked the cash register and the safe\n"
							+ "in the backroom he found there is no money. "
							+ "Upon this discovery, he proceeds to laught"
							+ "at you and then he steals a considerable"
							+ "portion of your merchandise. The theif also"
							+ "trashes your shop in the process. Luckly"
							+ "You have insurance and they cover the"
							+ "$8000 worth of damage and lost inventory. "
							+ "There is a catch however, You must now "
							+ "pay a deductible of $500 plus your normal"
							+ "premium.");
							losses = losses + 500;
							money = money - 500;
					JScrollPane scrollPane = new JScrollPane(alternateRobberyTextArea);  
					alternateRobberyTextArea.setLineWrap(true);  
					alternateRobberyTextArea.setWrapStyleWord(true); 
					scrollPane.setPreferredSize(new Dimension(175, 175));
					JOptionPane.showMessageDialog(null, scrollPane, "Random Event",  
					                                       JOptionPane.INFORMATION_MESSAGE, robberyGraphic);
	
					}
					break;
		case 5: theft();
		break;
		case 6: 
			JOptionPane.showMessageDialog(null,
					"A computer error has resulted in your supplier shipping \n"
							+ "you 250 units of merchandise for free!!!",
				    "Random Event",
				    JOptionPane.WARNING_MESSAGE);
					merchandise = merchandise + 250;
		break;
		
		}
		
		
	}
	
	public static void theft()
	{
		
		Random chancesOfTheft = new Random();
		theftProbabilityResults = chancesOfTheft.nextInt(oddsOfTheftRange);
		
		if(theftProbabilityResults >= 0 && theftProbabilityResults < 10)
		{
			percentLostToTheft = 0;
		}
		if(theftProbabilityResults > 10 && theftProbabilityResults < 30)
		{
			percentLostToTheft = 1;
		}
		if(theftProbabilityResults > 30 && theftProbabilityResults < 160)
		{
			percentLostToTheft = 3.5;
		}
		if(theftProbabilityResults > 160 && theftProbabilityResults < 500)
		{
			percentLostToTheft = 5;
		}
		if(theftProbabilityResults > 500 && theftProbabilityResults < 840)
		{
			percentLostToTheft = 25;
		}
		if(theftProbabilityResults > 840 && theftProbabilityResults < 970)
		{
			percentLostToTheft = 45;
		}
		if(theftProbabilityResults > 970 && theftProbabilityResults < 990)
		{
			percentLostToTheft = 70;
		}
		if(theftProbabilityResults > 990)
		{
			percentLostToTheft = 100;
		}
		percentLostToTheft = (percentLostToTheft*0.01);
		
		losses = losses + (finalRetailPrice*((int)(merchandise*percentLostToTheft)));
		merchandise = merchandise - (int)(merchandise*percentLostToTheft);
		
		JOptionPane.showMessageDialog(null,
				"During this term, You have lost "+((int)(merchandise*percentLostToTheft))+" items to shoplifting."
				+"\nThis makes a total of $"+(numberFormat.format(finalRetailPrice*((int)(merchandise*percentLostToTheft))))+" in losses.",
				"Shop Lifting Report",JOptionPane.WARNING_MESSAGE,personGraphic);
		
		
		
	}
	
	
	public static void checkInventory()
	{
	if(merchandise<20 && merchandise > 0)
		{
		merchandise = merchandise + (100+sales);
		money = money - (cost*(100+sales));
		investment = investment + (cost*(100+sales));
		
		System.out.println(sales);
		JOptionPane.showMessageDialog(null,
		    "Low Inventory Found:\n"
		   +"Responce: Inventory manager has ordered a "+ (100+sales)+" units of merchadise. \n"
		   		+ "The item(s) been express shipped into your store's inventory.\n"
		   +"\nA charge of $"+(cost*(100+sales))+" has been billed to your account.",
		    "Semi-Automatic Inventory Service",
		    JOptionPane.WARNING_MESSAGE, automatedInventory);
		}
	if(merchandise<=0 )
		{
		merchandise = merchandise + (100+sales);
		money = money - ((cost*(100+sales))+50);
		investment = investment + (cost*(100+sales));
		losses = losses + 50;
		
		JOptionPane.showMessageDialog(null,
			    "Insulfieant Inventory Found:\n"
			   +"Responce: Inventory manager has ordered a "+(100+sales)+" units of merchadise.\n The item(s) "
			   		+ "been express shipped into your store's inventory.\n"
			   +"A charge of  $"+(cost*(100+sales))+" has been billed to your account."
			   +"\nA $50 dollar fee has been billed to your account making total $"+((cost*(100+sales))+50)+".",
			    "Semi-Automatic Inventory Service",
			    JOptionPane.WARNING_MESSAGE, automatedInventory);
	}
	}
	
	public static void consumerResponce()
	{
		checkInventory();
		
		Random salesAmount = new Random();
		int inventoryRandonMaxConstant = (int)(Math.ceil(merchandise*0.75));
		inventoryRandonMaxConstant = Math.abs(inventoryRandonMaxConstant);
		if(!(inventoryRandonMaxConstant==0))
		{
			sales = salesAmount.nextInt(inventoryRandonMaxConstant); //get random number from 0% to 50% from the merchandise	
		}
		

		
		//increase sales for every 10% incremented in store sale
		if(percentOffItem > 0 && percentOffItem < 10) //1-9
		{
			sales = sales + ((int)(merchandise*0.05));
		}

		if(percentOffItem >= 10 && percentOffItem < 20) //10-19
		{
			sales = sales + ((int)(merchandise*0.10)); 
		}
		
		if(percentOffItem >= 20 && percentOffItem < 30) //20-29
		{
			sales = sales + ((int)(merchandise*0.15)); 
		}
		
		if(percentOffItem >= 30 && percentOffItem < 40) //30-39
		{
			sales = sales + ((int)(merchandise*0.25)); 
		}
		
		if(percentOffItem >= 40 && percentOffItem < 50) //40-49
		{
			sales = sales + ((int)(merchandise*0.35)); 
		}
		
		if(percentOffItem >= 50 && percentOffItem < 60) //50-59
		{
			sales = sales + ((int)(merchandise*0.45));
		}
		
		if(percentOffItem >= 60 && percentOffItem < 70) //60-69
		{
			sales = sales + ((int)(merchandise*0.60));
		}
		
		if(percentOffItem >= 70 && percentOffItem < 80) //70-79
		{
			sales = sales + ((int)(merchandise*0.75));
		}
		
		if(percentOffItem >= 80 && percentOffItem < 90) //80-89
		{
			sales = sales + ((int)(merchandise*0.80));
		}
	
		if(percentOffItem >= 90 && percentOffItem <= 100) //90-100
		{
			sales = sales + ((int)(merchandise*0.90));
		}
		//increase sales for the amount of workers and general skill level
		if(!(sales==0))
		{
		sales = sales + (int)((1.5*workers)+(2*workersSkillLevel)/2);
		}
		checkInventory();
	
	}



	//calculate the percent off (Triggered by all sales)
	public static void percentOffPrice()
	{

		finalRetailPrice = (originalRetailPrice-(originalRetailPrice*(0.01*percentOffItem)));
	}

	
	public static void payBills()
	{
		updateRuningCosts();
		JTextArea billReportTextArea = new JTextArea(
				 "*******************Bill Report******************"+"\n"
				+ "RENT: $"+(numberFormat.format(rent))+"\n"
				+ "WATER BILL: $"+(numberFormat.format(waterBill))+"\n"
				+ "POWER AND GAS BILL: $"+(numberFormat.format(gasAndElectricBill))+"\n"
				+ "INSURANCE PREMIUM: $"+(numberFormat.format(insurance))+"\n"
				+ "EMPLOYEE PAYROLL: $"+(numberFormat.format(employeePayroll))+"\n"
				+ "TOTAL $ "+(numberFormat.format(totalBills))+"\n"
				+ "*************************************************");
		JScrollPane scrollPane = new JScrollPane(billReportTextArea);  
		billReportTextArea.setLineWrap(true);  
		billReportTextArea.setWrapStyleWord(true); 
		scrollPane.setPreferredSize(new Dimension(250, 175));
		JOptionPane.showMessageDialog(null, scrollPane, "Incremental Bill Report",  
		                                       JOptionPane.INFORMATION_MESSAGE,moneyGraphic);
		
		totalInvestments = totalInvestments + totalBills;
		investment = investment + totalBills;
		money = money - totalBills;
	}
	
	
	//calculate all sales and add all data;
	public static void sales()
	{
		payBills();
		percentOffPrice();
		consumerResponce();
		randomEvents();
		

		merchandise = merchandise - sales; //subtract from inventory 
		
		totalSales = totalSales + sales; //add the cycle's sales to the total amount
		
		profits = profits + (sales * finalRetailPrice); //calculate profits from cycle
		
		totalProfits = (totalProfits + profits); //add current profit to total profit
		
		money = money + (sales*finalRetailPrice); //add given profits to current money
		
		totalInvestments = totalInvestments + investment; //Calculate total investments
		
		totalLosses = totalLosses + losses; //calculate all losses
		
		
		JTextArea billReportTextArea = new JTextArea(
				  "******** Status Update ********"+"\n"
				+ "Profits during this Round: \n$"+(numberFormat.format(profits))+"\n"
				+ "Losses during this Round: \n$"+(numberFormat.format(losses))+"\n"
				+ "Sales during this Round: unit(s) sold "+((sales))+"\n"
				+ "Investment During this Round: $"+(numberFormat.format(investment))+"\n"
				+ "Items lost to theft: "+(int)(merchandise*percentLostToTheft)+"\n"
				+ "Current Inventory in this round:  "+merchandise+" items"+"\n"
				+ "**********************************");
		JScrollPane scrollPane = new JScrollPane(billReportTextArea);  
		billReportTextArea.setLineWrap(true);  
		billReportTextArea.setWrapStyleWord(true); 
		scrollPane.setPreferredSize(new Dimension(200, 175));
		JOptionPane.showMessageDialog(null, scrollPane, "Status Update",  
		                                       JOptionPane.INFORMATION_MESSAGE,altCoverGraphic);
		System.out.println(playerName);
	}


	public static void terminateSim()
	{
		double profitToLossesRatio = totalProfits/totalLosses;
		double actualProfitAfterLosses = totalProfits-totalLosses;
		String report = "";
		double startingCapital = (starting_Cash+(starting_merchandise*cost));
		
		if(actualProfitAfterLosses<startingCapital)
		{
			report = "Well "+playerName+", Your business is under debt, You are struggling to makes ends meet.";
		}
		else if(actualProfitAfterLosses==startingCapital)
		{
			report = "Well "+playerName+", Your business is breaking even, You are struggling to makes ends meet.";
		}
		else if(actualProfitAfterLosses>startingCapital)
		{
			report = "Well "+playerName+", Your business is making a profit, Congradulations! You have run a successfull"
					+ "business in less than under-ideal conditions.";
		}
		
		
		JTextArea finalResultTextArea = new JTextArea(
				"**********Final Results********"+"\n"
				+"Total Profits (Raw Value): $"+(numberFormat.format(totalProfits))+"\n"
				+"Total Sales: \n "+totalSales+" items"+"\n"
				+"Total Investment (Raw Value): \n$"+(numberFormat.format(totalInvestments))+"\n"
				+"Total Losses: \n$"+(numberFormat.format(totalLosses))+"\n"
				+"Profits to Losses Ratio: \n"+(numberFormat.format(profitToLossesRatio))+"\n"
				+"Actual Profits After losses: $"+(numberFormat.format(actualProfitAfterLosses))+"\n"
				+"Starting Capital (Money & Merchandise at warehouse value):\n $"+(numberFormat.format(starting_Cash+(starting_merchandise*cost)))
				+"**********************************"
				+"\n"+report+"\n"
				+"**********************************");
		JScrollPane scrollPane = new JScrollPane(finalResultTextArea);  
		finalResultTextArea.setLineWrap(true);  
		finalResultTextArea.setWrapStyleWord(true); 
		scrollPane.setPreferredSize(new Dimension(175, 200));
		JOptionPane.showMessageDialog(null, scrollPane, "Final Results",  
		                                       JOptionPane.INFORMATION_MESSAGE);	
	

	}
}