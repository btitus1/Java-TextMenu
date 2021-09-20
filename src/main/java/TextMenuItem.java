public class TextMenuItem implements Runnable {

    private final String title;
    private Runnable exec;

    /**
     * Constructor with no Runnable code
     * @param title The title of the menu item
     */
    protected TextMenuItem(String title) {
        this(title, null);
    }

    /**
     * COnstructor with Runnable code defined
     * @param title The title of the menu item
     * @param exec The code to run when the menu item is selected
     */
    public TextMenuItem(String title, Runnable exec) {
        this.title = title;
        this.exec = exec;
    }

    /**
     * Gets the title of the menu
     * @return String representation of the menu item
     */
    public String getTitle() {
        return title;
    }

    /**
     * Determines if the menu item has a Runnable function
     * @return TRUE if there is a function, FALSE otherwise
     */
    public boolean isExec() {
        return exec != null;
    }

    /**
     * Sets the method to run for a menu item
     * @param exec The method to run
     */
    protected void setExec(Runnable exec) {
        this.exec = exec;
    }

    /**
     * Executes the method
     */
    public void run() {
        try {
            exec.run();
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }
}
