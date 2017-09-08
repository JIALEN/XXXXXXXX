import com.csvreader.CsvReader;

import java.io.IOException;

/**
 * Created by 张超 on 2017/9/7.
 */
public class readCsv
{
    public static void main(String[] args) {

        int count=0;
        String filePath = "C:\\Users\\zcer\\Documents\\WeChat Files\\zcaiwo\\Files\\整合话单excel_3_1.csv";

            try {
                // 创建CSV读对象
                CsvReader csvReader = new CsvReader(filePath);

                // 读表头
                csvReader.readHeaders();
                while (csvReader.readRecord()){
                    // 读一整行
                   System.out.println(csvReader.getRawRecord());
                    // 读这行的某一列
                   // System.out.println(csvReader.get("Link"));
                    count++;
                }
                System.out.println(count);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
