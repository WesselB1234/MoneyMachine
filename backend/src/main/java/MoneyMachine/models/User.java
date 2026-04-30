package MoneyMachine.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="students")
public class User {
    @Id
    @GeneratedValue
    @Column(name="user_id", length = 10, nullable = false, unique = true)
    private Long userId;

    @Column(name = "first_name", length = 100, nullable = false, unique = false)
    private String firstName;

    @Column(name = "last_name", length = 200, nullable = false, unique = false)
    private String lastName;

    @Column(name = "email", length = 300, nullable = false, unique = true)
    private String email;

    @Column(name = "bsn", length = 10, nullable = false, unique = true)
    private String bsn;

    @Column(name = "phone_number", length = 11, nullable = false, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, unique = true)
    private MoneyMachine.models.enums.Role role;

    @Column(name = "is_active", nullable = false, unique = false)
    private Boolean isActive;

    @Column(name = "is_apptoved", nullable = false, unique = false)
    private Boolean isApproved;

    
}
