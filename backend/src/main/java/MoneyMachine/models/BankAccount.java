package MoneyMachine.models;

import java.math.BigDecimal;

import MoneyMachine.models.enums.BankAccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="bank_accounts")
@Getter
@Setter
@NoArgsConstructor
public class BankAccount {

    @Id
    @Column(name = "iban", length = 10, nullable = false, unique = true)
    private String iban;

    @Column(name = "user_id", length = 10, nullable = false)
    private Long userId;

    @Column(name = "balance", length = 100, nullable = false)
    private BigDecimal balance;

    @Column(name = "absolute_limit", length = 200, nullable = false)
    private BigDecimal absoluteLimit;

    @Column(name = "single_transfer_limit", length = 300, nullable = false)
    private BigDecimal singleTransferLimit;

    @Column(name = "daily_transfer_limit", length = 10, nullable = false)
    private String dailyTransferLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", length = 11, nullable = false)
    private BankAccountType bankAccountType;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public BankAccount(String iban, Long userId, BigDecimal balance, BigDecimal absoluteLimit, BigDecimal singleTransferLimit, String dailyTransferLimit, BankAccountType bankAccountType) {
        this.iban = iban;
        this.userId = userId;
        this.balance = balance;
        this.absoluteLimit = absoluteLimit;
        this.singleTransferLimit = singleTransferLimit;
        this.dailyTransferLimit = dailyTransferLimit;
        this.bankAccountType = bankAccountType;
    }
}