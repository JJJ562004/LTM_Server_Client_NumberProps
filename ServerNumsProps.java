// package chat;
// import java.io.DataInputStream;
// import java.io.DataOutputStream;
// import java.io.IOException;
// import java.net.ServerSocket;
// import java.net.Socket;
// import java.util.HashMap;
// import java.util.Map;
// public class ServerNumsProps {
//     // Bài 1 Kiểm tra các thuộc tính của số
//     static String checkNumberProperties(int num) {
//         return (num + (isPrime(num) ? " là số nguyên tố.\n" : " không phải số nguyên tố.\n")
//                 + num + (isPerfectSquare(num) ? " là số chính phương.\n" : " không phải số chính phương.\n")
//                 + num + (isPerfectNumber(num) ? " là số hoàn hảo.\n" : " không phải số hoàn hảo.\n")
//                 + num + (isArmstrong(num) ? " là số Armstrong." : " không phải số Armstrong."));
//     }
//     // Kiểm tra số nguyên tố
//     static boolean isPrime(int num) {
//         if (num < 2) {
//             return false;
//         }
//         //Thay vì chạy hết đến num, cắt bớt bằng việc dùng sqrt()
//         for (int i = 2; i <= Math.sqrt(num); i++) {
//             if (num % i == 0) {
//                 return false;
//             }
//         }
//         return true;
//     }
//     // Kiểm tra số chính phương
//     static boolean isPerfectSquare(int num) {
//         int sqrt = (int) Math.sqrt(num);
//         return sqrt * sqrt == num;
//     }
//     // Kiểm tra số hoàn hảo
//     static boolean isPerfectNumber(int num) {
//         int sum = 1;
//         //Tìm kiếm ước của num
//         for (int i = 2; i <= num / 2; i++) {
//             if (num % i == 0) {
//                 sum += i;
//             }
//         }
//         return sum == num;
//     }
//     // Kiểm tra số Armstrong
//     static boolean isArmstrong(int num) {
//         int sum = 0, temp = num, digits = String.valueOf(num).length();
//         //Tách các chữ số của num
//         while (temp > 0) {
//             sum += Math.pow(temp % 10, digits);
//             temp /= 10;
//         }
//         return sum == num;
//     }
//     static Boolean isNumeric(String num) {
//         try {
//             Integer.parseInt(num);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }
//     // Bài 2 Tính tổng và tích các chữ số của số nguyên dương
//     static String sumAndProductOfDigits(int num) {
//         int sum = 0, product = 1;
//         //Tách các chữ số của num
//         while (num > 0) {
//             int digit = num % 10;
//             sum += digit;
//             product *= digit;
//             num /= 10;
//         }
//         return ("Tổng: " + sum + ", Tích: " + product);
//     }
//     // Bài 3 Tìm ước chung lớn nhất và bội chung nhỏ nhất
//     static String findGCDandLCM(int a, int b) {
//         int gcd = gcd(a, b);
//         int lcm = (a * b) / gcd;
//         return ("Ước chung lớn nhất: " + gcd + "\nBội chung nhỏ nhất: " + lcm);
//     }
//     //Hàm tính UCLN bằng thuật toán Euclid
//     static int gcd(int a, int b) {
//         while (b != 0) {
//             int temp = b;
//             b = a % b;
//             a = temp;
//         }
//         return a;
//     }
//     // Bài 4 Đảo ngược chuỗi
//     static String reverseString(String str) {
//         return ("Chuỗi đảo ngược: " + new StringBuilder(str).reverse());
//     }
//     // Xử lý chuỗi theo yêu cầu bài 5
//     static String processString(String str) {
//         return "Chuỗi đảo ngược: " + new StringBuilder(str).reverse() + "\n"
//                 + "Chuỗi chữ hoa: " + str.toUpperCase() + "\n"
//                 + "Chuỗi chữ thường: " + str.toLowerCase() + "\n"
//                 + "Chuỗi hoán đổi chữ hoa/thường: " + swapCase(str) + "\n"
//                 + "Số từ trong chuỗi: " + str.split("\\s+").length + "\n"
//                 +//lấy độ dài chuỗi đã tách khoảng cách bất cứ trắng (\\s), có thể nhiều hơn 1(+)
//                 "Nguyên âm có trong chuỗi: " + extractVowels(str);
//     }
//     // Hoán đổi chữ hoa thành chữ thường và ngược lại
//     static String swapCase(String str) {
//         StringBuilder result = new StringBuilder();
//         for (char c : str.toCharArray()) {
//             result.append(Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c));
//         }
//         return result.toString();
//     }
//     // Lấy nguyên âm từ chuỗi
//     static String extractVowels(String str) {
//         return str.replaceAll("(?i)[^aeiou]", "");//loại bỏ các kí tự không phải nguyên âm sau khi bật chế độ không phân biệt hoa thường
//     }
//     // Xử lý chuỗi theo yêu cầu bài 6
//     static String processText(String str) {
//         StringBuilder sb = new StringBuilder();
//         sb.append("Mỗi từ trên từng dòng:\n");
//         for (String word : str.split("\\s+")) {
//             sb.append(word + "\n");
//         }
//         sb.append("Bảng tần số ký tự:\n");
//         Map<Character, Integer> frequencyMap = new HashMap<>();
//         for (char c : str.toCharArray()) {
//             frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
//         }
//         for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
//             sb.append(entry.getKey() + ": " + entry.getValue() + "\n");
//         }
//         return sb.toString();
//     }
//     public static void main(String[] args) throws IOException {
//         ServerSocket serverSocket = new ServerSocket(2025);
//         Socket socket = serverSocket.accept();
//         DataInputStream din = new DataInputStream(socket.getInputStream());
//         DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//         Boolean stop = false;
//         while (!stop) {
//             String response = din.readUTF();
//             if (isNumeric(response) == true) {
//                 dos.writeUTF("Input là số\n" + checkNumberProperties(Integer.parseInt(response)) + "\n" + sumAndProductOfDigits(Integer.parseInt(response)));
//             } else {
//                 try {
//                     String[] numbers = response.split(",");
//                     int a = Integer.parseInt(numbers[0]);
//                     int b = Integer.parseInt(numbers[1]);
//                     dos.writeUTF(findGCDandLCM(a, b));
//                 } catch (Exception e) {
//                     dos.writeUTF("Input là chuỗi\n" + reverseString(response) + "\n" + processString(response) + "\n" + processText(response));
//                 }
//             }
//             dos.flush();
//         }
//         socket.close();
//     }
// }
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class ServerNumsProps {

    public static void main(String[] args) {
        try {
            // Render assigns a dynamic port via an environment variable
            int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "2025"));

            ServerSocket serverSocket = new ServerSocket(port, 50, InetAddress.getByName("0.0.0.0"));
            System.out.println("Cổng server: " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream din = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                try {
                        String message = input.readUTF();
                        System.out.println("Nhận: " + message);
                    } catch (EOFException e) {
                        System.err.println("Client ngắt kết nối.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                if (isNumeric(response)) {
                    int number = Integer.parseInt(response);
                    dos.writeUTF("Input là số\n" + checkNumberProperties(number) + "\n" + sumAndProductOfDigits(number));
                } else {
                    try {
                        String[] numbers = response.split(",");
                        int a = Integer.parseInt(numbers[0]);
                        int b = Integer.parseInt(numbers[1]);
                        dos.writeUTF(findGCDandLCM(a, b));
                    } catch (Exception e) {
                        dos.writeUTF("Input là chuỗi\n" + reverseString(response) + "\n" + processString(response) + "\n" + processText(response));
                    }
                }
                dos.flush();
                socket.close(); // Close connection after each request
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean isNumeric(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    static String checkNumberProperties(int num) {
        return (num + (isPrime(num) ? " là số nguyên tố.\n" : " không phải số nguyên tố.\n")
                + num + (isPerfectSquare(num) ? " là số chính phương.\n" : " không phải số chính phương.\n")
                + num + (isPerfectNumber(num) ? " là số hoàn hảo.\n" : " không phải số hoàn hảo.\n")
                + num + (isArmstrong(num) ? " là số Armstrong." : " không phải số Armstrong."));
    }

    static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    static boolean isPerfectSquare(int num) {
        int sqrt = (int) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    static boolean isPerfectNumber(int num) {
        int sum = 1;
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum == num;
    }

    static boolean isArmstrong(int num) {
        int sum = 0, temp = num, digits = String.valueOf(num).length();
        while (temp > 0) {
            sum += Math.pow(temp % 10, digits);
            temp /= 10;
        }
        return sum == num;
    }

    static String sumAndProductOfDigits(int num) {
        int sum = 0, product = 1;
        while (num > 0) {
            int digit = num % 10;
            sum += digit;
            product *= digit;
            num /= 10;
        }
        return ("Tổng: " + sum + ", Tích: " + product);
    }

    static String findGCDandLCM(int a, int b) {
        int gcd = gcd(a, b);
        int lcm = (a * b) / gcd;
        return ("Ước chung lớn nhất: " + gcd + "\nBội chung nhỏ nhất: " + lcm);
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    static String reverseString(String str) {
        return ("Chuỗi đảo ngược: " + new StringBuilder(str).reverse());
    }

    static String processString(String str) {
        return "Chuỗi đảo ngược: " + new StringBuilder(str).reverse() + "\n"
                + "Chuỗi chữ hoa: " + str.toUpperCase() + "\n"
                + "Chuỗi chữ thường: " + str.toLowerCase() + "\n"
                + "Chuỗi hoán đổi chữ hoa/thường: " + swapCase(str) + "\n"
                + "Số từ trong chuỗi: " + str.split("\\s+").length + "\n"
                + "Nguyên âm có trong chuỗi: " + extractVowels(str);
    }

    static String swapCase(String str) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            result.append(Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c));
        }
        return result.toString();
    }

    static String extractVowels(String str) {
        return str.replaceAll("(?i)[^aeiou]", "");
    }

    static String processText(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Mỗi từ trên từng dòng:\n");
        for (String word : str.split("\\s+")) {
            sb.append(word).append("\n");
        }
        sb.append("Bảng tần số ký tự:\n");
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}
