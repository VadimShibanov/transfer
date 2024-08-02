package com.bank.transfer.service;

import com.bank.transfer.entity.Audit;
import com.bank.transfer.repository.AuditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Сервис обменна данных при проведении аудита платежей
 */
@Service
@AllArgsConstructor
public class AuditServiceImpl implements AuditService{
    private final AuditRepository auditRepository;

    /**
     * Метод добавляет одну запись в таблицу аудитов
     */
    @Override
    public void add(Audit audit) {
        auditRepository.save(audit);
    }

    @Override
    public void delete(Audit audit) {
        auditRepository.delete(audit);
    }

    /**
     * Метод возвращает список всех аудитов для данного типа сущности
     */

    @Override
    public List<Audit> getAuditsByEntityType(String entityType) {
        return auditRepository.findByEntityType(entityType);
    }
}
