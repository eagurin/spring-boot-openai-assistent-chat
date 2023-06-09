package com.chatbot.openai.api.data.embedding;

import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * EmbeddingBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class EmbeddingBuilderImpl implements EmbeddingData.Builder {

    String model;
    List<String> input;
    String user;

    @Override
    public EmbeddingData.Builder setUser(String user) {
        requireNonNull(user, "\"user\" can not be null");
        this.user = user;
        return this;
    }

    @Override
    public EmbeddingData.Builder setInput(String input) {
        requireNonNull(input, "\"input\" can not be null");
        this.input = Collections.singletonList(input);
        return this;
    }

    @Override
    public EmbeddingData.Builder setInput(List<String> input) {
        requireNonNull(input, "\"input\" can not be null");
        this.input = input;
        return this;
    }

    @Override
    public EmbeddingData.Builder setModel(String model) {
        requireNonNull(model, "\"model\" can not be null");
        this.model = model;
        return this;
    }

    @Override
    public EmbeddingData build() {
        return EmbeddingImpl.create(this);
    }

}
