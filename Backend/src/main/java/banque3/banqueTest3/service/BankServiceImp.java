package banque3.banqueTest3.service;

import banque3.banqueTest3.Exception.BalanceNotSufficientException;
import banque3.banqueTest3.Exception.BankAccountNotFoundException;
import banque3.banqueTest3.Exception.CustomerNotFoundException;
import banque3.banqueTest3.dtos.AccountHistoryDto;
import banque3.banqueTest3.dtos.CurrentBankAccountDto;
import banque3.banqueTest3.dtos.CustomerDto;
import banque3.banqueTest3.dtos.SavingBankAccountDto;
import banque3.banqueTest3.entities.*;
import banque3.banqueTest3.enums.OperationType;
import banque3.banqueTest3.mappers.BankAccountMapper;
import banque3.banqueTest3.repositories.AccountOperationRepository;
import banque3.banqueTest3.repositories.BankAccountRepository;
import banque3.banqueTest3.repositories.CustomerRepositorie;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional @AllArgsConstructor
public class BankServiceImp  implements  BankAccountService{

    private CustomerRepositorie customerRepositorie ;
    private BankAccountRepository bankAccountrepository ;
    private AccountOperationRepository accountOperationRepository ;

    private BankAccountMapper dtoMapper ;

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */


        @Override
        public CustomerDto saveCustomer(CustomerDto customer) {
        log.info("Saving new Customer");
        Customer customer1 = dtoMapper.fromCustomerDto(customer);
        Customer savedCustomer = customerRepositorie.save(customer1);
        return dtoMapper.fromCustomer(savedCustomer);

         }

       @Override
       public Customer saveCustomer2(Customer customer) {

            Customer customer1 = customerRepositorie.save(customer) ;
            return customer1 ;
       }




         @Override
         public CustomerDto UpdateCustomer(CustomerDto customerDto){
             log.info("Updating new Customer");
             Customer customer1 = dtoMapper.fromCustomerDto(customerDto);
             Customer savedCustomer = customerRepositorie.save(customer1);

                 return dtoMapper.fromCustomer(savedCustomer);

    }
    @Override
    public void  deleteCustomer(Long id){


            customerRepositorie.deleteById(id);


        }




    @Override
    public CurrentBankAccountDto saveCurrentBankAccount(double initialeBalance, double overdraft, Long CustomerId) throws CustomerNotFoundException {

        Customer customer = customerRepositorie.findById(CustomerId).orElse(null) ;
        CurrentAccount bankAccount = new CurrentAccount() ;

        if (customer == null)
            throw new CustomerNotFoundException("Customer Not Found") ;


        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt( new Date());
        bankAccount.setBalance(initialeBalance);
        bankAccount.setCustomer(customer);
        bankAccount.setOverDraft(overdraft);

        CurrentAccount savedCurrentAccount = bankAccountrepository.save(bankAccount);

        return  dtoMapper.fromCurrentAccount(savedCurrentAccount) ;


    }

    @Override
    public SavingBankAccountDto saveSavingBankAccount(double initialeBalance, double interestRate, Long CustomerId) {
        Customer customer = customerRepositorie.findById(CustomerId).orElse(null) ;
        SavingAccount bankAccount = new SavingAccount() ;

        if (customer == null)
            throw new CustomerNotFoundException("Customer Not Found") ;


        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt( new Date());
        bankAccount.setBalance(initialeBalance);
        bankAccount.setCustomer(customer);
        bankAccount.setInterestRate(interestRate);

        SavingAccount savedSavingAccount = bankAccountrepository.save(bankAccount);


        return dtoMapper.fromSavingBankAccount(savedSavingAccount) ;

    }



    @Override
    public List<CustomerDto> listCustomer() {

           List<Customer> customer =  customerRepositorie.findAll();

           List<CustomerDto> collectDto =  customer.stream()
                   .map(cust->dtoMapper.fromCustomer(cust)).collect(Collectors.toList()) ;
            return collectDto ;

    }


    @Override
    public BankAccount getBankAccount(String accountId) {

        BankAccount bankAccount= bankAccountrepository.findById(accountId)
                .orElseThrow(()->new BankAccountNotFoundException("Bank Account not Found"));
        return  bankAccount ;

    }

    @Override
    public void debit(String accountId, double amount, String Description) {
        BankAccount bankAccount= getBankAccount(accountId) ;

        if (bankAccount.getBalance()<amount)
            throw new BalanceNotSufficientException("Balance insufficent ");

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setDesrciption(Description);
        accountOperation.setBankAccount(bankAccount);


        bankAccount.setBalance(bankAccount.getBalance()-amount);

        accountOperationRepository.save(accountOperation);

        bankAccountrepository.save(bankAccount) ;
    }

    @Override
    public void credit(String accountId, double amount, String Description) {

        BankAccount bankAccount= getBankAccount(accountId) ;

        

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setDesrciption(Description);
        accountOperation.setBankAccount(bankAccount);


        bankAccount.setBalance(bankAccount.getBalance()+amount);

        accountOperationRepository.save(accountOperation);

        bankAccountrepository.save(bankAccount) ;


    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestinition, double amount) {
        debit(accountIdSource,amount,"Transfer to"+ accountIdSource) ;
        credit(accountIdDestinition,amount,"Transfer from" +accountIdDestinition);


    }


    @Override
    public List<BankAccount> bankAccountList(){
        return bankAccountrepository.findAll();
    }



    @Override
    public CustomerDto getCustomer(Long id ){

         Customer customer = customerRepositorie.findById(id).orElseThrow(()->new CustomerNotFoundException(" customer ")) ;
           return dtoMapper.fromCustomer(customer) ;

    }

    @Override
    public List<AccountOperation> getOperationsByAccountId(String id){
           return accountOperationRepository.findByBankAccountId(id);
    }

    @Override
    public AccountHistoryDto bankAccountHistory(String accountId, int page, int size){
        Page<AccountOperation> accountOperation =  accountOperationRepository.findByBankAccountId(accountId, PageRequest.of(page,size)) ;



        AccountHistoryDto accountHistoryDto = new AccountHistoryDto() ;

        accountHistoryDto.setAccountOperations(accountOperation);

        accountHistoryDto.setAccountId(accountId);

        accountHistoryDto.setCurrentPage(page);

        accountHistoryDto.setPageSize(size);


        return  accountHistoryDto ;
    }

    @Override
    public List<Customer> searchCustomer(String keyword){
                return customerRepositorie.findCustomerByName("%"+keyword+"%") ;
    }
    @Override
    public List<BankAccount> getAllBankAccounts(){
            return bankAccountrepository.findAll();
    }

}
