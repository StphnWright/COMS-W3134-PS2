public class MyStack<T> implements MyStackInterface<T> {
    
    //declare instance variables
    private T[] array = (T[]) new Object[10];
    private int size;
    
    public void push(T x) {
        if(size == array.length){
            T[] newArray = (T[]) new Object[array.length * 2];
            for (int i = 0; i < array.length; i++){
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[size++] = x;
    }
	public T pop(){
        return isEmpty() ? null : array[--size];       
    }
	public T peek(){
        return isEmpty() ? null : array[size - 1];       
    }
	public boolean isEmpty(){
        return size == 0;
    }
	public int size(){
        return size;
        
    }
}
