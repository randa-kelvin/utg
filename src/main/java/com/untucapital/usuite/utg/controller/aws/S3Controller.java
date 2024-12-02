//package com.untucapital.usuite.utg.controller.aws;
//
//import com.itextpdf.kernel.pdf.PdfWriter;
//import com.itextpdf.kernel.pdf.PdfDocument;
//import com.itextpdf.layout.Document;
//import com.itextpdf.layout.element.Paragraph;
//import com.untucapital.usuite.utg.service.aws.S3Service;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.ByteArrayOutputStream;
//import java.util.UUID;
//
//@RestController
//public class S3Controller {
//
//    @Autowired
//    private S3Service s3Service;
//
//    @GetMapping("/generate-and-upload-pdf")
//    public String generateAndUploadPdf() {
//        try {
//            // Generate PDF
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
//            PdfDocument pdf = new PdfDocument(writer);
//            Document document = new Document(pdf);
//            document.add(new Paragraph("Hello, S3!"));
//            document.close();
//
//            // Generate a unique key for the PDF
//            String key = "pdfs/" + UUID.randomUUID() + ".pdf";
//
//            // Upload PDF to S3
//            s3Service.uploadPDF(key, byteArrayOutputStream.toByteArray());
//
//            return "PDF uploaded successfully. S3 key: " + key;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Failed to upload PDF: " + e.getMessage();
//        }
//    }
//}
