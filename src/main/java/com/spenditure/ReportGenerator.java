package com.spenditure;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class ReportGenerator {

    // Create a PdfPageEventHelper and override the onEndPage method
    class MyPdfPageEventHelper extends PdfPageEventHelper {
        Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);

        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            Phrase footer = new Phrase("Page " + document.getPageNumber(), ffont);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    footer,
                    (document.right() - document.left()) / 2 + document.leftMargin(),
                    document.bottom() - 10, 0);
        }
    }

    public void generateReport(User user) {
        List<Transaction> reportTransactions = user.getTransactionsAll();

        try {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50); // A4 page size, 50 unit margins
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Report.pdf"));
            writer.setPageEvent(new MyPdfPageEventHelper());
            document.open();

            java.net.URL logoOneUrl = getClass().getResource("/Images/GoldCover.png");
            com.itextpdf.text.Image cover = com.itextpdf.text.Image.getInstance(logoOneUrl);
            cover.scaleToFit(500, 500);
            cover.setAlignment(Element.ALIGN_CENTER);
            document.add(cover);

            java.net.URL logoTwoUrl = getClass().getResource("/Images/Logo.png");
            com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance(logoTwoUrl);

            logo.scaleToFit(100, 100);
            float x = PageSize.A4.getWidth() - logo.getScaledWidth() -250 ; // Center the logo horizontally
            float y = PageSize.A4.getHeight() - logo.getScaledHeight() - 75; 
            logo.setAbsolutePosition(x, y);
            cover.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);

            for (int i = 0; i < 3; i++) {
                document.add(new Paragraph(" "));
            }

            // title in Gramond 50 font Garamond
            Font titleFont = FontFactory.getFont("Garamond", 30, Font.BOLD, new BaseColor(218, 165, 32));
            Paragraph title = new Paragraph("Transaction Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // leave an empty line
            document.add(new Paragraph(" "));

            LineSeparator line = new LineSeparator();
            line.setPercentage(80);
            document.add(line);

            // Add table headers
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(80);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Define colors for header and alternating rows
            BaseColor headerColor = new BaseColor(218, 165, 32); // Green color for header
            BaseColor evenRowColor = new BaseColor(255, 245, 179); // Light gray color for even rows

            // Add headers with styling
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            for (String header : new String[] { "Amount", "Location", "Transaction Date" }) {
                PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
                cell.setBackgroundColor(headerColor);
                table.addCell(cell);
            }

            // Add data with alternating row color
            int rowCount = 0;
            for (Transaction transaction : reportTransactions) {
                if (rowCount % 2 == 1) {
                    for (String data : new String[] {
                            String.valueOf(transaction.getAmount()),
                            transaction.getLocation(),
                            transaction.getTransactionDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    }) {
                        PdfPCell cell = new PdfPCell(new Phrase(data));
                        cell.setBackgroundColor(evenRowColor);
                        table.addCell(cell);
                    }
                } else {
                    table.addCell(String.valueOf(transaction.getAmount()));
                    table.addCell(transaction.getLocation());
                    table.addCell(transaction.getTransactionDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                }
                rowCount++;

                if (document.bottom() < 50) {
                    document.newPage();
                }

                // Check if the document needs to be split to a new page
                if (table.getTotalHeight() > document.getPageSize().getHeight() - 100) { // Check if the table height
                    document.add(table);
                    document.newPage();
                    table = new PdfPTable(3); // Create a new table for the next page
                    table.setWidthPercentage(80); // Set the width to 80% of the page width
                    table.setSpacingBefore(10f);
                    table.setSpacingAfter(10f);
                }
            }

            document.add(table);
            document.close();

            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "start", "Report.pdf").start();
            } else {
                new ProcessBuilder("open", "Report.pdf").start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
