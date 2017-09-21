import com.monitorjbl.xlsx.StreamingReader;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.*;
import java.util.Locale;
/**
 * Created by 张超 on 2017/9/7.
 */
public class BigExcelReaderPoi {
    OutputStream os;
    OutputStreamWriter osw;
    BufferedWriter bw;
    WorkbookSettings ws=new WorkbookSettings();
    jxl.Workbook wk;
    FileInputStream in;
    public BigExcelReaderPoi() throws FileNotFoundException {
    }
    public void xlsConvertToCsv(String inputPath,String outputPath){
        try {
             os=new FileOutputStream(new File(outputPath));
             osw=new OutputStreamWriter(os,"UTF8");
             bw=new BufferedWriter(osw);
            //载入excel文件
            ws.setLocale(new Locale("en","EN"));
            wk= jxl.Workbook.getWorkbook(new File(inputPath),ws);
            //从工作簿workbook取得每页sheets
            for(int sheet=0;sheet<wk.getNumberOfSheets();sheet++){
                jxl.Sheet s=wk.getSheet(sheet);
                bw.write(s.getName());
                bw.newLine();
                jxl.Cell[] row=null;
                //从每页sheet取得每个区块cell
                for(int i=0;i<s.getRows();i++){
                    row=s.getRow(i);
                    if (row.length>0){
                        bw.write(row[0].getContents());
                        for (int j=1;j<row.length;j++){
                            bw.write(',');
                            bw.write(row[j].getContents());
                        }
                    }
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
    public void xlsxConvertToCsv(String inputPath,String outputPath){

        long startTime = System.currentTimeMillis();
        System.out.println("程序开始时间：" + (startTime) + "ms");
        try {
             os=new FileOutputStream(new File(outputPath));
             osw=new OutputStreamWriter(os,"UTF8");
             bw=new BufferedWriter(osw);
             in = new FileInputStream(inputPath);
            Workbook wk = StreamingReader.builder()
                    .rowCacheSize(100)  //缓存到内存中的行数，默认是10
                    .bufferSize(4096)  //读取资源时，缓存到内存的字节大小，默认是1024
                    .open(in);  //打开资源，必须，可以是InputStream或者是File，注意：只能打开XLSX格式的文件
            Sheet sheet = wk.getSheetAt(0);
            bw.write(sheet.getSheetName());
            bw.newLine();
            //遍历所有的行
            for (Row row : sheet) {
                //System.out.println("开始遍历第" + row.getRowNum() + "行数据：");

                //遍历所有的列
                for (Cell cell : row) {
                    // System.out.print(cell.getStringCellValue() + " ");
                    bw.write(cell.getStringCellValue());
                    bw.write(',');
                }
                bw.newLine();
                //System.out.println(" ");
            }
            long endTime = System.currentTimeMillis();
            System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //TEST
    public static void main(String args[]){
        BigExcelReaderPoi bigExcelReaderPoi= null;
        try {
            bigExcelReaderPoi = new BigExcelReaderPoi();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //xls-------->>>>>>csv
//        String inputPath="D:\\zhiyuan.xls";
//        String outputPath="D:\\input.csv";
//        bigExcelReaderPoi.xlsConvertToCsv(inputPath,outputPath);

        //xlsx------->>>>>>>csv
        String inputPath2="D:/zhiyuan.xlsx";
        String outputPath2="D:\\output.csv";
        bigExcelReaderPoi.xlsxConvertToCsv(inputPath2,outputPath2);
    }
}
