const InputBar = () =>{
    return(
        <div className="inputDiv">
            <input className="inputField" placeholder="Item Name"></input>
            <input className="inputField" placeholder="Item Description"></input>
            <input className="inputField" placeholder="Collection"></input>
            <button className="addBtn">Add</button>
        </div>
    )
}

export default InputBar; 