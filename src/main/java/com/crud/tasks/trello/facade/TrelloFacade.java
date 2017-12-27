package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrelloFacade {
    @Autowired
    private TrelloClient trelloClient;

    public List<TrelloBoard> fetchTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.
    }
}
