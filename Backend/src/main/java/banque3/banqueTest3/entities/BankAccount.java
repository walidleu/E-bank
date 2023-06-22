package banque3.banqueTest3.entities;




import banque3.banqueTest3.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //Our Bank in the schema is a  signle table not (joined table or table per class)
@DiscriminatorColumn(name = "Type",length = 4) // le type de chaque colone dans ce cas soit epargne soit courant
@Data @NoArgsConstructor @AllArgsConstructor
public class BankAccount {



    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @ManyToOne
    private Customer customer;


    @OneToMany(mappedBy = "bankAccount") @JsonIgnore
    private List<AccountOperation> accountOperations;



}

