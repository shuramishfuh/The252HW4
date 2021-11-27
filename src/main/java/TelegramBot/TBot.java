package TelegramBot;

import ImplementationFormatterEngine.InputParser;
import ImplementationFormatterEngine.Linker;
import ImplementationFormatterEngine.outPutFormatter;
import InterfacesformatterEngine.ILinker;
import InterfacesformatterEngine.IinputParser;
import InterfacesformatterEngine.IoutputFormatter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class TBot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        // if there is a message that has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // get the text of the message

            IinputParser iinputParser = new InputParser();
            String receivedText = update.getMessage().getText();
            var result = iinputParser.convertStringToInstruction(receivedText);
            ILinker linker = new Linker();
            var output = linker.callCoursera(result);
            IoutputFormatter formatter = new outPutFormatter();
            var ourMessage =   formatter.selctor(output);

            // send a reply
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(ourMessage);
            System.out.printf(ourMessage);
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