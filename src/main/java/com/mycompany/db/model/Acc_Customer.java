package com.mycompany.db.model;

public class Acc_Customer implements Identifiable{
private long Acc_CustomerID;   
private int Count_Characters;
private int Max_Level_Character;
private String Name_Max_Level_Character;
private int Forum_Posts;
private int Achievements_Completed;

@Override
    public long getId() { return Acc_CustomerID; }
@Override
    public void setId(long id) { this.Acc_CustomerID = id; }
	
    public int getCount_Characters() { return Count_Characters; }
    public void setCount_Characters(int Count_Characters) { this.Count_Characters = Count_Characters; }
	
    public int getMax_Level_Character() { return Max_Level_Character; }
    public void setMax_Level_Character(int Max_Level_Character) { this.Max_Level_Character = Max_Level_Character; }

    public String getName_Max_Level_Character() { return Name_Max_Level_Character; }
    public void setName_Max_Level_Character(String Name_Max_Level_Character) { this.Name_Max_Level_Character = Name_Max_Level_Character; }
	
    public int getForum_Posts() { return Forum_Posts; }
    public void setForum_Posts(int Forum_Posts) { this.Forum_Posts = Forum_Posts; }

    public int getAchievements_Completed() { return Achievements_Completed; }
    public void setAchievements_Completed(int Achievements_Completed) { this.Achievements_Completed = Achievements_Completed; }
}