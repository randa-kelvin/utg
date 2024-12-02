package com.untucapital.usuite.utg.dao;//package com.untucapital.usuite.utg.dao;
//
//import com.untucapital.usuite.utg.entity.PostGl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class TransactionDAO {
//
//    private final JdbcTemplate secondJdbcTemplate;  // Inject the JdbcTemplate for the second data source
//
//    @Autowired
//    public TransactionDAO(@Qualifier("secondJdbcTemplate") JdbcTemplate secondJdbcTemplate) {
//        this.secondJdbcTemplate = secondJdbcTemplate;
//    }
//
//    public void saveTransaction(PostGl transactionInfo) {
//        String sql = "INSERT INTO transaction_information " +
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
