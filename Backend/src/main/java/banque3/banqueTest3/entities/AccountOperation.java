package banque3.banqueTest3.entities;



import banque3.banqueTest3.enums.OperationType;
import jakarta.persistence.*;
import jdk.dynalink.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private Date operationDate ;

    private  double amount ;

    @Enumerated(EnumType.STRING)
    private OperationType type ;

    private  String desrciption ;
    @ManyToOne
    private  BankAccount  bankAccount ;

}
