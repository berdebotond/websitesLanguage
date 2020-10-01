import org.omg.PortableInterceptor.SUCCESSFUL;

import java.io.*;
import java.util.ArrayList;

public class FIle {
    private ArrayList<Tree> sites;

    FIle() {//reading with constructor
        try {
            File folder = new File("./");
            File[] listOfFiles = folder.listFiles();
            String st = null;
            Tree seged;
            this.sites = new ArrayList<Tree>();
            for (int i = 0; i < listOfFiles.length; i++) {
                File file = listOfFiles[i];
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    seged = new Tree();
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    while ((st = br.readLine()) != null) {
                        st = st.replaceAll(" ","");
                        seged.insert(Integer.parseInt(st));
                    }
                    String names = file.getName();
                    names = names.replace(".txt", "");
                    seged.addName(names);
                    this.sites.add(seged);
                }
            }
        }catch (IOException e) {
            System.out.print(e + "Langauges not found, add a new language");
        }
    }
    public void add(Tree newSite){
        sites.add(newSite);
    }
    public String check(Tree forCheck){
        int point = 0;
        int j = 0;
        String name = "";
        for (int i = 0; i < sites.size(); i++) {
            if (point < sites.get(i).getPoint(forCheck)) {
                name = sites.get(i).getName();
                j = i;
            }
        }
        ArrayList<Integer> list= forCheck.getArray();
        for(int i = 0; i < list.size(); i++){
            sites.get(j).insert(list.get(i));
        }
        return name;

    }
    @Override
    protected void finalize () {//saving with destructor
        for(int i = 0; i < sites.size(); i++ ){
            try {
                PrintWriter printWriter = new PrintWriter(sites.get(i).getName() + ".txt");
                printWriter.print(sites.get(i).toString());
                System.out.println("Saved Successfully");
                printWriter.close();
            }catch (IOException e){
                System.out.println("Error in saving");
            }
        }
    }



}

