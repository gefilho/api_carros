package carro.com.api.Service;
import java.io.OutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import carro.com.api.Model.Carro;

public class CarroRelatorioService {

    public void gerarRelatorioPdf(List<Carro> carros, OutputStream outputStream) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            Font font = new Font(Font.FontFamily.UNDEFINED, 18);
            Paragraph titulo = new Paragraph("Relatório de Carros", font);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

            PdfPTable table = new PdfPTable(6);
            table.addCell("Nome");
            table.addCell("Preço");
            table.addCell("Ano");
            table.addCell("Marcas");
            table.addCell("Cidade");
            table.addCell("Placa");

            for (Carro carro : carros) {
            	PdfPCell nomeCell = new PdfPCell(new Paragraph(carro.getNome()));
                nomeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(nomeCell);

                PdfPCell precoCell = new PdfPCell(new Paragraph("R$ " + String.valueOf(carro.getPreco())));
                precoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(precoCell);

                PdfPCell anoCell = new PdfPCell(new Paragraph(String.valueOf(carro.getAno())));
                anoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(anoCell);

                PdfPCell marcasCell = new PdfPCell(new Paragraph(carro.getMarca()));
                marcasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(marcasCell);

                PdfPCell cidadeCell = new PdfPCell(new Paragraph(carro.getCidade()));
                cidadeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cidadeCell);

                PdfPCell placaCell = new PdfPCell(new Paragraph(carro.getPlaca()));
                placaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(placaCell);

            }

            document.add(table);

            System.out.println("Relatório gerado com sucesso!");

        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
