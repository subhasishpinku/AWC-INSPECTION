package icdswb.in.ActivitySetGet;

public class PlaningList {
    private String projectId;
    private String projectName;
    private String sectorId;
    private String sectorName;
    private String centreId;
    private String centreName;
    private String planDT;
    public PlaningList(String projectId, String projectName, String sectorId, String sectorName, String centreId, String centreName, String planDT){
        this.projectId = projectId;
        this.projectName = projectName;
        this.sectorId =  sectorId;
        this.sectorName = sectorName;
        this.centreId = centreId;
        this.centreName = centreName;
        this.planDT = planDT;
        }

    public String getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getSectorId() {
        return sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public String getCentreId() {
        return centreId;
    }

    public String getCentreName() {
        return centreName;
    }

    public String getPlanDT() {
        return planDT;
    }
}
