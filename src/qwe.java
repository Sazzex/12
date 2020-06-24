import java.util.Arrays;

public class qwe {
    static final int size = 10;
    static final int h = size / 2;

    public static void main(String[] args) {
        float[] arr = new float[size];

        fillArray(arr);
        long time = System.currentTimeMillis();
        System.out.println("первый метод до " + Arrays.toString(arr));
        method1(arr);
        System.out.println("время выполнения " + (System.currentTimeMillis() - time));
        System.out.println("первый метод после " + Arrays.toString(arr));

        fillArray(arr);
        System.out.println();
        System.out.println("второй метод до " + Arrays.toString(arr));
        time = System.currentTimeMillis();
        method2(arr);
        System.out.println("время выполнения " + (System.currentTimeMillis() - time));
        System.out.println("второй метод после " + Arrays.toString(arr));
    }

    private static void fillArray(float a[]) {
        for (int i = 0; i < a.length; i++)
            a[i] = 1;
    }

    private static void method1(float a[]) {
        for (int i = 0; i < a.length; i++)
            a[i] = (float) (a[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }

    private static void method2a(float a[], int h) {
        for (int i = 0; i < a.length; i++) {
            a[i] = (float) (a[i] * Math.sin(0.2f + (i + h) / 5) * Math.cos(0.2f + (i + h) / 5)
                    * Math.cos(0.4f + (i + h) / 2));
        }
    }

    private static void method2(float a[]) {
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(a, 0, a1, 0, h);
        System.arraycopy(a, h, a2, 0, h);

        System.out.println("a1 до " + Arrays.toString(a1));
        System.out.println("a2 до " + Arrays.toString(a2));

        new Thread(() -> qwe.method2a(a1, 0)).start();
        new Thread(() -> qwe.method2a(a2, h)).start();

        System.out.println("a1 после " + Arrays.toString(a1));
        System.out.println("a2 после " + Arrays.toString(a2));

        System.arraycopy(a1, 0, a, 0, h);
        System.arraycopy(a2, 0, a, h, h);
    }

    private static boolean compareArrays(float a1[], float a2[]) {
        for (int i = 0; i < a1.length; i++) {
            if (a1[i] != a2[i])
                return false;
        }
        return true;
    }

}