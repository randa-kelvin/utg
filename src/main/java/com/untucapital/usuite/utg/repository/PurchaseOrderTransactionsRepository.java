package com.untucapital.usuite.utg.repository;


import com.untucapital.usuite.utg.model.PurchaseOrderTransactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PurchaseOrderTransactionsRepository extends JpaRepository<PurchaseOrderTransactions, String> {

    Optional<List<PurchaseOrderTransactions>> getPurchaseOrderTransactionsByPoRequisitionId(String id);

//    List<PurchaseOrderTransactions> findByRequisitionId(String requisitionId);

    boolean existsByPoItemAndPoSupplierAndPoCategoryAndPoQuantityAndPoAmountAndPoRequisitionId(
            String poItem, String poSupplier, String poCategory, String poQuantity, String poAmount, String poRequisitionId);

    List<PurchaseOrderTransactions> findPurchaseOrderTransactionsByPoCategory(String category);
}
