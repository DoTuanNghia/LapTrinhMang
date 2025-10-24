package RMIClient;

import RMI.CharacterService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class e7K0QyBJ {
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN196";
        String qCode = "e7K0QyBJ";

        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);

        CharacterService characterService = (CharacterService) registry.lookup("RMICharacterService");

        String request = characterService.requestCharacter(studentCode, qCode);

        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < request.length(); i++){
            map.put(request.charAt(i), map.getOrDefault(request.charAt(i), 0) + 1);
        }

        String ans = "";
        for (Character character : map.keySet()) {
            ans += String.valueOf(character) + String.valueOf(map.get(character));
        }

        characterService.submitCharacter(studentCode, qCode, ans);
    }
}
