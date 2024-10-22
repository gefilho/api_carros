package carro.com.api.Service;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import carro.com.api.Model.Carro;

@Service
public class CarroRelatorioService {

    public void gerarRelatorioPdf(List<Carro> carros, OutputStream outputStream) {
    	
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
        	PdfWriter.getInstance(document, outputStream);
            document.open();

            Font font = new Font(Font.FontFamily.UNDEFINED, 18);
            Paragraph titulo = new Paragraph("Relatório de Carros", font);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);
            
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);

            // Definir o cabeçalho da tabela
            String[] headers = {"Nome", "Preço", "Ano", "Marcas", "Cidade", "Placa"};
            for (String header : headers) {
                PdfPCell headerCell = new PdfPCell(new Paragraph(header));
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                headerCell.setPadding(5);
                table.addCell(headerCell);
            }
            
        	String padrao = "###, ###. ##";
        	DecimalFormat df = new DecimalFormat(padrao);
        	int count = 0;
        	
            for (Carro carro : carros) {
            	if (count >= 10) break;
            	
            	PdfPCell nomeCell = new PdfPCell(new Paragraph(carro.getNome()));
                nomeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                nomeCell.setPadding(5);
                table.addCell(nomeCell);

                String precoFormatado = df.format(carro.getPreco());
                PdfPCell precoCell = new PdfPCell(new Paragraph("R$ " + precoFormatado));
                precoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                precoCell.setPadding(5);
                table.addCell(precoCell);

                PdfPCell anoCell = new PdfPCell(new Paragraph(String.valueOf(carro.getAno())));
                anoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                anoCell.setPadding(5);
                table.addCell(anoCell);

                PdfPCell marcasCell = new PdfPCell(new Paragraph(carro.getMarca()));
                marcasCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                marcasCell.setPadding(5);
                table.addCell(marcasCell);

                PdfPCell cidadeCell = new PdfPCell(new Paragraph(carro.getCidade()));
                cidadeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cidadeCell.setPadding(5);
                table.addCell(cidadeCell);

                PdfPCell placaCell = new PdfPCell(new Paragraph(carro.getPlaca()));
                placaCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                placaCell.setPadding(5);
                table.addCell(placaCell);
                
                count++;
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
