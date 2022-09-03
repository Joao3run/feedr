package br.com.brn.feedrapi.adapters.outbounds.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "GL00FILE", schema = "GLOBAL")
public class GlobalFileEntity {
    @Id
    @Column(name = "CO00FILE", nullable = false, length = 50)
    private String id;

    @Column(name = "PATHFILE", nullable = false, length = 4000)
    private String path;

    @Column(name = "NOMEFILE", nullable = false, length = 4000)
    private String name;

    @Column(name = "EXTEFILE", nullable = false, length = 10)
    private String extension;

    @Column(name = "BASECLIE", length = 10)
    private String client;

    @Column(name = "DTCRFILE")
    private Instant creatAt;

    @Column(name = "SIZEFILE")
    private Integer size;

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
}
