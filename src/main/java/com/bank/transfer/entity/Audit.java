package com.bank.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

/**
 * Сущность, представляющая собой отчет при проведении банковского аудита
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "audit", schema = "transfer")
public class Audit {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entity_type",length=40)
    @NotBlank(message = "Should not be BLANK")
    private String entityType;

    @Column(name = "operation_type",length=40)
    @NotBlank(message = "Should not be BLANK")
    private String operationType;

    @Column(name = "created_by", length = 40)
    @NotBlank(message = "Should not be BLANK")
    private String createdBy;

    @Column(name = "modified_by", length = 40, nullable = true)
    @NotBlank(message = "Should not be BLANK")
    private String modifiedBy;

    @Column(name = "created_at")
    @NotNull(message = "Should not be NULL")
    private ZonedDateTime createdAt;

    @Column(name = "modified_at")
    @NotNull(message = "Should not be NULL")
    private ZonedDateTime modifiedAt;

    @Column(name = "new_entity_json")
    @NotBlank(message = "Should not be BLANK")
    private String newEntityJson;

    @Column(name = "entity_json")
    @NotBlank(message = "Should not be BLANK")
    private String entityJson;
}
