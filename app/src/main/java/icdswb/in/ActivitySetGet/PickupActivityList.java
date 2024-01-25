package icdswb.in.ActivitySetGet;

public class PickupActivityList {
    private String district;
    private String project;
    private String sector;
    private String center;
    private String distID;
    private String projectID;
    private String sectorID;
    private String centerID;

    public PickupActivityList(String district, String project, String sector, String center, String distID, String projectID, String sectorID, String centerID){
        this.district = district;
        this.project = project;
        this.sector =  sector;
        this.center = center;
        this.distID = distID;
        this.projectID = projectID;
        this.sectorID = sectorID;
        this.centerID = centerID;
    }

    public String getDistrict() {
        return district;
    }

    public String getProject() {
        return project;
    }

    public String getSector() {
        return sector;
    }

    public String getCenter() {
        return center;
    }

    public String getDistID() {
        return distID;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getSectorID() {
        return sectorID;
    }

    public String getCenterID() {
        return centerID;
    }
}
