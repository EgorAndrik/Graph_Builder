import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
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
            Socket socket = new Socket("localhost", 3030);

            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            dout.writeUTF("x**2 - 10");
            dout.flush();
            dout.close();

            InputStream ostream = socket.getInputStream();
            DataInputStream dos = new DataInputStream(ostream);
            BufferedImage bufferedImage = ImageIO.read(dos);

            try
            {
                File graph = new File("graph.png");
                ImageIO.write(bufferedImage, "png", graph);
                dos.close();
                ostream.close();
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}