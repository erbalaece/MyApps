
public class InsufficientFundsException extends Exception{

	public InsufficientFundsException(String message) {
		// TODO Auto-generated constructor stub
		super(message); 
	}
	
	
}

class InsufficientOpeningBalanceException extends Exception{
	
	public InsufficientOpeningBalanceException(String message){
		super(message);
	}
}


