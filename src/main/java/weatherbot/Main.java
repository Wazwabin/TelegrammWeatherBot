package weatherbot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Location;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import weatherbot.model.Weather;

import java.io.IOException;
import java.util.List;

public class Main {
    private static final String TELEGRAM_TOKEN ="1218265889:AAHGAqG4O0ykdikK17KvDQXFQi_BMuj_X_c";
    private static final String YANDEX_TOKEN="e8eac20e-0a2e-4b46-9f9f-27cfcf92d83b";
    private static int offset=0;

    public static void main(String[] args) throws IOException {

        //create bot
        TelegramBot bot = new TelegramBot(TELEGRAM_TOKEN);
        WeatherService weatherService = new WeatherService(YANDEX_TOKEN);
        while (true){

            //receiving incoming messages
            GetUpdates getUpdates = new GetUpdates().limit(1).offset(offset).timeout(0); //at once only 1 message
            GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
            List<Update> updates = updatesResponse.updates();

            if (!updates.isEmpty()){
                Update update = updates.get(0);
                System.out.println(update);
                Message message = update.message();
                System.out.println(message);

                //what bot answer to us
                String answer = "";
                if ("/start".equals(message.text())) {
                    answer = "Привет! Я могу показать прогноз погоды.\n" +
                    "Для этого прикрепи сюда свою локацию.\n";
                } else if (message.location() != null) {
                    Location location = message.location();

                    System.out.println();
                    Weather weather = weatherService.getWeather(location.latitude(), location.longitude());
                    answer = "Погода на сегодня: \n" +
                            "Температура " + weather.getFact().getTemp() + "\n" +
                            "Ощущается как " + weather.getFact().getFeels_like() + "\n" +
                            "Скорость ветра " + weather.getFact().getWinds_speed() + "\n" +
                            "Давление " + weather.getFact().getPressure_mm() + "\n" +
                            "Подробнее " + weather.getInfo().getUrl() + "\n";
                } else{
                    answer = "Я пока не знаю такой команды :(";}

                    SendMessage sendMessage = new SendMessage(message.chat().id(), answer);
                bot.execute(sendMessage);

                offset = update.updateId()+1; //mark incoming message as readen
            }
        }


    }
}
