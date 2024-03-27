module com.spenditure {
    requires transitive java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;
    requires javafx.media;
    requires java.desktop;
    requires itextpdf;
    requires tess4j;
    requires org.apache.pdfbox;

    opens com.spenditure to javafx.graphics, javafx.fxmln;
    exports com.spenditure;
}
