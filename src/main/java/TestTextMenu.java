public class TestTextMenu {

    private static final TextMenuItem item1= new TextMenuItem("item 1",new Runnable() {
        public void run() {
            System.out.println("running item 1");
        }
    });

    private static final TextMenuItem item2= new TextMenuItem("item 2",new Runnable() {
        public void run() {
            System.out.println("running item 2");
        }
    });

    private static final TextMenuItem item3= new TextMenuItem("item 3",new Runnable() {
        public void run() {
            System.out.println("running item 3");
        }
    });

    private static final TextMenu subNestedMenu= new TextMenu("sub-nested menu", true, false, item2, item3);
    private static final TextMenu nestedMenu= new TextMenu("nested menu", true, false, item2, item3, subNestedMenu);
    private static final TextMenu topMenu= new TextMenu("top menu", false, true, item1, nestedMenu);

    public static void main(String[] args) {

        topMenu.run();
    }
}
