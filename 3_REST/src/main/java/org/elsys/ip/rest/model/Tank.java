package org.elsys.ip.rest.model;

public class Tank {
    private int id;
    private String name;
    private String usedBy;
    private String manufacturer;
    private String type;
    private float weight;
    private float length;
    private float width;
    private float height;
    private int crew;
    private float speed;

    public Tank(int id, String name, String usedBy, String manufacturer,
                String type, float weight, float length, float width,
                float height, int crew, float speed) {
        this.id = id;
        this.name = name;
        this.usedBy = usedBy;
        this.manufacturer = manufacturer;
        this.type = type;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.crew = crew;
        this.speed = speed;
    }

    public Tank() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsedBy() {
        return usedBy;
    }

    public void setUsedBy(String usedBy) {
        this.usedBy = usedBy;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return id +
                "," + name +
                ',' + usedBy +
                ',' + manufacturer +
                ',' + type +
                "," + weight +
                "," + length +
                "," + width +
                "," + height +
                "," + crew +
                "," + speed;
    }
}
