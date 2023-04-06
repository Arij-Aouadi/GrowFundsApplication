package tn.esprit.spring.RestControllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import com.itextpdf.text.pdf.PdfWriter;
import tn.esprit.spring.DAO.Entities.Investtisment;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.InvesttismentRepository;
import tn.esprit.spring.Services.Classes.InvesttismentServices;
import tn.esprit.spring.Services.Interfaces.IInvesttismentServices;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InvestissmentPdfexport {
    private List<Investtisment> listInvestesment;


    public InvestissmentPdfexport(List<Investtisment> listInvestesment) {
        this.listInvestesment = listInvestesment;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell= new PdfPCell();
        cell.setBackgroundColor(BaseColor.CYAN);
        //cell.setPadding(5);
        //Font font =FontFactory.getFont(FontFactory.HELVETICA);
        //font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("Identifiant"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("taux d'investissment"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("amount"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Invesstissor first Name"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Invesstissor Profession"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Invesstissor Email"));
        table.addCell(cell);


    }

    private void writeTableData(PdfPTable table) {

        for (Investtisment investesment : listInvestesment) {
            User user = investesment.getInvestor();
            table.addCell(String.valueOf(investesment.getIdinvesttisment()));
            table.addCell(String.valueOf(investesment.getTauxInvesttisment()));
            table.addCell(String.valueOf(investesment.getAmount()));
            table.addCell(user.getUsername());
            table.addCell(user.getProfession());
            table.addCell(user.getEmail());

        }

    }
    void export(HttpServletResponse response) throws DocumentException, IOException{
        Document document=new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
        // Add the Logo's img

       // Image image =Image.getInstance(new File("C:\\Users\\ZAITER\\Downloads\\growfund.jpg").getAbsolutePath());
        Font font =FontFactory.getFont(FontFactory.COURIER_BOLD,14);
        //image.setAlignment(Element.ALIGN_LEFT);
        //image.getTransparency();
        //image.scaleToFit(50f, 50f);
        //document.add(image);
        Paragraph message =new Paragraph(new Paragraph(new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
        message.setAlignment(Element.ALIGN_RIGHT);
        message.setSpacingBefore(20f);
        document.add(message);
        Paragraph title= new Paragraph("List of all investissement",font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        PdfPTable table =new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(30);
        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();



    }
}
