import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Data {
    private HashMap<String, ArrayList<String>> mp = new HashMap<>();
    public void readData() throws IOException {
        mp.clear();
        FileReader fr;
        try
        {
            fr = new FileReader("slang.txt");
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
        fr.close();
        br.close();
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

    public String searchByWord2(String word){
        return mp.get(word).get(0);
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

    public void duplicate(String word, ArrayList<String> def){
        ArrayList<String> temp = mp.get(word);
        for(int i =0; i<def.size();i++){
            temp.add(def.get(i));
        }
        mp.put(word,temp);
    }

    public void editWord(String word, ArrayList<String> def){
        mp.put(word, def);
    }

    public void delete(String word){
        mp.remove(word);
    }

    public String showHistory() throws IOException{
        FileReader fr = new FileReader("history.txt");
        BufferedReader br = new BufferedReader(fr);
        String result = "";
        String temp = br.readLine();
        while(temp != null){
            result += temp;
            result += "\n";
            temp = br.readLine();
        }
        return result;
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

    public void saveData() throws IOException {
        FileWriter fw = new FileWriter("slang.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        mp.entrySet().forEach(entry -> {
            String key = entry.getKey();
            ArrayList<String> listDef = entry.getValue();
            String line = key + "`";
            for(int i =0; i<listDef.size();i++){
                if (i==entry.getValue().size()-1){
                    line += listDef.get(i) + "\n";
                }
                else{
                    line += listDef.get(i) + "| ";
                }
            }
            try {
                bw.write(line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        bw.close();
    }

    public void resetDict() throws IOException {
        File src = new File("originalSlang.txt");
        File dest = new File("slang.txt");
        Files.copy(src.toPath(), dest.toPath(), REPLACE_EXISTING);
    }
}
