package banque3.banqueTest3;

import banque3.banqueTest3.Exception.CustomerNotFoundException;
import banque3.banqueTest3.entities.AccountOperation;
import banque3.banqueTest3.entities.CurrentAccount;
import banque3.banqueTest3.entities.Customer;
import banque3.banqueTest3.entities.SavingAccount;
import banque3.banqueTest3.enums.AccountStatus;
import banque3.banqueTest3.enums.OperationType;
import banque3.banqueTest3.repositories.AccountOperationRepository;
import banque3.banqueTest3.repositories.BankAccountRepository;
import banque3.banqueTest3.repositories.CustomerRepositorie;
import banque3.banqueTest3.service.BankAccountService;
import banque3.banqueTest3.service.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BanqueTest3Application {

	public static void main(String[] args) {
		SpringApplication.run(BanqueTest3Application.class, args);
	}

		//@Bean
		CommandLineRunner commandLineRunner(BankService bankService){
					return args -> {
						bankService.consulter();
					};
		}

		//@Bean
		CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
			return args -> {
				Stream.of("bouchtaoui","boudrga","walid").forEach(name->{

					Customer customer = new Customer() ;
					customer.setName(name);
					customer.setEmail("contact"+"@"+name+".com");

		 			bankAccountService.saveCustomer2(customer);
				});

					bankAccountService.listCustomer().forEach(customer ->{
					System.out.println(customer.getName());
					bankAccountService.saveCurrentBankAccount(Math.random() * 12000, 9000, customer.getId());

					bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5, customer.getId());



				});
				bankAccountService.bankAccountList().forEach(account -> {
					for (int i=0;i<10;i++) {

						bankAccountService.credit(account.getId(), 10000 + Math.random() * 120000, "Credit");
						bankAccountService.debit(account.getId(), 1000 + Math.random() * 9000, "Debit");

					}
				});

			};

		}


	//@Bean
	CommandLineRunner commandLineRunner(CustomerRepositorie customerRepositorie,
										AccountOperationRepository accountOperationRepository,
										BankAccountRepository bankAccountRepository){
			return args -> {
				Stream.of("Nawfal","marwan","mehdi").forEach(name->{
					Customer customer = new Customer();
					customer.setName(name);
					customer.setEmail(name+"@gmail.com");
					customerRepositorie.save(customer) ;
				});
					customerRepositorie.findAll().forEach(item->{
					CurrentAccount currentAccount= new CurrentAccount();
					currentAccount.setId(UUID.randomUUID().toString());
					currentAccount.setBalance(Math.random()*90000);
					currentAccount.setStatus(AccountStatus.CREATED);
					currentAccount.setCreatedAt(new Date());
					currentAccount.setCustomer(item);
					currentAccount.setOverDraft(9000);
					bankAccountRepository.save(currentAccount);
				/* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++  */
					SavingAccount savingAccount = new SavingAccount();
					savingAccount.setId(UUID.randomUUID().toString());
					savingAccount.setBalance(Math.random()*90000);
					savingAccount.setStatus(AccountStatus.CREATED);
					savingAccount.setCreatedAt(new Date());
					savingAccount.setCustomer(item);
					savingAccount.setInterestRate(12);
					bankAccountRepository.save(savingAccount);


				});

				bankAccountRepository.findAll().forEach(item->{
					for (int i=0 ; i<5 ; i++){

						AccountOperation accountOperation= new AccountOperation();
						accountOperation.setBankAccount(item);
						accountOperation.setOperationDate(new Date());
						accountOperation.setType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
						accountOperation.setAmount(Math.random()*1100);
						accountOperationRepository.save(accountOperation);
					}

				});

			};
	}

}


