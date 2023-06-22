package banque3.banqueTest3.service;

import banque3.banqueTest3.Exception.CustomerNotFoundException;
import banque3.banqueTest3.dtos.AccountHistoryDto;
import banque3.banqueTest3.dtos.CurrentBankAccountDto;
import banque3.banqueTest3.dtos.CustomerDto;
import banque3.banqueTest3.dtos.SavingBankAccountDto;
import banque3.banqueTest3.entities.AccountOperation;
import banque3.banqueTest3.entities.BankAccount;
import banque3.banqueTest3.entities.Customer;

import java.util.List;

public interface BankAccountService {


    Customer saveCustomer2(Customer customer) ;
    CustomerDto saveCustomer(CustomerDto customer);
    void deleteCustomer(Long id) ;
    public CustomerDto UpdateCustomer(CustomerDto customerDto);
    CurrentBankAccountDto saveCurrentBankAccount(double initialeBalance, double overdraft, Long CustomerId) throws CustomerNotFoundException;



    SavingBankAccountDto saveSavingBankAccount(double initialeBalance, double interestRate, Long CustomerId) ;

    List<AccountOperation> getOperationsByAccountId(String id) ;

    List<Customer> searchCustomer(String id) ;
    AccountHistoryDto bankAccountHistory(String accountId, int page, int size) ;

    List<CustomerDto> listCustomer() ;
    BankAccount getBankAccount(String accountId) ;

    List<BankAccount> getAllBankAccounts();
    void debit(String accountId , double amount , String Description);

    void credit(String accountId , double amount , String Description);

    void transfer(String accountIdSource ,String accountIdDestinition , double amount);
    public List<BankAccount> bankAccountList() ;

    public  CustomerDto getCustomer(Long id ) ;
}
