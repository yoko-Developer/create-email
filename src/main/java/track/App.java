package track;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


public class App {

    public static void main(String[] args) {
        // 標準入力の読み取り
        String[] lines = getStdin();

        // 1行目：テンプレートの文字列数
        int N = Integer.parseInt(lines[0].trim());

        // 2行目：テンプレート
        String[] templateWords = lines[1].trim().split(" ");

        // 3行目：取引先の数
        int numberOfClients = Integer.parseInt(lines[2].trim());

        // 各取引先データの処理
        int lineIndex = 3; // 現在の行インデックス
        for (int i = 0; i < numberOfClients; i++) {
            // 取引先の情報数
            int numberOfInfo = Integer.parseInt(lines[lineIndex].trim());
            lineIndex++;

            // 取引先情報をMapに格納
            Map<String, String> clientData = new HashMap<>();
            for (int j = 0; j < numberOfInfo; j++) {
                String[] info = lines[lineIndex].trim().split(" ");
                clientData.put(info[0], info[1]);
                lineIndex++;
            }

            // テンプレートをコピーして置換処理
            StringBuilder email = new StringBuilder();
            boolean allReplacementsFound = true;
            for (String word : templateWords) {
                if (word.startsWith("#")) {
                    if (clientData.containsKey(word)) {
                        email.append(clientData.get(word)).append(" ");
                    } else {
                        allReplacementsFound = false;
                        break;
                    }
                } else {
                    email.append(word).append(" ");
                }
            }

            // 出力処理
            if (allReplacementsFound) {
                System.out.println(email.toString().trim());
            } else {
                System.out.println("Error: Lack of data");
            }
        }
    }

    // 標準入力の読み込みメソッド
    private static String[] getStdin() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        return lines.toArray(new String[lines.size()]);
    }
}
