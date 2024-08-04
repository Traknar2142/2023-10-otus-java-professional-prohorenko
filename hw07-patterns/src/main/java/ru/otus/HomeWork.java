package ru.otus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.ListenerPrinterConsole;
import ru.otus.listener.homework.HistoryListener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.LoggerProcessor;
import ru.otus.processor.Processor;
import ru.otus.processor.ProcessorSwapFields;
import ru.otus.processor.ProcessorThrowException;
import ru.otus.provider.DateTimeProvider;

public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    /*
    Реализовать to do:
      1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
      2. Сделать процессор, который поменяет местами значения field11 и field12
      3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
            Секунда должна определяьться во время выполнения.
            Тест - важная часть задания
            Обязательно посмотрите пример к паттерну Мементо!
      4. Сделать Listener для ведения истории (подумайте, как сделать, чтобы сообщения не портились)
         Уже есть заготовка - класс HistoryListener, надо сделать его реализацию
         Для него уже есть тест, убедитесь, что тест проходит
    */

    public static void main(String[] args) {
        /*
          по аналогии с Demo.class
          из элеменов "to do" создать new ComplexProcessor и обработать сообщение
        */

        DateTimeProvider dateTimeProvider = new DateTimeProvider(LocalDateTime.now());

        List<Processor> processors =
                List.of(new LoggerProcessor(new ProcessorThrowException(dateTimeProvider)), new ProcessorSwapFields());

        ComplexProcessor complexProcessor = new ComplexProcessor(processors, ex -> {});
        ListenerPrinterConsole listenerPrinter = new ListenerPrinterConsole();
        HistoryListener historyListener = new HistoryListener();
        complexProcessor.addListener(listenerPrinter);
        complexProcessor.addListener(historyListener);

        ObjectForMessage field13 = new ObjectForMessage();
        ArrayList<String> data = new ArrayList<>();
        data.add("123");
        data.add("456");
        field13.setData(data);

        Message message = new Message.Builder(1L)
                .field11("field11")
                .field12("field12")
                .field13(field13)
                .build();

        Message result = complexProcessor.handle(message);
        logger.info("result:{}", result);

        complexProcessor.removeListener(listenerPrinter);
        complexProcessor.removeListener(historyListener);
    }
}
