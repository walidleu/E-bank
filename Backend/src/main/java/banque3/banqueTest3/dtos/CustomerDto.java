package banque3.banqueTest3.dtos;




import banque3.banqueTest3.entities.BankAccount;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data @AllArgsConstructor @NoArgsConstructor
public class CustomerDto {

    private Long id;
    private String name;
    private String email;



}
