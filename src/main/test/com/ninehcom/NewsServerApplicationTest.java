package com.ninehcom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by yants on 2017/5/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NewsServerApplication.class)

public class NewsServerApplicationTest {

    @Test
    public void main() throws Exception {
        System.out.println("111111111111");
    }

}