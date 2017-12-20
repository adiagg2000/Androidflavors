
package com.example.android.flavor;


public class AndroidFlavor {

    private String mVersionName;

    private String mVersionNumber;

    private int mImageResourceId;

    private int mAudioResourceId;
    public AndroidFlavor(String vName, String vNumber, int imageResourceId, int audioResourseId)
    {
        mVersionName = vName;
        mVersionNumber = vNumber;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourseId;
    }

    public String getVersionName() {
        return mVersionName;
    }

    public String getVersionNumber() {
        return mVersionNumber;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }


}




