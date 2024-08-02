package com.bank.transfer.service;

import com.bank.transfer.entity.Audit;
import java.util.List;
public interface AuditService {
    void add(Audit audit);

    void delete(Audit audit);

    List<Audit> getAuditsByEntityType(String entityType);
}
