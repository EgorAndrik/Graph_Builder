import java.io.DataOutputStream;
import java.net.Socket;

public class WishesClient {

    public static void ConectWithServer(String arithmetic_example){
        try {
            Socket soc = new Socket("localhost", 3030);
            DataOutputStream dout = new DataOutputStream(soc.getOutputStream());
            dout.writeUTF(arithmetic_example);
            dout.flush();
            dout.close();
            soc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Socket soc = new Socket("localhost", 3030);
            DataOutputStream dout = new DataOutputStream(soc.getOutputStream());
            dout.writeUTF("x**2 - 10");

            dout.flush();
            dout.close();
            soc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}