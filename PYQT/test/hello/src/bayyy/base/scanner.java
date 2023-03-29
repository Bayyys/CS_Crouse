package bayyy.base;

import java.util.Scanner;

public class scanner {
    public static void main(String[] args) {

        // 创建一个扫描器对象，用于接收键盘输入的数据
        Scanner scanner = new Scanner(System.in);

        System.out.println("使用next方式接收：");

        if (scanner.hasNext()) {
            String str = scanner.next();
            System.out.println("输入的内容为：" + str);
        }

        if (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            System.out.println("输入的内容为：" + str);
        }

        scanner.close();

    }
}
