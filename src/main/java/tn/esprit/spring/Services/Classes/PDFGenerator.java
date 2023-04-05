package tn.esprit.spring.Services.Classes;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Transactions;
import tn.esprit.spring.DAO.Repositories.AccountRepository;
import tn.esprit.spring.DAO.Repositories.TransactionRepository;
import tn.esprit.spring.DAO.Repositories.UserRepository;

import javax.servlet.http.HttpServletResponse;
@Service
public class PDFGenerator {
    long rib=0;
    String name ="";
    String profession ="";
    String cin ="";
    public UserRepository userRepository;
    public TransactionRepository transactionRepository;
    public AccountRepository accountRepository;
    List<Long> myList = new ArrayList<Long>();
    public void generate(List < Transactions > transactionsList, HttpServletResponse response) throws DocumentException, IOException {
        // Creating the Object of Document
        Document document = new Document(PageSize.A4);
        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());
        // Opening the created document to change it
        document.open();
        // Creating font
        // Setting font style and size
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        // Creating paragraph
        LocalDate today = LocalDate.now(); // Récupérer la date d'aujourd'hui
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Définir le format de la date
        String formattedDate = today.format(formatter);
        ////////////////
        Font font2 = new Font();
        font2.setColor(Color.ORANGE);
        Paragraph paragraph0 = new Paragraph("CHECKING TRANSACTIONS STATEMENT");
        paragraph0.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph0);
        Paragraph paragraph5 = new Paragraph("Page : 1 of 1",font2);
        paragraph5.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph5);
        //////////////
        Paragraph paragraph2 = new Paragraph("Date :"+formattedDate);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph2);
        Paragraph paragraph3 = new Paragraph("GrowFunds Bank ");
        paragraph3.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(paragraph3);
        Paragraph paragraph1 = new Paragraph("List of the Transactions", fontTiltle);
        // Aligning the paragraph in the document
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        // Adding the created paragraph in the document
        document.add(paragraph1);
        //******************************************************************
//        for (Transactions transaction: transactionsList) {
//           myList.add(transaction.getRibsource());
//        }
////
//        long r =myList.get(1);
//        int r1 = (int) r;
//        int cin= accountRepository.retrieveCinByRib(r1);
//        String cinn=String.valueOf(cin);
//       name = userRepository.retrieveUsernameBycin(cinn);
////        profession = userRepository.retrieveProfessionByAccounNum(r);
////        cin = userRepository.retrievecinByAccounNum(r);


        Paragraph paragraph10 = new Paragraph("Client Name : ");
        paragraph10.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph10);
        Paragraph paragraph11 = new Paragraph("CIN :------------- ");
        paragraph11.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph11);
        Paragraph paragraph12 = new Paragraph("Profession  :---------- ");
        paragraph12.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paragraph12);



        //***************************************************************

        // Creating a table of the 4 columns
        PdfPTable table = new PdfPTable(7);
        // Setting width of the table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[] {2,1,2,2,2,2,2});
        table.setSpacingBefore(5);
        // Create Table Cells for the table header
        PdfPCell cell = new PdfPCell();
        // Setting the background color and padding of the table cell
        cell.setBackgroundColor(CMYKColor.pink);
        cell.setPadding(5);
        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);
        // Adding headings in the created table cell or  header
        // Adding Cell to table
        cell.setPhrase(new Phrase("Transaction_Date", font));
        table.addCell(cell);
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
        cell.setPhrase(new Phrase("Type_Transaction", font));
        table.addCell(cell);
        // Iterating the list of students
        for (Transactions transaction: transactionsList) {
            // Adding student id

            // Adding student name
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Définir le format de la date
            String date_trans = transaction.getTransactionDate().toString();
            table.addCell(date_trans);
            table.addCell(String.valueOf(transaction.getIdTrans()));

            table.addCell(String.valueOf(transaction.getAmount()));
            // Adding student section
            table.addCell(String.valueOf(transaction.getCategory()));
            table.addCell(String.valueOf(transaction.getRibrecipient()));
            table.addCell(String.valueOf(transaction.getRibsource()));

            table.addCell(String.valueOf(transaction.getTypetrans()));
        }
        // Adding the created table to the document
        document.add(table);
        // Closing the document
        document.close();
    }
}
