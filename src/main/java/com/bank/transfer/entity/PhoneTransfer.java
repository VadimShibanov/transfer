package com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * Сущность, представляющая платеж с использованием мобильного устройства
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phone_transfer", schema = "transfer")
public class PhoneTransfer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", nullable = false)
    @NotNull(message = "Should not be NULL")
    @Min(1_000L)
    @Max(1_000_000_000_000_000L)
    private Long phoneNumber;

    @Column(name = "amount", nullable = false, precision = 20, scale = 2)
    @Digits(integer=20,fraction=2, message = "Incompatible BigDecimal type")
    @DecimalMin(value = "0.01", inclusive = false, message = "Wrong amount")
    private BigDecimal amount;

    @NotBlank(message = "Should not be NULL")
    @Column(name = "purpose", nullable = false)
    private String purpose;

    @Column(name = "account_details")
    @Min(1)
    @Max(1_000_000_000)
    private Long accountDetails;
}
