package com.lxy;

import com.github.pagehelper.PageInfo;
import com.lxy.pojo.Type;
import com.lxy.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private MD5Utils md5Utils;
    @Test
    void contextLoads() {
        //String ratrme = md5Utils.getMD5Str("123456");
        //System.out.println(ratrme);
    }

}
