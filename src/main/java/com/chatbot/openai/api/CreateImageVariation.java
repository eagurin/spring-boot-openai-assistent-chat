package com.chatbot.openai.api;

import com.chatbot.openai.api.data.image.variation.ImageVariationData;
import com.chatbot.openai.net.OpenAIEndpoints;

/**
 * CreateImageVariation
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateImageVariation extends CreateImage {

    /**
     * CreateImageVariation
     * @param image - The image data specified
     */
    public CreateImageVariation(ImageVariationData image) {
        super(image, OpenAIEndpoints.CREATE_IMAGE_VARIATION, HttpRequestType.MULTI_DATA_POST);
    }

}
