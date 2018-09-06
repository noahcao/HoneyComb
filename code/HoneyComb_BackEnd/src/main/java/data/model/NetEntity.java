package data.model;

import org.springframework.data.annotation.Id;

import java.util.Set;

public class NetEntity {
    @Id
    private String netId;
    private Integer userId;
    private String name;
    private String description;
    private Set<NodeEntity> nodeArray;
    private Set<LinkEntity> linkArray;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Set<LinkEntity> getLinkArray() {
        return linkArray;
    }

    public Set<NodeEntity> getNodeArray() {
        return nodeArray;
    }

    public void setLinkArray(Set<LinkEntity> linkArray) {
        this.linkArray = linkArray;
    }

    public String getNetId() {
        return netId;
    }

    public void setNetId(String netId) {
        this.netId = netId;
    }

    public void setNodeArray(Set<NodeEntity> nodeArray) {
        this.nodeArray = nodeArray;
    }
}
