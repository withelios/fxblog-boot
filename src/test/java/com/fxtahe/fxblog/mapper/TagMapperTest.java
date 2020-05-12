package com.fxtahe.fxblog.mapper;

import com.fxtahe.fxblog.entity.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagMapperTest {




    @Test
    public void testInsert(){
        Tag tag = new Tag();
        tag.setTagName("python");
        tag.insert();
        System.out.println("tagId:"+tag.getId());
    }
}
