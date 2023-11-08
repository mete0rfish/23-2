package user;

import java.io.*;

public class UserFile {
    private static String readFile(){
        String allText = "";
        int buffer;
        try{
            BufferedReader buffRead = new BufferedReader(new FileReader("F:\\userFile\\userfile.txt"));
            while((buffer = buffRead.read()) != -1){
                allText += (char)buffer;
            }
            buffRead.close();
        } catch(Exception e){
            e.getStackTrace();
        }
        return allText;
    }

    static public void addUser(User user) {
        try {
            String backup = readFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\userFile\\userfile.txt"));
            backup = backup + user.getEmail() + " " + user.getPassword();
            bw.write(backup);
            bw.newLine();
            bw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

//        try{
//            OutputStream output = new FileOutputStream("F:\\userFile\\userfile.txt");
//            String userInfo = user.getEmail() + " " + user.getPassword();
//            byte[] by = userInfo.getBytes();
//            output.write(by);
//
//        } catch(Exception e){
//            e.printStackTrace();
//        }
    }

    static public String findUser(User user){
        String inputUser = user.getEmail() + " " + user.getPassword();
        File file = new File("F:\\userFile\\userfile.txt");
        try{
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while((line=br.readLine()) != null){
                    if (line.equals(inputUser)){
                        return line;
                    }
                }
            }
        } catch(IOException e){
            e.getStackTrace();
        }
        return null;
    }
}
