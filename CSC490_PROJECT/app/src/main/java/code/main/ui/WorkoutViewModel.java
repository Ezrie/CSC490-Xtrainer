package code.main.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import code.main.WorkoutActivity;

public class WorkoutViewModel extends ViewModel{

    //When MutableLiveData calls setValue(obj) from main thread, it also notifies any active observers.
    //If a background task, use postValue(obj)
    private MutableLiveData<String> mText;
    private MutableLiveData<String> currentTab;

    public WorkoutViewModel() {
        mText = new MutableLiveData<String>();
        currentTab = new MutableLiveData<String>();
    }

    public void setText(String _text) {
        mText.setValue(_text);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setCurrentTab(String _tab) {
        currentTab.setValue(_tab);
    }

    public LiveData<String> getCurrentTab() {
        return currentTab;
    }
}
