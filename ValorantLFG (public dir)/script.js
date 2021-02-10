//const socket = io('http://localhost:3000')


//----------Client side containers--------------
const form = document.getElementById('postInfo');//form
const postContainer = document.getElementById('postWrapper')
const API_URL = window.location.hostname === 'localhost' ? 'http://localhost:5500/posts' : 'https://valorantlfgposts-db.emaxthedestroyer.now.sh/posts';
const mixer = document.getElementById("mixer");
const twitch = document.getElementById("twitch-embed");
//const API_URL = 'http://localhost:5500/posts'
//when page loads, list ALL posts

displayPosts();

//--------------Submit Button-----------------
form.addEventListener('submit', (event)=>{//event listener
    event.preventDefault();//stops the page from refreshing
    
    //---------input fields---------
    const username = document.getElementById('riotUsername');
    const region = document.getElementById('region');
    const description = document.getElementById('description');

    //-----------Postcard Object-----------
    const postinfo = {
        username: username.value.toString(),
        region: region.value.toString(),
        description: description.value.toString()
    };

    //-------------reset form--------------
    username.value = '';
    region.value = '';
    description.value = '';

    //--------------Fetch-----------------
    //requests to 'POST' data to server
    fetch(API_URL, {
        method: 'POST',
        body: JSON.stringify(postinfo), //turning postinfo OBJ into Json
        headers: {
            'content-type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(createdPost =>{
            //after submitting the form, display all posts
            displayPosts()
        })
        
});

//-------------Display Posts------------------
//using a 'get' requests intead of 'POST'
function displayPosts(){

    //clearing outdated list before displaying updated list
    postContainer.innerHTML = '';

    fetch(API_URL)
        .then(response => response.json())
        .then(posts =>{
            console.log(posts)
            posts.reverse()
            posts.forEach(post =>{
                const postCard = document.createElement('div')//create a div
                postCard.setAttribute("id", "postcards")//giving the div and id for styling
                
                //Player username as header of the div
                const header = document.createElement('h1')
                header.innerText = post.username;

                //region as paragraph element of the div
                const region = document.createElement('h4')
                region.innerText = post.region

                //description as paragraph element of the div
                const description = document.createElement('p')
                description.innerText = post.description

                //Disoplay the div (append h1 and p)
                postCard.append(header)//username
                postCard.append(region)//region
                postCard.append(description)//gamemode
                postContainer.append(postCard)//add the post card info into the container
            })
        })
}

/*window.setInterval(function(){
    displayPosts()
  }, 1000);*/

  function streamSignUp(){
    window.open('mailto:literichgg@gmail.com');
  }

function displayTwitch(){
    twitch.style.display = "block";
    mixer.style.display = "none";
}