package labb2.task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class textComponent {
    ArrayList<String> pc;
    public textComponent(){
        pc=new ArrayList<String>();
    }
    public void addFile(File file) throws FileNotFoundException{
        String temp = "";
        Scanner scan = new Scanner(file);
        while(scan.hasNext()){
            temp += scan.next() + " ";
        }
        pc.add(temp);
    }
    public String getFile(int index){

            return pc.get(index);
    }
}
