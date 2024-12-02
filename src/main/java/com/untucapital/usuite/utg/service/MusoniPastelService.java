package com.untucapital.usuite.utg.service;//package com.untucapital.usuite.utg.service;
//
//import com.untucapital.usuite.utg.model.MusoniPastel;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.boot.configurationprocessor.json.JSONArray;
//import org.springframework.boot.configurationprocessor.json.JSONException;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Service;
//
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.sql.*;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//
//@Service
//@Getter
//@Setter
//@AllArgsConstructor
//@Configuration
//public class MusoniPastelService {
//    static final String DB_URL = "jdbc:mysql://localhost:3306/u-tran-gateway-db";
//    static final String USER = "tranGatewayAdmin";
//    static final String PASS = "u92uCuwte9@ta";
//
//    private static String readAll(Reader rd) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        int cp;
//        while ((cp = rd.read()) != -1) {
//            sb.append((char) cp);
//        }
//        return sb.toString();
//    }
//
//    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
//        InputStream is = new URL(url).openStream();
//        try {
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//            String jsonText = readAll(rd);
//            JSONObject json = new JSONObject(jsonText);
//            return json;
//        } finally {
//            is.close();
//        }
//    }
//
//    public static void main(String[] args) {
//        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
//
//        // Schedule the task to run every 5 hours
////        executor.scheduleAtFixedRate(MusoniPastel::runScript, 0, 24, TimeUnit.HOURS);
//    }
//    public static void runScript() {
//        try(
////                Class.forName("com.mysql.jdbc.Driver");
////                DriverManager.registerDriver(new com.jdbc.Driver ());
//                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//                Statement stmt0 = conn.createStatement();
//                Statement stmt = conn.createStatement();
//                Statement stmt1 = conn.createStatement();
//                Statement stmt2 = conn.createStatement();
//                Statement stmt3 = conn.createStatement();
//                Statement stmt4 = conn.createStatement();
//                Statement stmt5 = conn.createStatement();
//                Statement stmt6 = conn.createStatement();
//        ) {
//
////         #############################################################################################################
//
////         COLLECTING LOAN IDS FROM MUSONI TO DATABASE TABLE
//
////         #############################################################################################################
//
//
//            Timestamp timestamp = (new Timestamp(System.currentTimeMillis()));
//            long stamps = timestamp.getTime();
//            String stampString = String.valueOf(stamps);
//            String stamp = stampString.substring(0, stampString.length()-3);
//            long timestamps = Long.valueOf(stamp) - 86400L; // 1 day is 86400
//
//            JSONObject json = readJsonFromUrl("http://localhost:7878/api/utg/musoni/loans/modifiedSinceTimestamp/"+ 1694469600L);
//            JSONArray pageItems = json.getJSONArray("pageItems");
//
//
//
//            for (int i = 0; i < pageItems.length(); i++) {
//                JSONObject page = pageItems.getJSONObject(i);
//
//                //  Check if Loan_id exists
//                ResultSet res = stmt0.executeQuery("SELECT * FROM musoni_loan_ids");
//                int loanId = (Integer) page.get("id");
//                List<Integer> loansArray = new ArrayList<Integer>();
//                while (res.next()) {
//                    loansArray.add(res.getInt("loan_id"));
//                }
//                List<Integer> array = loansArray;
//                if (array.contains(loanId)) {
//                    System.out.println("Loan ID Already Exists");
//                    System.out.println(loanId);
//                }else {
//                    // INSERT LOAN ID INTO DATABASE
//                    System.out.println("Inserting records into the table...");
//                    String sql = "INSERT INTO `musoni_loan_ids`(`loan_id`) VALUES (" + loanId + ")";
//                    stmt0.executeUpdate(sql);
//                    System.out.println(loanId);
//                }
//            }
//
//
//
////         #############################################################################################################
//
////          SELECT LOAN IDS FROM TABLE AND MATCH WITH TRANSACTION IDS
//
////         #############################################################################################################
//
//            ResultSet rs = stmt4.executeQuery("SELECT * FROM musoni_loan_ids");
//            //                SELECTED LARGEST TRANS ID IN MUSONI
//            ResultSet transIdrs = stmt3.executeQuery("SELECT transaction_id FROM musoni_pastel WHERE transaction_id = (SELECT MAX(transaction_id) FROM musoni_pastel)");
//
//            int transactId = 504779; // Initialize with a default value
//
//            if (transIdrs.next()) {
//                transactId = transIdrs.getInt("transaction_id");
//            }
//            while (rs.next()) {
//                int loanId = rs.getInt("loan_id");
//                for (int transId = transactId; transId < transactId+20; transId++) {
//                    URL url = new URL("http://localhost:7878/api/utg/musoni/getTransations/loanid/" + loanId +"/transactionId/"+ transId);
//                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.connect();
//                    int code = connection.getResponseCode();
//                    System.out.println(code);
//
//                    if (code == 200) {
//                        json = readJsonFromUrl("http://localhost:7878/api/utg/musoni/getTransations/loanid/" + loanId +"/transactionId/"+ transId);
////                        RETURNED JSON WHEN STATUS OK (200)
////                        System.out.println(json.toString());
//                        JSONObject type = json.getJSONObject("type");
//
////                        GET TRANSACTION TYPE
//                        Object transactionType = type.get("value");
//                        System.out.println(transactionType);
//
////                        GET TRANSACTION DATE
//                        JSONArray datetime = json.getJSONArray("date");
//                        String oldstring = datetime.get(0) + "-" + datetime.get(1) + "-" + datetime.get(2);
//                        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
//                        SimpleDateFormat old_format = new SimpleDateFormat("yyyy-MM-dd");
//                        SimpleDateFormat new_format = new SimpleDateFormat("dd-MMM-yyyy");
//                        Date transDates = old_format.parse(oldstring);
//                        String transDate = new_format.format(transDates);
//                        System.out.println(transDate);
//
////                        GET TRACTIONACTION AMOUNT
//                        Object amount = json.get("amount");
//                        System.out.println(amount);
//
////                        GET TRANSACTION CODE
//                        JSONObject currency = json.getJSONObject("currency");
//                        Object currencyCode = currency.get("code");
//                        System.out.println(currencyCode);
//                        System.out.println("Loan ID is: " + loanId + "\n And transaction ID is: " + transId);
//
//
////                        GET OFFICE NAME FROM LOAN ID
//                        JSONObject officeJson = readJsonFromUrl("http://localhost:7878/api/utg/musoni/loans/" + loanId);
//                        String officeName = officeJson.getString("officeName");
//                        System.out.println("Branch name is: " + officeName);
//
//
//
//                        String fromAccount = "000/000/000";
//                        String toAccount = "000/000/000";
////                        FOR DISBURSMENT TRANSACTION
//                        if (transactionType.equals("Disbursement")) {
//                            System.out.println(transactionType);
//                            String transType = "LOA-DIS";
//                            String transDescr = "Disbursement-transaction";
//
//                            if(Objects.equals(officeName, "Harare") || Objects.equals(officeName, "HarareA")) {
//                                fromAccount = "8422/000/HRE/FCA";   // from teller
//                                toAccount = "8000/000/HO/LD";     // to disbursement acc
//                            }
//
//                            else if (Objects.equals(officeName, "Bulawayo")) {
//                                fromAccount = "8422/000/BYO/FCA";
//                                toAccount = "8000/000/HO/LD";     // to disbursement acc
//
//                            } else if (Objects.equals(officeName, "Gweru")) {
//                                fromAccount = "8422/000/GWR/FCA";
//                                toAccount = "8000/000/HO/LD";     // to disbursement acc
//
//                            } else if (Objects.equals(officeName, "Gokwe")) {
//                                fromAccount = "8422/000/GKW/FCA";
//                                toAccount = "8000/000/HO/LD";     // to disbursement acc
//
//                            } else {
//                                fromAccount = "8422/000/HO/FCA/FCA";
//                                toAccount = "8000/000/HO/LD";     // to disbursement acc
//                            }
//                            String referenc = "DS"+transId;
//
//                            System.out.println("############################# CAN NOW READ SCRIPT ##############################");
//
//                            ResultSet searchTransId = stmt1.executeQuery("SELECT * FROM musoni_pastel WHERE transaction_id = " + transId);
//
//                            List<Integer> transIdArray = new ArrayList<Integer>();
//                            while (searchTransId.next()) {
//                                transIdArray.add(searchTransId.getInt("transaction_id"));
//                            }
//                            List<Integer> transArray = transIdArray;
//
//                            if (transArray.contains(transId)) {
//                                System.out.println("Transaction ID Already Exists");
//                                System.out.println(transId);
//                            }else {
//                                //  INSERT TRANSACTION DETAILS INTO DATABASE
//                                System.out.println("Inserting transaction records into the table...");
//                                String sql = "INSERT INTO `musoni_pastel` (`amount`, `currency`, `description`, `exchange_rate`, `from_account`, `loan_id`, `reference`, `to_account`, `transaction_date`, `transaction_id`, `transaction_type`, `synced`) VALUES (" + amount + ", '001' , '" + transDescr + "'  , '1', '" +fromAccount + "', " + loanId + ", '" + referenc + "', '" + toAccount + "', '" + transDate + "', '" +transId + "', '" +transType + "', '0')";
//                                stmt1.executeUpdate(sql);
////                                stmt1.close();
//                                System.out.println(transId);
//
//                            }
//                            searchTransId.close();
//                        }
//
////                        FOR REPAYMENT TRANSACTION
//                        else if (transactionType.equals("Repayment")) {
//                            String transType = "LOA-REP";
//                            String transDescr = "Repayment Transaction";
//                            if(Objects.equals(officeName, "Harare") || Objects.equals(officeName, "HarareA")) {
//                                fromAccount = "8000/000/HO/LR";   // from loan repayment
//                                toAccount = "8422/000/HRE/FCA";     // to teller acc
//                            }
//
//                            else if (Objects.equals(officeName, "Bulawayo")) {
//                                fromAccount = "8000/000/HO/LR";
//                                toAccount = "8422/000/BYO/FCA";
//
//                            } else if (Objects.equals(officeName, "Gweru")) {
//                                fromAccount = "8000/000/HO/LR";
//                                toAccount = "8422/000/GWR/FCA";
//
//                            } else if (Objects.equals(officeName, "Gokwe")) {
//                                fromAccount = "8000/000/HO/LR";
//                                toAccount = "8422/000/GKW/FCA";
//
//                            } else {
//                                fromAccount = "8000/000/HO/LR";
//                                toAccount = "8422/000/HO/FCA/FCA";
//                            }
//                            String referenc = "RP"+transId;
//
//                            ResultSet searchTransId = stmt5.executeQuery("SELECT * FROM musoni_pastel WHERE transaction_id = " + transId);
//
//                            List<Integer> transIdArray = new ArrayList<Integer>();
//                            while (searchTransId.next()) {
//                                transIdArray.add(searchTransId.getInt("transaction_id"));
//                            }
//                            List<Integer> transArray = transIdArray;
//
//                            if (transArray.contains(transId)) {
//                                System.out.println("Transaction ID Already Exists");
//                                System.out.println(transId);
//                            }else {
//                                // INSERT TRANSACTION DETAILS INTO DATABASE
//                                System.out.println("Inserting transaction records into the table...");
//                                String sql = "INSERT INTO `musoni_pastel` (`amount`, `currency`, `description`, `exchange_rate`, `from_account`, `loan_id`, `reference`, `to_account`, `transaction_date`, `transaction_id`, `transaction_type`, `synced`) VALUES (" + amount + ", '001' , '" + transDescr + "'  , '1', '" +fromAccount + "', " + loanId + ", '" + referenc + "', '" + toAccount + "', '" + transDate + "', '" +transId + "', '" +transType + "', '0')";
//                                stmt.executeUpdate(sql);
//                                System.out.println(transId);
//                            }
//                        } else {
//                            System.out.println("Non of the conditions met!!!");
//                        }
//                    }
//                }
//                // EXIT WHILE LOOP
//            }
//
////      ##########################################################################################################
//
////      POST TRANSACTION FROM DATABASE TO PASTEL EVOLUTION
//
////      ##########################################################################################################
//
//
//            ResultSet non_synced = stmt6.executeQuery("SELECT * FROM musoni_pastel WHERE synced = 0");
//
//            while (non_synced.next()) {
//                int nonSyncedTrans = non_synced.getInt("transaction_id");
//                JSONObject jsonObject = new JSONObject();
//                //Inserting key-value pairs into the json object
//                jsonObject.put("APIUsername", "Admin");
//                jsonObject.put("APIPassword", "Admin");
//                jsonObject.put("TransactionType", non_synced.getString("transaction_type"));
//                jsonObject.put("TransactionDate", non_synced.getString("transaction_date"));
//                jsonObject.put("FromAccount", non_synced.getString("from_account"));
//                jsonObject.put("ToAccount", non_synced.getString("to_account"));
//                jsonObject.put("Reference", non_synced.getString("reference"));
//                jsonObject.put("Description", non_synced.getString("description"));
//                jsonObject.put("Currency", non_synced.getString("currency"));
//                jsonObject.put("Amount", non_synced.getString("amount"));
//                jsonObject.put("ExchangeRate", non_synced.getString("exchange_rate"));
//
//                URL url = new URL("http://192.168.2.104:1335/api/PastelTeller/PostJournalTxn");
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                con.setRequestMethod("POST");
//                con.setDoOutput(true);
//                byte[] out = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
//                int length = out.length;
//                con.setFixedLengthStreamingMode(length);
//                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                con.connect();
//
////                int respCode = con.getResponseCode();
////                System.out.println(respCode);
//
//                try (OutputStream os = con.getOutputStream()) {
//                    os.write(out);
//                    int respCode = con.getResponseCode();
//                    System.out.println("..Reached Here2..");
//                    System.out.println("Your response is: " + respCode);
//                    System.out.println(jsonObject);
//
//                    if (respCode == 200) {
//                        String sql = "UPDATE `musoni_pastel` SET `synced`='1' WHERE `transaction_id` = " + nonSyncedTrans;
//                        stmt2.executeUpdate(sql);
//                        // stmt2.close();
//                    } else {
//                        System.out.println("..Failed to post transaction..");
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.print("############################################### \n\nEXECUTION PROCESS HAS COMPLETED SUCCESSFULLY \n\n#################################################");
//
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
