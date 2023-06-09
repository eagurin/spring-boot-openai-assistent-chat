package com.chatbot.openai.api.data.image;

import static java.util.Objects.requireNonNull;

/**
 * ImageBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ImageBuilderImpl implements ImageData.Builder {

    String prompt;
    int n;
    String size;
    String responseFormat;
    String user;

    @Override
    public ImageData.Builder setUser(String user) {
        requireNonNull(user, "\"user\" can not be null");
        this.user = user;
        return this;
    }

    @Override
    public ImageData.Builder setN(int n) {
        this.n = n;
        return this;
    }

    @Override
    public ImageData.Builder setPrompt(String prompt) {
        requireNonNull(prompt, "\"prompt\" can not be null");
        this.prompt = prompt;
        return this;
    }

    @Override
    public ImageData.Builder setResponseFormat(String responseFormat) {
        requireNonNull(responseFormat, "\"responseFormat\" can not be null");
        this.responseFormat = responseFormat;
        return this;
    }

    @Override
    public ImageData.Builder setSize(String size) {
        requireNonNull(size, "\"size\" can not be null");
        this.size = size;
        return this;
    }

    @Override
    public ImageData build() {
        return ImageImpl.create(this);
    }
}
