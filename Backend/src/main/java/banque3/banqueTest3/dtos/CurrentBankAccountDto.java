package banque3.banqueTest3.dtos;




import banque3.banqueTest3.entities.AccountOperation;
import banque3.banqueTest3.entities.Customer;
import banque3.banqueTest3.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data @NoArgsConstructor @AllArgsConstructor
public class CurrentBankAccountDto {


    private String id;
    private double balance;
    private Date createdAt;

    private AccountStatus status;
    private CustomerDto customerDto ;
    private  double overDraft ;

}

