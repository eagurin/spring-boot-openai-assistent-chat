package com.chatbot.openai.api;

import com.chatbot.openai.api.data.moderation.ModerationData;
import com.chatbot.openai.api.data.moderation.response.ModerationResponseData;
import com.chatbot.openai.api.data.moderation.response.results.ModerationResultsData;
import com.chatbot.openai.api.data.moderation.response.results.categories.ModerationCategoriesData;
import com.chatbot.openai.api.data.moderation.response.results.scores.ModerationScoresData;
import com.chatbot.openai.net.OpenAIEndpoints;
import com.chatbot.openai.util.JacksonJsonDeserializer;

import java.util.List;
import java.util.Optional;

/**
 * CreateModeration
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 12/03/2023}
 * @since 1.1.3
 * {@code - 12/03/2023}
 */
public class CreateModeration extends OAPI {

    /**
     * CreateModeration
     * @param data - The moderation data specified
     */
    public CreateModeration(ModerationData data) {
        super(JacksonJsonDeserializer.valuesAsString(data), OpenAIEndpoints.CREATE_MODERATION, HttpRequestType.POST);
    }

    /**
     * asData
     * @return ModerationResponseData
     */
    public ModerationResponseData asData() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ModerationResponseData.class);
        }
        if (!(this.deserializedData instanceof ModerationResponseData)) {
            return null;
        }
        return (ModerationResponseData) this.deserializedData;
    }

    /**
     * asResults
     * @return ModerationResponseData
     */
    public ModerationResultsData asResults() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ModerationResponseData.class);
        }
        if (!(this.deserializedData instanceof ModerationResponseData)) {
            return null;
        }
        Optional<List<ModerationResultsData>> optionalResults = ((ModerationResponseData) this.deserializedData)
                .results();
        if(optionalResults.isEmpty()) {
            return null;
        }

        List<ModerationResultsData> results = optionalResults.get();
        if(results.isEmpty()) {
            return null;
        }

        return results.get(0);
    }


    /**
     * asCategories
     * @return ModerationCategoriesData
     */
    public ModerationCategoriesData asCategories() {
        return this.asResults().getCategories();
    }

    /**
     * asScores
     * @return ModerationScoresData
     */
    public ModerationScoresData asScores() {
        return this.asResults().getCategoryScores();
    }

    /**
     * isFlagged
     * @return boolean isFlagged
     */
    public boolean isFlagged() {
        return this.asResults().isFlagged();
    }

    /**
     * asJson
     * @return String (JSON)
     */
    @Override
    public String asJson() {
        if(this.deserializedData == null) {
            this.deserializedData = deserialize(ModerationResponseData.class);
        }
        if (!(this.deserializedData instanceof ModerationResponseData)) {
            return null;
        }
        return JacksonJsonDeserializer.valuesAsString((ModerationResponseData) this.deserializedData);
    }

}