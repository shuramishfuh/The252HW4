package TelegramBot;

import Implementations.IMCourSeeraFactory;
import Implementations.Initializer;
import Interfaces.CourSeera;
import Interfaces.CourSeeraFactory;
import Interfaces.Course;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TBot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        // if there is a message that has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // get the text of the message
            String receivedText = "I reveived : \n " + update.getMessage().getText();



            // send a reply
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(receivedText);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return "Cmps252JavaBot";
    }

    @Override
    public String getBotToken() {
        return "2142734392:AAHj31KTvkDYXkCeOCPAyOwER93hrwwmWpM";
    }
}