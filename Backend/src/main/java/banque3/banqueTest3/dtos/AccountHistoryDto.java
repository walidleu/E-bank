package banque3.banqueTest3.dtos;

import banque3.banqueTest3.entities.AccountOperation;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class AccountHistoryDto {


    private String accountId ;

    private double balance ;

    private int currentPage ;

    private int totalPages ;

    private int pageSize ;


    private Page<AccountOperation> accountOperations ;

}
