import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;


public class TestFichier{

  public static void main( String[] Args){
  
  try{  
  
    BufferedReader fichier ;
    fichier = new BufferedReader(new FileReader("testJava.txt"));
    int nbChar = fichier.read();
    String test = new String();
  do{
    test = fichier.readLine();
    System.out.println(test); 
  }while(test!=null);
    fichier.close();
  
  }catch(FileNotFoundException e){
    System.out.println(e.getMessage());
  }catch(IOException e){
    System.out.println(e.getMessage());
  }
  }

}