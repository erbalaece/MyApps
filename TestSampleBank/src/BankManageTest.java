import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class BankManageTest {

	private int min_limit=1;        
	private double min_bal=0;

	List<Customer> customerList = new ArrayList<Customer>();
	Customer customer = new  Customer();

	static int totRec=1;
	BankManageTest()
	{
	}

	//TO  ADD NEW CUSTOMER
	public void newEntry()
	{
		String str;
		int acno;
		double amt;
		boolean permit;
		permit=true;

		if(permit = true)   //create customer
		{

			System.out.println("\n=====ADDING NEW CUSTOMER=====");
			try{

				Customer customer = new Customer();
				customer.setAccNo(totRec);
			   //Created  AutoNumber  to accNo so no invalid id occurs

				System.out.println("Account Number :  "+customer.getAccNo());

				BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("Enter Name :  ");
				System.out.flush();
				customer.setName(obj.readLine());

				System.out.print("Enter Customer Age : ");
				System.out.flush();
				customer.setAge(Integer.parseInt(obj.readLine()));

				System.out.print("Enter gender m/f:  ");
				System.out.flush();
				customer.setGender(obj.readLine());

				System.out.print("Enter City / Village / town :  ");
				System.out.flush();
				customer.setPlace(obj.readLine());

				System.out.print("Enter Initial  Amount to be deposited : ");
				System.out.flush();
				str=obj.readLine();
				double bal = Double.parseDouble(str);
				boolean flag = false;
				//
				switch (customer.getPlace()) {
				case "city":
					if(customer.getAge() <=20)
						min_bal =customer.getGender().equalsIgnoreCase("M")?200:50;
					else if(customer.getAge()<=60 )
						min_bal = customer.getGender().equalsIgnoreCase("M")?500:300;
					else if(customer.getAge()>60 && customer.getAge()<90)
						min_bal = customer.getGender().equalsIgnoreCase("M")?400:200;
					break;
				case "village":
					if(customer.getAge() <=20)
						min_bal =customer.getGender().equalsIgnoreCase("M")?100:50;
					else if(customer.getAge()<=60 )
						min_bal = customer.getGender().equalsIgnoreCase("M")?800:300;
					else if(customer.getAge()>60 && customer.getAge()<90)
						min_bal = customer.getGender().equalsIgnoreCase("M")?300:20;
					break;
				case "town":
					if(customer.getAge() <=20)
						min_bal =customer.getGender().equalsIgnoreCase("M")?7000:3300;
					else if(customer.getAge()<=60 )
						min_bal = customer.getGender().equalsIgnoreCase("M")?10000:500;
					else if(customer.getAge()>60 && customer.getAge()<90)
						min_bal = customer.getGender().equalsIgnoreCase("M")?100:20;
					break;

				default:
					break;
				}
				//Validation that minimun amount
				if(bal < min_bal) {
					System.out.println("exception : account rejected, initial deposit amount is more than "+min_bal);
					throw new InsufficientOpeningBalanceException("account rejected, initial deposit amount is more than "+min_bal);
				}
				customer.setBalance(bal);
				
				customer.setMin_bal(min_bal);

				customerList.add(customer);
				totRec++;         // Incrementing Total Record
				System.out.println("Account created");
				System.out.println("\n\n\n");
			}
			catch(Exception e)
			{}
			finally {
				min_bal= 0;
			}
		}
	}

	public Customer getCustomer(Integer accno) {
		Customer cust = new Customer();
		for(Customer customer : customerList) {
			if(customer.getAccNo()==accno){
				cust = customer;
			}
		}
		return cust;
	}


	//TO DISPLAY DETAILS OF RECORD
	public void display()
	{
		String str;
		int acno=0;

		System.out.println("\n\n=====DISPLAYING DETAILS OF CUSTOMER=====\n");
		try{
			BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter Account number : ");
			System.out.flush();
			str=obj.readLine();
			acno=Integer.parseInt(str);
			
			if (validateAccount(acno)==true)
			{ 
//				Customer customer = getCustomer(acno);
				for(Customer customer: customerList) {
					if(customer.getAccNo()==acno) {
						System.out.println("Account Number : "+customer.getAccNo());
						System.out.println("Name : "+customer.getName());
						System.out.println("Customer Age : "+customer.getAge());
						System.out.println("Balance Amount : "+customer.getBalance()+"\n\n\n");
					}
				}
			}
			
		}
		catch(Exception e)
		{}
	}



	//TO WITHDRAW BALANCE
	public void withdraw(double amt)
	{
		String str;
		double checkamt;
		int acno;
		System.out.println("\n=====TRANSACTION AMOUNT=====");

		try{
			//Reading deposit value
			BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));

			System.out.print("Enter Account No : ");
			System.out.flush();
			str=obj.readLine();
			acno=Integer.parseInt(str);

			if (validateAccount(acno)==true)
			{
				Customer cust = getCustomer(acno);
				if(null!=cust) {		
				
					System.out.println("Balance is : "+cust.getBalance());
					
					
					checkamt=cust.getBalance()+amt;
	
					System.out.println(cust.getMin_bal());
					if(checkamt >= cust.getMin_bal())
					{
						cust.setBalance(checkamt);
						//Displaying Depsit Details
						System.out.println("\nAfter Updation...");
						System.out.println("Account Number :  "+cust.getAccNo());
						System.out.println("Balance Amount :  "+cust.getBalance()+"\n\n\n");
					}
					else
					{
						System.out.println("transaction unsuccessfull, please maintain minimum balance of Rs "+cust.getMin_bal());
						throw new InsufficientFundsException("transaction unsuccessfull, please maintain minimum balance of Rs  "+cust.getMin_bal());
					}
					
				}else {
					System.out.println("Account no is not available");
				}
				
			
			}
			}
		catch(Exception e)
		{}
	}
	public boolean validateAccount(int acno) {
		boolean valid = true;
		if (acno<min_limit  || acno>totRec)   //To check whether accNo is valid or Not
		{
			System.out.println("Invalid Account Number \n\n");
			valid=false;
		}
		return valid;		
	}

	public void transaction() {
		// TODO Auto-generated method stub
		
		try {
		BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the transaction amount : ");
		System.out.flush();
		String str;
			str = obj.readLine();
		double amt=Double.parseDouble(str);
		withdraw(amt);
			


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
