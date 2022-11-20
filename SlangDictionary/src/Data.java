import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Data {
    private HashMap<String, ArrayList<String>> mp = new HashMap<>();
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
        BufferedReader br = new BufferedReader(fr);
        String check = br.readLine();
        String key ="";
        while(check!=null){
            String temp[] = check.split("`");
            key = temp[0];
            ArrayList<String> def = new ArrayList<String>();
            String [] subDef = temp[1].split("\\| ");
            for (String i: subDef){
                def.add(i);
            }
            String nextCheck = br.readLine();
            if (nextCheck!=null){
                String[] nextTemp = nextCheck.split("`");

                while(nextTemp.length==1){
                    def.add(nextTemp[0]);
                    nextCheck = br.readLine();
                    nextTemp = nextCheck.split("`");
                }
            }
            mp.put(key, def);
            check = nextCheck;
        }
    }
    public void printData(){
        mp.entrySet().forEach(entry -> {
            System.out.print(entry.getKey() + " ");
            for(int i =0; i<entry.getValue().size();i++){
                System.out.print(entry.getValue().get(i) + " ");
            }
        });
    }

    public String searchByWord(String word){
        ArrayList<String> value = mp.get(word);
        String result = "";
        for(int i =0 ; i< value.size();i++){
            result += value.get(i);
            result += "\n";
        }
        return result;
    }

    public String searchByDef(String input){
        ArrayList<String> listKey = new ArrayList<>();
        String result = "";
        mp.entrySet().forEach(entry->{
            ArrayList<String> listDef = entry.getValue();
            short check = 0;
            for(int i =0; i<listDef.size();i++){
                if(listDef.get(i).contains(input))
                    check = 1;
            }
            if (check == 1){
                listKey.add(entry.getKey());
            }
        });
        if (listKey.size()==0) return "";
        for(int i =0;i<listKey.size();i++){
            result+= listKey.get(i);
            result+= "\n";
        }
        return result;
    }

    public boolean checkExist(String word){
        return mp.containsKey(word);
    }
    public void addWord(String word, ArrayList<String> def){
        if(!checkExist(word)){
            mp.put(word, def);
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

        String randomKey = keyList.get(randIdx);

        return randomKey;
    }

    public void addSearchKeyToHis(String input)throws IOException{
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime());
        String type = " - You search Key: " + input + "\n";
        FileWriter fw = new FileWriter("history.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(timeStamp + type);
        bw.close();
    }

    public void addSearchValueToHis(String input) throws IOException{
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss").format(Calendar.getInstance().getTime());
        String type = " - You search Definition: " + input + "\n";
        FileWriter fw = new FileWriter("history.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(timeStamp + type);
        bw.close();
    }

    public void resetDict(){

    }
    public static void main(String[] arg) throws IOException{
        Data data = new Data();
        data.readData();

        System.out.println(data.checkExist("Slag"));


    }
}
