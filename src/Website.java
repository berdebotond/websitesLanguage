import org.jsoup.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Website {

    private String urlAdress;

    Website(String urlAdress){
        this.urlAdress = urlAdress;
    }
    //setter for adress
    public void setHtmlAdress(String adress){
        this.urlAdress = adress;
    }
    //HTML text content to a string
    @Override
    public String toString(){
        return getContent();
    }
    //format a htmlcontent
    private String format(String htmlContent){
        htmlContent = htmlContent.replaceAll("!"," ");
        htmlContent = htmlContent.replaceAll("&"," ");
        htmlContent = htmlContent.replaceAll("@","");
        htmlContent = htmlContent.replaceAll(" ","");
        htmlContent = htmlContent.toLowerCase();
        return htmlContent;
    }

    //get a html file content text
    public String getContent() {
        StringBuilder sb = new StringBuilder();
        try {
            URLConnection connection = new URL(Jsoup.parse(urlAdress).text()).openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
        } catch (IOException e) {
            System.out.println(e+"Error with URL adress" + "\n");
        }
        return format(sb.toString());
    }
    //two char to an int
    private int charToInt(char a,char b){
        return (((int)a*1000 )+ ((int)b) );
    }
    //set content data to a tree
    public Tree toTree(){
        Tree aWebsite = new Tree();
        char[] chars = toString().toCharArray();
        int b,asciisum;
        for(int i = 0; i < chars.length; i+=2){
            if( i == chars.length-1){
                aWebsite.insert(charToInt(chars[i],'0'));
            }else{
                b = i+1;
                asciisum = charToInt(chars[i], chars[b]);
                aWebsite.insert(asciisum);
            }
        }
        return aWebsite;
    }

}
