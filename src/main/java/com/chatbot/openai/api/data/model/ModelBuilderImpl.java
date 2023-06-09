package com.chatbot.openai.api.data.model;

import com.chatbot.openai.api.data.model.permissions.ModelPermissionsData;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * ModelBuilderImpl
 *
 * @author <a href="https://github.com/jetkai">Kai</a>
 * @version 1.1.3
 * {@code - 07/03/2023}
 * @since 1.1.0
 * {@code - 07/03/2023}
 */
final class ModelBuilderImpl implements ModelData.Builder {
    
    String id; //text-babbage:001
    String object; //model
    int created; //1642018370
    String ownedBy; //openai
    String root; //text-babbage:001
    String parent; //null
    List<ModelPermissionsData> permission;

    @Override
    public ModelBuilderImpl setId(String id) {
        requireNonNull(id, "\"id\" can not be null");
        this.id = id;
        return this;
    }

    @Override
    public ModelBuilderImpl setCreated(int created) {
        this.created = created;
        return this;
    }

    @Override
    public ModelBuilderImpl setObject(String object) {
        requireNonNull(object, "\"object\" can not be null");
        this.object = object;
        return this;
    }

    @Override
    public ModelBuilderImpl setParent(String parent) {
        requireNonNull(parent, "\"parent\" can not be null");
        this.parent = parent;
        return this;
    }

    @Override
    public ModelBuilderImpl setOwnedBy(String ownedBy) {
        requireNonNull(ownedBy, "\"ownedBy\" can not be null");
        this.ownedBy = ownedBy;
        return this;
    }

    @Override
    public ModelBuilderImpl setRoot(String root) {
        requireNonNull(root, "\"root\" can not be null");
        this.root = root;
        return this;
    }

    @Override
    public ModelBuilderImpl setPermissions(List<ModelPermissionsData> permission) {
        requireNonNull(permission, "\"permission\" can not be null");
        this.permission = permission;
        return this;
    }

    @Override
    public ModelData build() {
        return ModelImpl.create(this);
    }

}