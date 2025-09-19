import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.WeakHashMap;

public class Main{
    private static char A;
    private static char B;
    private static char C;
    private static WeakHashMap<String, Character> map = new WeakHashMap<>();
    private static int HEX_FILE_SIZE = 96;
    private static void startWHM() {
        map.put("umL",   '0');
        map.put("zeroL", '1');
        map.put("AonB",  '2');
        map.put("nAonB", '3');
        map.put("AeBn",  '4');
        map.put("nB",    '5');
        map.put("nA",    '6');
        map.put("nAxnB", '7');
        map.put("AxB",   '8');
        map.put("copiaA",'9');
        map.put("copiaB",'A');
        map.put("AeB",   'B');
        map.put("AenB",  'C');
        map.put("nAeB",  'D');
        map.put("AoB",   'E');
        map.put("nAeBn", 'F');
    }
    private static char parser(String line) {
        char firstChar = line.charAt(0);
        String valueString = "";
        for(int i=2; i<line.length()-1; i++){
            valueString += line.charAt(i);
        }
        if(firstChar == 'X') A = valueString.charAt(0);
        else if(firstChar == 'Y') B = valueString.charAt(0);
        else if(firstChar == 'W') C = map.get(valueString);
        
        return firstChar;
    }
    private static String code() {
        return String.format("%c%c%c", A,B,C);
    }
    private static void writeOnFile(List<String> commands) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("testeula.hex"))) {
            for (String cmd : commands) {
                writer.write(cmd);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args) throws Exception {
	    startWHM();
	    List<String> commands = new ArrayList<>();
    	Scanner sc = new Scanner(System.in);
    	String text = sc.nextLine();
    	if(!text.equals("inicio:")) {
            sc.close();
            throw new Exception(String.format("Input invalido: %s", text));
        };
    	text=sc.nextLine();
    	while(sc.hasNext() || !text.equals("fim.")){
    	    char key = parser(text);
    	    if(key == 'W') commands.add(code());
    	    text=sc.nextLine();
    	}
    	
    	if(commands.size() > HEX_FILE_SIZE){
    	    sc.close();
            throw new Exception(String.format("Cannot be more than %d positions", HEX_FILE_SIZE));
    	}
    	writeOnFile(commands);
        sc.close();
	}
}