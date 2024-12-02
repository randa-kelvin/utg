package com.untucapital.usuite.utg.dao;//package com.untucapital.usuite.utg.dao;
//
//import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class VaultDAO {
//
//    private final JdbcTemplate secondJdbcTemplate;  // Inject the JdbcTemplate for the second data source
//
//    @Autowired
//    public VaultDAO(@Qualifier("secondJdbcTemplate") JdbcTemplate secondJdbcTemplate) {
//        this.secondJdbcTemplate = secondJdbcTemplate;
//    }
//
//    public void saveTransaction(TransactionInfo transactionInfo) {
//        String sql = "INSERT INTO t " +
//                "(to_account, transaction_type, exchange_rate, description," +
//                " from_account, reference, currency, amount, transaction_date) " +
//                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//
//        secondJdbcTemplate.update(sql,
//                transactionInfo.getToAccount(),
//                transactionInfo.getTransactionType(),
//                transactionInfo.getExchangeRate(),
//                transactionInfo.getDescription(),
//                transactionInfo.getFromAccount(),
//                transactionInfo.getReference(),
//                transactionInfo.getCurrency(),
//                transactionInfo.getAmount(),
//                transactionInfo.getTransactionDate()
//        );
//    }
//}
