package be.technifutur.spring.demo.service.impl;

import be.technifutur.spring.demo.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {


    // ATTENTION la liste "messages" met à mal le concept de STATELESSNESS
    // => Inacceptable dans une vrai API RESTFULL
    private final List<String> messages = new ArrayList<>();

    @Override
    public String getLastMessage() {
        if( messages.isEmpty() )
            return null;

        return messages.get( messages.size()-1 );
    }

    @Override
    public List<String> getMessages() {
        return List.copyOf( messages );
    }

    @Override
    public String getMessage(int index) {
        return messages.get( index );
    }

    @Override
    public void addMessage(String toAdd) {
        messages.add( toAdd );
    }

    @Override
    public void deleteMessage(int index) {
        messages.remove( index );
    }

    @Override
    public void changeMessage(int index, String replacemnt) {
        messages.set( index, replacemnt );
    }
}
