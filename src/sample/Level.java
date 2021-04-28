package sample;

import java.util.*;

public class Level {
    String question;
    String answer;
    String[] variants = new String[4];
    Random rand = new Random();

    Map<String,String> data = new HashMap<>();
    ArrayList<String> keys = new ArrayList<>();
    public Level(){
        data.put("cat","кот");
        data.put("dog","собака");
        data.put("pig","свинка");
        data.put("table","стол");
        data.put("black","черный");
        data.put("white","белый");
        data.put("brother","брат");
        data.put("sister","сестра");
        data.put("car","машина");

        for (String s:data.keySet()){
            keys.add(s);
        }
        question = getWord();
        answer = data.get(question);
        fillVariants();
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String[] getVariants() {
        return variants;
    }

    private void fillVariants() {
        Set<String> temp = new HashSet<>();
        temp.add(answer);
        while(temp.size()!=4){
            temp.add(data.get(getWord()));
        }
        variants = temp.toArray(new String[0]);
    }

    private String getWord(){
        return keys.get(rand.nextInt(keys.size()));
    }

    public static void main(String[] args) {
        new Level();
    }
}
