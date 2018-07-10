package yang.counter;

import java.io.*;
import java.util.regex.Pattern;

public class Main {

    private static File file = new File("StringUtils.java");
    private static Pattern unNullPattern = Pattern.compile("^[\\s]{0,}$");

    public static void main(String[] args) {
        replaceFileStr();

        BufferedReader reader;
        String temp;

        try {

            reader = new BufferedReader(new FileReader("temp.txt"));
            int lines = 0;
            while ((temp = reader.readLine())!=null) {
                if (!unNullPattern.matcher(temp).matches()) {
                    lines ++;
                    System.out.println(lines + "\t" +temp);
                }
            }
            System.out.println(lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void replaceFileStr(){
        try {
            FileReader fis = new FileReader(file);
            char[] data = new char[1024];
            int rn;
            StringBuilder sb=new StringBuilder();
            while ((rn = fis.read(data)) > 0) {
                String str=String.valueOf(data,0,rn);
                sb.append(str);
            }
            fis.close();
            String str = sb.toString().replaceAll("(?<!:)//.*|/\\*(\\s|.)*?\\*/", "");
            str = str.replaceAll("(?<!:)//.*", "");
            str = str.replaceAll("<!--[^<]+-->","");

            FileWriter fout = new FileWriter("temp.txt");
            fout.write(str.toCharArray());
            fout.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
