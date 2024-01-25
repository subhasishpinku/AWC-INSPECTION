package icdswb.in.Activity_ListInspaction;

public class ListInspaction  {
    private int id;
    private String title;
    private int image;
    private String buildingdetails;
    private String informationoftoilet;
    private String informationofkitchen;
    private String adequatespaceforpse;
    private String electricity;
    private String drinkingwater;
    private String foodstoreddetails;
    private String conditionofequipmentotherawc;
    private String shishualoy;
    private String snpserved;
    private String nutritionalstatusobserved;
    private String otherinspection;
    private String allinspactionid;
    public ListInspaction(int id, String title, int image,String buildingdetails,String informationoftoilet,String informationofkitchen,String adequatespaceforpse,String electricity,String drinkingwater,String foodstoreddetails,String conditionofequipmentotherawc,String shishualoy,String snpserved,String nutritionalstatusobserved,String otherinspection,String allinspactionid){
        this.id = id;
        this.title = title;
        this.image = image;
        this.buildingdetails = buildingdetails;
        this.informationoftoilet = informationoftoilet;
        this.informationofkitchen = informationofkitchen;
        this.adequatespaceforpse = adequatespaceforpse;
        this.electricity =electricity;
        this.drinkingwater = drinkingwater;
        this.foodstoreddetails = foodstoreddetails;
        this.conditionofequipmentotherawc = conditionofequipmentotherawc;
        this.shishualoy = shishualoy;
        this.snpserved = snpserved;
        this.nutritionalstatusobserved = nutritionalstatusobserved;
        this.otherinspection = otherinspection;
        this.allinspactionid = allinspactionid;
        }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public String getBuildingdetails() {
        return buildingdetails;
    }

    public String getInformationoftoilet() {
        return informationoftoilet;
    }

    public String getInformationofkitchen() {
        return informationofkitchen;
    }

    public String getAdequatespaceforpse() {
        return adequatespaceforpse;
    }

    public String getElectricity() {
        return electricity;
    }

    public String getDrinkingwater() {
        return drinkingwater;
    }

    public String getFoodstoreddetails() {
        return foodstoreddetails;
    }

    public String getConditionofequipmentotherawc() {
        return conditionofequipmentotherawc;
    }

    public String getShishualoy() {
        return shishualoy;
    }

    public String getSnpserved() {
        return snpserved;
    }

    public String getNutritionalstatusobserved() {
        return nutritionalstatusobserved;
    }

    public String getOtherinspection() {
        return otherinspection;
    }

    public String getAllinspactionid() {
        return allinspactionid;
    }
}
