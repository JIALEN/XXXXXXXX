
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by 张超 on 2017/9/15.
 */
//Nio读大文件
public class NioRead {
    public static void NioRead(){
        try {
            //获取通道channel
            FileInputStream fileInputStream=new FileInputStream("D://zhiyuan.xlsx");
            FileChannel channel=fileInputStream.getChannel();
            long size=channel.size();
            byte[] bytes = new byte[1024];
            //创建缓冲区
            ByteBuffer buffer=ByteBuffer.allocate(1024);
            channel.read(buffer);
            int len;
            while((len=channel.read(buffer))!=-1){
                buffer.flip();
                bytes=buffer.array();
                System.out.print(new String(bytes));
                buffer.clear();
            }
            while(buffer.hasRemaining()){
                System.out.println((char) buffer.get());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        NioRead();
    }

}
