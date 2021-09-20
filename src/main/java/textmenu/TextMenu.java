package textmenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextMenu extends TextMenuItem {

    /**
     * Standard quit menu item
     */
    private static final TextMenuItem quit = new TextMenuItem("quit", new Runnable() {
        public void run() {
            System.exit(0);
        }
    });

    /**
     * Standard back menu item
     */
    private static final TextMenuItem back = new TextMenuItem("back");

    List<TextMenuItem> items;

    /**
     * Constructor for text menu with one or more menu items
     * @param title The title of the menu
     * @param items List of TextMenuItems
     */
    public TextMenu(String title, TextMenuItem... items) {
        this(title, false, true, items);
    }

    /**
     * Constructor for text menu with one or ore menu items with back and quit
     * @param title The title of the menu
     * @param addBack TRUE if back menu item is to be added, FALSE otherwise
     * @param addQuit TRUE if quit menu item is to be added, FALSE otherwise
     * @param items List of TextMenuItems
     */
    public TextMenu(String title, boolean addBack, boolean addQuit, TextMenuItem... items) {
        super(title);
        setExec(this);

        initialize(addBack, addQuit, items);
    }

    /**
     * Initialize a menu
     * @param addBack TRUE if back menu item is to be added, FALSE otherwise
     * @param addQuit TRUE if quit menu item is to be added, FALSE otherwise
     * @param items List of TextMenuItems
     */
    private void initialize(boolean addBack, boolean addQuit, TextMenuItem... items) {

        this.items = new ArrayList<>(Arrays.asList(items));
        if (addBack) this.items.add(back);
        if (addQuit) this.items.add(quit);
    }

    /**
     * Displays all the TextMenuItems (menu)
     */
    private void display() {

        int option = 0;
        System.out.println(getTitle() + ":");
        for (TextMenuItem item : items) {
            System.out.println((option++) + ": " + item.getTitle());
        }
        System.out.print("select option: ");
        System.out.flush();
    }

    /**
     * Generic prompt function to allow user to select a menu item
     * @return The specific TextMenuItem object that was selected
     */
    private TextMenuItem prompt() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            display();

            String line = br.readLine();
            try {
                int option = Integer.parseInt(line);
                if (option >= 0 && option < items.size())
                    return items.get(option);
            } catch (NumberFormatException e) {
                // catch the error of someone entering an invalid menu item
            }

            System.out.println("not a valid menu option: " + line);
        }
    }

    /**
     * Run function for each of the items prompts
     */
    public void run() {

        try {
            for (TextMenuItem item = prompt(); item.isExec(); item = prompt())
                item.run();
        } catch (Throwable t) {
            t.printStackTrace(System.out);
        }
    }
}
