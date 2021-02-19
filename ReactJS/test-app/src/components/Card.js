import { FaTimes } from 'react-icons/fa'

const Card = ({card, onRemove}) =>{
    return(
        <div className="card">
            <FaTimes 
                className="xBtn" 
                onClick={() => onRemove(card.id)}
            />
            <h3>
                {card.title}
            </h3>
            <p>{card.description}</p>
        </div>
    )
}

export default Card; 