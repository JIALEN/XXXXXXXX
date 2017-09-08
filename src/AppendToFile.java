import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张超 on 2017/9/7.
 */
public class AppendToFile {
    //读取目录下所有文件
    public  List<String>  readFiles(String filePath){
        File file=new File(filePath);
        File sonFile=null;
        List<String> filePaths=new ArrayList<>();
        if(file.isDirectory()){
            String[] fileList=file.list();
            for(int i=0;i<fileList.length;i++){
                filePaths.add(filePath+"\\"+fileList[i]);
                System.out.println(filePaths);
            }
        }
        return filePaths;
    }

    //读取单个文件内容
    public String readFile(String filePath){
        File file=new File(filePath);
        BufferedReader bufferedReader=null;
        try {
            bufferedReader=new BufferedReader(new FileReader(file));
            String tempString=null;
            String content="";
            int line=1;
            //一次读入一行，直到读入null结束
            while((tempString=bufferedReader.readLine())!=null){
                content+=tempString+"\n";
            }
            return content;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String filePath="E:\\upload_bak\\zcer";
        AppendToFile appendToFile=new AppendToFile();
        List<String> filePaths=appendToFile.readFiles(filePath);
        FileWriter writer=null;
        for(int i=0;i<filePaths.size();i++){
            try {
                writer=new FileWriter(filePaths.get(0),true);
                writer.write(appendToFile.readFile(filePaths.get(i)));
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
