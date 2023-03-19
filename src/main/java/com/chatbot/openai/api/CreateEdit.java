package com.chatbot.openai.api;

import com.chatbot.openai.api.data.edit.EditData;
import com.chatbot.openai.net.OpenAIEndpoints;
import com.chatbot.openai.util.JacksonJsonDeserializer;

/**
 * CreateEdit
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.0
 * {@code - 07/03/2023}
 * @since 1.0.0
 * {@code - 02/03/2023}
 */
public class CreateEdit extends CreateCompletion {

    /**
     * CreateEdit
     * @param edit - The edit data specified
     */
    public CreateEdit(EditData edit) {
        super(JacksonJsonDeserializer.valuesAsString(edit), OpenAIEndpoints.CREATE_EDIT);
    }

}
