using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class inGameTimer : MonoBehaviour
{
    public float seconds = 0;
    public int minutes = 0;
    public GameManager timeManager;
    public Text timer;
    public Text endScreenTimer;
    //public Text endTime;
    public void totalTime()
    {    
        if(seconds>59.999){minutes+=1; seconds = 0;}
    }
    // Update is called once per frame
    void Update()
    {
        if(SceneManager.GetActiveScene().name != "MainMenu")
        {
            //timeManager.gameHasEnded = false;
            
            if(timeManager.gameHasEnded == false)
            {
                seconds += Time.deltaTime;
                timer.text = minutes+":"+seconds.ToString("0");//inGame realtime timer
                totalTime();
                //Debug.Log(seconds +" "+timeManager.gameHasEnded);
            }
            if(timeManager.gameHasEnded == true)
            {
                seconds = (int)seconds;
                minutes = (int)minutes;
                endScreenStats();
                resetTimer();
                //Debug.Log(seconds +" "+timeManager.gameHasEnded);
                timeManager.gameHasEnded = false;
            }
        }
        else
        {
            resetTimer();
        }
        
        
    }
    void endScreenStats()
    {
        //timer.text = minutes+":"+seconds.ToString("0");//to string trunates the decimals
        endScreenTimer.text = timer.text;
    }
    public void resetTimer()
    {
        seconds = 0;
        minutes = 0;
    }
}
