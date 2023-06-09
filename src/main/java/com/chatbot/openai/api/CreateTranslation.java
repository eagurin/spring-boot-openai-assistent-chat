package com.chatbot.openai.api;

import com.chatbot.openai.api.data.completion.CompletionData;

/**
 * CreateTranslation
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 05/03/2023}
 */
public class CreateTranslation extends CreateCompletion {

    /**
     * CreateTranslation
     * @param translation - The translation data specified
     */
    public CreateTranslation(CompletionData translation) {
        super(translation);
    }

}
