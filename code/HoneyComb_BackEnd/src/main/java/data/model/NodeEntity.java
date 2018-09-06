package data.model;

import org.springframework.data.annotation.Id;

public class NodeEntity {
    @Id
    private Integer nodeId;
    private Float radius;
    private String nodeColor;

    public Float getRadius() {
        return radius;
    }

    public void setRadius(Float radius) {
        this.radius = radius;
    }

    public void setNodeColor(String nodeColor) {
        this.nodeColor = nodeColor;
    }

    public String getNodeColor() {
        return nodeColor;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }
}
