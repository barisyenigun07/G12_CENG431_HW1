package io;

import java.io.*;
import java.util.*;

public class FileIO {
	private String line;
    private int count;

    private String filename;

    public FileIO(String filename){
        this.filename = filename;
    }

    public String[][] getData(){
        count = 0;
        String[][] data = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null){
                count++;
            }
            br.close();
            data = new String[count][];
            int row = 0;
            BufferedReader br2 = new BufferedReader(new FileReader(filename));
            while ((line = br2.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line, ",");
                int col = 0;
                data[row] = new String[st.countTokens()];
                while (st.hasMoreElements()){
                    data[row][col] = st.nextToken();
                    col++;
                }
                row++;
            }
            br2.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public void writeData(String data){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            bw.write(data);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean fileExists(){
        File file = new File(filename);
        return file.exists();
    }
}
