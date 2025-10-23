package BaiTapTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class WMgL8yn8 {
    public static void main(String[] args) throws IOException, RuntimeException {
        String host = "203.162.10.109";
        int port = 2206;

        Socket socket = new Socket(host, port);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        os.write("B22DCCN604;WMgL8yn8".getBytes());
        os.flush();

        byte[] buffer = new byte[1024];
        int bytesRead = is.read(buffer);
        String s = new String(buffer, 0, bytesRead);
        System.out.println(s);
        ArrayList<Integer>a = new ArrayList<>();
        String []tmp = s.trim().split(",");
        for(String x: tmp) a.add(Integer.parseInt(x));
        int n = a.size();
        int []f= new int[n], trace = new int[n]; Arrays.fill(trace, -1);
        for(int i = 0;i<n;i++) f[i] = 1;
        for(int i=0;i<n;i++){
            for(int j = 0;j<i;j++){
                if(a.get(j) < a.get(i) && f[i] < f[j] + 1){
                    f[i] = Integer.max(f[i],f[j]+1);
                    trace[i] = j;
                }
            }
        }
        int maxLength = 0;
        int endIndex = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] > maxLength) {
                maxLength = f[i];
                endIndex = i;
            }
        }
        // Khôi phục dãy con LIS
        ArrayList<Integer> lis = new ArrayList<>();
        while (endIndex != -1) {
            lis.add(0, a.get(endIndex));
            endIndex = trace[endIndex];
        }
        String ans = String.format("%d", lis.get(0));
        for(int i = 1;i<lis.size();i++) ans+=String.format(",%d", lis.get(i));
        ans+=String.format(";%d", lis.size());
        System.out.println(ans);
        os.write(ans.getBytes());
        os.flush();

        is.close();
        os.close();
        socket.close();
    }
}
