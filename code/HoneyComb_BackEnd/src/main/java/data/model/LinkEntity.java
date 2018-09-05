package data.model;

import org.springframework.data.annotation.Id;

public class LinkEntity {
    @Id
    private Integer linkId;
    private Integer source;
    private Integer target;
    private Float value;

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public Integer getTarget() {
        return target;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
