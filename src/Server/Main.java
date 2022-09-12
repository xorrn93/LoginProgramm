package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


    public class Client {

    public static void main(String[] args) throws Exception{

        ServerSocket server = new ServerSocket(25000);
        Socket sock = server.accept();

        DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
        DataInputStream os = new DataInputStream(sock.getInputStream());

        Scanner sc = new Scanner(System.in);

        SignupDAO dao = new SignupDAO.getInstance();

        int menu = dis.readInt();

        if(menu == 1){ // login
            String id = dis.readUTF();
            String pw = dis.readUTF();

            switch (dao.isId)
            case 1 :


            if(dao.isIdExsit(id) == false){
                dos.writeInt(2);
                dos.flush();// id wrong{
            } else if(dao.isPwExist(id,pw)){
                if()
                dos.writeBoolean(true);
            }
        }
        if(){
            String id = dis.readUTF();
            boolean isIdExist = dao.isIdExist(id);
            if(isIdExist = true){

                dos.writeBoolean(false);
            }

        }
        dao.isIdExist(null);
        if (dao.is)

    }
}