
public class Images {
    
    String[] cardImages = new String[20];

    public Images() {

        StdIn.setFile("imagenames.in");
        for (int i = 0; i < 20; i++)
        {
            cardImages[i] = StdIn.readString();
        }
    }

    public String getImageString(int i) {

        return cardImages[i];
    }
}