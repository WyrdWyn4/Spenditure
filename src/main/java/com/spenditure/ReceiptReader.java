package com.spenditure;

import net.sourceforge.tess4j.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;


public class ReceiptReader {
    public String readReceipt(String filePath) throws Exception {
        String text;
        if (filePath.endsWith(".pdf")) {
            text = readPdfReceipt(filePath);
        } else {
            text = readImageReceipt(filePath);
        }
        return text;
    }

    private String readPdfReceipt(String filePath) throws Exception {
        System.out.println("Reading pdf");
        PDDocument document = PDDocument.load(new File(filePath));
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }

    private String readImageReceipt(String filePath) throws Exception {
        System.out.println("Reading image");
        Tesseract tesseract = new Tesseract();
        String datapath = ReceiptReader.class.getResource("/tessdata").getPath();
        tesseract.setDatapath(datapath);
        String text = tesseract.doOCR(new File(filePath));
        return text;
    }
}