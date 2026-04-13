package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class TeacherCourse
{
    @SerializedName("zkratka")
    public String code;
    @SerializedName("katedra") //kdyz tu byl nazev tak mi nefungovalo nacitani souboru z testData
    public String title;
}
