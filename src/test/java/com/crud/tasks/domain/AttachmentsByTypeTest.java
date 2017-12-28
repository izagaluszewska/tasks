package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AttachmentsByTypeTest {
    @Test
    public void testGetter() {
        //Given
        Trello trello = new Trello(1, 1);
        AttachmentsByType attachmentsByType = new AttachmentsByType(trello);
        //When
        Trello result = attachmentsByType.getTrello();
        //Then
        Assert.assertEquals(trello, result);
    }
}
