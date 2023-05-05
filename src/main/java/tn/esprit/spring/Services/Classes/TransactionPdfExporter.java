package tn.esprit.spring.Services.Classes;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import tn.esprit.spring.DAO.Entities.Transactions;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class TransactionPdfExporter  {
    public List<Transactions> transactionsList;
    long rib ;
    public TransactionPdfExporter(long rib){ this.rib=rib ;}

    private void writeTableHeader(PdfPTable table) {


        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("ID_Transaction", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Amount", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Category", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Rib_Recipient", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Rib_Source", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Transaction_Date", font));

        table.addCell(cell);
        cell.setPhrase(new Phrase("Type_Transaction", font));
        table.addCell(cell);

    }
    private void writeTableData(PdfPTable table) {

        for (Transactions transaction : transactionsList) {
            if (transaction.getRibsource() == 10) {
                // Adding student id
                table.addCell(String.valueOf(transaction.getIdTrans()));
                // Adding student name
                table.addCell(String.valueOf(transaction.getAmount()));
                // Adding student section
                table.addCell(String.valueOf(transaction.getCategory()));
                table.addCell(String.valueOf(transaction.getRibrecipient()));
                table.addCell(String.valueOf(transaction.getRibsource()));
                //table.addCell(String.valueOf(transaction.getTransactionDate()));
                table.addCell(String.valueOf(transaction.getTypetrans()));

            }
        }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("bank Transaction identification ", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {3f, 1.5f, 3, 2f, 2f, 3});
        table.setSpacingBefore(15);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
