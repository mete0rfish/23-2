package post;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PostFile {


    public static ArrayList<Post> readFileByLine() throws FileNotFoundException {
        ArrayList<Post> postList = new ArrayList<>();

        Scanner scan = new Scanner(new File("F:\\userFile\\postfile.txt"));
        int idx=1;
        while(scan.hasNext()){
            String title = scan.next();
            String description = scan.next();
            postList.add(new Post(title, description));
        }
        return postList;
    }

    private static String readFile(){
        String allText = "";
        int buffer;
        try{
            BufferedReader buffRead = new BufferedReader(new FileReader("F:\\userFile\\postfile.txt"));
            while((buffer = buffRead.read()) != -1){
                allText += (char)buffer;
            }
            buffRead.close();
        } catch(Exception e){
            e.getStackTrace();
        }
        return allText;
    }

    static public void addPost(Post post) {
        try {
            String backup = readFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\userFile\\postfile.txt"));
            backup = backup + post.getTitle() + " " + post.getDescription();
            bw.write(backup);
            bw.newLine();
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
