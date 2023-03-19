package com.chatbot.controller;

import com.chatbot.dto.ResponseModel;
import com.chatbot.openai.api.data.completion.chat.ChatCompletionData;
import com.chatbot.openai.api.data.completion.chat.message.ChatCompletionMessageData;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.chatbot.openai.api.CreateChatCompletion;
import com.chatbot.openai.openai.OpenAI;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@CrossOrigin
@RestController
public class Controller {

    private final List<ChatCompletionMessageData> messageHistory = new ArrayList<>();
    private final String requestId = UUID.randomUUID().toString();

    Dotenv dotenv = Dotenv.load();

    @GetMapping("/send")
    public ResponseModel send(HttpServletRequest request, @RequestParam String message) {
//        String requestId = UUID.randomUUID().toString();
//        log.info("requestId {}, ip {}, send a message : {}", requestId, request.getRemoteHost(), message);
        if (!StringUtils.hasText(message)) {
            return ResponseModel.fail("message can not be blank");
        }
        try {
            String responseMessage = messageBuilders(message);
//            log.info("requestId {}, ip {}, get a reply : {}", requestId, request.getRemoteHost(), responseMessage);
            return ResponseModel.success(responseMessage);
        } catch (Exception e) {
            log.error("requestId {}, ip {}, error", requestId, request.getRemoteHost(),e);
            return new ResponseModel(500, "error", e.getMessage());
        }
    }

    private String messageBuilders(String message) {
        // Создаем объект Message Data с сообщением, которое мы хотим отправить
        ChatCompletionMessageData messageData = ChatCompletionMessageData.builder()
                .setRole("user")
                .setContent(message)
                .build();

        // Сохраняем сообщение, которое хотим отправить, в список истории сообщений
        messageHistory.add(messageData);

        // Создаем структуру данных, содержащую историю сообщений и информацию о модели
        ChatCompletionData completionData = ChatCompletionData.builder()
                .setModel("gpt-3.5-turbo-0301")
                .setMaxTokens(256)
                .setTopP(1)
//                .setStream(true)
                .setMessages(messageHistory)
                .setTemperature(0.7F)
                .setUser(requestId)
                .setStop("\\n")
//                .setFrequencyPenalty(0.0)
//                .setPresencePenalty(0.8F)
                .build();

        // Отправляет запрос на конечную точку OpenAI и анализирует данные ответа
        // Вместо openAI.sendRequest(); вы можете инициализировать запрос
        // для класса вручную - openAI.createChatCompletion().initialize();
        OpenAI openAI = OpenAI.builder()
                .setApiKey(dotenv.get("openaiapikey"))
                .createChatCompletion(completionData)
                .build()
                .sendRequest();

        // Проверяем, есть ли действительный ответ от OpenAI
        // Вы также можете вызвать Optional<CreateChatCompletion> createChatCompletion = openAI.chatCompletion();
        CreateChatCompletion createChatCompletion = openAI.getChatCompletion();

        // Сохраняем ответ чата от ИИ, это позволяет ИИ видеть полную историю нашего чата
        // Включая как наши сообщения, так и сообщения ИИ
        messageHistory.addAll(createChatCompletion.asChatResponseDataList());

        // Разбираем ответ как обычный текст
        return createChatCompletion.asText();
    }

}
