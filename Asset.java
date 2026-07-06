package model;

public class Asset {

    private int assetId;
    private String assetName;
    private String assetType;
    private String serialNumber;
    private String assignedTo;
    private String purchaseDate;
    private String status;

    public Asset() {
    }

    public Asset(String assetName, String assetType,
                 String serialNumber, String assignedTo,
                 String purchaseDate, String status) {
        this.assetName = assetName;
        this.assetType = assetType;
        this.serialNumber = serialNumber;
        this.assignedTo = assignedTo;
        this.purchaseDate = purchaseDate;
        this.status = status;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getStatus() {
        return status;
    }
}