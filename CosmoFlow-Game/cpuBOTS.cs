using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Pathfinding;

public class cpuBOTS : MonoBehaviour
{
    Transform target;
    private GameObject finishLine;
    private float speed;
    private float nextWaypointDistance;

    Path path;
    int currentWaypoint = 0;
    bool reachedEndOfPath = false;

    Seeker seeker;
    Rigidbody2D rb;

    void Start()
    {
        speed = 8000f;
        nextWaypointDistance = 100f;
        finishLine = GameObject.FindWithTag("botPlayers");
        target = finishLine.transform;
        seeker = GetComponent<Seeker>();
        rb = GetComponent<Rigidbody2D>();

        InvokeRepeating("updatePath", 0f, .5f);

    }
    void updatePath()
    {
        if(seeker.IsDone())
        {
            seeker.StartPath(rb.position, target.position, OnPathComplete);
        }
    }
    void OnPathComplete(Path p)
    {
        if(!p.error)
        {
            path = p;
            currentWaypoint = 0;
        }
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        //rb.velocity = new Vector2(40, rb.velocity.y);
        rb.constraints = RigidbodyConstraints2D.FreezePositionY;
        

        if(path == null)
            return;

        if(currentWaypoint >= path.vectorPath.Count)
        {
            reachedEndOfPath = true;
            return;
        }
        else
        {
            reachedEndOfPath = false;
        }

        Vector2 direction = ((Vector2)path.vectorPath[currentWaypoint] - rb.position).normalized;
        Vector2 force = direction * speed * Time.deltaTime;

        rb.AddForce(force);

        float distance = Vector2.Distance(rb.position, path.vectorPath[currentWaypoint]);

        if(distance < nextWaypointDistance)
        {
            currentWaypoint++;
        }
    }
}
