package banque3.banqueTest3.Exception;

public class BankAccountNotFoundException extends RuntimeException{
    public BankAccountNotFoundException(String m) {
            super(m);
    }
}
