package com.chatbot.openai.api;

import com.chatbot.openai.api.data.audio.AudioData;
import com.chatbot.openai.api.data.audio.response.AudioResponseData;
import com.chatbot.openai.net.OpenAIEndpoints;
import com.chatbot.openai.util.JacksonJsonDeserializer;

import java.text.Normalizer;

/**
 * CreateTranscription
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateTranscription extends OAPI {

    /**
     * CreateTranscription
     * @param transcription - The translation data specified
     */
    public CreateTranscription(AudioData transcription) {
        super(transcription, OpenAIEndpoints.CREATE_TRANSCRIPTION, HttpRequestType.MULTI_DATA_POST);
    }

    /**
     * CreateTranscription
     * @param transcription - The translation data specified
     * @param endpoint - The OpenAI endpoint URI
     */
    public CreateTranscription(AudioData transcription, OpenAIEndpoints endpoint) {
        super(transcription, endpoint, HttpRequestType.MULTI_DATA_POST);
    }

    /**
     * asNormalizedText
     * @return - String with replaced ascii characters, and removes "\n"
     */
    public String asNormalizedText() {
        //Replaces any characters that do not match the regex
        String normalized = Normalizer.normalize(this.asText(), Normalizer.Form.NFD);
        return normalized
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("\n", "");
    }

    /**
     * asText
     * @return - String with the raw text responded back from OpenAI
     */
    public String asText() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(AudioResponseData.class);
        }
        if (!(this.deserializedData instanceof AudioResponseData)) {
            return null;
        }
        return ((AudioResponseData) this.deserializedData).getText();
    }

    /**
     * asData
     * @return ModelData
     */
    public AudioResponseData asData() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(AudioResponseData.class);
        }
        if (!(this.deserializedData instanceof AudioResponseData)) {
            return null;
        }
        return (AudioResponseData) this.deserializedData;
    }

    /**
     * asJson
     * @return String (JSON)
     */
    @Override
    public String asJson() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(AudioResponseData.class);
        }
        if (!(this.deserializedData instanceof AudioResponseData)) {
            return null;
        }
        return JacksonJsonDeserializer.valuesAsString((AudioResponseData) this.deserializedData);
    }

}
