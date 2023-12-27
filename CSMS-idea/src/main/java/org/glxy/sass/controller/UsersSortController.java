package org.glxy.sass.controller;

import org.glxy.sass.entity.VO.UsersSort;
import org.glxy.sass.service.UsersSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usersSort")
public class UsersSortController {

    @Autowired
    UsersSortService usersSortService;

    @GetMapping("/selectSum")
    public List<UsersSort> selectSum() {
//        List<UsersSort> usersSort = usersSortService.selectSum();
//        UsersSort iusersSort = new UsersSort();
//        for (int i = 0; i < usersSort.size(); i++) {
//
//            Integer sex = usersSort.get(i).getUsersSex();
//            if (sex == 0) {
//                iusersSort.setType("男");
//            } else {
//                iusersSort.setType("女");
//            }
//            iusersSort.setSum(usersSort.get(i).getSum());
//            System.out.println(iusersSort);
//        }
//
//
//        return null;
//
//    }
        return usersSortService.selectSum();
    }
}
