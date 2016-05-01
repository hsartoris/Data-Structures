public class SwapTest {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void main(String[] args) {
        int[] x = {5,4,3,2,1,0};
        swap(x, 3, 4);
        for(int i = 0; i < x.length; i++) {
            System.out.println(x[i]);
        }
    }
}