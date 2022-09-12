package Server;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignupDAO {

    private static SignupDAO instance = null;

    public synchronized static SignupDAO getInstance() {
        if(instance == null) {
            instance = new SignupDAO();
        }
        return instance;
    }
    private BasicDataSource bds = new BasicDataSource();
    private SignupDAO() { //반복으로 인해 생성자로 빼두기
        this.bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        this.bds.setUsername("wellcome");
        this.bds.setPassword("wellcome");
        this.bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        this.bds.setInitialSize(30); //30개 커넥션 //기본 8?
    }

    private Connection getConnection() throws Exception{
        return bds.getConnection();
//      bds.getConnection(); //DirverManager.getConnection과 유사
        //Connection 을 울타리에 미리 만들어놓고 대여&반환 시스템
    }



    public int insert(SignupDTO dto) throws Exception{
        String sql = "insert into member values(?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement pstat = con.prepareStatement(sql);){
            pstat.setString(1, dto.getId());
            pstat.setString(2, dto.getPassword());
            pstat.setString(3, dto.getName());
            int result = pstat.executeUpdate();

            con.commit();
            return result;
        }

    }


    public boolean islogined(String id) throws Exception{
        ////회원가입, 로그인시 동일id존재
        Connection con = getConnection();
        String sql = "select * from member where id = ?";

        PreparedStatement pstat = con.prepareStatement(sql);
        pstat.setString(1,id);

        ResultSet rs = pstat.executeQuery();
        boolean result = rs.next();
        return result;
    }

    public boolean isPwExist(String pw) throws Exception{

        Connection con = getConnection();
        String sql =  "select * from member where password = ?";

        PreparedStatement pstat = con.prepareStatement(sql);
        pstat.setString(1,pw);

        ResultSet rs = pstat.executeQuery();
        boolean result = rs.next();

        return result;
    }

}