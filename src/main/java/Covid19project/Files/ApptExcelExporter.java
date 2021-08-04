package Covid19project.Files;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import Covid19project.Model.Data.Appointment;
import Covid19project.Model.Data.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApptExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Appointment> listAppointments;

    public ApptExcelExporter(List<Appointment> listAppointments) {
        this.listAppointments = listAppointments;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Appointments");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Appointment ID", style);
        createCell(row, 1, "Test Center ID", style);
        createCell(row, 2, "User CPR", style);
        createCell(row, 3, "Time", style);
        createCell(row, 4, "Date", style);


    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Appointment appointment : listAppointments) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, appointment.getApptId(), style);
            createCell(row, columnCount++, appointment.getTestCenterId(), style);
            createCell(row, columnCount++, appointment.getCprOfUser(), style);
            createCell(row, columnCount++, appointment.getTime(), style);
            createCell(row, columnCount++, appointment.getDate(), style);


        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

}
