import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
public class ReadImageNames{
   public static void main(String args[]) throws IOException {

    StdOut.setFile("imagenames2.in");
    //Creating a File object for directory
    File directoryPath = new File("C:/Users/Dimab/OneDrive/Desktop/Personal Coding Projects/JavaSwing/Images");
    //Creating filter for png files
    FilenameFilter pngFilefilter = new FilenameFilter(){
         public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.endsWith(".png")) {
               return true;
            } else {
               return false;
            }
         }
      };        
      String imageFilesList[] = directoryPath.list(pngFilefilter);
      for(String fileName : imageFilesList) {
         StdOut.println(fileName);
         StdOut.println(fileName);
        }  
   }
}