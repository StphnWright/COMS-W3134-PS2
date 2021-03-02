public class TwoStackTest {
    
    public static void main(String [] args) {
        
        TwoStackQueueInterface<String> q = new TwoStackQueue<>();
        
        q.enqueue("a");
        System.out.println(q.size());
        q.enqueue("b");
        System.out.println(q.size());
        q.enqueue("c");
        System.out.println(q.size());
       
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}
