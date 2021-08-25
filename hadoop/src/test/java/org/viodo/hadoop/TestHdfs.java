package org.viodo.hadoop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.viodo.hadoop.client.HdfsTemplate;

/**
 * @author xc.chen
 * @version 1.0
 * @date 2021-08-23
 */
@SpringBootTest
public class TestHdfs {

    @Autowired
    private HdfsTemplate hdfsTemplate;

    @Test
    public void testExist(){
        boolean isExist = hdfsTemplate.checkExists("/use");
        System.out.println(isExist);
    }
}
