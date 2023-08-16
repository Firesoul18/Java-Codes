class RecursiveCalls{

    static void first(){
        System.out.println("First");
        second();
    }

    static void second(){
        System.out.println("Second");
        third();
    }

    static void third(){
        System.out.println("Third");
    }

    public static void main(String[] args) {
        first();
    }
}