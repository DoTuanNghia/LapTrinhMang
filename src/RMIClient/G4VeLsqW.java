package RMIClient;

import RMI.CharacterService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class G4VeLsqW {
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN604";
        String qCode = "G4VeLsqW";

        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);

        CharacterService characterService = (CharacterService) registry.lookup("RMICharacterService");

        String request = characterService.requestCharacter(studentCode, qCode);
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < request.length(); i++) {
            map.put(request.charAt(i), map.getOrDefault(request.charAt(i), 0) + 1);
        }
        System.out.println(map);

        ArrayList<String> list = new ArrayList<>();
        for (Character character : map.keySet()) {
            list.add("\"" + String.valueOf(character)+ "\"" + ": " + String.valueOf(map.get(character)) );
        }
        String ans = "{"  + String.join(", ", list) + "}";
        System.out.println(ans);
        characterService.submitCharacter(studentCode, qCode, ans);
    }
}
