import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Exam2 {
    public static void main(String[] args) throws Exception {
        Socket client = new Socket("192.168.50.65",23000);

        OutputStream os = client.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        InputStream is = client.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        dos.writeUTF(JOptionPane.showInputDialog("id :"));
        dos.flush();

        dos.writeUTF(JOptionPane.showInputDialog("password :"));
        dos.flush();

        boolean msg= dis.readBoolean();// 매개변수가 필요없음
        System.out.println("서버로 부터 전송된 메세지 : " + msg);

    }
}
