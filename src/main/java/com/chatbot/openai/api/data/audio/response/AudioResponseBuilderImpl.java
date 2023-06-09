package com.chatbot.openai.api.data.audio.response;


import static java.util.Objects.requireNonNull;

/**
 * AudioResponseBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class AudioResponseBuilderImpl implements AudioResponseData.Builder {

    String text;

    @Override
    public AudioResponseData.Builder setText(String text) {
        requireNonNull(text, "\"text\" can not be null");
        this.text = text;
        return this;
    }

    @Override
    public AudioResponseData build() {
        return AudioResponseImpl.create(this);
    }
}
