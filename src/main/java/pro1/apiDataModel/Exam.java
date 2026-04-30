package pro1.apiDataModel;

import com.google.gson.annotations.SerializedName;

public class Exam {
    @SerializedName("obsazeni")
    public int registeredStudentsCount;
    @SerializedName("ucitIdno")
    public long teacherId;
}
