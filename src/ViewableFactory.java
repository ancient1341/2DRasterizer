import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class ViewableFactory {
    private Scanner scanner;
    String fileName;
    ArrayList<Viewable> viewables = new ArrayList<Viewable>();
    GamePanel panel;

    ViewableFactory(GamePanel panel, String fileName) {
        this.fileName = fileName;
        this.panel = panel;

        openFile();
        readFile();
        closeFile();
    }


    void openFile() {
        try {
            scanner = new Scanner(new File(fileName));
        } catch(Exception e) {
            System.out.println("Map File not Found");
        }
    }

    void readFile(){
        ArrayList<Integer> verticies = new ArrayList<Integer>();
        while(scanner.hasNext()){
            String type = scanner.next();
            boolean newObject = true;
            while(newObject) {
                verticies.add(Integer.parseInt(scanner.next()));
                verticies.add(Integer.parseInt(scanner.next()));
                verticies.add(Integer.parseInt(scanner.next()));
                verticies.add(Integer.parseInt(scanner.next()));
                newObject = Boolean.parseBoolean(scanner.next());
            }
            createViewable(type, (ArrayList<Integer>)verticies.clone());
            verticies.clear();
        }
    }

    void createViewable(String type, ArrayList<Integer> verticies){
        if(type == "rectangle")
            System.out.println("yep");
            viewables.add(new ViewableRect(verticies.get(0), verticies.get(1), verticies.get(2), verticies.get(3), panel));
    }

    public void closeFile(){
        scanner.close();
    }

    public ArrayList<Viewable> getViewables(){
        return viewables;
    }
}

/*
    Text format:
    type x1 y1 x2 y2 continue
         x1 y1 x2 y2 end
    type x1 y1 x2 y2 end
    type x1 y1 x2 y2 end...
*/
