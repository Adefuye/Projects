using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class MainPlayerControls : MonoBehaviour 
{
 
    public Rigidbody2D rb;
    public int health = 100;
    public GameManager manageXP;
    public float playerPositionY;
    //bool Rigidbody2D.rotation =true;
    public Text healthUI;
    public AudioManager audioManager;
    public PlayerCollision collisionScript;
    public GameObject shieldSprite;
    public GameObject healthSprite;
    public GameObject boostSprite;
	//Use this for initialization
	public void Start() 
    {
            rb = GetComponent<Rigidbody2D>();
            rb.angularVelocity = Time.deltaTime + rb.angularVelocity;
            
    }
	// Update is called once per frame
	void Update()
    {
        if(Input.GetKeyDown(KeyCode.X))
        {
            //FindObjectOfType<AudioManager>().Play("getToExtraction");//PLAYS AUDIO  
        }
        if(SceneManager.GetActiveScene().name != "MainMenu")
        {
            healthUI.text = health.ToString();
            if(health < 0){healthUI.text = "0";}
            PlayerMovement();
            //audioManager.Play("Ambience");
        }
    }
    public void spinCounterWeight(){rb.angularDrag = 10;}
    public void wait(){Invoke("PlayerMovement", .8f);}
    public void PlayerMovement()
    {
     
        this.enabled = true; //this class is enabled        
        rb.velocity = new Vector2(60, rb.velocity.y); //Initial Speed/Direction (speed, direction)
        
        //FindObjectOfType<AudioManager>().Play("MovementSound");//PLAYS AUDIO           
        rb.angularDrag = 10;
        rb.constraints = RigidbodyConstraints2D.None;

    //-----------------C O N T R O L  R O T A T I O N---------------

        if(rb.angularVelocity > 50f || rb.angularVelocity < -50f)
        {
            rb.angularDrag += 5;
            Invoke("spinCounterWeight", .5f);//slows down rotation speed
        }
        else if(rb.angularVelocity > 100f || rb.angularVelocity < -100f)
        {
            rb.angularDrag += 12;
            Invoke("spinCounterWeight", .5f);
        }
        else if(rb.angularVelocity > 150f || rb.angularVelocity < -150f)
        {
            rb.angularDrag += 20;
            Invoke("spinCounterWeight", .5f);
        }
        else if(rb.angularVelocity > 200f || rb.angularVelocity < -200f)
        {
            rb.angularDrag += 30;
            Invoke("spinCounterWeight", .5f);
        }

    //-------------------------C O N T R O L S-----------------------

        if(Input.GetKeyDown("up") || Input.GetKeyDown("w"))
        {
            moveUP();
        }
        else if (Input.GetKeyDown("down") || Input.GetKeyDown("s"))
        {
            moveDown();
        }
        //Input.GetTouch(0).position.x < screenWidth/2
    //-----------------S P E C I A L  A B I L I T Y------------------
    //Will create a new class for different character abilities, then call one of those methods
    //in this method depending on what character is being used
        else if (Input.GetKeyDown("space"))
        {
            super();
        }   
    }//end playerMovement
    //------------------T O U C H  C O T R O L S----------------------
    public void moveUP()
    {
        if(health > 0)
        {
            FindObjectOfType<AudioManager>().Play("Thrust");//SOUND EFFECT
            rb.velocity = new Vector2(0, 60);// Move up
        }
        else{Debug.Log("Movement Disabled After Death");}
    }
    public void moveDown()
    {
        if(health > 0)
        {
            FindObjectOfType<AudioManager>().Play("Thrust");//SOUND EFFECT
            rb.velocity = new Vector2(0, -60);// Move down
        }
        else{Debug.Log("Movement Disabled After Death");}
    }
    public void super()
    {
        if(collisionScript.superIsReady == true)
        {
            if(collisionScript.superType == "boost"){boostSuper();}
            if(collisionScript.superType == "shield"){shieldSuper();}
            if(collisionScript.superType == "health"){healthSuper();}
        }
    }
    public void boostSuper()
    {
        this.enabled = false;
        if(this.enabled == false)
        {
            collisionScript.superIsReady = false;
            boostSprite.SetActive(true);
            FindObjectOfType<AudioManager>().Play("DashSound");//SOUND EFFECT
            rb.velocity = new Vector2(250, rb.velocity.y);//speed boost
            Invoke("boostAnimation", 1f);
            wait();//waits before re-enabling movement  
        }
    }
    void boostAnimation(){boostSprite.SetActive(false);}
    public void shieldSuper()
    {
        collisionScript.superIsReady = false;
        collisionScript.takesDamage = false;//disables taking damage
        shieldSprite.SetActive(true);
        Invoke("shieldTimer", 5f);//lasts 5 seconds
        FindObjectOfType<AudioManager>().Play("healthSuper");//SOUND EFFECT
    }
    void shieldTimer()
    {
        shieldSprite.SetActive(false);
        collisionScript.takesDamage = true;//enables taking damage
    }
    public void healthSuper()
    {
        collisionScript.superIsReady = false;
        health += 50;
        FindObjectOfType<AudioManager>().Play("shieldSuper");//SOUND EFFECT
        healthSprite.SetActive(true);
        Invoke("healthAnimation", 2f);
    }
    void healthAnimation()
    {
        healthSprite.SetActive(false);
    }
    
}//end class
