package banque3.banqueTest3.web;


import banque3.banqueTest3.dtos.CustomerDto;
import banque3.banqueTest3.entities.Customer;
import banque3.banqueTest3.service.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j @CrossOrigin("*")
public class CustomerRestController {

    private BankAccountService bankAccountService;

    @GetMapping("/")
    public List<CustomerDto> customerList(){
        return  bankAccountService.listCustomer();
    }


    @GetMapping("/customer/{id}")
    public CustomerDto getCustomer(@PathVariable(name="id") Long id){

        return  bankAccountService.getCustomer(id);

    }

    @PostMapping("/save")
    public CustomerDto saveCustomer(@RequestBody CustomerDto customerDto){

        return bankAccountService.saveCustomer(customerDto) ;

    }


    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){

        bankAccountService.deleteCustomer(id) ;
    }

    @GetMapping("/customers/search")
    public List<Customer> SearchCustomer(@RequestParam(name ="keyword",defaultValue ="")String keyword){
        return  bankAccountService.searchCustomer(keyword) ;
    }








}
