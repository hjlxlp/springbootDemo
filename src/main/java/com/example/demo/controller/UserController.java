package com.example.demo.controller;

import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());

    @GetMapping("/")
    public List<User> getUserList() {
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    @PostMapping("/")
    public String postUser(@ModelAttribute User user) {
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(), user);
        return "success";
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    @PutMapping("/{id}")
    public String putUser(@PathVariable Integer id, @ModelAttribute User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        users.remove(id);
        return "success";
    }

}
