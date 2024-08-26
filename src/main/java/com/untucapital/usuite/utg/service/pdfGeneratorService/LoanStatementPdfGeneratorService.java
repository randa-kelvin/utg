package com.untucapital.usuite.utg.service.pdfGeneratorService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.text.pdf.PdfPTable;
import com.untucapital.usuite.utg.dto.client.repaymentSchedule.ClientStatementResponse;
import com.untucapital.usuite.utg.model.transactions.interim.dto.SavingsTransactionDTO;
import com.untucapital.usuite.utg.model.transactions.interim.dto.TransactionDTO;
import com.untucapital.usuite.utg.service.MusoniService;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoanStatementPdfGeneratorService {

    private final MusoniService musoniService;

    public byte[] generateLoanSchedulePdf(List<Map<String, Object>> loanAccRepay) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Add a title
        document.add(new Paragraph("Client Loan Schedule").setFontSize(18).setBold().setTextAlignment(TextAlignment.CENTER));

        // Add table headers
        float[] columnWidths = {1, 2, 2, 2, 2};
        Table table = new Table(columnWidths);

        table.addHeaderCell(new Cell().add(new Paragraph("Date")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Paid By")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Total Due")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Total Paid")).setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Cell().add(new Paragraph("Total Outstanding")).setBackgroundColor(ColorConstants.LIGHT_GRAY));

        // Add table data
        for (Map<String, Object> loanBal : loanAccRepay) {
            table.addCell(new Cell().add(new Paragraph((String) loanBal.get("date"))));
            table.addCell(new Cell().add(new Paragraph((String) loanBal.get("paidBy"))));
            table.addCell(new Cell().add(new Paragraph((String) loanBal.get("totalDue"))));
            table.addCell(new Cell().add(new Paragraph((String) loanBal.get("totalPaid"))));
            table.addCell(new Cell().add(new Paragraph((String) loanBal.get("totalOutstanding"))));
        }

        document.add(table);
        document.close();

        return baos.toByteArray();
    }


    public ByteArrayInputStream generateAmortizationSchedulePdf(String loanAccount) throws ParseException {

        List<ClientStatementResponse> clientStatementResponses = musoniService.getClientRepaymentSchedule(loanAccount);


        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Summarized Statement")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(18));

            Table table = new Table(new float[]{3, 3, 3, 3, 3});
//            table.addHeaderCell("Period");
            table.addHeaderCell("Due Date");
            table.addHeaderCell("Paid By");
            table.addHeaderCell("Total Due");
            table.addHeaderCell("Total Paid");
            table.addHeaderCell("Total Outstanding");

            for (ClientStatementResponse entry : clientStatementResponses) {
//                table.addCell(String.valueOf(entry.getPeriod()));
                table.addCell(String.valueOf(entry.getDueDate()));
                if(entry.getPaidBy() != null) {
                    table.addCell(String.valueOf(entry.getPaidBy()));
                }else {
                    table.addCell("- - -");
                }
                table.addCell(String.format("%.2f", entry.getAmountDue()));
                table.addCell(String.format("%.2f", entry.getAmountPaid()));
                table.addCell(String.format("%.2f", entry.getAmountOutstanding()));
            }

            document.add(table);
            document.close();

            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TransactionDTO> getCombinedTransactions(int loanId, int savingsId, int postMaturityFeeId) throws JsonProcessingException {
        List<TransactionDTO> combinedTransactions = new ArrayList<>();

        // Get transactions by loan ID
        List<TransactionDTO> loanTransactions = musoniService.getTransactionsByLoanId(loanId);
        combinedTransactions.addAll(loanTransactions);

        // Get transactions by savings ID
        List<SavingsTransactionDTO> savingsTransactions = musoniService.getTransactionsBySavingsId(savingsId);
        combinedTransactions.addAll(convertToTransactionDTO(savingsTransactions));

        // Get transactions by post-maturity fee ID
        List<TransactionDTO> postMaturityFeeTransactions = musoniService.getTransactionsByPostMaturityFeeId(postMaturityFeeId);
        log.info("postMaturityFeeTransactions : {}",postMaturityFeeTransactions);
        combinedTransactions.addAll(postMaturityFeeTransactions);

        List<TransactionDTO> penaltyTransactions = musoniService.getAndProcessLoanRepayment(String.valueOf(loanId));
        combinedTransactions.addAll(penaltyTransactions);

        // Optionally, sort the combined transactions by date or another criterion
         combinedTransactions.sort(Comparator.comparing(TransactionDTO::getDate));

        return combinedTransactions;
    }

//
//    private List<TransactionDTO> convertToTransactionDTO(List<SavingsTransactionDTO> savingsTransactions) {
//        List<TransactionDTO> transactionDTOs = new ArrayList<>();
//        for (SavingsTransactionDTO savingsTransaction : savingsTransactions) {
//            TransactionDTO transactionDTO = new TransactionDTO();
//            transactionDTO.setId(savingsTransaction.getId());
//            transactionDTO.setCode(savingsTransaction.getCode());
//            transactionDTO.setValue(savingsTransaction.getValue());
//            transactionDTO.setDate(savingsTransaction.getDate());
//            transactionDTO.setAmount(savingsTransaction.getAmount());
//            // Map other necessary fields
//            transactionDTOs.add(transactionDTO);
//        }
//        return transactionDTOs;
//    }

    private List<TransactionDTO> convertToTransactionDTO(List<SavingsTransactionDTO> savingsTransactions) {
        // Convert SavingsTransactionDTO to TransactionDTO, if needed
        // Assuming that TransactionDTO and SavingsTransactionDTO have similar structure
        return savingsTransactions.stream()
                .map(savingsTransaction -> {
                    TransactionDTO transactionDTO = new TransactionDTO();
                    transactionDTO.setId(savingsTransaction.getId());
                    transactionDTO.setType(savingsTransaction.getTransactionType());
                    LocalDate date = MusoniUtils.convertToLocalDate(savingsTransaction.getDate());
                    transactionDTO.setDate(date);
//                    transactionDTO.setDate(savingsTransaction.getDate());
                    transactionDTO.setCurrency(savingsTransaction.getCurrency());
                    transactionDTO.setAmount(savingsTransaction.getAmount());
                    transactionDTO.setSubmittedByUsername(null); // Or set the appropriate value
                    // Set other fields as needed
                    return transactionDTO;
                })
                .collect(Collectors.toList());
    }

    public ByteArrayInputStream generateInterimStatementPdf(int loanId, int savingsId, int postMaturityFeeId) throws ParseException, JsonProcessingException {
        List<TransactionDTO> transactions = getCombinedTransactions(loanId, savingsId, postMaturityFeeId);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Add Header
            document.add(new Paragraph("Loan Statement")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(18));

            document.add(new Paragraph("Account No: " + loanId)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(12));

            document.add(new Paragraph("Interest Rate: 9.0%")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(12));

            document.add(new Paragraph("Print Date: " + new SimpleDateFormat("dd.MM.yyyy").format(new Date()))
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(12));

            document.add(new Paragraph("\n"));

            // Add Table with width adjustments
            float[] columnWidths = {3, 3, 3, 3, 3}; // Adjust the number of columns and their relative widths
            Table table = new Table(columnWidths);
            table.setWidth(UnitValue.createPercentValue(100)); // Set the table to fill the width of the page

            // Add table headers
            table.addHeaderCell(new Cell().add(new Paragraph("Date")));
            table.addHeaderCell(new Cell().add(new Paragraph("Transaction Type")));
            table.addHeaderCell(new Cell().add(new Paragraph("Debit")));
            table.addHeaderCell(new Cell().add(new Paragraph("Credit")));
            table.addHeaderCell(new Cell().add(new Paragraph("Balance")));

            double balance = 0.0;

            // Iterate through transactions and populate the table
            for (TransactionDTO transaction : transactions) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(transaction.getDate()))));

                String transactionType = transaction.getType().getValue();
                if (transactionType.equalsIgnoreCase("Accrual")) {
                    transactionType = "Interest Applied";
                } else if (transactionType.equalsIgnoreCase("Deposit")) {
                    transactionType = "Repayment";
                }
                table.addCell(new Cell().add(new Paragraph(transactionType)));

                if (transactionType.equalsIgnoreCase("Disbursement") || transactionType.equalsIgnoreCase("Interest Applied") || transactionType.equalsIgnoreCase("Fee Applied")) {
                    table.addCell(new Cell().add(new Paragraph(String.format("%.2f", transaction.getAmount()))));
                    table.addCell(new Cell().add(new Paragraph("0.00")));
                    balance += transaction.getAmount();
                } else if (transactionType.equalsIgnoreCase("Repayment")) {
                    table.addCell(new Cell().add(new Paragraph("0.00")));
                    table.addCell(new Cell().add(new Paragraph(String.format("%.2f", transaction.getAmount()))));
                    balance -= transaction.getAmount();
                } else {
                    table.addCell(new Cell().add(new Paragraph("0.00")));
                    table.addCell(new Cell().add(new Paragraph("0.00")));
                }

                table.addCell(new Cell().add(new Paragraph(String.format("%.2f", balance))));
            }

            document.add(table);
            document.close();

            return new ByteArrayInputStream(out.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
