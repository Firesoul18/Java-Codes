public class ConsumerProducer{
	public static void main(String[] args) {
        Produce p = new Produce();
        for(int i=0; i<66; i++){
            System.out.println(i);
        new Thread(new Prod(p)).start();
        new Thread(new cons(p)).start();
        // System.out.println(Thread.getAllStackTraces());
        System.out.println(Thread.activeCount());
        }
    }
}
class Produce{
    boolean Produced=false;
    synchronized int produce(){
        if(Produced){
            try {
                wait();   
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("One Produced");
        Produced=true;
        notifyAll();
        return 1;
    }
    synchronized void consume(){
        if(!Produced){
            try {
                wait();   
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Produced=false;
        System.out.println("Consumed");
        notifyAll();
    }

}
class Prod implements Runnable{
    private Produce p;
    Prod(Produce p){
        this.p=p;
    }
    @Override
    public void run() {
        p.produce();
    }
}
class cons implements Runnable{
    private Produce p;
    cons(Produce p){
        this.p=p;
    }
    @Override
    public void run() {
        p.consume();
    }
}