import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 张超 on 2017/9/21.
 */
public class StringUtil {
    public static List getMatcher(String regex, String source) {
                 String result = "";
                 List results=new ArrayList();
                 int i=0;
                 //Pattern： 一个Pattern是一个正则表达式经编译后的表现模式。
                 //Matcher： 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
                 Pattern pattern = Pattern.compile(regex);
                 Matcher matcher = pattern.matcher(source);
                 while (matcher.find()) {
                         result = matcher.group(1);
                         results.add(result);
                     }

                 return results;
             }
    public static void main(String[] args) {
                 String url = "http:172.12.1.123/test.txt，http://172.132.1.123/test.txt";
                 String regex = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})";
         //        String regex = "(\\d{1,3}\\.){1,3}(\\d{1,3})";
                 System.out.println(getMatcher(regex,url));
             }
}
