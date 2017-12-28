package com.crud.tasks.domain;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BadgesTest {
    @Test
    public void testGetVotes() {
        //Given
        Trello trello = new Trello(1, 2);
        AttachmentsByType attachmentsByType = new AttachmentsByType(trello);
        Badges badges = new Badges(200, attachmentsByType);
        //When
        int result = badges.getVotes();
        //Then
        Assert.assertEquals(200, result);
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetAttachments() {
        //Given
        Trello trello = new Trello(1, 2);
        AttachmentsByType attachmentsByType = new AttachmentsByType(trello);
        Badges badges = new Badges(200, attachmentsByType);
        //When
        AttachmentsByType result = badges.getAttachmentsByType();
        //Then
        Assert.assertEquals(attachmentsByType, result);
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.getTrello().getBoard());
        Assert.assertEquals(2, result.getTrello().getCard());
    }
}
