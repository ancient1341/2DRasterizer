import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class ViewableFactory {
    private Scanner scanner;
    String fileName;
    ArrayList<Viewable> viewables = new ArrayList<Viewable>();
    GamePanel panel;
    boolean foundMap;

    ViewableFactory(GamePanel panel, String fileName) {
        this.fileName = fileName;
        this.panel = panel;
        foundMap = false;

        openFile();
        if(foundMap) {
            readFile();
            closeFile();
        }
    }


    void openFile() {
        try {
            scanner = new Scanner(new File(fileName));
            foundMap = true;
        } catch(Exception e) {
            System.out.println("Map File not Found");
            System.out.println("Generating Test Map instead...");
            generateTestMap();
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
            System.out.println("generating rectangle");
            viewables.add(new ViewableRect(verticies.get(0), verticies.get(1), verticies.get(2), verticies.get(3), panel));
    }

    public void closeFile(){
        scanner.close();
    }

    public ArrayList<Viewable> getViewables(){
        return viewables;
    }

    private void generateTestMap(){
        viewables.add(new ViewableRect(0, 50, 6000, 50, panel));
        viewables.add(new ViewableRect(250, 0, 50, 30, panel));
        viewables.add(new ViewableRect(600, -150, 50, 40, panel));
        viewables.add(new ViewableRect(700, -300, 100, 100, panel));
        viewables.add(new ViewableRect(2000, -1500, 100, 100, panel));

    }
}

/*
    Text format:
    type x1 y1 x2 y2 continue
         x1 y1 x2 y2 end
    type x1 y1 x2 y2 end
    type x1 y1 x2 y2 end...
*/
