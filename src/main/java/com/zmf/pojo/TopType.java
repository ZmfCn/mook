package com.zmf.pojo;

public class TopType {
    /**
     * This field corresponds to the database column top_type.type_id
     *
     */
    private String typeId;

    /**
     * This field corresponds to the database column top_type.type_name
     *
     */
    private String typeName;

    /**
     * This method returns the value of the database column top_type.type_id
     *
     * @return the value of top_type.type_id
     *
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * This method sets the value of the database column top_type.type_id
     *
     * @param typeId the value for top_type.type_id
     *
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    /**
     * This method returns the value of the database column top_type.type_name
     *
     * @return the value of top_type.type_name
     *
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * This method sets the value of the database column top_type.type_name
     *
     * @param typeName the value for top_type.type_name
     *
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }
}