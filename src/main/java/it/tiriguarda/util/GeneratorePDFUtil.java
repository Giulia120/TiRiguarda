package it.tiriguarda.util;

import java.io.File;
import java.util.List;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import it.tiriguarda.dto.EventoRiepilogo;

public class GeneratorePDFUtil {
	
	private GeneratorePDFUtil() {
	    // Costruttore privato per prevenire l'istanziazione di questa classe utility
	}
	
    public static void genera(File file, List<EventoRiepilogo> eventi) throws Exception {
        PdfWriter writer = new PdfWriter(file.getAbsolutePath());
        PdfDocument pdf = new PdfDocument(writer);
        
        Document document = new Document(pdf);
        document.setMargins(36, 36, 36, 36);

        Table headerTable = new Table(UnitValue.createPercentArray(new float[]{25, 75})).useAllAvailableWidth();
        headerTable.setBorder(null);

        try {
            String imagePath = GeneratorePDFUtil.class.getResource("/it/tiriguarda/view/images/logo.png").toString();
            ImageData data = ImageDataFactory.create(imagePath);
            Image logo = new Image(data);
            logo.setWidth(170);
            headerTable.addCell(new Cell().add(logo).setBorder(null));
        } catch (Exception e) {
            headerTable.addCell(new Cell().add(new Paragraph("")).setBorder(null));
        }

        Cell titleCell = new Cell().add(new Paragraph("Report Riepilogo Attività").setFontSize(20).setBold()).setBorder(null);
        headerTable.addCell(titleCell);

        document.add(headerTable);
        document.add(new Paragraph("\n"));

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