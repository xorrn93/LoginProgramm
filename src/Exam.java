import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Exam {
    public static void main(String[] args) throws Exception{


        Socket client = new Socket("192.168.50.28",25000);

        InputStream is = client.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        OutputStream os = client.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        // UPGRADE
        while (true) {
            String msg= dis.readUTF();// 매개변수가 필요없음
            System.out.println("서버로 부터 전송된 메세지 : " + msg);

            dos.writeUTF(JOptionPane.showInputDialog("메세지 입력 :")); // UTF 방식으로 인코딩 되어 전송
            dos.flush();

        }

    }
}
