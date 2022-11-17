import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Data {
    private HashMap<String, String[]> mp = new HashMap<>();
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
                String values[] = value.split("\\|");
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
    public void printData(){
        mp.entrySet().forEach(entry -> {
            System.out.print(entry.getKey() + " ");
            for(int i =0; i<entry.getValue().length;i++){
                System.out.print(entry.getValue()[i] + " ");
            }
        });
    }

    public String[] searchByWord(String word){
        String[] result = mp.get(word);
        for(int i =0; i<result.length;i++){
            System.out.print(result[i]+" ");
        }
        System.out.println();
        return result;
    }

    public boolean checkExist(String word){
        return mp.containsKey(word);
    }
    public void addWord(String word, String def){
        if(!checkExist(word)){
            String[] temp = {def};
            mp.put(word, temp);
        }
        else{
            if(1==1){
                duplicate();
            }
            else{
                overwrite();
            }
        }
    }

    public void duplicate(){

    }

    public void overwrite(){

    }

    public void editWord(){

    }
    public void deleteWord(String word){
        mp.remove(word);
    }
    public String getRandom(){
        Set<String> keySet = mp.keySet();
        List<String> keyList = new ArrayList<>(keySet);
        int size = keyList.size();
        int randIdx = new Random().nextInt(size);
        System.out.println(keyList.get(randIdx));
        return keyList.get(randIdx);
    }
    public void resetDict(){

    }
}
