package com.chatbot.openai.api;

import com.chatbot.openai.api.data.audio.AudioData;
import com.chatbot.openai.net.OpenAIEndpoints;

/**
 * CreateTranscriptionTranslation
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateTranscriptionTranslation extends CreateTranscription {

    /**
     * CreateTranslation
     * @param translation - The translation data specified
     */
    public CreateTranscriptionTranslation(AudioData translation) {
        super(translation, OpenAIEndpoints.CREATE_TRANSCRIPTION_TRANSLATION);
    }

}
