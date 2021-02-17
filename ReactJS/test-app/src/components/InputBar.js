const InputBar = () =>{
    function addToList(){
        console.log('Added to list');
    }

    return(
        <div className="inputDiv">
            <input className="inputField" placeholder="Item Name"></input>
            <input className="inputField" id="descriptionInput" placeholder="Item Description"></input>
            <button className="addBtn" onClick={addToList}>Add</button>
        </div>
    )
}

export default InputBar; 