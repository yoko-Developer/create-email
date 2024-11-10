package track;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        String[] lines = getStdin();
        int currentIndex = 0;

        // テンプレート情報の読み込み
        int n = Integer.parseInt(lines[currentIndex++].trim());
        String[] template = lines[currentIndex++].trim().split(" ");

        // 取引先の数の読み込み
        int numClients = Integer.parseInt(lines[currentIndex++].trim());

        // 各取引先ごとにメール生成
        for (int i = 0; i < numClients; i++) {
            int numEntries = Integer.parseInt(lines[currentIndex++].trim());

            // 取引先の情報をマッピング
            Map<String, String> replacements = new HashMap<>();
            for (int j = 0; j < numEntries; j++) {
                String[] data = lines[currentIndex++].trim().split(" ");
                replacements.put(data[0], data[1]);
            }

            // メールの文章を生成
            StringBuilder result = new StringBuilder();
            boolean dataMissing = false;
            for (String word : template) {
                if (word.startsWith("#")) {
                    if (replacements.containsKey(word)) {
                        result.append(replacements.get(word)).append(" ");
                    } else {
                        dataMissing = true;
                        break;
                    }
                } else {
                    result.append(word).append(" ");
                }
            }

            // 結果の出力
            if (dataMissing) {
                System.out.println("Error: Lack of data");
            } else {
                System.out.println(result.toString().trim());
            }
        }
    }

    // 標準入力からデータを読み込むメソッド
    private static String[] getStdin() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> lines = new ArrayList<>();
        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        return lines.toArray(new String[0]);
    }

}
