package Client;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    // view
    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1",25000);

            OutputStream os = client.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            InputStream is = client.getInputStream();

            DataInputStream dis = new DataInputStream(is);
            Scanner sc = new Scanner(System.in);
            // ServerDAO dao = ServerDAO.getInstance();

            while (true) {
+
                        System.out.println("<<인증시스템>>\n"
                                "1. 로그인\n" +
                        "2. 회원가입\n" +
                        "3. 종료\n" +
                        ">>");
                int num = Integer.parseInt(sc.nextLine());
                dos.writeInt(num);

                if(num == 1 ){

                        JOptionPane.showMessageDialog(null,"Ok","Login",JOptionPane.QUESTION_MESSAGE);
                        dos.writeUTF(JOptionPane.showInputDialog("id :"));
                        dos.writeUTF(JOptionPane.showInputDialog("pw :"));// UTF 방식으로 인코딩 되어 전송
                        dos.flush();
                        dis.readInt();


                        int loginCode = dis.readInt();
                        if (loginCode == 1){// id 가 틀린건지 pw 틀린건지
                            JOptionPane.showMessageDialog(null,"로그인 성공!","Login",JOptionPane.INFORMATION_MESSAGE);
                        } else if (loginCode == 2) {
                            JOptionPane.showMessageDialog(null,"존재 하지 않는 아이디 입니다.","Login",JOptionPane.WARNING_MESSAGE);
                        } else if (loginCode == 3){
                            JOptionPane.showMessageDialog(null,"패스워드를 다시 입력해주세요","Login",JOptionPane.WARNING_MESSAGE);
                        }// 매개변수가 필요없음

                } else if (num==2) {
                    // 회원가입

                        while (true){

                            JOptionPane.showMessageDialog(null,"Ok","Register",JOptionPane.QUESTION_MESSAGE);

                            dos.writeUTF(JOptionPane.showInputDialog("id 입력 :"));
//                        dos.writeUTF(JOptionPane.showInputDialog("pw 입력 :"));// UTF 방식으로 인코딩 되어 전송
//                        dos.writeUTF(JOptionPane.showInputDialog("name 입력 :"));// UTF 방식으로 인코딩 되어 전송
                            dos.flush();

                            boolean id_result= dis.readBoolean();
                            if (id_result){
                                JOptionPane.showMessageDialog(null,"ID is duplicated!","Register",JOptionPane.WARNING_MESSAGE);
//                                중복
                                break;
                            } else {
                                try {
                                    dos.writeUTF(JOptionPane.showInputDialog("id 입력 :"));// UTF 방식으로 인코딩 되어 전송
                                    dos.writeUTF(JOptionPane.showInputDialog("pw 입력 :"));
                                    dos.writeUTF(JOptionPane.showInputDialog("name 입력 :"));
                                    dos.flush();

                                    JOptionPane.showMessageDialog(null,"Sucess!","Register",JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                }catch (Exception e){
                                    JOptionPane.showMessageialog(null,"Fail!","Register",JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        }

                    }


                } else if (num==3) {
                    JOptionPane.showMessageDialog(null,"Exit","Exit",JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } else{
                    JOptionPane.showMessageDialog(null,"올바른 값을 넣어주세요","Error",JOptionPane.INFORMATION_MESSAGE);
                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
