import java.net.Socket;
import java.io.*;

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
            int countFielInDir = 0;
            try {
                File[] file =  new File("serverSock/graphDir").listFiles();
                countFielInDir = file.length;
            }
            catch (Exception e){
                e.printStackTrace();
            }

            int count = 0;
            while (count <= countFielInDir) {
                try {
                    File[] file =  new File("serverSock/graphDir").listFiles();
                    if (file != null)
                        count = file.length;
                    else
                        count = 0;
                    System.out.println("Total no. of files : " + count);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            int i;
            FileInputStream fis = new FileInputStream ("src/graph.png");
            DataOutputStream os = new DataOutputStream(soc.getOutputStream());
            while ((i = fis.read()) > -1)
                os.write(i);
            os.flush();
            os.close();

            soc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}