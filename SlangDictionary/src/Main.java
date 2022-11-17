import java.io.IOException;

public class Main {
    final Data data = new Data();
    public boolean findWord(String input){
        return false;
    }
    public boolean findDef(String input){
        return false;
    }
    public boolean showHistory(){
        return false;
    }
    public boolean addWord(String input){
        return false;
    }
    public boolean duplicateWord(){
        return false;
    }
    public boolean overdriveWord(){
        return false;
    }
    public boolean editWord(){
        return false;
    }
    public boolean deleteWord(){
        return false;
    }
    public boolean resetDict(){
        return false;
    }
    public static void main(String[] args) throws IOException {
        Interface gui = new Interface();
        Data data = new Data();
        try{
            data.readData();
        }
        catch(IOException exc)
        {
            System.out.println("Error reading file");
        }
        //data.printData();
        data.searchByWord(data.getRandom());
    }
}