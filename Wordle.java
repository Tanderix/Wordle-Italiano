import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.util.Random;
public class Wordle {
    public static void main(String args[]) {
        Scanner inputReader = new Scanner(System.in);
        File myFile = new File("wordlist.txt");
        String wotd = "";
        Boolean found = false;
        Boolean wordFound = false;
        String guess;
        int tries = 0;

        //*** SCELTA PAROLA CASUALE */
        Random generator = new Random();
        int rnd = 1 + generator.nextInt(8261);
        try{
            Scanner fileReader = new Scanner(myFile);
            for(int i=1;i<=rnd;i++){
                String data = fileReader.nextLine();
                wotd = data;
            }
            //System.out.println("Parola del giorno: " + wotd);
            fileReader.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        
        //*** INIZIO GIOCO */
        do{
            //*** INPUT TENTATIVO */
            System.out.print("Inserisci una parola: ");
            guess = inputReader.next().toUpperCase();
            tries++;
            //*** CHECK PAROLA = PRIMO TENTATIVO */
            if(guess.equals(wotd)){
                found = true;
            }else{  
                //*** CHECK LETTER IN POSIZIONE ESATTA */

                //*** CONTROLLO LUNGHEZZA */
                if(guess.length() == wotd.length()){
                    //*** RICERCA IN VOCABOLARIO */
                    try{
                        Scanner fileReader = new Scanner(myFile);
                        while (fileReader.hasNextLine() && !wordFound) {
                            String data = fileReader.nextLine();
                            if(data.equals(guess)){
                                wordFound = true;
                            }
                        }
                        fileReader.close();
                    }catch (FileNotFoundException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    
                    //*** CONTROLLO SE PAROLA TROVATA */
                    if(!wordFound){
                        System.out.println("Parola non trovata nel vocabolario!");
                    }else{
                        for(int i=0;i<guess.length();i++){
                            if(guess.charAt(i) == wotd.charAt(i)){
                                System.out.print(guess.charAt(i));
                            }else{
                                System.out.print(Character.toLowerCase(guess.charAt(i)));
                            }
                        }
                        System.out.println();
                    }
                    wordFound = false;
                }else{
                    System.out.println("Parola di lunghezza sbagliata! Deve essere lunga 5 lettere!");
                }
            }
        }while(!found);

        //*** STAMPA SUCCESSO FINALE */
        if(found){
            System.out.println(guess.toUpperCase());
            System.out.println("Parola trovata in " + tries + " tentativi!");
        }
        inputReader.close();
    }
}