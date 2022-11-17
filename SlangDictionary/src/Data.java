import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Data {
    final HashMap<String, String[]> mp = new HashMap<>();
    public void readData() throws IOException {
        mp.clear();
        FileReader fr;
        try
        {
            fr = new FileReader("./slang.txt");
        }
        catch(IOException exc)
        {
            System.out.println("Error opening file");
            return;
        }
        String key = "";
        String value = "";
        int check = 0;
        while(true){
            int i = fr.read();
            if(i == -1) break;
            if((char)i == '`') {
                check = 1;
                continue;
            }
            if((char)i == '\n'){
                String[] values = value.split("\\|");
                mp.put(key, values);
                key = "";
                value ="";
                check = 0;
            }
            if(check == 0){
                key+=(char)i;
            }
            if(check == 1){
                value+=(char)i;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        Data data = new Data();
        try{
            data.readData();
        }
        catch(IOException exc)
        {
            System.out.println("Error reading file");
        }
    }
}
