package it.tiriguarda.util;

import java.io.File;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.element.Table;

import it.tiriguarda.dto.EventoRiepilogo;

public class GeneratorePDFUtil {
    public static void genera(File file, List<EventoRiepilogo> eventi) throws Exception {
        PdfWriter writer = new PdfWriter(file.getAbsolutePath());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Report Riepilogo Attività").setFontSize(20).setBold());

        Table table = new Table(UnitValue.createPercentArray(new float[]{30, 70})).useAllAvailableWidth();
        table.addHeaderCell("Data");
        table.addHeaderCell("Descrizione");

        for (EventoRiepilogo e : eventi) {
            table.addCell(e.getData().toString());
            table.addCell(e.getDescrizione());
        }

        document.add(table);
        document.close();
    }
}