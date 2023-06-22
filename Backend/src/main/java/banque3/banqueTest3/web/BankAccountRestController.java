package banque3.banqueTest3.web;

import banque3.banqueTest3.dtos.AccountHistoryDto;
import banque3.banqueTest3.dtos.CreditDto;
import banque3.banqueTest3.dtos.DebitDto;
import banque3.banqueTest3.dtos.TransferDto;
import banque3.banqueTest3.entities.AccountOperation;
import banque3.banqueTest3.entities.BankAccount;
import banque3.banqueTest3.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @AllArgsConstructor @CrossOrigin("*")
public class BankAccountRestController {


    private BankAccountService bankAccountService ;

    @GetMapping("/accounts/{id}")
    public BankAccount getBankAccount(@PathVariable String id){

        return bankAccountService.getBankAccount(id) ;

    }

    @GetMapping("/accounts")
    public List<BankAccount> getAllBankAccounts(){
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/operations/{id}")
    public List<AccountOperation> getOperationByAccount(@PathVariable String id ){

        return bankAccountService.getOperationsByAccountId(id) ;

    }

        @GetMapping("/history/{accountId}/pageOperation")
        public AccountHistoryDto  getAccountHistory(@PathVariable String accountId ,
                                                @RequestParam(name = "page",defaultValue = "0") int page,
                                                @RequestParam(name = "size",defaultValue = "5") int size
                                                ){
                        return bankAccountService.bankAccountHistory(accountId,page,size) ;
    }

    @PostMapping("/accounts/debit")
    public DebitDto DebitDto(DebitDto debitDto){
            this.bankAccountService.debit(debitDto.getAccountID(),debitDto.getAmount(),debitDto.getDesc());

            return debitDto ;
    }
    @PostMapping("/accounts/credit")
    public CreditDto CreditDto(@RequestBody CreditDto creditDto){
        this.bankAccountService.debit(creditDto.getAccountID(),creditDto.getAmount(),creditDto.getDesc());

        return creditDto ;
    }

    @PostMapping("/accounts/transfer")
        public void trasnferMoney(@RequestBody TransferDto transferDto){

        bankAccountService.transfer(transferDto.getAccountSource(),transferDto.getAccountDestination(),transferDto.getAmount());



    }






}