package com.crud.tasks.trello.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTestSuite {

    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void gettersWorkTest() {
        //Given
        //When
        String endpoint = trelloConfig.getTrelloApiEndpoint();
        String trelloAppKey = trelloConfig.getTrelloAppKey();
        String trelloToken = trelloConfig.getTrelloToken();
        String userName = trelloConfig.getUsername();
        //Then
        assertEquals("https://api.trello.com/1", endpoint);
        assertEquals("89556dc7b868b1950515e2f5f0a8bb7f", trelloAppKey);
        assertEquals("22dec0b9d354b302dc01c0cda98d5bc5a404edca39ea73282254c955cd51e29e", trelloToken);
        assertEquals("SirVertar", userName);
    }
}
