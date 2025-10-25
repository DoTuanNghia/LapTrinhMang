/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS;

import java.util.ArrayList;
import vn.medianews.*;

/**
 *
 * @author Tuan Nghia
 */
public class J1MxKylh {

    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN604";
        String qCOde = "J1MxKylh";

        CharacterService_Service service = new CharacterService_Service();
        CharacterService port = service.getCharacterServicePort();

        String receive = port.requestString(studentCode, qCOde);
        ArrayList<String> ans = new ArrayList();

        ans.add(PascalCase(receive));
        ans.add(camelCase(receive));
        ans.add(snake_case(receive));

        port.submitCharacterStringArray(studentCode, qCOde, ans);
    }

    public static String PascalCase(String s) {
        String[] temp = s.split("[\\s+_]");
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            ans.add(temp[i].substring(0, 1).toUpperCase() + temp[i].substring(1).toLowerCase());
        }
        return String.join("", ans);
    }

    public static String camelCase(String s) {
        String[] temp = s.toLowerCase().split("[\\s+_]");
        ArrayList<String> ans = new ArrayList<>();
        ans.add(temp[0]);
        for (int i = 1; i < temp.length; i++) {
            ans.add(temp[i].substring(0, 1).toUpperCase() + temp[i].substring(1).toLowerCase());
        }
        return String.join("", ans);
    }

    public static String snake_case(String s) {
        String[] temp = s.toLowerCase().split("[\\s+_]");
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            ans.add(temp[i]);
        }
        return String.join("_", ans);
    }
}
