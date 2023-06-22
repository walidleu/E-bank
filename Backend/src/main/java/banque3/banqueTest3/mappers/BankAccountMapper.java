package banque3.banqueTest3.mappers;

import banque3.banqueTest3.dtos.CurrentBankAccountDto;
import banque3.banqueTest3.dtos.CustomerDto;
import banque3.banqueTest3.dtos.SavingBankAccountDto;
import banque3.banqueTest3.entities.CurrentAccount;
import banque3.banqueTest3.entities.Customer;
import banque3.banqueTest3.entities.SavingAccount;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapper {

            public CustomerDto fromCustomer(Customer customer){


                CustomerDto customerDto = new CustomerDto();
                BeanUtils.copyProperties(customer,customerDto);

               /* customerDto.setId(customer.getId());
                customerDto.setName(customer.getName());
                customerDto.setEmail(customer.getEmail());
                */
                return customerDto ;

            }

            public Customer fromCustomerDto(CustomerDto customerDto){
                    Customer customer = new Customer();
                    BeanUtils.copyProperties(customerDto,customer);
                    return customer ;
            }


            public SavingBankAccountDto fromSavingBankAccount(SavingAccount savingAccount){

                SavingBankAccountDto savingBankAccountDto = new SavingBankAccountDto() ;
                BeanUtils.copyProperties(savingAccount,savingBankAccountDto);
                return  savingBankAccountDto ;

            }

            public SavingAccount fromSavingBankAccountDto(SavingBankAccountDto savingBankAccountDto){

                SavingAccount savingAccount = new SavingAccount();
                BeanUtils.copyProperties(savingBankAccountDto,savingAccount);
                return savingAccount ;

            }
            public CurrentBankAccountDto fromCurrentAccount(CurrentAccount currentAccount){

                    CurrentBankAccountDto currentBankAccountDto = new CurrentBankAccountDto();
                    BeanUtils.copyProperties(currentAccount,currentBankAccountDto);
                    return currentBankAccountDto;

            }

            public CurrentAccount fromCurrentAccountDto(CurrentBankAccountDto currentBankAccountDto){


                CurrentAccount currentAccount = new CurrentAccount();
                BeanUtils.copyProperties(currentBankAccountDto,currentAccount);
                return currentAccount;

    }


    }