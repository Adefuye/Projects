import {useState} from 'react'

const InputBar = ({onAdd}) =>{
    

    const [title, setTitle] = useState('')
    const [description, setDescription] = useState('')

    const onSubmit = (e) =>{
        e.preventDefault();

        if(!title){
            alert('The Name for your item is missing');
            return;
        }
        if(!description){
            alert('Your Item Description is missing');
            return;
        }

        onAdd({title, description})

        setTitle('')
        setDescription('')
    }

    return(
        <div className="inputDiv" onSubmit={onSubmit} >

            <input className="inputField" placeholder="Item Name"
                value={title} onChange={(e) => setTitle(e.target.value)}>    
            </input>

            <input className="inputField" id="descriptionInput" placeholder="Item Description"
                value={description} onChange={(e) => setDescription(e.target.value)}
            ></input>

            <button className="addBtn" onClick={onSubmit}>Add</button>

        </div>
    )
}

export default InputBar; 