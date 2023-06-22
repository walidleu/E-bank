package banque3.banqueTest3.service;

import banque3.banqueTest3.dtos.AccountHistoryDto;
import banque3.banqueTest3.entities.BankAccount;
import banque3.banqueTest3.entities.CurrentAccount;
import banque3.banqueTest3.entities.SavingAccount;
import banque3.banqueTest3.repositories.BankAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository ;
    public void consulter(){
        BankAccount bankAccount = bankAccountRepository.findById("0117ebcd-f8d1-42be-9ebb-4ded81b519d3").orElse(null);

        if (bankAccount !=null){
            System.out.println("****************************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if(bankAccount instanceof CurrentAccount){
                ((CurrentAccount) bankAccount).getOverDraft();
            } else if (bankAccount instanceof SavingAccount) {
                ((SavingAccount) bankAccount).getInterestRate();
            }


        }
    }






}
