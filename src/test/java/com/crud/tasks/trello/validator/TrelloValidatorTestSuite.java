package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTestSuite {
    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoardTest() {
        //Given
        List<TrelloList> TrelloLists1 = new ArrayList<>();
        TrelloLists1.add(new TrelloList("1", "test_list", false));
        List<TrelloList> TrelloLists2 = new ArrayList<>();
        TrelloLists2.add(new TrelloList("2", "normal_list", false));

        List<TrelloBoard> trelloBoards1 = new ArrayList<>();
        trelloBoards1.add(new TrelloBoard("1", "test", TrelloLists1));
        trelloBoards1.add(new TrelloBoard("2", "normal", TrelloLists2));

        //When
        List<TrelloBoard> filteredTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoards1);

        //Then
        assertEquals(1, filteredTrelloBoards.size());
        assertEquals("normal", filteredTrelloBoards.get(0).getName());
    }
}
