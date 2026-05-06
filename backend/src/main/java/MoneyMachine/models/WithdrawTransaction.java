package MoneyMachine.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WithdrawTransaction extends Transaction {

    public WithdrawTransaction(long initiatingUserId, BigDecimal amount, String message, boolean isActive, String fromAccountIban) {
        super(initiatingUserId, amount, message, isActive); 
        this.fromAccountIban = fromAccountIban;
    }

    @Column(name = "from_account_iban", nullable = false)
    private String fromAccountIban;
}
