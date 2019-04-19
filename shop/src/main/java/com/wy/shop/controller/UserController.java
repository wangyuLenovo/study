package com.wy.shop.controller;

import com.wy.shop.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {


//    @Autowired
//    private UserMapper userMapper;


    @RequestMapping("/queryUserInfoByPage")
    @ResponseBody
    public List<User> queryUserInfoByPage() {


        ArrayList<User> users = new ArrayList<>();
        User u1 = new User(1l, "王宇", "123456");
        User u2 = new User(1l, "郑悦峰", "123456");
        User u3 = new User(1l, "吴良峰", "123456");
        users.add(u1);


//        List<User> userList = userMapper.queryUserInfoByPage();

        return users;
    }

//    @RequestMapping(value = "/userList",method = RequestMethod.GET)
//    public String userList(HttpRequest request,Model model) {
//
//        List<User> userList = userMapper.queryUserInfoByPage();
//        model.addAttribute("userList",userList);
//        return "useList";
//    }

    public static void main(String[] args) {

        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/shop?" + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8&useSSL=true";
            connection = DriverManager.getConnection(url);


            Statement statement = connection.createStatement();


            String sql = "select * from user";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                System.out.println(username);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
