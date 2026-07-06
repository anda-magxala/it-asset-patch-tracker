package model;

public class Patch {

    private String patchName;
    private String version;
    private String releaseDate;

    public Patch(String patchName,
                 String version,
                 String releaseDate) {

        this.patchName = patchName;
        this.version = version;
        this.releaseDate = releaseDate;
    }

    public String getPatchName() {
        return patchName;
    }

    public String getVersion() {
        return version;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}