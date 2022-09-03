package br.com.brn.feedrapi.application.domain.models;

import br.com.brn.feedrapi.application.utils.Utils;

import java.time.Instant;

public class GlobalFile {
    private String id;

    private String path;

    private String name;

    private String extension;

    private String client;

    private Instant creatAt;

    private Integer size;

    private byte[] content;

    private String url;

    public GlobalFile() {
    }

    public GlobalFile(String id, String path, String name, String extension, String client, Instant creatAt, Integer size) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.extension = extension;
        this.client = client;
        this.creatAt = creatAt;
        this.size = size;
    }

    public GlobalFile(String name, String client, byte[] content) {
        this.id = Utils.getRandomHexString(50);
        this.name = name;
        this.client = client;
        this.content = content;
        String type = ".jpg";
        if (name.indexOf(".") > 0) {
            type = name.substring(name.lastIndexOf("."));
        }
        this.extension = type;
        this.creatAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Instant getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Instant creatAt) {
        this.creatAt = creatAt;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
