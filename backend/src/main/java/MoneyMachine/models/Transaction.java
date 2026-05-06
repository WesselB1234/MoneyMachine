package MoneyMachine.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    
    @Id
    @GeneratedValue
    @Column(name = "transaction_id", nullable = false, unique = true)
    private long transactionId;
    @Column(name = "initiating_user_id", nullable = false)
    private long initiatingUserId;
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    @Column(name = "message", nullable = false)
    private String message;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public Transaction(long initiatingUserId, BigDecimal amount, String message, Boolean isActive) {
        this.initiatingUserId = initiatingUserId;
        this.amount = amount;
        this.message = message;
        this.isActive = isActive;
    }
}
