import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class logReader {
    public static void main(String[] args){
    String filePath = "d:\\data\\raw_logs.txt";

    List<String> logs = readLogs(filePath);
    System.out.println("读取到日志条数:" + logs.size());

    System.out.println("前三条实例");
    for(int i = 0; i < Math.min(3,logs.size());i++){
        System.out.println(logs.get(i));
    }
        }

    public static List<String> readLogs(String filePath){
        List<String> logs = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;

            while ((line = br.readLine()) != null){
                if (!line.trim().isEmpty()){
                    logs.add(line);
                }
            }
        }catch (IOException e){
            System.out.println("文件读取失败：" + e.getMessage());
        }
        return  logs;
    }
}
