public class TwoStackQueue<T> implements TwoStackQueueInterface<T>{
    
    private MyStack<T> s1;
    private MyStack<T> s2;
    
    public TwoStackQueue(){
        s1 = new MyStack<>();
        s2 = new MyStack<>();
    }
    
    public void enqueue(T x){
        s1.push(x);
    }
	
    private void transfer(MyStack<T> from, MyStack<T> to){
        while(!from.isEmpty()){
            to.push(from.pop());
        }
    }
    
    public T dequeue(){
        transfer(s1, s2);
        T element = s2.pop();
        transfer(s2, s1);
        return element;
    }
    
	public int size(){
        return s1.size();
    }
	
    public boolean isEmpty(){
        return s1.isEmpty();
    }	
}
